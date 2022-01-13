import { IonButton, IonCheckbox, IonContent, IonInput, IonItem, IonItemDivider, IonLabel, IonList } from "@ionic/react";
import { useState } from "react";

const checkboxList = [
  { val: 'Biology', isChecked: true },
  { val: 'Chemistry', isChecked: false },
  { val: 'Genetics', isChecked: false }
];

const CreateTransaction = () => {
    const [text, setText] = useState<string>();
    const [checked, setChecked] = useState(false);
    
    return (
        <IonContent>
            <IonItem>
                <IonLabel position="floating">Owner</IonLabel>
                <IonInput value={text}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position="floating">Content</IonLabel>
                <IonInput value={text}></IonInput>
            </IonItem>
            <IonList>
                <IonItemDivider>Categories</IonItemDivider>
                {checkboxList.map(({ val, isChecked }, i) => (
                    <IonItem key={i}>
                        <IonLabel>{val}</IonLabel>
                        <IonCheckbox slot="end" value={val} checked={isChecked} />
                    </IonItem>
                ))};
            </IonList>

            <IonButton>Create Transaction</IonButton>
        </IonContent>
    );
}

export default CreateTransaction;