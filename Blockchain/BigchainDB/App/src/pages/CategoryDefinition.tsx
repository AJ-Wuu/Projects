import { IonContent, IonHeader, IonItem, IonLabel, IonText, IonTitle, IonToolbar } from "@ionic/react";
import tagList from "../components/CategoryTagList";

const CategoryDefinition: React.FC = () => {
    return (
        <>
        <IonHeader>
            <IonToolbar>
                <IonTitle>Category Definition</IonTitle>
            </IonToolbar>
        </IonHeader>
        <IonContent>
            {tagList.map(({ val, definition }, i) => (
                <IonItem key={i}>
                    <IonLabel position="fixed">{val}</IonLabel>
                    <IonText><p>{definition}</p></IonText>
                </IonItem>
            ))};
        </IonContent>
        </>
    );
}

export default CategoryDefinition;