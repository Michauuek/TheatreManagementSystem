import Button from "@mui/material/Button";

import { useGoogleLogin } from "@react-oauth/google";
import axios from "axios";

type Props = {
  name?: string;
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

        // check if user session is undefined or null
        console.log(userSession);
        if (userSession === undefined) {
          throw new Error("Loggin failed");
        }

        // debug only - remove in production
        console.log(userSession);

        // set axios default
        axios.defaults.headers.common["user_session"] = userSession;
      },
      onError: (errorResponse) => console.log(errorResponse),
    });
  
  // if name is undefined, then set it to "Login with google"
  if (props.name === undefined || props.name === null) {
    props.name = "Login with google";
  }

  return <Button onClick={GoogleLogin}>{props.name}</Button>;
}
