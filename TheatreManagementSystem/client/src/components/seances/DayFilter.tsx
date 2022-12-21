import * as React from "react";
import Button from "react-bootstrap/Button";
import ButtonGroup from "react-bootstrap/ButtonGroup";
import ButtonToolbar from "react-bootstrap/ButtonToolbar";
import { performanceProps, seanceExtendedProps } from "../db/DBModel";
import DateButton from "./DateButton";

type Props = {};

function addDays(numOfDays: number, date = new Date()) {
  date.setDate(date.getDate() + numOfDays);

  return date;
}

const DayFilter = (props: Props) => {
  // todo - take days from db and generate buttons based on that
  const now = new Date();
  const buttons = [];
  for (let i = 0; i < 18; i++) {
    buttons.push(<DateButton date={addDays(i)} />);
  }

  return (
    <ButtonToolbar aria-label="Toolbar with button groups">
      <ButtonGroup 
        className="me-2 button-group flex-wrap"
        aria-label="First group"
      >
        {buttons}
      </ButtonGroup>
    </ButtonToolbar>
  );
};

export default DayFilter;
