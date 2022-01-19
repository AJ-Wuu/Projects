import { IonItem, IonLabel, IonList, IonTextarea } from "@ionic/react";

const AssetDetailAccess = (props: { assets: any[]; }) => {
    return (
        <div>
            {props.assets.map((asset) => (
                <IonList className="asset-preview" key={asset.id}>
                    <IonItem><IonLabel>Title: {asset.data.token.title}</IonLabel></IonItem>
                    <IonItem><IonLabel>Author: {asset.data.token.author}</IonLabel></IonItem>
                    <IonItem><IonLabel>SharesLeft: {asset.data.tokenNumbers}</IonLabel></IonItem>
                    <IonItem><IonLabel>Category: {asset.data.token.category}</IonLabel></IonItem>
                    <IonItem>
                        <IonLabel position="stacked">Content</IonLabel>
                        <IonTextarea
                            readonly
                            value={asset.data.token.content}>
                        </IonTextarea>
                    </IonItem>
                </IonList>
            ))}
        </div>
    );
}

export default AssetDetailAccess;