import { IonItem, IonLabel } from "@ionic/react";
import { useState } from "react";
import { Link } from "react-router-dom";

type Publication = {
    Title: string,
    Author: string,
    Content: string,
    UserID: number,
    PublicationID: number
}

const UserContentAccess = () => {
    const props = ([
        { Title: 'bio1 this should be a long name to test how it would be displayed and it is still not long enough', Author: 'A', Content: 'contents of bio1', UserID: 1, PublicationID: 11 },
        { Title: 'bio2', Author: 'B', Content: 'contents of bio2', UserID: 2, PublicationID: 22 },
        { Title: 'bio3', Author: 'C', Content: 'contents of bio3', UserID: 3, PublicationID: 33 }
    ]);

    const Author = props[0].Author;

    return (
        <div className="publication-list">
            <h2>{Author}</h2>
            {props.map((publication) => (
                <IonItem className="publication-preview" key={publication.PublicationID}>
                    <IonLabel>
                        <Link to={`/publications/${publication.PublicationID}`}>{publication.Title}</Link>
                    </IonLabel>
                    <IonLabel>DOI: {publication.PublicationID}</IonLabel>
                </IonItem>
            ))}
        </div>
    );
}

export default UserContentAccess;