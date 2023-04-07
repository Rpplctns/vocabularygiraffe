import './Words.css'

import colors from '../../const/colors.json'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLinesLeaning, faCheck, faCloud, faHourglassHalf} from "@fortawesome/free-solid-svg-icons";
import {useEffect, useState} from "react";
import Loading from "../loading/Loading";

const Words = () => {

    const [words, setWords] = useState(null)

    useEffect(() => {
            fetch("http://localhost:8080/api/words/")
                .then(res => res.json())
                .then(data => setWords(data))
        },
        []
    )

    return words === null ? (
        <Loading/>
    ) : (
        <div className={"words_container"}>
            <p className={"words_header"} style={{color: colors.wrong}}>
                <FontAwesomeIcon icon={faLinesLeaning}/>
                {" "}Learning
            </p>
            <div className={"words_category_container"}>
                {
                    words.filter((word) => word.status < 243).map((word, i) => {
                        return (
                            <p className={"words_word"} key={i}>{word.content}</p>
                        )
                    })
                }
            </div>
            <p className={"words_header"} style={{color: colors.correct}}>
                <FontAwesomeIcon icon={faCheck}/>
                {" "}Learned
            </p>
            <div className={"words_category_container"}>
                {
                    words.filter((word) => word.status >= 243).map((word, i) => {
                        return (
                            <p className={"words_word"} key={i}>{word.content}</p>
                        )
                    })
                }
            </div>
        </div>
    )
}
export default Words