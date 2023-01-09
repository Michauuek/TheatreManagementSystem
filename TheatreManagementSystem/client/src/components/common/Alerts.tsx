import { Alert } from "react-bootstrap"


const redAlert = (message : string) =>{

    // console.log("asdasdasdsa");
    return(
        <div className="alertContainerStyle">
        <Alert className="alertStyle" key='danger' variant='danger'>{message}</Alert>
        </div>
    )
}

export default redAlert;