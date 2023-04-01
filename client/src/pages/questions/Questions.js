import "./Questions.css"

import OpenQuestion from "./OpenQuestion";
import Answer from "./Answer";

import {useEffect, useState} from "react";

import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

import example_set from "../../example_set.json"

const Questions = () => {
    const question_set = example_set

    const question_count = question_set.length

    const [card, setCard] = useState(0)
    const [answers, setAnswers] = useState(Array(question_count))

    useEffect(() => {
        //if(card === question_count * 2)  //SUBMIT
    }, [card])

    return (
        <div className={"question_container"}>
            <FontAwesomeIcon
                onClick={() => setCard(card + 1)}
                className={"question_next"}
                icon={faChevronRight}
            />
            {
                (card % 2 === 0 && card < question_count * 2) &&
                (
                    <OpenQuestion
                        number={card / 2 + 1}
                        sentence={question_set[card / 2].sentence.split(question_set[card / 2].word)}
                        word_type={question_set[card / 2]["word_type"]}
                        setAnswer={(a) => setAnswers(() => {
                            let res = answers;
                            res[card / 2] = a
                            return res
                        })}
                    />
                )
            }
            {
                (card % 2 !== 0 && card < question_count * 2) && (
                    <Answer
                        number={(card - 1) / 2 + 1}
                        sentence={question_set[(card - 1) / 2].sentence.split(question_set[(card - 1) / 2].word)}
                        answer_given={answers[(card - 1) / 2]}
                        answer_correct={question_set[(card - 1) / 2].word}
                        status={question_set[(card - 1) / 2].status}
                    />
                )
            }
        </div>
    )
}

export default Questions