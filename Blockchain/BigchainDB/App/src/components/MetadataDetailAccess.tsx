import { IonItem, IonLabel, IonList, IonText } from "@ionic/react";

const metaDetailAccess = (props: { metadata: any[]; }) => {
    return (
        <div>
            {props.metadata.map((meta) => (
                <IonList className="meta-preview" key={meta.id}>
                    <IonItem><h2>Metadata:</h2></IonItem>
                    <IonItem>
                        <IonLabel position="fixed">From</IonLabel>
                        <IonText>
                            <p>{meta.metadata.preOwner[meta.metadata.preOwner.length-1].name}</p>
                            <p>{meta.metadata.preOwner[meta.metadata.preOwner.length-1].email}</p>
                        </IonText>
                    </IonItem>
                    <IonItem>
                        <IonLabel position="fixed">To</IonLabel>
                        <IonText>
                            <p>{meta.metadata.newOwner[meta.metadata.newOwner.length-1].name}</p>
                            <p>{meta.metadata.newOwner[meta.metadata.newOwner.length-1].email}</p>
                        </IonText>
                    </IonItem>
                    <IonItem>
                        <IonLabel position="fixed">Date</IonLabel>
                        <IonText><p>{(meta.metadata.time as string).substring(0,10)}</p></IonText>
                    </IonItem>
                    <IonItem>
                        <IonLabel position="fixed">Shares</IonLabel>
                        <IonText><p>{meta.metadata.tokenNumber[meta.metadata.tokenNumber.length-1]}</p></IonText>
                    </IonItem>
                    <IonItem>
                        <IonLabel position="fixed">Notes</IonLabel>
                        <IonText><p>{meta.metadata.notes}</p></IonText>
                    </IonItem>
                </IonList>
            ))}
        </div>
    );
}

export default metaDetailAccess;