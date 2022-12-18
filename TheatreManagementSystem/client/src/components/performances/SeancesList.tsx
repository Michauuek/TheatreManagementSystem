import { valueToPercent } from "@mui/base";
import React from "react";
import { useLocation } from "react-router-dom";
import { seanceProps } from "../db/DBModel";
import { getSeancesRangeByPerformanceId } from "../db/seanceAPI";
import HourSeance from "../seances/HourSeance";

type Props ={
    performanceId: number,
}

const SeancesList = (props : Props) =>{

    const [result, setResult] = React.useState<seanceProps[]>();
    let toDate = new Date();
    toDate.setDate(toDate.getDate() + 21);


    const location = useLocation();
    React.useEffect(() => {
        getSeancesRangeByPerformanceId(toDate, props.performanceId).then((data) => {
      setResult(data);
    });
  }, [location]);
    
  let lastDate: string = "1980-01-01";

  const printDate = (date : string) =>{
    if(date !== lastDate){
        lastDate = date;
        return(
            <> 
            <br/>{lastDate}<br/>
            </>
        )
    }
    else{
        return(<></>)
    }
    
  }

    return(
        <>
        <div className="actor-list-cast-title">Najbliższe przedstawienia:</div>
        <hr/>
        {result?.map((value) => {
            return(
                <> 
                {printDate(value.seanceDate)}
                {value.id != null &&
                     <HourSeance seanceId={value.id} time={new Date(value.seanceDate + "T" + value.seanceTime)}/>}
                </>
            );
        })}
        </>
    )
}

export default SeancesList