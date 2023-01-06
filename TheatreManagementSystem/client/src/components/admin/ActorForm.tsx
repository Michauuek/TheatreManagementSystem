import { Box, Button, TextField } from "@mui/material";
import React from "react";
import { seanceProps as SeanceProps } from "../db/DBModel";




import Form from 'react-bootstrap/Form';

function ActorForm() {
  return (
    <Box
        component="form"
        sx={{
            '& .MuiTextField-root': { m: 1, width: '35ch' },
        }}
        noValidate
        autoComplete="off">

        <div>
            <TextField
                        id="performance-form-title"    
                        label="Imię" 
                        variant="outlined" />
        </div>
        <div>
            <TextField 
                        id="performance-form-length" 
                        label="Nazwisko" 
                        variant="outlined" />
        </div>
        <div>   
            <TextField
                        id="performance-from-image" 
                        label="Url zdjęcia" 
                        variant="outlined" />
        </div>
        <div>
            <Button variant="contained">Dodaj</Button>
        </div>
    </Box>
  );
}

export default ActorForm;