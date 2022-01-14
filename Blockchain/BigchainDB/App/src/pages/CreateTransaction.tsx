import { IonAlert, IonButton, IonContent, IonInput, IonItem, IonLabel, IonSelect, IonSelectOption, IonTextarea } from "@ionic/react";
import { useState } from "react";
import createAsset from "../bigchaindb/CreateAsset";

const tagList = [
  { val: 'Biology', isChecked: false },
  { val: 'Chemistry', isChecked: false },
  { val: 'Genetics', isChecked: false }
];

const CreateTransaction = () => {
    const [author, setAuthor] = useState<string>("");
    const [content, setContent] = useState<string>("");
    const [metaNotes, setMetaNotes] = useState<string>("");
    const [category, setCategory] = useState<string[]>([]);
    const [haveBlank, setHaveBlank] = useState(false);
    const [confirmCreate, setConfirmCreate] = useState(false);
    const [creatAssetSuccessfully, setCreatAssetSuccessfully] = useState(false);
    const [needRegister, setNeedRegister] = useState(false);
    var user = {
        name: "",
        email: "",
        institution: [""],
        account: ""
    };

    const document = {
        author: author,
        content: content,
        category: category
    }

    const metadata = {
        owner: user,
        time: new Date(),
        notes: metaNotes
    }

    const handleGetCurrentUser = () => {
        let json = localStorage.getItem("currentuserinfo");
        if (json === null) {
            setNeedRegister(true);
        }
        else {
            setNeedRegister(false);
            user = JSON.parse(json);
            console.log(user);
        }
    }

    const handleSubmit = () => {
        if (document.author === undefined || document.author === "" || document.content === undefined || document.content === "" || document.category.length === 0) {
            setHaveBlank(true);
        }
        else {
            setConfirmCreate(true);
        }
    }

    const handleCreate = async () => {
        handleGetCurrentUser();
        console.log(document);
        console.log(metaNotes);
        createAsset(
            user.account, 
            document,
            metadata
        ).then(() => {
            setCreatAssetSuccessfully(true);
        });
    }

    const handleGoToRegister = (action:boolean) => {
        if (action === true) {
            window.location.href = "/login";
        }
    }

    const handleGoBackHome = (action:boolean) => {
        if (action === true) {
            window.location.href = "/home";
        }
    }
    
    return (
        <IonContent>
            <IonItem>
                <IonLabel position="fixed">Author</IonLabel>
                <IonInput type="text" required={true} onIonChange={(e) => setAuthor((e.target as HTMLInputElement).value)}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position="stacked">Content</IonLabel>
                <IonTextarea placeholder="Enter your research content here..." required={true} onIonChange={(e) => setContent((e.target as HTMLInputElement).value)}></IonTextarea>
            </IonItem>
            <IonItem>
                <IonLabel position="stacked">Notes</IonLabel>
                <IonTextarea placeholder="Enter additional notes here..." required={true} onIonChange={(e) => setMetaNotes((e.target as HTMLInputElement).value)}></IonTextarea>
            </IonItem>
            <IonItem>
                <IonLabel>Category/ies</IonLabel>
                <IonSelect multiple={true} cancelText="Cancel" okText="Okay" onIonChange={e => setCategory(e.detail.value)}>
                    {tagList.map(({ val }, i) => (
                        <IonSelectOption key={i}>{val}</IonSelectOption>
                    ))};
                </IonSelect>
            </IonItem>

            <IonAlert 
                isOpen={needRegister} 
                header={'Unknown User'} 
                message={"Please register first."} 
                buttons={[
                    {
                        text: 'Go To Register',
                        handler: () => handleGoToRegister(true)
                    }]} />
            <IonAlert 
                isOpen={haveBlank} 
                onDidDismiss={() => setHaveBlank(false)} 
                header={'ERROR'} 
                message={"Please fill in all blanks."} 
                buttons={['OK']} />
            <IonAlert 
                isOpen={confirmCreate} 
                onDidDismiss={() => setConfirmCreate(false)} 
                header={"Sure to create this transaction?"} 
                message={"Make sure the content is correct. You can not change nor delete it after creating the transaction."} 
                buttons={[
                    {
                        text: 'Cancel',
                        role: 'cancel',
                        cssClass: 'secondary',
                        handler: () => setConfirmCreate(false)
                    },
                    {
                        text: 'Create',
                        id: 'confirm-button',
                        handler: () => {setConfirmCreate(false); handleCreate();}
                    }]} />
            <IonAlert 
                isOpen={creatAssetSuccessfully} 
                onDidDismiss={() => setCreatAssetSuccessfully(false)} 
                header={"Asset Created Successfully!"} 
                message={"You can now create another asset or go back to home page."} 
                buttons={[
                    {
                        text: 'Home',
                        handler: () => {setCreatAssetSuccessfully(false); handleGoBackHome(true);}
                    },
                    {
                        text: 'Create Another',
                        handler: () => {setCreatAssetSuccessfully(false); handleGoBackHome(false);}
                    }]} />
            <IonButton onClick={handleSubmit}>Create Transaction</IonButton>
        </IonContent>
    );
}

export default CreateTransaction;