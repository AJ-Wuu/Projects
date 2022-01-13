import { IonButton, IonButtons, IonContent, IonTitle, IonToolbar } from "@ionic/react";
import UserContentAccess from "../components/UserContentAccess";
import UserHomePage from "./UserHomePage";

const Home = () => {
    const handleLogInButton = () => {
        window.location.href = "/generallogin";
    }

    return (
        <IonContent>
            <IonToolbar>
                <IonButtons slot="end">
                    <IonButton onClick={handleLogInButton}>Log In</IonButton>
                </IonButtons>
                <IonTitle>Home Page</IonTitle>
            </IonToolbar>
            <UserHomePage></UserHomePage>
        </IonContent>
    );
}

export default Home;