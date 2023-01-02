import { Box, Button, TextField } from "@mui/material";
import React from "react";
import LoginButton from "../common/auth";

type hallFormError = {
    ClientName?: boolean|undefined;
    ClientEmail?: boolean|undefined;
    ClientPhone?: boolean|undefined;
    OauthLoginError?: boolean|undefined;
};


export type ReservationFormInfo = {
    ClientName: string;
    ClientEmail: string;
    ClientPhone: string;
}

type HallFormState = {
    ClientName: string;
    ClientEmail: string;
    ClientPhone: string;
    errors: hallFormError;
}

type HallFormProps = {
    onSumbit: (data: ReservationFormInfo) => void;
    onLogged: () => void;
}

export class HallFrom extends React.Component<HallFormProps, HallFormState> {
    onSumbitEvent: (data: ReservationFormInfo) => void;
    onLoggedEvent: () => void;

    constructor(props: HallFormProps) {
        super(props);

        this.onSumbitEvent = props.onSumbit;
        this.onLoggedEvent = props.onLogged;
        this.onLogged = this.onLogged.bind(this);
        this.onSumbit = this.onSumbit.bind(this);
        this.handleChange = this.handleChange.bind(this);
        
        this.state = {
            ClientName: "",
            ClientEmail: "",
            ClientPhone: "",
            errors: {}
        };  
    }

    onSumbit() {
        this.onSumbitEvent(this.matureState());
    }
    
    onLogged() {
        this.onLoggedEvent();
    }

    onLoginError() {
        this.setState({errors: {OauthLoginError: true}});
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
                    <TextField error={this.state.errors.ClientEmail??false}
                               id="hall-form-name"
                               label="Nazwisko" 
                               variant="outlined" 
                               onChange={this.handleChange} />
                    <TextField error={this.state.errors.ClientEmail??false}
                               id="hall-from-email" 
                               label="Email" 
                               variant="outlined" 
                               onChange={this.handleChange} />
                    <TextField error={this.state.errors.ClientEmail??false}
                               id="hall-from-phone" 
                               label="Telefon" 
                               variant="outlined" 
                               onChange={this.handleChange} />
                    
                </div>
                <div>
                    <Button variant="contained" onClick={this.onSumbit}>Zarezewuj!</Button>
                    <LoginButton onSuccessCallBack={this.onLogged} onErrorCallBack={this.onLoginError} name="Zaloguj się przez Google"/>
                </div>
            </Box>
        );
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
            case "hall-form-name": this.setState({ ClientName: value });
                break;
            case "hall-from-email": this.setState({ ClientEmail: value });
                break;
            case "hall-from-phone": this.setState({ ClientPhone: value });
        }
    }
    matureState(): ReservationFormInfo  {
        if (this.state.ClientName === "") {
            this.setState({errors: {ClientName: true}});
            throw new Error("Musisz podać Nazwisko");
        }
        if (this.state.ClientEmail === "") {
            this.setState({errors: {ClientEmail: true}});
            throw new Error("Musisz podać Email");
        }
        if (this.state.ClientPhone === "") {
            this.setState({errors: {ClientPhone: true}});
            throw new Error("Musisz podać Telefon");
        }
        
        return {
            ClientName: this.state.ClientName,
            ClientEmail: this.state.ClientEmail,
            ClientPhone: this.state.ClientPhone
        };
    }

}