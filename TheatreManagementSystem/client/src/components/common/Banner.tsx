import 'bootstrap/dist/css/bootstrap.css';
import Container from "react-bootstrap/Container";
import './styles.css';

export default function Banner() {
  return (
    <div id = "banner-img">
      <Container id="banner">
          <img className="banner-img mx-auto d-block shadow" src={require('../../files/banner/painting1.png')}></img>
          <img className="banner-img mx-auto d-block shadow d-none d-xs-block" src={require('../../files/banner/painting2.png')}></img>
          <img className="banner-img mx-auto d-block shadow d-none d-lg-block" src={require('../../files/banner/painting3.png')}></img>
      </Container>
    </div>
  );
}


// return (
//   <>
//       <img id="banner-img" src={require('../../files/banner/banner.png')}></img>
//   </>
// );