import { Box, Button, TextField } from "@mui/material";
import React from "react";
import { seanceProps as SeanceProps } from "../db/dataBaseModel";

type SeanceFormError = {
    hallName?: boolean|undefined;
    performanceID?: boolean|undefined;
    seanceDate?: boolean|undefined;
    seanceTime?: boolean|undefined;
};

type SeanceFormState = {
    hallName: string;
    performanceId: number | undefined;
    seanceDate: string;
    seanceTime: string | undefined;
    errors: SeanceFormError
}

type OnClickEvent = (props: SeanceProps) => void;

// current time
const now = new Date();
// get date in format mm/dd/yyyy
const date = now.toISOString().slice(0, 10);
// get time in format hh:00
const time = `${now.toTimeString().slice(0, 2)}:00`;


const defaultSeance: SeanceFormState = {
    hallName: "",
    performanceId: undefined,
    seanceDate: date,
    seanceTime: time,
    errors: {hallName:undefined, performanceID:undefined, seanceDate:undefined, seanceTime:undefined},
}


export class SeanceForm extends React.Component<{onClickEvent: OnClickEvent}, SeanceFormState> {
    event: OnClickEvent;

    constructor(props: {onClickEvent: OnClickEvent}) {
        super(props);
        
        this.state = defaultSeance;

        this.handleChange = this.handleChange.bind(this);
        this.onClick = this.onClick.bind(this);
        this.event = props.onClickEvent;
    }

    render() {
        return (
            <Box
                component="form"
                sx={{
                    '& .MuiTextField-root': { m: 1, width: '25ch' },
                }}
                noValidate
                autoComplete="off">

                <div>
                    <TextField error={this.state.errors.hallName??false}
                               id="hall-form-hallid"       //TODO: change to dropdown list 
                               label="Sala" 
                               variant="outlined" 
                               onChange={this.handleChange} />
                    <TextField error={this.state.errors.performanceID??false}
                               id="hall-from-performanceid" //TODO: change to dropdown list 
                               label="Występ" 
                               variant="outlined" 
                               onChange={this.handleChange} />
                </div>
                <div>
                    <TextField error = {this.state.errors.seanceDate??false}
                               id="hall-from-seancedate" 
                               label="Data" 
                               variant="standard" 
                               type="date" 
                               defaultValue={date} 
                               onChange={this.handleChange} />
                    <TextField error = {this.state.errors.seanceTime??false}
                               id="hall-form-sancetime" 
                               label="Godzina" 
                               variant="standard" 
                               type="time" 
                               defaultValue={time}
                               onChange={this.handleChange} />
                </div>
                <div>
                    <Button variant="contained" onClick={this.onClick} >Dodaj</Button>
                </div>
            </Box>
        );
    }

    onClick() {
        try{
            this.event(this.matureState());
        } catch(e) {
            this.onEventError(e);    
        }
    }

    onEventError(e: any) {
        //TODO DISPLAY ERROR MESSAGE OR ADD ERROR TO STATE
        if (e instanceof Error){
            console.log(e.message);
        } else {
            console.log(e);
        }
    }

    handleChange(event: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>): void {
        event.persist(); // allow native event access (see: https://facebook.github.io/react/docs/events.html)
        
        let Id = event.target.id; 
        let value = event.target.value;

        switch (Id) {
            case "hall-form-hallid": this.setState({ hallName: value });
                break;
            case "hall-from-performanceid": this.setState({ performanceId: parseInt(value) });
                break;
            case "hall-from-seancedate": this.setState({ seanceDate: value });
                break;
            case "hall-form-sancetime": this.setState({ seanceTime: value });
                break;
        }
    }
    matureState(): SeanceProps {
        if (this.state.hallName === "") {
            this.setState({errors: {hallName: true}});
            throw new Error("Hall name is empty");
        }
        if (this.state.performanceId === undefined) {
            this.setState({errors: {performanceID: true}});
            throw new Error("PerformanceID is empty or not a number");
        }
        if (this.state.seanceDate === "") {
            this.setState({errors: {seanceDate: true}});
            throw new Error("Seance date is empty");
        }

        return this.state as SeanceProps;
    }

}