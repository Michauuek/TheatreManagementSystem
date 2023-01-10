import { useParams } from "react-router";
import Banner from "../common/Banner";
import NavbarFun from "../common/NavbarFun";
import { Col, Container, Image, Row } from "react-bootstrap";
import { getSeancesBySeanceId, seanceExtendedProps } from "../db/seanceAPI";
import { useEffect, useState } from "react";
import { LoadingSpinner } from "../common/Loading";
import { HallDisplay } from "./HallDisplay";
import { Typography } from "@mui/material";
import { getHallLayoutWithStatus, hallProps } from "../db/hallAPI";

export const HallDisplayScreen = () => {
  const params = useParams();
  const [isLoading, setIsLoading] = useState(true);
  const [currentSeance, setCurrentSeance] = useState<seanceExtendedProps>();
  const [currentHall, setCurrentHall] = useState<hallProps>();

  let seanceId = parseInt(params?.id || "0");

  useEffect(() => {
    let seancePromies = getSeancesBySeanceId(seanceId).then((data) => {
      setCurrentSeance(data);
    });

    let hallPromies = getHallLayoutWithStatus(seanceId).then((data) => {
      setCurrentHall(data);
    });

    Promise.all([seancePromies, hallPromies]).then(() => {
      setIsLoading(false);
    });
  }, []);

  const seanceTime = new Date(
    currentSeance?.seanceDate + "T" + currentSeance?.seanceTime
  );

  // generate formated string 2021-05-01 - 12:00
  const [date, haur] = [
    seanceTime.toLocaleDateString(),
    seanceTime.toLocaleTimeString().slice(0, -3),
  ];

  let app = (
    <Container className="performance-screen-main-row border">
      <Row className="performance-screen-title performance-text-center">
        {currentSeance?.title}
      </Row>
      <Row>
        <hr />
        <Col className="performance-screen-image-outer">
          <Image
            src={currentSeance?.imageUrl}
            className="performance-screen-image"
          />
        </Col>
        <Col className="performance-screen-description">
          <Typography
            variant="h3"
            component="div"
            gutterBottom
            justifyContent="center"
          >
            {date}
          </Typography>
          <Typography variant="h2" component="div" gutterBottom>
            {haur}
          </Typography>
        </Col>
      </Row>
      <hr />
      <Row>
        <Col className="performance-screen-description">
          <Typography variant="h3" component="div" gutterBottom>
            {currentSeance?.hallName}
          </Typography>
        </Col>
      </Row>
      <HallDisplay hall={currentHall!!} seanceId={currentSeance?.id!!}></HallDisplay>
    </Container>
  );

  return (
    <div>
      <Banner />
      <NavbarFun />

      <div className="App">
        <LoadingSpinner isLoading={isLoading} children={app} />
      </div>
    </div>
  );
};
