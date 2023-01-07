import { Box, Button, FormControl, InputLabel, MenuItem, Select, TextField } from "@mui/material";
import React, { useRef, RefObject  } from "react";
import { hallProps, performanceProps, seanceProps} from "../db/DBModel";
import { useLocation } from "react-router-dom";
import { getHalls } from "../db/hallAPI";
import { AddSeance } from "../db/seanceAPI";



type AddSeanceFormProps = {
    performanceId:number,

  }
  
const AddSeanceForm = (props: AddSeanceFormProps) => {
  const [halls, setResult] = React.useState<hallProps[]>();
  const location = useLocation();

  React.useEffect(() => {
    getHalls().then((data) => {
      setResult(data);
    });
  }, [location]);

  const now: Date = new Date();
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    const dateElement = document.getElementById('seance-form-date') as HTMLInputElement;
    const timeElement = document.getElementById('seance-form-time') as HTMLInputElement;
    const hallElement = document.getElementById('seance-form-hall') as HTMLInputElement;
    console.log(hallElement.value, dateElement.value, timeElement.value);
    if (true) {
      let seance: seanceProps = {
        // title : titleElement.value,
        hallName: hallElement.value,
        performanceId: props.performanceId,
        seanceDate: dateElement.value,
        seanceTime: timeElement.value,
      };

      AddSeance(seance);
      dateElement.value = now.toISOString().split('T')[0];
      timeElement.value = '00:00';
    }
  };
  return (
    <Box
  component="form"
  sx={{
    '& .MuiTextField-root': { m: 1, width: '35ch' },
  }}
  noValidate
  autoComplete="off"
  onSubmit={handleSubmit}>
  <div>
  <TextField
      id="seance-form-date"
      label="Birthday"
      type="date"
      defaultValue="2017-05-24"
      sx={{ width: 220 }}
      InputLabelProps={{
        shrink: true,
      }}
      variant="outlined"
    />
  </div>
  <div>
  <TextField
      id="seance-form-time"
      label="Alarm clock"
      type="time"
      defaultValue="07:30"
      InputLabelProps={{
        shrink: true,
      }}
      inputProps={{
        step: 300, // 5 min
      }}
      sx={{ width: 150 }}
      variant="outlined"
    />
  </div>
  <div>
  <TextField
        id="seance-form-hall"
        select
        label="Native select"
        defaultValue=""
        SelectProps={{
          native: true,
        }}
        helperText="Please select your currency"
        variant="outlined"
      >
        {halls?.map((option) => (
          <option key={option.hallName} value={option.hallName}>
            {option.hallName}
          </option>
        ))}
      </TextField>
  </div>
  <Button variant="contained" type="submit">Dodaj</Button>
</Box>
  );
}
export default AddSeanceForm;