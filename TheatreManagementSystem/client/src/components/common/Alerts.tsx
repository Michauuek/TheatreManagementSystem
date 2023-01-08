import { Alert } from "react-bootstrap"


const redAlert = (message : string) =>{

    console.log("asdasdasdsa");
    return(
        <div className="alertContainerStyle">
        <Alert key='danger' variant='danger'>{message}</Alert>
        </div>
    )
}

export default redAlert;