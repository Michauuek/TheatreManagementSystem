import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.css';
import LoginButton, { DisplIfAdmin } from './Auth';
import React, { useEffect } from 'react';
import { PrivilegesContext } from './PrivilegesContext';


//todo do naprawy XD cały system z trzmaniem stanu autoryzacji XD
function NavbarLogin() {
  const [user, setUser] = React.useState("Zaloguj");
  const [privileges, _] = React.useContext(PrivilegesContext)!!;

  function updateUserState() {
        if(privileges === 'ADMIN') {
          setUser("Wyloguj 🤴");
        } else if(privileges === 'ACTOR') {
          setUser("Wyloguj 🎭");
        } else if(privileges === 'GUEST') {
          setUser("Wyloguj 🤠");
        } else {
          setUser("Zaloguj");
        }
  }

  useEffect(() => {
    updateUserState();
  }, [privileges]);

  return (
      <LoginButton onErrorCallBack={()=>{
        setUser("Zaloguj ❌");
      }} name = {user}/>
  );
}

export default function NavbarFun() {
  return (
    <>
      <Navbar bg="dark" variant="dark" sticky="top">
        <Container>
          <Navbar.Brand href="/">Strona Główna</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="/" >Seanse</Nav.Link>
            <Nav.Link href="/hall">Testy</Nav.Link>
          </Nav>

          <DisplIfAdmin>
            <Nav className="me-auto">
              <Nav.Link href="/admin">Admin</Nav.Link>
            </Nav>
          </DisplIfAdmin>

          <Nav className="ml-auto">
            {NavbarLogin()}
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}
