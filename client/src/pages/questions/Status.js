import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import {faCircleCheck, faCircleXmark, faCircleMinus} from "@fortawesome/free-solid-svg-icons"

import colors from '../../const/colors.json'

const Status = ({status}) => {
    return (
        <div className={"question_status"}>
            {
                status.map((value) => {
                    return (
                        <FontAwesomeIcon
                            icon={(value == null) ? faCircleMinus : (value ? faCircleCheck : faCircleXmark)}
                            color={(value == null) ? colors.grey : (value ? colors.correct : colors.wrong)}
                        />
                    )
                })
            }
        </div>
    )
}

export default Status