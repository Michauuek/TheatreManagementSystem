import Button from "@mui/material/Button";

import { useGoogleLogin } from "@react-oauth/google";
import axios from "axios";
import React, { useCallback, useEffect } from "react";

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
        window.sessionStorage.setItem("user_session", userSession);

        // set axios default
        axios.defaults.headers.common["user_session"] = userSession;

        await whoIm(true);

        // on success callback
        if (props.onSuccessCallBack !== undefined) {
          props.onSuccessCallBack();
        }
      } catch {
        await whoIm(true);

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

  return <Button onClick={GoogleLogin}>{props.name}</Button>;
}

export async function whoIm(forceUpdate=false) {
  // check if user is logged & privileges are in session
  if (!forceUpdate) {
    if (window.sessionStorage.getItem("privileges") !== null && window.sessionStorage.getItem("privileges") !== "NONE") {
      return window.sessionStorage.getItem("privileges");
    }
  }

  return await axios
    .get("http://localhost:8081/auth/privileges", {
      withCredentials: true,
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => {
      var privileges = response.data.replace('"', "");

      console.log(privileges);

      // cache privileges
      window.sessionStorage.setItem("privileges", privileges);

      return privileges;
    })
    .catch((_) => {
      window.sessionStorage.setItem("privileges", "NONE");

      return "NONE";
    });
}

export async function amIanAdmin() {
  return await whoIm().then((response) => {
    return response === "ADMIN";
  });
}

export async function amIanActor() {
  return await whoIm().then((response) => {
    return response === "ACTOR";
  });
}

export async function amIanGuest() {
  return await whoIm().then((response) => {
    return response === "GUEST";
  });
}

export function DisplIfAdmin(props: { children: React.ReactNode }) {
  const [condition, setCondition] = React.useState(false);

  const onStorageChange = (event: StorageEvent) => {
    if (event.key === "privileges") {
      event.newValue === "ADMIN" ? setCondition(true) : setCondition(false);
    }
  };

  useEffect(() => {
    window.addEventListener("storage", onStorageChange);

    amIanAdmin().then((response) => {
      setCondition(response);
    });

    return () => {
      window.removeEventListener("storage", onStorageChange);
    };
  }, [condition]);

  return <div>{condition ? props.children : null}</div>;
}
