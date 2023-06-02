import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const AddWord = () => {
    return (
        <div className={"card_container"}>
            <h1>Add word</h1>
            <input className={"data_input"} placeholder={"word"} />
            <FontAwesomeIcon
                className={"next_button"}
                icon={faChevronRight}
            />
        </div>
    )
}

export default AddWord