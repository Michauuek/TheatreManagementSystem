import Button from "@mui/material/Button";

import { useGoogleLogin } from "@react-oauth/google";
import axios from "axios";

type Props = {
  name?: string;
  onSuccessCallBack?: () => void;
  onErrorCallBack?: () => void;
};

export default function LoginButton(props: Props) {
    const GoogleLogin = useGoogleLogin({
      flow: "auth-code",
      onSuccess: async (codeResponse) => {
        // get tokens from backend
        const tokens = await axios.post("http://localhost:8081/auth/login", {
          id: codeResponse.code,
        });

        let userSession = tokens.headers["user_session"];

        if (userSession === undefined) {
          throw new Error("Loggin failed");
        }

        // set axios default
        axios.defaults.headers.common["user_session"] = userSession;

        // on success callback
        if (props.onSuccessCallBack !== undefined) {
          props.onSuccessCallBack();
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
