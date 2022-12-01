import { Box, Button, TextField } from "@mui/material";
import React from "react";
import { hallProps, seatProps, seatState } from "../db/DBModel";

type HallDisplayState = {
  props: hallProps;
};

// Component that displays a Canvas with a seats of a hall.
export class HallDisplay extends React.Component<{ hall: hallProps }, HallDisplayState> {
  constructor(props: { hall: hallProps }) {
    super(props);
    this.state = {
      props: props.hall,
    };
  }

  render() {
    return (
      <div>
        <Box
          id="canvas"
          sx={{
            width: "100wh",
            height: 500,
            backgroundColor: "#F0F0F2",

          }}
        >
          {this.state.props.seats.map((seat) => {
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
                }}
                
              >{seat.seatName}</Box>
            );
          })}
        </Box>
      </div>
    );
  }
  getColor(seat: seatProps)
  {
    switch(seat.state){
        case seatState.FREE:
            return "primary.main";
        case seatState.RESERVED:
            return "error.main";
        case seatState.SELECTED:
            return "success.main";
    }
  }
}
