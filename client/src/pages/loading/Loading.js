import './Loading.css'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faHourglassHalf} from "@fortawesome/free-solid-svg-icons";

const Loading = () => {
    return (
        <div className={"loading_container"} >
            <FontAwesomeIcon icon={faHourglassHalf} shake={true} className={"loading_icon"}/>
        </div>
    )
}

export default Loading