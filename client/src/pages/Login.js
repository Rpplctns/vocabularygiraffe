import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link} from "react-router-dom";
import {logIn} from "../serverUtils";
import {useState} from "react";
import colors from "../const/colors.json";

const Login = ({navigate}) => {
    const [err, setErr] = useState('')
    const [login, setLogin] = useState('')
    const [password, setPassword] = useState('')

    return (
        <div className={"card_container"}>
            <h1>Log in</h1>
            <input className={"data_input"} placeholder={"login"} onChange={(e) => setLogin(e.target.value)}/>
            <input className={"data_input"} type={"password"} placeholder={"password"} onChange={(e) => setPassword(e.target.value)} />
            <p style={{color: colors.wrong, textAlign: "center"}}>{err}</p>
            <FontAwesomeIcon
                className={"next_button"}
                icon={faChevronRight}
                onClick={
                    async () => {
                        await logIn(login, password)
                            .then(() => navigate('/'))
                            .catch(e => {
                                setErr(e.message)
                            })
                    }
                }
            />
            <Link to={"/register"} className={"link"}>Don't have an account yet? Create one!</Link>
        </div>
    )
}

export default Login