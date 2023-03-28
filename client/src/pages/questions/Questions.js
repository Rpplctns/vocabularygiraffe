import "./Questions.css"

import OpenQuestion from "./OpenQuestion";
import Answer from "./Answer";

import {useEffect, useState} from "react";

import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const Questions = ({question_set, setResponse}) => {
    const question_count = question_set.length

    const [page, setPage] = useState(0)
    const [answer, setAnswer] = useState("")
    const [answers, setAnswers] = useState([])
    const [question, setQuestion] = useState(question_set[0])

    useEffect(() => {
        if(page % 2 === 0 && page < question_count){
            setAnswer("")
            setQuestion(question_set[page / 2])
        }
    }, [page])

    useEffect(() => {
        if(page % 2 === 1) setAnswers(answers.concat([answer === question.word]))
    }, [page])

    useEffect(() => {
        if(page === question_count * 2) setResponse(answers)
    }, [page])

    return (
        <div className={"question_container"}>
            <FontAwesomeIcon
                onClick={() => setPage(page + 1)}
                className={"question_next"}
                icon={faChevronRight}
            />
            {
                (page % 2 === 0 && page < question_count * 2) &&
                (
                    <OpenQuestion
                        number={page / 2 + 1}
                        sentence={question.sentence.split(question.word)}
                        word_type={question["word_type"]}
                        setAnswer={(a) => setAnswer(a)}
                    />
                )
            }
            {
                (page % 2 !== 0 && page < question_count * 2) && (
                    <Answer
                        number={(page - 1) / 2 + 1}
                        sentence={question.sentence.split(question.word)}
                        answer_given={answer}
                        answer_correct={question.word}
                        status={question.status}
                    />
                )
            }
        </div>
    )
}

export default Questions