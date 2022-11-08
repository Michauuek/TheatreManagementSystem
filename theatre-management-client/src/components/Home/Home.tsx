import { useState, useEffect } from "react";



type seanceProps = {
  id: number;
  title: string;
  genre: string;
  director: string;
  duration: number
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
      <div>Hello World 2</div>
        {result?.map((value) => {
          return (
            <div>
                <h1>Siema</h1>
              <div>{value.title}</div>
            </div>
          );
        })}
      </h1>
      <h2>Start editing to see some magic dsadsahappen!</h2>
    </div>
  );
}
