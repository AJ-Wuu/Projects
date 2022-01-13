import { IonAlert, IonButton, IonContent, IonHeader, IonInput, IonItem, IonLabel, IonSelect, IonSelectOption, IonTitle, IonToolbar } from "@ionic/react";
import { useState } from "react";
import * as BigchainDB from 'bigchaindb-driver';
import createAsset from "../bigchaindb/transaction";

const institutionList = [
    { val: 'University1' },
    { val: 'University2' },
    { val: 'University3' }
];

const LogIn = () => {
    const [name, setName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [institution, setInstitution] = useState<string[]>([]);
    const [haveBlank, setHaveBlank] = useState(false);
    const [confirmRegister, setConfirmRegister] = useState(false);

    const user = {
        name: name,
        email: email,
        institution: institution,
        account: new BigchainDB.Ed25519Keypair()
    };

    const handleSubmit = () => {
        if (user.name === undefined || user.name === "" || user.email === undefined || user.email === "" || user.institution.length === 0) {
            setHaveBlank(true);
        }
        else {
            setConfirmRegister(true);
        }
    }

    const handleRegister = () => {
        console.log("Register");
        createAsset(
            user.account, 
            user, 
            {
                Title: "whoami",
                Author: user.name,
                Email: user.email
            }
        );
    }

    return (
        <>
        <IonHeader>
            <IonToolbar>
                <IonTitle>
                    Register Your Device
                </IonTitle>
            </IonToolbar>    
        </IonHeader>
        <IonContent>
            <IonItem>
                <IonLabel position="fixed">Name</IonLabel>
                <IonInput type="text" required={true} onIonChange={(e) => setName((e.target as HTMLInputElement).value)}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position="fixed">Email</IonLabel>
                <IonInput type="email" required={true} onIonChange={(e) => setEmail((e.target as HTMLInputElement).value)}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel>Institution(s)</IonLabel>
                <IonSelect multiple={true} cancelText="Cancel" okText="Okay" onIonChange={e => setInstitution(e.detail.value)}>
                    {institutionList.map(({ val }) => (
                        <IonSelectOption value={val}>{val}</IonSelectOption>
                    ))};
                </IonSelect>
            </IonItem>

            <IonAlert 
                isOpen={haveBlank} 
                onDidDismiss={() => setHaveBlank(false)} 
                header={'ERROR'} 
                message={"Please fill in all blanks."} 
                buttons={['OK']} />
            <IonAlert 
                isOpen={confirmRegister} 
                onDidDismiss={() => setConfirmRegister(false)} 
                header={"Sure to proceed?"} 
                message={"Your information cannot be changed after registration. Your device will only be able to access this account."} 
                buttons={[
                    {
                        text: 'Cancel',
                        role: 'cancel',
                        cssClass: 'secondary',
                        handler: () => setConfirmRegister(false)
                    },
                    {
                        text: 'Proceed',
                        id: 'confirm-button',
                        handler: () => {setConfirmRegister(false); handleRegister();}
                    }]} />
            <IonButton onClick={handleSubmit}>Register</IonButton>
        </IonContent>
        </>
    );
}

export default LogIn;