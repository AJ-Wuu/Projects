import { IonHeader, IonToolbar, IonButton, IonTitle, IonContent, IonAlert, IonItem, IonLabel } from "@ionic/react";
import { useState } from "react";
var user:any;

const Home: React.FC = () => {
    const [isFirstTime, setIsFirstTime] = useState(true);
    const [needRegister, setNeedRegister] = useState(false);
    
    const handleCheckMyAssets = () => {
        window.location.href = "/myassets";
    }

    const handleCheckMyTransactions = () => {
        window.location.href = "/mytransactions";
    }

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
        }
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
                <IonTitle>Home Page</IonTitle>
            </IonToolbar>
        </IonHeader>
        <IonContent>
            <IonItem>
                <IonLabel>Your public key is {user.account.publicKey}</IonLabel>
            </IonItem>
            <IonButton onClick={handleCheckMyAssets}>Check My Assets</IonButton>
            <IonButton onClick={handleCheckMyTransactions}>Check My Transactions</IonButton>
            <IonAlert 
                isOpen={needRegister} 
                header={'User Unknown'}
                message={"Please register first."} 
                buttons={[
                    {
                        text: 'Go To Register',
                        handler: () => handleGoToRegister(true)
                    }]} />
        </IonContent>
        </>
    );
}

export default Home;