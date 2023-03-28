import "./bars.css"

import {faMoon, faSun, faUser} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";

const Header = ({theme, setTheme}) => {
    return (
        <div className={"bars_header"}>
            <p className={"header_text"}>
                Vocabulary Giraffe
            </p>
            <FontAwesomeIcon
                className={"header_button_separator"}
                icon={faUser}
            />
        </div>
    )
}

export default Header