import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.css';
import LoginButton, { amIanAdmin, DisplIfAdmin, whoIm } from './Auth';
import React, { useCallback, useEffect } from 'react';


//todo do naprawy XD cały system z trzmaniem stanu autoryzacji XD
function NavbarLogin() {
  let [user, setUser] = React.useState("Zaloguj");

  function updateUserState() {
      whoIm().then(role => {
        if(role === 'ADMIN') {
          setUser("Zaloguj 🤴");
        } else if(role === 'ACTOR') {
          setUser("Zaloguj 🎭");
        } else if(role === 'GUEST') {
          setUser("Zaloguj 🤠");
        } else {
          setUser("Zaloguj");
        }
      });
  }

  const onStorageChange = useCallback((event: StorageEvent) => {
    if (event.key === "privileges") {
      updateUserState();
    }
  }, []);

  useEffect(() => {
    updateUserState();

    window.addEventListener("storage", onStorageChange);

    return () => {
      window.removeEventListener("storage", onStorageChange);
    }
  }, [user]);

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
