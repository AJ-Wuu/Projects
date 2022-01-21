import { IonAlert, IonButton, IonButtons, IonContent, IonHeader, IonTitle, IonToolbar } from "@ionic/react";
import { useState } from "react";
import BigchainDBConnection from "../bigchaindb/Connection";
import UserAllAssetsAccess from "../components/UserAllAssetsAccess";

const MyAssets: React.FC = () => {
    const [isFirstTime, setIsFirstTime] = useState(true);
    const [needRegister, setNeedRegister] = useState(false);
    const [assets, setAssets] = useState<any[]>([]);
    var user = {
        name: "",
        email: "",
        institution: [""],
        account: {
            publicKey: "",
            privateKey: ""
        }
    };

    const handleGetCurrentUser = () => {
        let json = localStorage.getItem("currentuserinfo");
        if (json === null) {
            setIsFirstTime(false);
            setNeedRegister(true);
        }
        else {
            setIsFirstTime(false);
            setNeedRegister(false);
            user = JSON.parse(json);
            console.log(user);
            handleSearchUserAssets(user.account?.publicKey);
        }
    }

    const handleSearchUserAssets = async (userPublicKey:string) => {
        await BigchainDBConnection.searchAssets(userPublicKey)
            .then(assets => {
                setAssets(assets);
                console.log(assets);
                var val = BigchainDBConnection.listTransactions(assets[0].id);
                console.log(val);
            });
    }

    const handleGoToRegister = (action:boolean) => {
        if (action === true) {
            window.location.href = "/login";
        }
    }

    isFirstTime && handleGetCurrentUser();
    return (
        <>
        <IonHeader>
            <IonToolbar>
                <IonButtons slot="end">
                    <IonButton onClick={handleGetCurrentUser}>Get Assets</IonButton>
                </IonButtons>
                <IonTitle>My Assets</IonTitle>
            </IonToolbar>
        </IonHeader>
        <IonContent>
            <IonAlert 
                isOpen={needRegister} 
                header={'User Unknown'} 
                message={"Please register first."} 
                buttons={[
                    {
                        text: 'Go To Register',
                        handler: () => handleGoToRegister(true)
                    }]} />
            <UserAllAssetsAccess assets={assets}></UserAllAssetsAccess>
        </IonContent>
        </>
    );
}

export default MyAssets;