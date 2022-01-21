import { IonAlert, IonButton, IonButtons, IonContent, IonHeader, IonTitle, IonToolbar } from "@ionic/react";
import { useState } from "react";
import BigchainDBConnection from "../bigchaindb/Connection";
import UserAllTransactionsAccess from "../components/UserAllTransactionsAccess";

const MyTransactions: React.FC = () => {
    const [isFirstTime, setIsFirstTime] = useState(true);
    const [needRegister, setNeedRegister] = useState(false);
    const [getTransactionsFinished, setGetTransactionsFinished] = useState(false);
    var user = {
        name: "",
        email: "",
        institution: [""],
        account: {
            publicKey: "",
            privateKey: ""
        }
    };
    var transactions:any[] = [];

    const handleGetTransactionDetails = async (output:any) => {
        console.log("in handleGetTransactionDetails");
        let currDetail = await BigchainDBConnection.getTransaction(output.transaction_id);
        console.log(currDetail);
        transactions.push(currDetail);
        console.log(transactions);
        console.log("out handleGetTransactionDetails");

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
            BigchainDBConnection.listOutputs(user.account.publicKey)
                .then((outputs) => {
                    Promise.all(outputs.map(output => handleGetTransactionDetails(output)));
                    console.log(outputs);
                    console.log(transactions);
                })
                .then(() => {
                    setGetTransactionsFinished(true);
                });
        }
    }

    const handleGoToRegister = (action:boolean) => {
        if (action === true) {
            window.location.href = "/login";
        }
    }

    isFirstTime && handleGetCurrentUser();
    console.log(transactions);
    console.log(getTransactionsFinished);
    return (
        <>
        <IonHeader>
            <IonToolbar>
                <IonButtons slot="end">
                    <IonButton onClick={handleGetCurrentUser}>Get Transactions</IonButton>
                </IonButtons>
                <IonTitle>My Transactions</IonTitle>
            </IonToolbar>
        </IonHeader>
        <IonContent>
            <IonAlert 
                isOpen={needRegister} 
                header={'User Unknown'} 
                message={"Please register first."} 
                buttons={[
                    {
                        text: 'Go To Register',
                        handler: () => handleGoToRegister(true)
                    }]} />
            {getTransactionsFinished ? <UserAllTransactionsAccess transactions={transactions}></UserAllTransactionsAccess> : <h1>Loading...</h1>}
        </IonContent>
        </>
    );
}

export default MyTransactions;