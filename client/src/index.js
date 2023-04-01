import './index.css';

import React, {useEffect, useState} from 'react';
import ReactDOM from 'react-dom/client';
import colors from "./const/colors.json";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Header from "./pages/header/Header";
import Home from "./pages/home/Home";
import Questions from "./pages/questions/Questions";
import Words from "./pages/words/Words";

const App = () => {

    const [theme, setTheme] = useState(false)

    useEffect(() => {
    }, [theme])

    return (
        <div className={"App"} style={{
            backgroundColor: colors[theme ? "light" : "dark"].background,
            color: colors[theme ? "light" : "dark"].foreground
        }}>
            <BrowserRouter>
                <Header theme={theme} setTheme={(a) => setTheme(a)}/>
                <Routes>
                    <Route path={"/"} element={<Home/>}/>
                    <Route path={"quiz"} element={<Questions/>}/>
                    <Route path={"words"} element={<Words/>}/>
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
