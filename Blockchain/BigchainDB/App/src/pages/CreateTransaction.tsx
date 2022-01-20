import { IonAlert, IonButton, IonContent, IonInput, IonItem, IonLabel, IonSelect, IonSelectOption, IonTextarea } from "@ionic/react";
import { useState } from "react";
import { Link } from "react-router-dom";
import createAsset from "../bigchaindb/CreateAsset";
import tagList from "../components/CategoryTagList";

var user:any;
var preOwner:any[] = [];
var newOwner:any[] = [];
var tokenNumbers:number[] = [];

const CreateTransaction: React.FC = () => {
    const [isFirstTime, setIsFirstTime] = useState(true);
    const [author, setAuthor] = useState<string>("");
    const [title, setTitle] = useState<String>("");
    const [content, setContent] = useState<string>("");
    const [metaNotes, setMetaNotes] = useState<string>("");
    const [category, setCategory] = useState<string>("");
    const [tokenTotal, setTokenTotal] = useState<number>(20000);
    const [haveBlank, setHaveBlank] = useState(false);
    const [confirmCreate, setConfirmCreate] = useState(false);
    const [creatAssetSuccessfully, setCreatAssetSuccessfully] = useState(false);
    const [needRegister, setNeedRegister] = useState(false);

    const document = {
        author: author,
        title: title,
        content: content,
        category: category,
        createdBy: user
    }

    const metadata = {
        preOwner: preOwner,
        newOwner: newOwner,
        time: new Date(),
        tokenNumber: tokenNumbers,
        notes: metaNotes,
        assetTitle: title
    }

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
            document.createdBy = user;
            preOwner.push(user);
            newOwner.push(user);
            metadata.preOwner = preOwner;
            metadata.newOwner = newOwner;
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
        tokenNumbers.push(tokenTotal);
        metadata.tokenNumber = tokenNumbers;
        console.log(metadata);
        console.log(user.account);
        console.log(document);
        console.log(tokenTotal);
        createAsset(
            user.account, 
            document,
            tokenTotal,
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
    
    isFirstTime && handleGetCurrentUser();
    return (
        <IonContent>
            <IonItem>
                <IonLabel position="fixed">Author</IonLabel>
                <IonInput type="text" required={true} onIonChange={(e) => setAuthor((e.target as HTMLInputElement).value)}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position="fixed">Title</IonLabel>
                <IonInput type="text" required={true} onIonChange={(e) => setTitle((e.target as HTMLInputElement).value)}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position="stacked">Content</IonLabel>
                <IonTextarea placeholder="Enter your research content here..." required={true} onIonChange={(e) => setContent((e.target as HTMLInputElement).value)}></IonTextarea>
            </IonItem>
            <IonItem>
                <IonLabel position="fixed">Shares</IonLabel>
                <IonInput type="number" placeholder="Suggested number of shares: 20000" required={true} onIonChange={(e) => setTokenTotal(parseInt(e.detail.value!))}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position="stacked">Notes</IonLabel>
                <IonTextarea placeholder="Enter additional notes here..." required={true} onIonChange={(e) => setMetaNotes((e.target as HTMLInputElement).value)}></IonTextarea>
            </IonItem>
            <IonItem>
                <IonLabel><Link to={`/categorydefinition`} target="_blank">Category</Link></IonLabel>
                <IonSelect interface="action-sheet" placeholder="Select One" multiple={false} onIonChange={e => setCategory(e.detail.value)}>
                    {tagList.map(({ val }, i) => (
                        <IonSelectOption key={i}>
                            <IonLabel>{val}</IonLabel>
                        </IonSelectOption>
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
            <IonButton shape="round" fill="solid" onClick={handleSubmit}>Create Transaction</IonButton>
        </IonContent>
    );
}

export default CreateTransaction;