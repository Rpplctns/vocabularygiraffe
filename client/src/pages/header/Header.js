import "./Header.css"

import {faMoon, faSun, faUser} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import colors from "../../const/colors.json";
import {Link} from "react-router-dom";

const Header = ({theme, setTheme}) => {
    return (
        <div className={"header_container"} style={{color: colors[theme ? "light" : "dark"].foreground}}>
            <Link className={"header_text"} to={"/"}  >
                Vocabulary Giraffe
            </Link>
            <FontAwesomeIcon
                className={"header_button_separator"}
                icon={faUser}
            />
            <FontAwesomeIcon
                className={"header_button"}
                onClick={() => setTheme(!theme)}
                style={{backgroundColor: colors[theme ? "light": "dark"].background}}
                icon={theme ? faMoon : faSun}
            />
        </div>
    )
}

export default Header