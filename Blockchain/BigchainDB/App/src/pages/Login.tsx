import { IonCol, IonContent, IonGrid, IonHeader, IonIcon, IonInput, IonItem, IonLabel, IonRow, IonTitle, IonToolbar } from "@ionic/react";
import { useState } from "react";

const Login = () => {
    const [text, setText] = useState<string>();

    return (
        <>
        <IonHeader>
            <IonToolbar>
                <IonTitle>
                    Log In
                </IonTitle>
            </IonToolbar>    
        </IonHeader>
        <IonContent>
            <IonItem>
                <IonLabel position="fixed">UserName:</IonLabel>
                <IonInput value={text}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position="fixed">Password:</IonLabel>
                <IonInput value={text}></IonInput>
            </IonItem>
        </IonContent>
        </>
    );
}

export default Login;