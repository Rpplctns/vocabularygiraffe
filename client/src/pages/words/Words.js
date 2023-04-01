import './Words.css'

import colors from '../../const/colors.json'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLinesLeaning, faCheck, faCloud} from "@fortawesome/free-solid-svg-icons";
import {useState} from "react";

const Words = () => {

    const [words, setWords] = useState(null)

    fetch("http://localhost:8080/api/words/")
        .then(res => res.json())
        .then(data => setWords(data))

    return (
        <div className={"words_container"}>
            <p className={"words_header"} style={{color: colors.wrong}}>
                <FontAwesomeIcon icon={faCloud}/>
                {" "}Planned
            </p>
            <div className={"words_category_container"}>
                {
                    (words !== null) && words.map((word, i) => {
                        return (
                            <p className={"words_word"} key={i}>{word.content}</p>
                        )
                    })
                }
            </div>
            <p className={"words_header"} style={{color: colors.yellow}}>
                <FontAwesomeIcon icon={faLinesLeaning}/>
                {" "}Learning
            </p>
            <div className={"words_category_container"}>
                {
                    (words !== null) &&  words.map((word, i) => {
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
                    (words !== null) && words.map((word, i) => {
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