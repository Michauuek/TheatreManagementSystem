import "./App.css";
import Home from "./components/Home/Home";
import { GoogleOAuthProvider } from "@react-oauth/google";
import { BrowserRouter, Route, Routes, Navigate } from "react-router-dom";
import SeancesScreen from "./components/seances/SeancesScreen";
import PerformanceScreen from "./components/performances/PerformanceScreen";
import { HallDisplayScreen } from "./components/halls/HallDisplayScreen";
import AdminPanelScreen from "./components/admin/AdminPanelScreen";
import axios from "axios";
import PerformanceAdminList from "./components/admin/PerformanceAdminList";
import SeanceAdminList from "./components/admin/SeanceAdminList";
import { PrivilegesContextProvider } from "./components/common/PrivilegesContext";


const clientId =
  "684105178392-12tts41fh93lbeo01u9hlji59i2ihor5.apps.googleusercontent.com";

if (localStorage.getItem("user_session")) {
  axios.defaults.headers.common["user_session"] = localStorage.getItem(
    "user_session"
  );
}


function App() {
  const now = new Date();
  const today_path: string = `/seances/${now.toISOString().split("T")[0]}`;

  return (
    <PrivilegesContextProvider>
      <GoogleOAuthProvider clientId={clientId}>
        <BrowserRouter>
          <Routes>
            <Route path="/" element={<Navigate to={today_path} />}></Route>
            <Route path="/seances/:date" element={<SeancesScreen />}></Route>
            <Route path="/hall" element={<Home />}></Route>
            <Route path="/performance/:id" element={<PerformanceScreen />}></Route>
            <Route path="/reserve/:id" element={<HallDisplayScreen />}></Route>
            <Route path="/admin" element={<AdminPanelScreen />}></Route>
            <Route path="/admin/performances" element={<PerformanceAdminList />}></Route>
            <Route path="/admin/performance/:id" element={<SeanceAdminList />}></Route>
          </Routes>
        </BrowserRouter>
      </GoogleOAuthProvider>
    </PrivilegesContextProvider>
  );
}

export default App;
