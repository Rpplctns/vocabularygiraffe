import {faChevronRight} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Link} from "react-router-dom";
import {register} from "../serverUtils";
import colors from '../const/colors.json'
import {useState} from "react";

const Register = ({navigate}) => {
    const [err, setErr] = useState('')
    const [login, setLogin] = useState('')
    const [password, setPassword] = useState('')
    const [passwordRep, setPasswordRep] = useState('')

    return (
        <div className={"card_container"}>
            <h1>Register</h1>
            <input className={"data_input"} placeholder={"login"} onChange={(e) => setLogin(e.target.value)}/>
            <input className={"data_input"} type={"password"} placeholder={"password"} onChange={(e) => setPassword(e.target.value)} />
            <input className={"data_input"} type={"password"} placeholder={"password"} onChange={(e) => setPasswordRep(e.target.value)} />
            <p style={{color: colors.wrong, textAlign: "center"}}>{err}</p>
            <FontAwesomeIcon
                className={"next_button"}
                icon={faChevronRight}
                onClick={
                    async () => {
                        if(password !== passwordRep) {
                            setErr("Passwords do not match")
                            return
                        }
                        await register(login, password)
                            .then(() => navigate('/'))
                            .catch(e => {
                                setErr(e.message)
                            })
                    }
                }
            />
            <Link to={"/login"} className={"link"}>Log in instead</Link>
        </div>
    )
}

export default Register