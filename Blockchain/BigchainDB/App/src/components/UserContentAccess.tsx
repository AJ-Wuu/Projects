import { IonItem, IonLabel } from "@ionic/react";
import { Link } from "react-router-dom";

const UserContentAccess = (props: { assets: any[]; }) => {
    return (
        <div>
            {props.assets.map((asset) => (
                <IonItem className="asset-preview" key={asset.id}>
                    <IonLabel>Title: <Link to={`/assets/${asset.id}`}>{asset.data.token.title}</Link></IonLabel>
                    <IonLabel>Author: {asset.data.token.author}</IonLabel>
                    <IonLabel>Category: {asset.data.token.category}</IonLabel>
                </IonItem>
            ))}
        </div>
    );
}

export default UserContentAccess;