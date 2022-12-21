import { Box, Typography } from "@mui/material";
import React from "react";
import { hallProps, seatProps, seatState } from "../db/DBModel";

type HallDisplayState = {
  hall: hallProps;
  hallInfo: {
    maxX: number;
    maxY: number;
    minX: number;
    minY: number;
    smallestDistance: number;
  };
};

function findSmallestDist(hall: hallProps) {
  function distSquared(seat1: seatProps, seat2: seatProps) {
    let distX = seat1.posX - seat2.posX;
    let distY = seat1.posY - seat2.posY;

    return distX * distX + distY * distY;
  }
  let smallestDist = Infinity;
  hall.seats.forEach((seat) => {
    hall.seats.forEach((seat2) => {
      if (seat.id !== seat2.id) {
        let dist = distSquared(seat, seat2);
        if (dist < smallestDist) {
          smallestDist = dist;
        }
      }
    });
  });
  return Math.sqrt(smallestDist);
}

// Component that displays a Canvas with a seats of a hall.
export class HallDisplay extends React.Component<
  { hall: hallProps },
  HallDisplayState
> {
  constructor(props: { hall: hallProps }) {
    super(props);
    this.state = {
      hall: props.hall,
      hallInfo: {
        maxX: Math.max(...props.hall.seats.map((seat) => seat.posX)),
        maxY: Math.max(...props.hall.seats.map((seat) => seat.posY)),
        minX: Math.min(...props.hall.seats.map((seat) => seat.posX)),
        minY: Math.min(...props.hall.seats.map((seat) => seat.posY)),
        smallestDistance: findSmallestDist(props.hall),
      },
    };
  }
  calculateSmallestPercentDist() {
    let smallestPercentDistX = this.normalize(this.state.hallInfo.smallestDistance, 0, this.state.hallInfo.maxX-this.state.hallInfo.minX);
    let smallestPercentDistY = this.normalize(this.state.hallInfo.smallestDistance, 0, this.state.hallInfo.maxY-this.state.hallInfo.minY);

    return Math.min(smallestPercentDistX, smallestPercentDistY);
  }
  normalize(value: number, min: number, max: number) {
    return (value - min) / (max - min);
  }
  render() {
    return <div>{this.generateHallCanvas()}</div>;
  }

  // todo - scrollable x-canvas and absolute x canvas.
  generateHallCanvas() {
    let hall = this.state.hall;

    return (
      <Box
        id="canvas"
        sx={{
          width: 800,
          height: 500,
          backgroundColor: "#F0F0F2",
          margin: "auto",
        }}
      >
        <div
          style={{
            width: 800,
            height: "100%",
            backgroundColor: "transparent",
            padding: "40px 40px 40px 40px",
            overflowX: "clip",
          }}
        >
          {hall.seats.map((seat, i) => {
            let dist = this.calculateSmallestPercentDist();
            console.log(dist);

            return (
              <Box
                sx={{
                  position: "relative",
                  left:
                    this.normalize(
                      seat.posX,
                      this.state.hallInfo.minX,
                      this.state.hallInfo.maxX
                    ) *
                      100 +
                    "%",
                  top:
                    this.normalize(
                      seat.posY,
                      this.state.hallInfo.minY,
                      this.state.hallInfo.maxY
                    ) *
                      100 +
                    "%",
                  width: "20px",
                  height: "20px",
                  borderRadius: 1,
                  userSelect: "none",
                  fontSize: 12,
                  textAlign: "center",
                  lineHeight: "20px",
                  marginTop: "-20px",
                  marginLeft: "-20px",
                  backgroundColor: this.getColor(seat),
                }}
              >
                {seat.seatName}
              </Box>
            );
          })}
        </div>
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
      default:
        return "primary.main";
    }
  }
}
