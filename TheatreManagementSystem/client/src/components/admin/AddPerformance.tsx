import { Box, Button, TextField } from "@mui/material";
import React, { useRef } from "react";
import { seanceProps as SeanceProps } from "../db/DBModel";
import { RefObject } from 'react';
import { createRef } from 'react';
import { addPerformance, performanceRequest } from "../db/performanceAPI";



type AddPerformanceFormProps = {
    // any props you need to pass to the form
  }
  
  const AddPerofrmanceForm = (props: AddPerformanceFormProps) => {
    const handleSubmit = (event : React.FormEvent<HTMLFormElement>) =>{
      event.preventDefault();
      const titleElement = document.getElementById('performance-form-title') as HTMLInputElement;
      const descriptionElement = document.getElementById('performance-form-description') as HTMLInputElement;
      const lengthElement = document.getElementById('performance-form-length') as HTMLInputElement;
      const imageElement = document.getElementById('performance-form-image') as HTMLInputElement;
      const title = titleElement.value;
      const description = descriptionElement.value;
      const length = lengthElement.value;
      const image = imageElement.value;
      if(titleElement.value.length > 3 && descriptionElement.value.length > 10){
      let perf: performanceRequest = {
        title : titleElement.value,
        description: descriptionElement.value,
        length: lengthElement.value,
        imageUrl : imageElement.value,
      }
      addPerformance(perf)
      titleElement.value = "";
      descriptionElement.value = "";
      lengthElement.value = "";
      imageElement.value = "";
    }
      // console.log(title);
    }

    // const handleChange = (event : React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {

    // }
  
    return (
      <Box
    component="form"
    sx={{
      '& .MuiTextField-root': { m: 1, width: '35ch' },
    }}
    noValidate
    autoComplete="off"
    onSubmit={handleSubmit}
  >
    <div>
      <TextField
        id="performance-form-title"    
        label="Tytuł" 
        variant="outlined"
      />
    </div>
    <div>
      <TextField
        id="performance-form-description" 
        label="Opis" 
        multiline
        rows={4}
        variant="outlined"
      />
    </div>
    <div>
      <TextField 
        id="performance-form-length" 
        label="Długość trwania" 
        variant="outlined"
      />
    </div>
    <div>   
      <TextField
        id="performance-form-image" 
        label="Url zdjęcia" 
        variant="outlined"
      />
    </div>
    <Button variant="contained" type="submit">Dodaj</Button>
  </Box>
    );
  }
  export default AddPerofrmanceForm;