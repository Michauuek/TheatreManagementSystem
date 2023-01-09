import Button from 'react-bootstrap/Button';
import { useNavigate } from 'react-router-dom';

type Props = {
    date:Date;
}

const DateButton = (props : Props) =>{
    const day_path : string =  props.date.toISOString().split("T")[0];
    const navigate = useNavigate();
    const date_split = props.date.toISOString().split("T")[0].split("-");
    const navigateDay = () => {
        console.log("wybrana data:" + day_path + " " + props.date.toISOString());
        navigate('/seances/' + day_path);    
      };
    
    return(
        <Button variant='outline-secondary' onClick={navigateDay} style={{width: "60px"}}>{date_split[2]}.{date_split[1]}</Button> 
    )
}

export default DateButton