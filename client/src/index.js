import './style.css';

import React, {useEffect, useState} from 'react';
import ReactDOM from 'react-dom/client';
import colors from "./const/colors.json";
import {BrowserRouter, Link, Route, Routes} from "react-router-dom";
import Questions from "./pages/questions/Questions";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
import Register from "./pages/Register";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faMoon, faSun} from "@fortawesome/free-solid-svg-icons";
import AddWord from "./pages/AddWord";
import Quiz from "./pages/Quiz";

const App = () => {

    const [theme, setTheme] = useState(false)

    useEffect(() => {
    }, [theme])

    return (
        <div className={"App"} style={{
            backgroundColor: colors[theme ? "light" : "dark"]["background"],
            color: colors[theme ? "light" : "dark"]["foreground"]
        }}>
            <BrowserRouter>
                <Link className={"header_text"} to={"/"}> Vocabulary Giraffe </Link>
                <FontAwesomeIcon
                    className={"header_button"}
                    onClick={() => setTheme(!theme)}
                    icon={theme ? faMoon : faSun}
                />
                <Routes>
                    <Route path={"/"} element={<Dashboard/>}/>
                    <Route path={"quiz"} element={<Quiz/>}/>
                    <Route path={"login"} element={<Login/>}/>
                    <Route path={"register"} element={<Register/>}/>
                    <Route path={"add"} element={<AddWord/>}/>
                </Routes>
            </BrowserRouter>
        </div>
    );
}

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
    <React.StrictMode>
        <App/>
    </React.StrictMode>
);
