import * as React from "react";
import Button from "react-bootstrap/Button"
import { useNavigate } from "react-router-dom";
type Props = {
    seanceId: number;
    time: Date;
}

const HourSeance = (props : Props) =>{

    const seance_path : string = props.seanceId.toString();
    const navigate = useNavigate();
    const navigateSeance = () => {
        // 👇️ navigate to /
        navigate('/reserve/' + seance_path);
      };


    return(
        <button type="button" onClick={navigateSeance} className="btn btn-outline-secondary hour-button">
            {props.time.getHours()}:{props.time.getMinutes()}
        </button>
    )
}

export default HourSeance