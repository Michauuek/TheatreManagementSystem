import { useState, useEffect } from "react";

import  {Button} from "../../argon-react-native-master/components" 

type seanceProps = {
  id: number,
  HallId: string;
  PerformanceID: number;
  seanceDate: string;
  seanceTime: string;
};

export default function Home() {
  const [result, setResult] = useState<seanceProps[]>([]);
  useEffect(() => {
    const api = async () => {
      const data = await fetch("http://127.0.0.1:8080/seance/all", {
        method: "GET"
      });
      const jsonData = await data.json();
      setResult(jsonData);
    };
    console.log(result)
    api();
  }, []);

  return (
    <div className="App">
      <h1>
      <div>Hello World</div>
      <Button>Hello Argon Button</Button>
        {result?.map((value) => {
          return (
            <div>
                <h1>Siema</h1>
              <div>{value.HallId}</div>
            </div>
          );
        })}
      </h1>
      <h2>Start editing to see some magic dsadsahappen!</h2>
    </div>
  );
}
