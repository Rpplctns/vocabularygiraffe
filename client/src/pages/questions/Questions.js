import "./Questions.css"

import OpenQuestion from "./OpenQuestion";
import Answer from "./Answer";

import {useEffect, useState} from "react";

import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import Results from "./Results";
import Loading from "../Loading";

const question_count = 10

const Questions = () => {

    const [questions, setQuestions] = useState(null)
    const [card, setCard] = useState(0)
    const [answers, setAnswers] = useState(Array(question_count).fill(""))

    useEffect(() => {
        if (card === question_count * 2) {
            console.log("submitting...")
            const responseData = {
                "exercises": [
                    questions.map((val, i) => {
                        return ({
                            "first": val["wordId"],
                            "second": val["word"] === answers[i]
                        })
                    })
                ]
            }
            console.log(responseData)
            fetch("http://localhost:8080/api/quiz/", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(responseData)
            })
                .then(() => console.log("...submit"))
        }
    }, [answers, card, questions])

    useEffect(() => {
        fetch("http://localhost:8080/api/quiz/")
            .then(res => res.json())
            .then(data => setQuestions(data["exercises"]))
    }, [])

    return card === question_count * 2 ? (
        <Results/>
    ) : questions === null ? (
        <Loading/>
    ) : card % 2 === 0 ? (
        <div className={"question_container"}>
            <FontAwesomeIcon
                onClick={() => setCard(card + 1)}
                className={"question_next"}
                icon={faChevronRight}
            />
            <OpenQuestion
                number={card / 2 + 1}
                sentence={questions[card / 2]["sentence"].replace(/\[.*?]/g, "#").split("#")}
                word_type={questions[card / 2]["wordType"]}
                setAnswer={(a) => setAnswers(() => {
                    let res = answers;
                    res[card / 2] = a
                    return res
                })}
            />
        </div>
    ) : (
        <div className={"question_container"}>
            <FontAwesomeIcon
                onClick={() => setCard(card + 1)}
                className={"question_next"}
                icon={faChevronRight}
            />
            <Answer
                number={(card - 1) / 2 + 1}
                sentence={questions[(card - 1) / 2]["sentence"].replace(/\[.*?]/g, "#").split("#")}
                answer_given={answers[(card - 1) / 2]}
                answer_correct={questions[(card - 1) / 2].word}
                status={[null, null, null, null, null]}// questions[(card - 1) / 2].status}
            />
        </div>
    )
}

export default Questions