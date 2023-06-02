import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link} from "react-router-dom";

const Login = () => {
    return (
        <div className={"card_container"}>
            <h1>Log in</h1>
            <input className={"data_input"} placeholder={"login"}/>
            <input className={"data_input"} type={"password"} placeholder={"password"}/>
            <FontAwesomeIcon
                className={"next_button"}
                icon={faChevronRight}
            />
            <Link to={"/register"} className={"link"}>Don't have an account yet? Create one!</Link>
        </div>
    )
}

export default Login