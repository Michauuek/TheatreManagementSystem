import * as React from "react";
import Button from "react-bootstrap/Button"
type Props = {
    seanceId: number;
    time: Date;
}

const HourSeance = (props : Props) =>{

    return(
        <button type="button" className="btn btn-outline-secondary hour-button">
            {props.time.getHours()}:{props.time.getMinutes()}
        </button>
    )
}

export default HourSeance