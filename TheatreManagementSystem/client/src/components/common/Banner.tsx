import 'bootstrap/dist/css/bootstrap.css';
import './styles.css';

export default function Banner() {
  return (
    <>
        <img id="banner-img" src={require('../../files/banner/banner.png')}></img>
    </>
  );
}
