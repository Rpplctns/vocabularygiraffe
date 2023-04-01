import './Home.css'
import {Link} from "react-router-dom";

const Home = () => {
    return (
        <div className={"home_container"}>
            <Link to={"/quiz"} >quiz </Link>
            <Link to={"/words"} >words</Link>
        </div>
    )
}

export default Home
