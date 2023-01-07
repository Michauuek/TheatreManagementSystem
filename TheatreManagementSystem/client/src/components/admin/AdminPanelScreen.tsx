import { useState, useEffect } from "react";



import NavbarFun from "../common/NavbarFun";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import AddPerofrmanceForm from "./AddPerformance";
import { SeanceForm } from "./SeanceForm";
import { seanceProps } from "../db/DBModel";
import SeanceItem from "./SeanceItem";
import SeanceAdminList from "./SeanceAdminList";


const AminPanelScreen = () => {

  return (
    <div>
      <Banner />
      <NavbarFun />
      <div className="App">
        

        //Klikamy na performance i wyświetlamy wszystkie seanse
        <SeanceAdminList performanceId={1}></SeanceAdminList>
      </div>

      
      <Footer />
    </div>

  );
}

export default AminPanelScreen;