import { useState, useEffect } from "react";



import NavbarFun from "../common/NavbarFun";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import AddPerofrmanceForm from "./AddPerformance";
import { SeanceForm } from "./SeanceForm";
import { seanceProps } from "../db/DBModel";
import SeanceItem from "./SeanceItem";
import React from "react";
import { getSeancesRangeByPerformanceId } from "../db/seanceAPI";
import { useLocation } from "react-router";



type Props = {
    performanceId: number;
}

const SeanceAdminList = (props: Props) => {
  let toDate = new Date();
  toDate.setDate(toDate.getDate() + 210);  
  const [result, setResult] = React.useState<seanceProps[]>();
  const location = useLocation();

  React.useEffect(() => {
    getSeancesRangeByPerformanceId(toDate, props.performanceId).then((data) => {
      setResult(data);
    });
  }, [location]);

  return (
    <div>
      <div className="App">
        {result?.map((value) => {
            return (
            <>
            <SeanceItem
            seanceId={value.id!}
            title={value.performanceId.toString()}
            dateTime={new Date(value.seanceDate + "T" + value.seanceTime + "Z")}
            />
            </>
        );
        })}
      </div>
    </div>

  );
}

export default SeanceAdminList;