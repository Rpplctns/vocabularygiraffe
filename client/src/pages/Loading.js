import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faHourglassHalf} from "@fortawesome/free-solid-svg-icons";

const Loading = () => {
    return (
        <FontAwesomeIcon icon={faHourglassHalf} shake={true} className={"loading_icon"}/>
    )
}

export default Loading