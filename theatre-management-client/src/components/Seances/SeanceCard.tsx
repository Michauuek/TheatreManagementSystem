import * as React from 'react';
import { useParams } from 'react-router';


type CardProps = {
    title: string
}
export const SeanceCard: React.FunctionComponent<CardProps> = ({title}) => 
<aside>
    <h2>{ title }</h2>
    <p>
        { "Siema to jest film" }
    </p>
</aside>



