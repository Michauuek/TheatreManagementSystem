import { useState, useEffect } from "react";



import NavbarFun from "../common/NavbarFun";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import AddPerofrmanceForm from "./AddPerformanceForm";
import { SeanceForm } from "./SeanceForm";
import { seanceProps } from "../db/DBModel";
import SeanceItem from "./SeanceItem";
import SeanceAdminList from "./SeanceAdminList";
import AddSeanceForm from "./AddSeanceForm";
import PerformanceAdminList from "./PerformanceAdminList";



const AminPanelScreen = () => {

  return (
    <div>
      <Banner />
      <NavbarFun />
      <div className="App">
        
{/* 
        //Klikamy na performance i wyświetlamy wszystkie seanse
        <SeanceAdminList performanceId={1}></SeanceAdminList>
        <AddSeanceForm performanceId={1}/> */}
        <PerformanceAdminList/>
      </div>

      

      
      <Footer />
    </div>

  );
}

export default AminPanelScreen;