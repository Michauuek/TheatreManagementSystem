import { autocompleteClasses, Box, Typography } from "@mui/material";
import React from "react";
import { hallProps, seatProps, seatState } from "../db/DBModel";
import "./styles.css";

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

    //for debugging set some seats to be reserved

    this.state.hall.seats[0].state = seatState.RESERVED;
  }
  calculateSmallestPercentDist() {
    let smallestPercentDistX = this.normalize(
      this.state.hallInfo.smallestDistance,
      0,
      this.state.hallInfo.maxX - this.state.hallInfo.minX
    );
    let smallestPercentDistY = this.normalize(
      this.state.hallInfo.smallestDistance,
      0,
      this.state.hallInfo.maxY - this.state.hallInfo.minY
    );

    return Math.min(smallestPercentDistX, smallestPercentDistY);
  }
  normalize(value: number, min: number, max: number) {
    return (value - min) / (max - min);
  }
  render() {
    return <div>{this.generateHallCanvas()}</div>;
  }

  generateSeats() {
    let hall = this.state.hall;

    return hall.seats.map((seat, i) => {
      return (
        <Box
          className={this.getClass(seat)}
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
            backgroundColor: this.getColor(seat),
          }}
        >
          {seat.seatName}
        </Box>
      );
    })
  }

  getZoomedHall() {
    return (
      <div style={{ overflow: "auto" }}>
        <Box
          id="canvas"
          sx={{
            width: "clamp(800px, 100%, 100%)",
            height: 500,
          }}
        >
          <div id="canvas-content">
            {this.generateSeats()}
          </div>
        </Box>
      </div>
    );
  }

  getUnzoomedHall() {
    return (<div style={{ overflow: "auto" }}>
      <Box
        id="canvas"
        sx={{
          width: "clamp(800px, 100%, 100%)",
          height: 500,
        }}
      >
        <div id="canvas-content" className="const-sized">
          {this.generateSeats()}
        </div>
      </Box>
    </div>);
  }

  // todo - scrollable x-canvas and absolute x canvas.
  generateHallCanvas() {
    return this.getZoomedHall();
  }

  getClass(seat: seatProps) {
    switch (seat.state as seatState) {
      case seatState.FREE:
        return "seat seat-free";
      case seatState.RESERVED:
        return "seat seat-reserved";
      case seatState.SELECTED:
        return "seat seat-selected";
      default:
        return "seat";
    }
  }

  getColor(seat: seatProps) {
    switch (seat.state as seatState) {
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
