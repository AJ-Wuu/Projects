import { IonButton, IonButtons, IonContent, IonTitle, IonToolbar } from "@ionic/react";

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
        </IonContent>
    );
}

export default Home;