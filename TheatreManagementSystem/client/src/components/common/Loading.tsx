import { CircularProgress } from "@mui/material"



export type Props = {
    // Component if loading 
    isLoading: boolean;
    // Component to display if not loading
    children: React.ReactNode;
}


export const LoadingSpinner = (props: Props) => {
    // spinner using mui
    return (
        <div>
            {props.isLoading ? <CircularProgress className="CircularProgress" /> : props.children}
        </div>
    )
}