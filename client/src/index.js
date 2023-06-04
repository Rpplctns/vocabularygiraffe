import './style.css';
import colors from "./const/colors.json";

import React, {useState} from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter, Link, Route, Routes, useNavigate} from "react-router-dom";

import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMoon, faSun} from "@fortawesome/free-solid-svg-icons";

import Dashboard from "./pages/Dashboard";
import Quiz from "./pages/Quiz";
import Login from "./pages/Login";
import Register from "./pages/Register";
import AddWord from "./pages/AddWord";

const App = () => {
    const navigate = useNavigate()
    const [theme, setTheme] = useState(false)

    return (
        <div className={"App"} style={{
            backgroundColor: colors[theme ? "light" : "dark"]["background"],
            color: colors[theme ? "light" : "dark"]["foreground"]
        }}>
            <Link className={"header_text"} to={"/"}> Vocabulary giraffe </Link>
            <FontAwesomeIcon
                className={"header_button"}
                onClick={() => setTheme(!theme)}
                icon={theme ? faMoon : faSun}
            />
            <Routes>
                <Route path={"/"} element={<Dashboard navigate={(x) => navigate(x)}/>}/>
                <Route path={"quiz"} element={<Quiz navigate={(x) => navigate(x)}/>}/>
                <Route path={"login"} element={<Login navigate={(x) => navigate(x)}/>}/>
                <Route path={"register"} element={<Register navigate={(x) => navigate(x)}/>}/>
                <Route path={"add"} element={<AddWord navigate={(x) => navigate(x)}/>}/>
            </Routes>
        </div>
    );
}

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <React.StrictMode>
        <BrowserRouter>
            <App/>
        </BrowserRouter>
    </React.StrictMode>
);
