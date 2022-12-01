import { Box, Button, TextField, Typography } from "@mui/material";
import React from "react";
import { hallProps, hallWithSeance, seatProps, seatState } from "../db/DBModel";

type HallDisplayState = {
  hall: hallWithSeance;
};

// Component that displays a Canvas with a seats of a hall.
export class HallDisplay extends React.Component<
  { hall: hallWithSeance },
  HallDisplayState
> {
  constructor(props: { hall: hallWithSeance }) {
    super(props);
    if (props.hall.seanceInfo !== undefined) {
      if (props.hall.seanceInfo.hallName != props.hall.hallName) {
        console.error(
          "Halls provided in seanceInfo and hallInfo are different"
        );
      }
    }
    this.state = { hall: props.hall };
  }

  render() {
    return (
    <div>
      {this.generateTitle()} 
      {this.generateHallCanvas()}
    </div>);
  }

  generateTitle() {
    // generate title 
    let title = 
      <Typography variant="h1" gutterBottom>
        {this.state.hall.hallName}
      </Typography>;


    // generate subtitle - aka time
    let time;
    if (this.state.hall.seanceInfo !== undefined) {
      time = 
      <Typography variant="h2" gutterBottom> 
        {this.state.hall.seanceInfo.seanceDate} - {this.state.hall.seanceInfo.seanceTime} 
      </Typography>; 
    } else {
      // no seance info
      time = 
      <Typography variant="h2"> 
        Brak informacji o seansie 
      </Typography>;
    }

    return (
      <div>
        {title}
        {time}
      </div>
    );
  }

  generateHallCanvas() {
    let hall = this.state.hall;

    return (
      <Box
        id="canvas"
        sx={{
          width: "100wh",
          height: 500,
          backgroundColor: "#F0F0F2",
        }}
      >
        {hall.seats.map((seat) => {
          return (
            <Box
              sx={{
                position: "relative",
                top: seat.posX,
                left: seat.posY,
                width: "20px",
                height: "20px",
                bgcolor: this.getColor(seat),
                borderRadius: 1,
                userSelect: "none",
                fontSize: 12,
                textAlign: "center",
                lineHeight: "20px",
                overflow: "hidden",
              }}
            >
              {seat.seatName}
            </Box>
          );
        })}
      </Box>
    );
  }

  getColor(seat: seatProps) {
    switch (seat.state) {
      case seatState.FREE:
        return "primary.main";
      case seatState.RESERVED:
        return "error.main";
      case seatState.SELECTED:
        return "success.main";
    }
  }
}
