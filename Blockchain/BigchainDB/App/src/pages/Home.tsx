import { IonAlert, IonButton, IonButtons, IonContent, IonTitle, IonToolbar } from "@ionic/react";
import { useState } from "react";
import BigchainDBConnection from "../bigchaindb/Connection";
import UserContentAccess from "../components/UserContentAccess";

const Home = () => {
    const [needRegister, setNeedRegister] = useState(false);
    const [assets, setAssets] = useState<any[]>([]);
    var user = {
        name: "",
        email: "",
        institution: [""],
        account: ""
    };

    const handleGetCurrentUser = () => {
        let json = localStorage.getItem("currentuserinfo");
        if (json === null) {
            setNeedRegister(true);
        }
        else {
            setNeedRegister(false);
            user = JSON.parse(json);
            console.log(user);
            searchUserAssets(user.name);
        }
    }

    const searchUserAssets = async (name:string) => {
        BigchainDBConnection.searchAssets(name)
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