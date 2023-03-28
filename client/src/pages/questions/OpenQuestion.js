import colors from "../../const/colors.json"

const OpenQuestion = ({number, sentence, word_type, setAnswer}) => {
    return (
        <div className={"question_card"}>
            <p className={"question_header"}>Question {number}</p>
            <p className={"question_contents"}>{sentence[0]} {" _ "}
            <span className={"question_word_type"}>{"(" + word_type + ") "}</span>
            {sentence[1]}</p>
            <input className={"question_answer"} spellCheck={false} type={"text"} onChange={
                (event) => {
                    event.target.value = event.target.value
                        .replace(/[^a-zA-Z]/g, '')
                        .slice(0, 20)
                        .toLowerCase()
                    setAnswer(event.target.value)
                }
            } autoFocus={true}/>
        </div>
    )
}

export default OpenQuestion