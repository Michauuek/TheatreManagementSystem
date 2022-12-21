import * as React from "react";
import { Col, Container, Image, Row } from "react-bootstrap";
import { useLocation, useParams } from "react-router";
import Banner from "../common/Banner";
import Footer from "../common/Footer";
import NavbarFun from "../common/NavbarFun";
import { performanceProps } from "../db/DBModel";
import { getPerformanceById } from "../db/performanceAPI";
import ActorsList from "./ActorsList";
import SeancesList from "./SeancesList";
import "./styles.css";

const PerformanceScreen = () => {
  const params = useParams();
  let performanceId: number = -1;
  if (params.id !== undefined) {
    performanceId = parseInt(params.id);
  }

  const [result, setResult] = React.useState<performanceProps>();

  const location = useLocation();
  React.useEffect(() => {
    getPerformanceById(performanceId).then((data) => {
      setResult(data);
    });
  }, [location]);

  return (
    <div>
      <Banner />
      <NavbarFun />
      <div className="App">
        <Container fluid={true}>
          <Row>
            <Col md={9}>
              <Container className="performance-screen-main-row border">
                <Row className="performance-screen-title">{result?.title}</Row>
                <Row>
                  <hr />
                  <Col className="performance-screen-image-outer">
                    <Image
                      src={result?.imageUrl}
                      className="performance-screen-image"
                    />
                  </Col>
                  <Col className="performance-screen-description">
                    {result?.description}
                  </Col>
                </Row>
              </Container>
              <Container className="performance-screen-main-row border">
                <Row className="actor-list">
                  <ActorsList performanceId={performanceId} />
                </Row>
              </Container>
            </Col>
            <Col
              md={3}
              className="performance-screen-main-row border seance-list-section"
            >
              <SeancesList performanceId={performanceId}></SeancesList>
            </Col>
          </Row>
        </Container>
      </div>
      <Footer />
    </div>
  );
};

export default PerformanceScreen;
