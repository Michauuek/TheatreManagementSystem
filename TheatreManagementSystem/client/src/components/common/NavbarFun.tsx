import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.css';
import LoginButton, { DisplIfAdmin } from './Auth';
import React, { useEffect } from 'react';
import { PrivilegesContext } from './PrivilegesContext';
import { Dropdown, NavDropdown } from 'react-bootstrap';


//todo do naprawy XD cały system z trzmaniem stanu autoryzacji XD
function NavbarLogin() {
  const [user, setUser] = React.useState("Zaloguj");
  const [privileges, _] = React.useContext(PrivilegesContext)!!;

  function updateUserState() {
    if (privileges === 'ADMIN') {
      setUser("Wyloguj 🤴");
    } else if (privileges === 'ACTOR') {
      setUser("Wyloguj 🎭");
    } else if (privileges === 'GUEST') {
      setUser("Wyloguj 🤠");
    } else {
      setUser("Zaloguj");
    }
  }

  useEffect(() => {
    updateUserState();
  }, [privileges]);

  return (
    <LoginButton onErrorCallBack={() => {
      setUser("Zaloguj ❌");
    }} name={user} variant='dark' />
  );
}

export default function NavbarFun() {
  return (
    <>
      <Navbar bg="dark" variant="dark" sticky="top" expand="lg">
        <Container>
          <Navbar.Brand href="/" className="navbar-brand">Strona Główna</Navbar.Brand>
          <Navbar.Toggle aria-controls="basic-navbar-nav" />
          <Navbar.Collapse id="basic-navbar-nav">
            <Nav className="mx-auto me-auto">
              <Nav.Link href="/" >Seanse</Nav.Link>
              <Nav.Link href="/hall">Testy</Nav.Link>
            </Nav>

            <DisplIfAdmin>
              <Nav className="mx-auto me-auto">
                <NavDropdown title="admin" id="basic-nav-dropdown">
                  <NavDropdown.Item href="#/action-1">Action</NavDropdown.Item>
                  <NavDropdown.Item href="#/action-2">Another action</NavDropdown.Item>
                  <NavDropdown.Item href="#/action-3">Something else</NavDropdown.Item>
                </NavDropdown>
              </Nav>
            </DisplIfAdmin>
            <Nav className="mx-auto me-auto">
              {NavbarLogin()}
            </Nav>
          </Navbar.Collapse>
        </Container>
      </Navbar>
    </>
  );
}
