import colors from "../const/colors.json";
import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const Question = ({number, sentence, type}) => {
    return (
        <>
            <h1>Question {number}</h1>
            <p>
                {sentence[0]} _ <span style={{color: colors.grey}}>[{type}]</span> {sentence[1]}
            </p>
            <input className={"data_input"} placeholder={"answer"} spellCheck={false} type={"text"} onChange={
                (event) => {
                    event.target.value = event.target.value
                        .replace(/[^a-zA-Z]/g, '')
                        .slice(0, 20)
                        .toLowerCase()
                }
            }/>
        </>
    )
}

const Quiz = () => {
    return (
        <div className={"full_page_container"}>
            <Question number={1} sentence={["1", "2"]} type={"v."}/>
            <Question number={2} sentence={["1", "2"]} type={"v./adj."}/>
            <FontAwesomeIcon
                className={"next_button"}
                style={{color: colors.correct}}
                icon={faChevronRight}
            />
        </div>
    )
}

export default Quiz