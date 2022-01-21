import { IonItem, IonLabel, IonList, IonText } from "@ionic/react";

const AssetDetailAccess = (props: { assets: any[]; }) => {
    return (
        <div>
            {props.assets.map((asset) => (
                <IonList className="asset-preview" key={asset.id}>
                    <IonItem><h2>Asset:</h2></IonItem>
                    <IonItem>
                        <IonLabel position="fixed">Title</IonLabel>
                        <IonText><p>{asset.data.token.title}</p></IonText>
                    </IonItem>
                    <IonItem>
                        <IonLabel position="fixed">Author</IonLabel>
                        <IonText><p>{asset.data.token.author}</p></IonText>
                    </IonItem>
                    <IonItem>
                        <IonLabel position="fixed">SharesLeft</IonLabel>
                        <IonText><p>{asset.data.tokenNumbers}</p></IonText>
                    </IonItem>
                    <IonItem>
                        <IonLabel position="fixed">Category</IonLabel>
                        <IonText><p>{asset.data.token.category}</p></IonText>
                    </IonItem>
                    <IonItem>
                        <IonLabel position="fixed">Content</IonLabel>
                        <IonText><p>{asset.data.token.content}</p></IonText>
                    </IonItem>
                </IonList>
            ))}
        </div>
    );
}

export default AssetDetailAccess;