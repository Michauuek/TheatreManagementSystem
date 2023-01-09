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
  const this_week = [];
  for (let i = 0; i < 7; i++) {
    this_week.push(<DateButton date={addDays(i)} />);
  }
  const next_week = [];
  for (let i = 8; i < 14; i++) {
    next_week.push(<div className="item col-xs-4 text-center"><DateButton date={addDays(i)} /></div>);
  }

  return (
    <div className="d-flex flex-wrap justify-content-center d-grid gap-2">
        {this_week}
        <Dropdown>
            <Dropdown.Toggle variant="outline-secondary" id="dropdown-basic">
                Następny tydzień
            </Dropdown.Toggle>
            <Dropdown.Menu className="row">
                {next_week}
            </Dropdown.Menu>
        </Dropdown>
    </div>
  );
};

export default DayFilter;
