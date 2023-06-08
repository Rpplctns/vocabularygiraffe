import colors from '../const/colors.json'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLinesLeaning, faCheck, faPlay, faPlus, faRightFromBracket, faTrash} from "@fortawesome/free-solid-svg-icons";
import {useEffect, useState} from "react";
import Loading from "./Loading";
import {cookies, deleteAccount, deleteWord, getWords, logOut, setCategory} from "../serverUtils";

const Dashboard = ({navigate}) => {
    const [words, setWords] = useState(null)

    useEffect(() => {
        if (cookies.get("token") === undefined){
            navigate('login/')
            return
        }
        getWords((x) => setWords(x))
            .catch(err => navigate("login/"))
    }, [])

    return words === null ? (
        <Loading/>
    ) : (
        <div className={"full_page_container"}>
            <div className={"row_container"}>
                <div className={"text_button"}
                    onClick={() => navigate('/quiz')}
                >
                    <FontAwesomeIcon icon={faPlay}/>
                    {" "}Start learning
                </div>
                <div className={"text_button"}
                     onClick={() => navigate('/add')}
                >
                    <FontAwesomeIcon icon={faPlus}/>
                    {" "}Add words
                </div>
            </div>
            <h1 style={{color: colors.wrong}}>
                <FontAwesomeIcon icon={faLinesLeaning}/>
                {" "}Learning
            </h1>
            <div className={"row_container"}>
                {
                    words.filter((word) => word.category === 0).map((word, i) => {
                        return (
                            <div className={"text_button"} key={i} style={{cursor: "default"}}>{word["content"]}
                                <span style={{color: colors.grey}}>[{word["type"]}]</span>
                                <div className={"separator"}/>
                                <FontAwesomeIcon className={"small_button"} icon={faTrash}
                                                 onClick={() => {
                                                     deleteWord(word["id"])
                                                         .then(() => getWords((x) => setWords(x))).catch(err => navigate("login/"))
                                                 }}
                                />
                                <FontAwesomeIcon className={"small_button"} icon={faCheck}
                                                 onClick={() => {
                                                     setCategory(word["id"], 1)
                                                         .then(() => getWords((x) => setWords(x))).catch(err => navigate("login/"))
                                                 }}
                                />
                            </div>
                        )
                    })
                }
            </div>
            <h1 style={{color: colors.correct}}>
                <FontAwesomeIcon icon={faCheck}/>
                {" "}Learned
            </h1>
            <div className={"row_container"}>
                {
                    words.filter((word) => word.category === 1).map((word, i) => {
                        return (
                            <div className={"text_button"} key={i}>{word["content"]}
                                <span style={{color: colors.grey}}>[{word["type"]}]</span>
                                <div className={"separator"}/>
                                <FontAwesomeIcon className={"small_button"} icon={faTrash}
                                                 onClick={() => {
                                                     deleteWord(word["id"])
                                                         .then(() => getWords((x) => setWords(x)))
                                                 }}
                                />
                                <FontAwesomeIcon className={"small_button"} icon={faLinesLeaning}
                                                 onClick={() => {
                                                     setCategory(word["id"], 0)
                                                         .then(() => getWords((x) => setWords(x)))
                                                 }}
                                />
                            </div>
                        )
                    })
                }
            </div>
            <div className={"row_container"}>
                <div className={"text_button"}
                     onClick={() => {
                         logOut()
                         navigate('/login')
                     }}
                >
                    <FontAwesomeIcon icon={faRightFromBracket}/>
                    {" "}Log out
                </div>
                <div className={"text_button"} style={{color: colors.wrong}}
                     onClick={() => {
                         deleteAccount()
                             .then(() => navigate('/login'))
                     }}
                >
                    <FontAwesomeIcon icon={faTrash}/>
                    {" "}Delete account
                </div>
            </div>
        </div>
    )
}
    export default Dashboard