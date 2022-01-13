import { IonIcon, IonLabel, IonTabBar, IonTabButton } from "@ionic/react";
import { home, create, train } from 'ionicons/icons';

const NavBar = () => {
    return (
        <IonTabBar slot="bottom">
            <IonTabButton tab="home" href="/" selected={false}>
                <IonIcon icon={home} />
                <IonLabel>Home</IonLabel>
            </IonTabButton>
            <IonTabButton tab="create" href="/createtransaction">
                <IonIcon icon={create} />
                <IonLabel>Create</IonLabel>
            </IonTabButton>
            <IonTabButton tab="transfer" href="/transfertransaction">
                <IonIcon icon={train} />
                <IonLabel>Transfer</IonLabel>
            </IonTabButton>
        </IonTabBar>
    );
}

export default NavBar;