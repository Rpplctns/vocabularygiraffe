import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link} from "react-router-dom";

const Register = () => {
    return (
        <div className={"card_container"}>
            <h1>Register</h1>
            <input className={"data_input"} placeholder={"login"}/>
            <input className={"data_input"} type={"password"} placeholder={"password"}/>
            <input className={"data_input"} type={"password"} placeholder={"repeat password"}/>
            <FontAwesomeIcon
                className={"next_button"}
                icon={faChevronRight}
            />
            <Link to={"/login"} className={"link"}>Log in instead</Link>
        </div>
    )
}

export default Register