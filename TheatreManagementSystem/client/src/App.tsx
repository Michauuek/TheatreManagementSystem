
import React from 'react';
import './App.css';
import Home from './components/Home/Home';
import { GoogleOAuthProvider } from '@react-oauth/google';


const clientId = "684105178392-12tts41fh93lbeo01u9hlji59i2ihor5.apps.googleusercontent.com";

function App() {
  return (
    <GoogleOAuthProvider clientId={clientId}><Home/></GoogleOAuthProvider>
  );
}

export default App;
