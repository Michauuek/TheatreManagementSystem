
import React from 'react';
import './App.css';
import Home from './components/Home/Home';
import { GoogleOAuthProvider } from '@react-oauth/google';
import PerformanceScreen from './components/performance/PerformanceScreen';
import { BrowserRouter, Route, Link, Routes } from "react-router-dom";

const clientId = "684105178392-12tts41fh93lbeo01u9hlji59i2ihor5.apps.googleusercontent.com";

function App() {
  return (

    <GoogleOAuthProvider clientId={clientId}>
    <BrowserRouter>
    <Routes>
      <Route path="/" element={<PerformanceScreen/>}></Route>
      <Route path="/hall" element={<Home/>}></Route>
    </Routes>
    
    </BrowserRouter>
    </GoogleOAuthProvider>
    
  );
}

export default App;
