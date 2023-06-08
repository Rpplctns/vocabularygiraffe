import Cookies from "universal-cookie";

const SERVER = "http://localhost:8080"
const cookies = new Cookies()

export {SERVER, cookies}

// USERS

const updateToken = () => {
    fetch(SERVER + "/api/users/refresh/?" + new URLSearchParams({
        login: cookies.get("login"),
        token: cookies.get("token")
    }))
        .then(res => res.text())
        .then(res => cookies.set("token", res, {path: "/"}))
}

const logIn = async (login, password) => {
    await fetch(SERVER + "/api/users/login/?" + new URLSearchParams({
        login: login,
        password: password
    }))
        .then(async res => {
            if (res.ok) return res.text()
            throw new Error(await res.text())
        })
        .then(res => {
            cookies.set("token", res, {path: "/"})
            cookies.set("login", login)
        })
}

const register = async (login, password) => {
    await fetch(SERVER + "/api/users/register/?" + new URLSearchParams({
        login: login,
        password: password
    }))
        .then(async res => {
            if (res.ok) return res.text()
            throw new Error(await res.text())
        })
        .then(res => {
            cookies.set("token", res, {path: "/"})
            cookies.set("login", login)
        })
}

const logOut = () => {
    cookies.remove("token")
    cookies.remove("login")
}

const deleteAccount = async () => {
    await fetch(SERVER + "/api/users/?" + new URLSearchParams({
        login: cookies.get("login"),
        token: cookies.get("token")
    }), {method: "DELETE"})
        .then(() => {
            cookies.remove("token")
            cookies.remove("login")
        })
}

const getToken = () => {
    return cookies.get("token")
}

export {updateToken, logIn, register, logOut, deleteAccount, getToken}

// WORDS

const getWords = async (setter) => {
    await updateToken()
    await fetch(SERVER + "/api/words/?" + new URLSearchParams({
        token: cookies.get("token")
    }))
        .then(res => {
            if(res.ok) return res.json()
            throw new Error("unknown error")
        })
        .then(res => setter(res))
}

const addWord = async (word, category) => {
    await updateToken()
    await fetch(SERVER + "/api/words/?" + new URLSearchParams({
        word: word,
        category: category,
        token: cookies.get("token")
    }), {method: "POST"})
        .then(async res => {
            if (res.ok) return
            throw new Error(await res.text())
        })
}

const deleteWord = async (id) => {
    await updateToken()
    await fetch(SERVER + '/api/words/' + id + '?' + new URLSearchParams({
        token: cookies.get("token")
    }), {method: "DELETE"})
        .then(res => {
            if(res.ok) return
            throw new Error("unknown error")
        })
}

const setCategory = async (id, category) => {
    await updateToken()
    await fetch(SERVER + '/api/words/' + id + '?' + new URLSearchParams({
        category: category,
        token: cookies.get("token")
    }), {method: "PUT"})
        .then(res => {
            if(res.ok) return
            throw new Error("unknown error")
        })
}

export {getWords, addWord, deleteWord, setCategory}

// QUIZ

const getQuestions = async (setter) => {
    await updateToken()
    await fetch(SERVER + "/api/quiz/?" + new URLSearchParams({
        token: cookies.get("token")
    }))
        .then(res => {
            if(res.ok) return res.json()
            throw new Error("unknown error")
        })
        .then(res => setter(res["exercises"]))
}

export {getQuestions}
