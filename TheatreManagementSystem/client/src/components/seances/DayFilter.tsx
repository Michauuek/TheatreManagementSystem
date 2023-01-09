import * as React from "react";
import { Dropdown } from "react-bootstrap";
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
  const dates = [];
  for (let i = 0; i < 14; i++) {
    dates.push(<DateButton date={addDays(i)} />);
  }
  return (
    <div className="d-flex flex-wrap justify-content-center d-grid gap-2">
        {dates}
    </div>
  );
};

export default DayFilter;
