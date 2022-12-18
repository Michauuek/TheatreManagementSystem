import React from "react";
import { Card, CardImg } from "react-bootstrap";
import { useLocation } from "react-router-dom";
import { CardHeader } from "reactstrap";
import { getCastByPerformanceId } from "../db/actorAPI";
import { actorCastProps } from "../db/DBModel";
import "./styles.css";


type Props = {
    performanceId : number,
}

const ActorsList = (props: Props) =>{

    const [result, setResult] = React.useState<actorCastProps[]>();

    const location = useLocation();
    React.useEffect(() => {
        getCastByPerformanceId(props.performanceId).then((data) => {
      setResult(data);
    });
  }, [location]);


    return(
        <>
        <div className="actor-list-cast-title">Obsada:</div><br/>
        <hr/>
        {result?.map((value) => {
            return(
                <div className="actor-list-card">
                    <img className="actor-list-photo" src={value.photoUrl}></img>
                <div className="actor-list-name">{value.name} {value.surname}<br/></div>
                <div className="actor-list-role">{value.role}</div>
                </div>
            );
        })}
        </>
    )
}

export default ActorsList