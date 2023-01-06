import Button from "@mui/material/Button";

import { useGoogleLogin } from "@react-oauth/google";
import axios from "axios";
import React, { useEffect } from "react";

type Props = {
  name?: string;
  onSuccessCallBack?: () => void;
  onErrorCallBack?: () => void;
};

export default function LoginButton(props: Props) {
    const GoogleLogin = useGoogleLogin({
      flow: "auth-code",
      onSuccess: async (codeResponse) => {
        try {
          // get tokens from backend
          const tokens = await axios.post("http://localhost:8081/auth/login", {
            id: codeResponse.code,
          });

          let userSession = tokens.headers["user_session"];

          console.log(userSession);

          if (userSession === undefined) {
            throw new Error("Loggin failed");
          }
          
          // reset privileges cache
          sessionStorage.setItem("user_session", userSession);

          // set axios default
          axios.defaults.headers.common["user_session"] = userSession;

          whoIm();

          // on success callback
          if (props.onSuccessCallBack !== undefined) {
            props.onSuccessCallBack();
          }
        } catch {
          if (props.onErrorCallBack !== undefined) {
            props.onErrorCallBack();
          } 
        }
      },
      onError: (errorResponse) => {
        console.log(errorResponse);
        
        // on error callback
        if (props.onErrorCallBack !== undefined) {
          props.onErrorCallBack();
        } 
      }
    });
  
  // if name is undefined, then set it to "Login with google"
  if (props.name === undefined || props.name === null) {
    props.name = "Login with google";
  }

  return <Button onClick={GoogleLogin}>{props.name}</Button>;
}

export async function whoIm() {
  // check if user is logged & privileges are in session

  if (sessionStorage.getItem("privileges") !== null) {
    return sessionStorage.getItem("privileges");
  }

  return await axios.get("http://localhost:8081/auth/privileges").then((response) => { 
    var privileges = response.data.replace('"', "");
    
    // cache privileges
    sessionStorage.setItem("privileges", privileges);

    return privileges;
  }).catch((_) => {
    return "GUEST";
  });
}

export async function amIanAdmin() {
  return await whoIm().then((response) => { return response === "ADMIN";  });
}

export async function amIanActor() {
  return await whoIm().then((response) => { return response === "ACTOR";  });
}

export async function amIanGuest() {
  return await whoIm().then((response) => { return response === "GUEST";  });
}

export function DisplIfAdmin(props: { children: React.ReactNode }) {
  const [condition, setCondition] = React.useState(false);

  window.addEventListener("storage", (event) => {
    
    if (event.key === "privileges") {
      event.newValue === "ADMIN" ? setCondition(true) : setCondition(false);
    }
  });

  amIanAdmin().then((response) => {
    setCondition(response);
  });

  return (
    <div>{condition ? props.children : null}</div>
  );
}