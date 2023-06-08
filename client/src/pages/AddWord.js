import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {useState} from "react";
import {addWord} from "../serverUtils";
import colors from "../const/colors.json";

const AddWord = ({navigate}) => {
    const [err, setErr] = useState('')
    const [word, setWord] = useState('')

    return (
        <div className={"card_container"}>
            <h1>Add word</h1>
            <input className={"data_input"} placeholder={"word"} autoFocus={true} onChange={e => {
                e.target.value = e.target.value
                    .replace(/[^a-zA-Z]/g, '')
                    .toLowerCase()
                setWord(e.target.value)
            }}/>
            <p style={{color: colors.wrong, textAlign: "center"}}>{err}</p>
            <FontAwesomeIcon
                className={"next_button"}
                icon={faChevronRight}
                onClick={
                    async () => {
                        await addWord(word, 0)
                            .then(() => navigate('/'))
                            .catch(e => setErr(e.message))
                    }
                }
            />
        </div>
    )
}

export default AddWord