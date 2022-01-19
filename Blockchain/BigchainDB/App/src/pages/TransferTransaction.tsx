import { IonAlert, IonButton, IonContent, IonInput, IonItem, IonLabel, IonSelect, IonSelectOption, IonTextarea } from "@ionic/react";
import { useState } from "react";
import BigchainDBConnection from "../bigchaindb/Connection";
import TransferAsset from "../bigchaindb/TransferAsset";
import TransferAsset2 from "../bigchaindb/TransferAsset2";

var chosenTxID:string = "";
var chosenTransaction:any = null;

const TransferTransaction = () => {
    const [assets, setAssets] = useState<any[]>([]);
    const [bobPublicKey, setBobPublicKey] = useState<string>("");
    const [bobValid, setBobValid] = useState(true);
    const [chooseAssetTitle, setChooseAssetTitle] = useState<string>("");
    const [numberToShare, setNumberToShare] = useState<number>(1);
    const [metaNotes, setMetaNotes] = useState<string>("");
    const [haveBlank, setHaveBlank] = useState(false);
    const [confirmTransfer, setConfirmTransfer] = useState(false);
    const [creatAssetSuccessfully, setCreatAssetSuccessfully] = useState(false);
    const [needRegister, setNeedRegister] = useState(false);
    
    var user = {
        name: "",
        email: "",
        institution: [""],
        account: ""
    };

    const metadata = {
        prevOwner: user,
        newOwner: bobPublicKey,
        time: new Date(),
        notes: metaNotes
    }

    const searchUserAssets = (name:string) => {
        BigchainDBConnection.searchAssets(name)
            .then(assets => {
                setAssets(assets);
            });
    }

    const getTxIDByAssetTitle = (title:string) => {
        BigchainDBConnection.searchAssets(title)
            .then(assets => {
                chosenTxID = assets[0].id;
                console.log(chosenTxID);
                chosenTransaction = assets[0];
                console.log(chosenTransaction);
            });
    }

    const getChooseAsset = (title:string) => {
        setChooseAssetTitle(title);
        getTxIDByAssetTitle(title);
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
            searchUserAssets(user.name);
            metadata.prevOwner = user;
        }
    }

    const handleSubmit = async () => {
        if (chooseAssetTitle === undefined || chooseAssetTitle === "" || numberToShare === 0) {
            setHaveBlank(true);
        }
        else if (bobPublicKey === undefined || bobPublicKey === "") {
            setBobValid(false);
        }
        else {
            setConfirmTransfer(true);
        }
    }

    const handleTransfer = () => {
        handleGetCurrentUser();
        setTimeout(() => {
            console.log(chosenTxID);
            console.log(chosenTransaction);
            TransferAsset2(
                user.account,
                bobPublicKey,
                chosenTransaction,
                numberToShare,
                metadata
            ).then(res => {
                setCreatAssetSuccessfully(true);
            });
            /*
            TransferAsset(
                user.account,
                bobPublicKey,
                chosenTxID,
                numberToShare,
                metadata
            ).then(() => {
                setCreatAssetSuccessfully(true);
            });
            */
        }, 2000);
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
                <IonLabel position="fixed">Share to</IonLabel>
                <IonInput type="text" placeholder="Enter their public key" required={true} onIonChange={(e) => setBobPublicKey((e.target as HTMLInputElement).value)}></IonInput>
            </IonItem>
            <IonItem>
                <IonButton onClick={handleGetCurrentUser}>Asset</IonButton>
                <IonSelect interface="action-sheet" placeholder="Select One" multiple={false} onIonChange={e => getChooseAsset(e.detail.value)}>
                    {assets.map((asset, i) => (
                        <IonSelectOption key={i}>
                            <IonLabel>{asset.data.token.title}</IonLabel>
                        </IonSelectOption>
                    ))};
                </IonSelect>
            </IonItem>
            <IonItem>
                <IonLabel position="fixed">Shares</IonLabel>
                <IonInput type="number" placeholder="Suggest amount to share: 1" required={true} onIonChange={(e) => setNumberToShare(parseInt(e.detail.value!))}></IonInput>
            </IonItem>
            <IonItem>
                <IonLabel position="stacked">Notes</IonLabel>
                <IonTextarea placeholder="Enter additional notes here..." required={true} onIonChange={(e) => setMetaNotes((e.target as HTMLInputElement).value)}></IonTextarea>
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
                isOpen={!bobValid} 
                header={'Invalid Public Key'} 
                message={"This public key doesn't exist."} 
                buttons={[
                    {
                        text: 'Retype'
                    }]} />
            <IonAlert 
                isOpen={haveBlank} 
                onDidDismiss={() => setHaveBlank(false)} 
                header={'ERROR'} 
                message={"Please fill in all blanks."} 
                buttons={['OK']} />
            <IonAlert 
                isOpen={confirmTransfer} 
                onDidDismiss={() => setConfirmTransfer(false)} 
                header={"Sure to Transfer this transaction?"} 
                message={"Make sure the content is correct. You can not change nor delete it after creating the transaction."} 
                buttons={[
                    {
                        text: 'Cancel',
                        role: 'cancel',
                        cssClass: 'secondary',
                        handler: () => setConfirmTransfer(false)
                    },
                    {
                        text: 'Transfer',
                        id: 'confirm-button',
                        handler: () => {setConfirmTransfer(false); handleTransfer();}
                    }]} />
            <IonAlert 
                isOpen={creatAssetSuccessfully} 
                onDidDismiss={() => setCreatAssetSuccessfully(false)} 
                header={"Asset Transferd Successfully!"} 
                message={"You can now Transfer another asset or go back to home page."} 
                buttons={[
                    {
                        text: 'Home',
                        handler: () => {setCreatAssetSuccessfully(false); handleGoBackHome(true);}
                    },
                    {
                        text: 'Transfer Another',
                        handler: () => {setCreatAssetSuccessfully(false); handleGoBackHome(false);}
                    }]} />
            <IonButton onClick={handleSubmit}>Transfer Transaction</IonButton>
        </IonContent>
    );
}

export default TransferTransaction;