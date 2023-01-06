import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import 'bootstrap/dist/css/bootstrap.css';
import LoginButton, { whoIm } from './auth';
import React from 'react';


function NavbarLogin() {
  let [user, setUser] = React.useState("");

  return (
      <LoginButton onSuccessCallBack={()=> {
        whoIm().then(role => {
          if(role === '"ADMIN"') {
            setUser(" 🤴");
          } else if(role === '"ACTOR"') {
            setUser(" 🎭");
          } else {
            setUser(" 🤠");
          }
        });
      }} onErrorCallBack={()=>{
        setUser(" ❌");
      }} name = {"Zaloguj" + user}/>
  );
}

export default function NavbarFun() {
  return (
    <>
      <Navbar bg="dark" variant="dark" sticky="top">
        <Container>
          <Navbar.Brand href="/">Nasz teatr</Navbar.Brand>
          <Nav className="me-auto">
            <Nav.Link href="/" >Home</Nav.Link>
            <Nav.Link href="#features">Features</Nav.Link>
            <Nav.Link href="#pricing">Pricing</Nav.Link>
            <Nav.Link href="/hall">Hall</Nav.Link>
          </Nav>
          <Nav className="ml-auto">
            {NavbarLogin()}
          </Nav>
        </Container>
      </Navbar>
    </>
  );
}


//   return (
//     <>
//     <HashRouter>
//       <Routes>
//           <Route path="/" element={<Home />} />
//           <Route path="/performances" element={<PerformanceScreen />} />

//           <Navbar bg="dark" variant="dark" sticky="top">
//           <Container>
//             <Navbar.Brand href="#home">Teatr Bagatela XD</Navbar.Brand>
//             <Nav className="me-auto">
//               <Link to ="/">Home</Link>
//               <Link to ="/performances">Performance</Link>
//               <Link to ="/">Tak</Link>
//             </Nav>
//           </Container>
//         </Navbar>
//       </Routes>
//     </HashRouter>
     
//     </>
//   );
// }

// const rootElement = document.getElementById("root");
// render(<NavbarFun />, rootElement);
