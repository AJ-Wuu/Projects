import { IonAlert, IonButton, IonContent, IonHeader, IonInput, IonItem, IonLabel, IonSelect, IonSelectOption, IonTitle, IonToolbar } from "@ionic/react";
import { useState } from "react";
import * as BigchainDB from 'bigchaindb-driver';
import Create from "../bigchaindb/Create";
import { setCurrentUserInfo } from "../bigchaindb/CurrentUserInfo";

const institutionList = [
    { val: 'University1', id: 1 },
    { val: 'University2', id: 2 },
    { val: 'University3', id: 3 }
];

const LogIn: React.FC = () => {
    const [needRegister, setNeedRegister] = useState(true);
    const [name, setName] = useState<string>("");
    const [email, setEmail] = useState<string>("");
    const [institution, setInstitution] = useState<string[]>([]);
    const [haveBlank, setHaveBlank] = useState(false);
    const [confirmRegister, setConfirmRegister] = useState(false);
    const [confirmDelete, setConfirmDelete] = useState(false);
    const [creatAssetSuccessfully, setCreatAssetSuccessfully] = useState(false);

    const user = {
        name: name,
        email: email,
        institution: institution,
        account: new BigchainDB.Ed25519Keypair()
    };

    const handleNeedRegister = () => {
        if (localStorage.getItem("currentuserinfo") === null) {
            setNeedRegister(true);
        }
        else {
            setNeedRegister(false);
        }
    }

    const handleGoBackHome = () => {
        window.location.href = "/home";
    }

    const handleDeleteCurrentUser = () => {
        localStorage.removeItem("currentuserinfo");
    }

    const handleSubmit = () => {
        if (user.name === undefined || user.name === "" || user.email === undefined || user.email === "" || user.institution.length === 0) {
            setHaveBlank(true);
        }
        else {
            setConfirmRegister(true);
        }
    }

    const handleLocalRegister = () => {
        setCurrentUserInfo(user.name, user.email, user.institution, user.account);
    }

    const handleRegister = async () => {
        Create(
            user.account, 
            user, 
            0,
            {
                Title: "whoami",
                Author: user.name,
                Email: user.email
            }
        ).then(() => {
            handleLocalRegister();
            setCreatAssetSuccessfully(true);
        });
    }

    const handleGoToCreateTransaction = () => {
        window.location.href = "/createtransaction";
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
                    {institutionList.map(({ val }, i) => (
                        <IonSelectOption key={i}>{val}</IonSelectOption>
                    ))};
                </IonSelect>
            </IonItem>

            <IonAlert 
                isOpen={true} 
                header={"Check if registered"} 
                buttons={[
                    {
                        text: 'check now',
                        handler: () => {handleNeedRegister()}
                    }]} />
            <IonAlert 
                isOpen={!needRegister} 
                header={"Already Registered"} 
                buttons={[
                    {
                        text: 'Delete Current User',
                        handler: () => setConfirmDelete(true)
                    },
                    {
                        text: 'Home',
                        handler: () => {handleGoBackHome()}
                    }]} />
            <IonAlert 
                isOpen={confirmDelete} 
                onDidDismiss={() => setConfirmDelete(false)} 
                header={"Sure to delete?"} 
                message={"If proceed, this account is lost and there is no way to get it back."} 
                buttons={[
                    {
                        text: 'Cancel',
                        handler: () => {setConfirmDelete(false); handleGoBackHome();}
                    },
                    {
                        text: 'Proceed',
                        id: 'confirm-button',
                        handler: () => {setConfirmDelete(false); handleDeleteCurrentUser();}
                    }]} />
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
            <IonAlert 
                isOpen={creatAssetSuccessfully} 
                onDidDismiss={() => setCreatAssetSuccessfully(false)} 
                header={"Asset Created Successfully!"} 
                message={"This app will automatically restart itself to offer you the best experience."} 
                buttons={[
                    {
                        text: 'Continue',
                        handler: () => {setCreatAssetSuccessfully(false); handleGoToCreateTransaction();}
                    }]} />
            <IonButton shape="round" fill="solid" onClick={handleSubmit}>Register</IonButton>
        </IonContent>
        </>
    );
}

export default LogIn;
