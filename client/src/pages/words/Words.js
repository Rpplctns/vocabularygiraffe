import './Words.css'

import colors from '../../const/colors.json'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLinesLeaning, faCheck, faCloud} from "@fortawesome/free-solid-svg-icons";

const Words = ({words}) => {
    return (
        <div className={"words_container"}>
            <p className={"words_header"} style={{color: colors.wrong}}>
                <FontAwesomeIcon icon={faCloud}/>
                {" "}Planned
            </p>
            <div className={"words_category_container"}>
                {
                    words.planned.map(word => {
                        return (
                            <p className={"words_word"}>{word}</p>
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
                    words.learning.map(word => {
                        return (
                            <p className={"words_word"}>{word}</p>
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
                    words.learned.map(word => {
                        return (
                            <p className={"words_word"}>{word}</p>
                        )
                    })
                }
            </div>
        </div>
    )
}
export default Words