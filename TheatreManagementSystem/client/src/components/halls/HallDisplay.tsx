import { Box } from "@mui/material";
import React from "react";
import { NavigateFunction } from "react-router";
import seatState, { hallProps, seatProps } from "../db/hallAPI";
import { makeReservation, makeReservationViaOauth, ReservationRequest, ReservationViaOauthRequest } from "../db/reservationAPI";
import { HallFrom, ReservationFormInfo } from "./HallForm";
import "./styles.css";

type HallDisplayState = {
  hall: hallProps;
  hallInfo: {
    maxX: number;
    maxY: number;
    minX: number;
    minY: number;
  };
  seanceId: number;
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
  { hall: hallProps, seanceId: number, navigate: NavigateFunction },
  HallDisplayState
> {
  constructor(props: { hall: hallProps, seanceId: number, navigate: NavigateFunction }) {
    super(props);
    this.state = {
      hall: props.hall,
      hallInfo: {
        maxX: Math.max(...props.hall.seats.map((seat) => seat.posX)),
        maxY: Math.max(...props.hall.seats.map((seat) => seat.posY)),
        minX: Math.min(...props.hall.seats.map((seat) => seat.posX)),
        minY: Math.min(...props.hall.seats.map((seat) => seat.posY)),
        // smallestDistance: findSmallestDist(props.hall),
      },
      seanceId: props.seanceId,
    };

    this.onSeatClick = this.onSeatClick.bind(this);
    this.onSubmitted = this.onSubmitted.bind(this);
    this.onLogged = this.onLogged.bind(this);
  }

  onSeatClick(seat: seatProps) {
    if (seat.state === seatState.FREE) {
      seat.state = seatState.SELECTED;
    } else if (seat.state === seatState.SELECTED) {
      seat.state = seatState.FREE;
    }
    this.setState({ hall: this.state.hall });
  }

  // calculateSmallestPercentDist() {
  //   let smallestPercentDistX = this.normalize(
  //     this.state.hallInfo.smallestDistance,
  //     0,
  //     this.state.hallInfo.maxX - this.state.hallInfo.minX
  //   );
  //   let smallestPercentDistY = this.normalize(
  //     this.state.hallInfo.smallestDistance,
  //     0,
  //     this.state.hallInfo.maxY - this.state.hallInfo.minY
  //   );

  //   return Math.min(smallestPercentDistX, smallestPercentDistY);
  // }
  normalize(value: number, min: number, max: number) {
    return (value - min) / (max - min);
  }
  render() {
    return <div><div>{this.generateHallCanvas()}</div> <div><HallFrom onLogged={this.onLogged} onSumbit={this.onSubmitted} /></div></div>;
  }


  bookedSeats() {
    return this.state.hall.seats.filter(seat => seat.state === seatState.SELECTED).map(seat => seat.id!!);
  }

  onLogged() {
    let reservationRequest: ReservationViaOauthRequest = {
      seanceId: this.state.seanceId,
      reservedSeats: this.bookedSeats(),
    }

    makeReservationViaOauth(reservationRequest)
      .then(res => {
        this.props.navigate("/Confirmation");
        //window.location.reload();
      })
      .catch(err => {
        // unhappy path
      });
  }
  onSubmitted(form: ReservationFormInfo) {
    // prepare data
    let reservationRequest: ReservationRequest = {
      seanceId: this.state.seanceId,
      clientName: form.ClientName,
      clientEmail: form.ClientEmail,
      clientPhone: form.ClientPhone,
      reservedSeats: this.bookedSeats(),
    }
    // send data
    makeReservation(reservationRequest)
      .then(res => {
        this.props.navigate("/confirmation/" + reservationRequest.clientEmail);
      })
      .catch(err => {
        //todo unhappy path
      });
  }

  generateSeats() {
    let hall = this.state.hall;
    let scale = 1.0 * this.state.hall.seatScale;

    return hall.seats.map((seat, i) => {

      let x = this.normalize(
        seat.posX,
        this.state.hallInfo.minX,
        this.state.hallInfo.maxX
      ) * 100;
      let y = this.normalize(
        seat.posY,
        this.state.hallInfo.minY,
        this.state.hallInfo.maxY
      ) * 100;

      return (
        <Box
          className={this.getClass(seat)}
          sx={{
            position: "relative",
            left: `calc(${x}% + 20px)`,
            top:  `calc(${y}% + 20px)`,
            backgroundColor: this.getColor(seat),
            width: scale + "cm",
            height: scale + "cm",

            marginTop: -scale + "cm",
            marginLeft: -scale + "cm",
          }}
          onClick={() => this.onSeatClick(seat)}
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
