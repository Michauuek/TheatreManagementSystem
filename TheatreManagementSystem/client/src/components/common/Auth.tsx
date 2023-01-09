import { useGoogleLogin } from "@react-oauth/google";
import axios from "axios";
import React, { useCallback, useContext, useEffect } from "react";
import { Button } from "react-bootstrap";
import { PrivilegesContext } from "./PrivilegesContext";

type Props = {
  name?: string;
  onSuccessCallBack?: () => void;
  onErrorCallBack?: () => void;
  variant?: string;
};

export default function LoginButton(props: Props) {
  // get context
  const [p, setUserPrivileges] = useContext(PrivilegesContext)!!;

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
        window.sessionStorage.setItem("user_session", userSession);

        // set axios default
        axios.defaults.headers.common["user_session"] = userSession;

        await UpdateWhoIm(p, setUserPrivileges);

        // on success callback
        if (props.onSuccessCallBack !== undefined) {
          props.onSuccessCallBack();
        }
      } catch {
        await UpdateWhoIm(p, setUserPrivileges);

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
    },
  });

  // if name is undefined, then set it to "Login with google"
  if (props.name === undefined || props.name === null) {
    props.name = "Login with google";
  }

  return <Button onClick={GoogleLogin} variant={props.variant}>{props.name}</Button>;
}

export async function UpdateWhoIm(privileges: string, setPrivileges: React.Dispatch<React.SetStateAction<string>>) {

  return await axios
    .get("http://localhost:8081/auth/privileges", {
      withCredentials: true,
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      var p = response.data.replace('"', "");

      if(p != privileges) {
        // cache privileges
        window.sessionStorage.setItem("privileges", p);
        
        // set context
        setPrivileges(p)
      }
    })
    .catch((_) => {
      window.sessionStorage.setItem("privileges", "NONE");
      setPrivileges("NONE")
    });
}

export function DisplIfAdmin(props: {children: any}) {
  const [userPrivileges, _] = useContext(PrivilegesContext)!!;

  if (userPrivileges === "ADMIN") {
      return props.children;
  }
  return null;
}

export function DisplIfActor(props: {children: any}) {
  const [userPrivileges, _] = useContext(PrivilegesContext)!!;

  if (userPrivileges === "ACTOR") {
      return props.children;
  }
  return null;
}