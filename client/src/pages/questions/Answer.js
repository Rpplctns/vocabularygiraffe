import colors from "../../const/colors.json"
import Status from "./Status";

const Answer = ({number, sentence, answer_given, answer_correct, status}) => {
    return (
        <div className={"question_card"}>
            <p className={"question_header"}>Question {number}</p>
            <p className={"question_contents"}>
                {sentence[0]}
                {
                    (answer_correct === answer_given) && (
                        <span className={"question_answer"} style={{color: colors.correct}}>
                            {" " + answer_given + " "}
                        </span>
                    )
                }
                {
                    (answer_correct !== answer_given) && (
                        <>
                            <span className={"question_answer"} style={{color: colors.wrong, textDecoration: "line-through"}}>
                                {" " + answer_given.length > 0 ? answer_given : "-"}
                            </span>
                            <span className={"question_answer"} style={{color: colors.correct}}>
                                {" " + answer_correct + " "}
                            </span>
                        </>
                    )
                }
                {sentence[1]}
            </p>
            <Status status={status.slice(1, 5).concat(answer_correct === answer_given)}/>
        </div>
    )
}

export default Answer