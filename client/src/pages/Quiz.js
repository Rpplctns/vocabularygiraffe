import colors from "../const/colors.json";
import {
    faCheck,
    faChevronRight,
    faLinesLeaning,
    faTrash
} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {useEffect, useState} from "react";
import {cookies, deleteWord, getQuestions, setCategory} from "../serverUtils";
import Loading from "./Loading";

const Question = ({number, question, setAnswer}) => {
    let sentenceArray = question["sentence"].replace(/\[.*?\]/g, "#").split("#")
    return (
        <>
            <h1>Question {number + 1}</h1>
            <p>
                {sentenceArray[0]} _ <span style={{color: colors.grey}}>[{question["type"]}]</span> {sentenceArray[1]}
            </p>
            <input className={"small_data_input"} placeholder={"answer"} spellCheck={false} type={"text"} onChange={
                (e) => {
                    e.target.value = e.target.value
                        .replace(/[^a-zA-Z]/g, '')
                        .slice(0, 20)
                        .toLowerCase()
                    setAnswer(e.target.value)
                }
            }/>
        </>
    )
}

const Solution = ({number, question, givenAnswer, decision, setDecision}) => {
    let sentenceArray = question["sentence"].replace(/\[.*?\]/g, "#").split("#")
    return (
        <>
            <h1>Question {number + 1}</h1>
            <p>
                {sentenceArray[0]}
                {(question["word"] !== givenAnswer) &&
                    <span style={{color: colors.wrong, textDecoration: "line-through"}}>{givenAnswer}</span>
                } <span style={{color: colors.correct}}>{question["word"]}</span>
                {sentenceArray[1]}
            </p>
            <div className={"row_container"}>
                <div className={"text_button"} style={decision === 0 ? {color: colors.yellow} : {color: colors.grey}}
                     onClick={
                         () => {
                             setDecision(0)
                         }
                     }
                >
                    <FontAwesomeIcon icon={faLinesLeaning}/>
                    {" "}I don't know that word yet
                </div>
                {(question["word"] === givenAnswer) && <div className={"text_button"} style={decision === 1 ? {color: colors.correct} : {color: colors.grey}}
                                                            onClick={
                                                                () => {
                                                                    setDecision(1)
                                                                }
                                                            }
                >
                    <FontAwesomeIcon icon={faCheck}/>
                    {" "}I know that word
                </div>}
                {(question["word"] !== givenAnswer) && <div className={"text_button"} style={decision === 2 ? {color: colors.wrong} : {color: colors.grey}}
                                                            onClick={
                                                                () => {
                                                                    setDecision(2)
                                                                }
                                                            }
                >
                    <FontAwesomeIcon icon={faTrash}/>
                    {" "}I want to stop learning that word
                </div>}
            </div>
        </>
    )
}

const Quiz = ({navigate}) => {
    const [questions, setQuestions] = useState(null)
    const [answers, setAnswers] = useState(Array(10))
    const [decisions, setDecisions] = useState(Array(10).fill(0))

    const [results, setResults] = useState(false)

    useEffect(() => {
        if (cookies.get("token") === undefined) {
            navigate('login/')
            return
        }
        getQuestions((x) => setQuestions(x))
    }, [])

    return questions === null ? (
        <Loading/>
    ) : !results ? (
        <div className={"full_page_container"}>
            {
                questions.map((question, i) => {
                    return (
                        <Question number={i} question={question} setAnswer={
                            (x) => setAnswers(() => {
                                let res = [...answers]
                                res[i] = x
                                return res
                            })
                        } key={i}/>
                    )
                })
            }
            <FontAwesomeIcon
                className={"next_button"}
                icon={faChevronRight}
                onClick={() => setResults(true)}
            />
        </div>
    ) : (
        <div className={"full_page_container"}>
            {
                questions.map((question, i) => {
                    return (
                        <Solution number={i} question={question} givenAnswer={answers[i]} decision={decisions[i]} setDecision={
                            (x) => setDecisions(() => {
                                let res = [...decisions]
                                res[i] = x
                                return res
                            })
                        } key={i}/>
                    )
                })
            }
            <FontAwesomeIcon
                className={"next_button"}
                icon={faChevronRight}
                onClick={
                    async () => {
                        for (let i = 0; i < questions.length; i++) {
                            if (decisions[i] === 1) {
                                await setCategory(questions[i]['id'], 1)
                            }
                            if (decisions[i] === 2) {
                                await deleteWord(questions[i]['id'])
                            }
                        }
                        navigate('/')
                    }
                }
            />
        </div>
    )
}

export default Quiz