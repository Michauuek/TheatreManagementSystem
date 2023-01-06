import { useState, useEffect } from "react";



import NavbarFun from "../common/NavbarFun";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import AddPerofrmanceForm from "./AddPerformance";
import { SeanceForm } from "./SeanceForm";
import { seanceProps } from "../db/DBModel";


const AminPanelScreen = () => {

  return (
    <div>
      <Banner />
      <NavbarFun />
      <div className="App">
        {/* <SeanceForm onClickEvent={function (props: seanceProps): void {
                  throw new Error("Function not implemented.");
              } }/> */}
        <AddPerofrmanceForm/>
      </div>

      
      <Footer />
    </div>

  );
}

export default AminPanelScreen;