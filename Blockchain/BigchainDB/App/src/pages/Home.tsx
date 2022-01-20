import { IonAlert, IonButton, IonButtons, IonContent, IonTitle, IonToolbar } from "@ionic/react";
import { useState } from "react";
import BigchainDBConnection from "../bigchaindb/Connection";
import UserContentAccess from "../components/UserContentAccess";

const Home: React.FC = () => {
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
        BigchainDBConnection.searchAssets(userPublicKey)
            .then(assets => {
                setAssets(assets);
                console.log(assets);
            });
    }

    const handleGoToRegister = (action:boolean) => {
        if (action === true) {
            window.location.href = "/login";
        }
    }

    isFirstTime && handleGetCurrentUser();
    return (
        <IonContent>
            <IonToolbar>
                <IonButtons slot="end">
                    <IonButton onClick={handleGetCurrentUser}>Get Assets</IonButton>
                </IonButtons>
                <IonTitle>Home Page</IonTitle>
            </IonToolbar>
            <IonAlert 
                isOpen={needRegister} 
                header={'User Unknown'} 
                message={"Please register first."} 
                buttons={[
                    {
                        text: 'Go To Register',
                        handler: () => handleGoToRegister(true)
                    }]} />
            <UserContentAccess assets={assets}></UserContentAccess>
        </IonContent>
    );
}

export default Home;