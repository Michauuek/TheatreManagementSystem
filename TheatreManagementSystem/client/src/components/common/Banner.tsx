import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.css';
import { Image } from 'react-bootstrap';
import './styles.css'

export default function Banner() {
  return (
    <>
        <img id="banner-img" src={require('../../files/banner/banner.png')}></img>
    </>
  );
}
