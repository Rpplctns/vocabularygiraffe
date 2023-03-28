import './App.css';
import Questions from "./pages/questions/Questions";
import colors from "./const/colors.json"
import {useEffect, useState} from "react";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faMoon, faSun } from '@fortawesome/free-solid-svg-icons'

import example_set from "./example_set.json"
import example_wordset from "./example_wordset.json"
import Words from "./pages/words/Words";
import Header from "./pages/bars/Header";
import Footer from "./pages/bars/Footer";

function App() {
    const [theme, setTheme] = useState(false)
    const [response, setResponse] = useState([])
    const [inQuiz, setInQuiz] = useState(false)
    useEffect(() => {
        if(response.length !== 0) setInQuiz(false)
    }, [response])
    return (
        <div className={"App"} style={{
            backgroundColor: colors[theme ? "light" : "dark"].background,
            color: colors[theme ? "light" : "dark"].foreground
        }}>
            <Header theme={theme} setTheme={(a) => setTheme(a)} />

            {
                (!inQuiz) && (<button onClick={() => {
                    setResponse([])
                    setInQuiz(true)
                }}>start quiz</button>)
            }
            {
                (!inQuiz) && (<p>last quiz {response.toString()}</p>)
            }
            {
                (inQuiz) && (<Questions question_set={example_set} setResponse={(a) => setResponse(a)}/>)
            }
            {/*<Words words={example_wordset}/>*/}
            <FontAwesomeIcon
                onClick={() => setTheme(!theme)}
                className={"themeToggle"}
                style={{backgroundColor: colors[theme ? "light": "dark"].background}}
                icon={theme ? faMoon : faSun}
            />
        </div>
    );
}

export default App;
