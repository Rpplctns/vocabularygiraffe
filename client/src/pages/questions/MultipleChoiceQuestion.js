import colors from "../../const/colors.json"

const MultipleChoiceQuestion = ({number, sentence, word_type, answers}) => {
    return (
        <div className={"question_card"}>
            <p className={"question_header"}>Question {number}</p>
            <p className={"question_contents"}>{sentence[0]} {" _ "}
                <span className={"question_word_type"}>{"(" + word_type + ") "}</span>
                {sentence[1]}</p>
            <p className={"question_answer"} style={{color: colors.primary}}>
                {
                    answers.map((answer, index) => {
                            return (
                                <>
                                    <span className={"question_word_type"}>({index + 1})</span>{" " + answer + " "}
                                </>
                            )
                        }
                    )
                }
            </p>
        </div>
    )
}

export default MultipleChoiceQuestion