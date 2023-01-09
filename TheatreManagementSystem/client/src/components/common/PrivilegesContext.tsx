import { createContext, useContext, useState } from "react";


export const PrivilegesContext = createContext<[string, React.Dispatch<React.SetStateAction<string>>]|null>(null);


export function PrivilegesContextProvider(props: {children: any}) {
    const [userPrivileges, setUserPrivileges] = useState<string>(sessionStorage.getItem("privileges") || "NONE");

    return (
        <PrivilegesContext.Provider value={[userPrivileges, setUserPrivileges]}>
            {props.children}
        </PrivilegesContext.Provider>
    );
}


