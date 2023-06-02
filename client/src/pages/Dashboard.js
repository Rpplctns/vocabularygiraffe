import colors from '../const/colors.json'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faLinesLeaning, faCheck, faPlay, faPlus, faRightFromBracket, faTrash} from "@fortawesome/free-solid-svg-icons";
import {useEffect, useState} from "react";
import Loading from "./Loading";

const Dashboard = () => {

    const [words, setWords] = useState([
        {
            "id": "7d05a88f-daaa-41d1-8c5a-8974529a9a3a",
            "content": "trip",
            "type": "n./v./adj.",
            "status": 0,
            "category": 0,
            "used": "2023-05-31T06:42:15.035201",
            "user": "47c1de93-1c24-4c1c-9e24-3c8f1f8e4970"
        },
        {
            "id": "029f0828-3241-43a4-83a8-eb37f0e6d3f6",
            "content": "club",
            "type": "n./v.",
            "status": 0,
            "category": 1,
            "used": "2023-05-31T06:42:23.822064",
            "user": "47c1de93-1c24-4c1c-9e24-3c8f1f8e4970"
        },
        {
            "id": "870c745d-d88a-4388-b88c-d6fbb9a71fa5",
            "content": "spurs",
            "type": "v./n.",
            "status": 0,
            "category": 0,
            "used": "2023-05-31T06:43:21.893102",
            "user": "47c1de93-1c24-4c1c-9e24-3c8f1f8e4970"
        }
    ])

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
        <div className={"full_page_container"}>
            <div className={"row_container"}>
                <div className={"text_button"}>
                    <FontAwesomeIcon icon={faPlay}/>
                    {" "}Start quiz
                </div>
                <div className={"text_button"}>
                    <FontAwesomeIcon icon={faPlus}/>
                    {" "}Add words
                </div>
            </div>
            <h1 style={{color: colors.wrong}}>
                <FontAwesomeIcon icon={faLinesLeaning}/>
                {" "}Learning
            </h1>
            <div className={"row_container"}>
                {
                    words.filter((word) => word.category === 0).map((word, i) => {
                        return (
                            <div className={"text_button"} key={i}>{word["content"]} <span
                                style={{color: colors.grey}}>[{word["type"]}]</span></div>
                        )
                    })
                }
            </div>
            <h1 style={{color: colors.correct}}>
                <FontAwesomeIcon icon={faCheck}/>
                {" "}Learned
            </h1>
            <div className={"row_container"}>
                {
                    words.filter((word) => word.category === 1).map((word, i) => {
                        return (
                            <div className={"text_button"} key={i}>{word["content"]} <span
                                style={{color: colors.grey}}>[{word["type"]}]</span></div>
                        )
                    })
                }
            </div>
            <div className={"row_container"}>
                <div className={"text_button"}>
                    <FontAwesomeIcon icon={faRightFromBracket}/>
                    {" "}Log out
                </div>
                <div className={"text_button"} style={{color: colors.wrong}}>
                    <FontAwesomeIcon icon={faTrash}/>
                    {" "}Delete account
                </div>
            </div>
        </div>
    )
}
export default Dashboard