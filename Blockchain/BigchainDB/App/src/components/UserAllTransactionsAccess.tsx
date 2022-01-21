import { IonItem, IonLabel } from "@ionic/react";

const UserAllTransactionsAccess = (props: { transactions: any[]; }) => {
    console.log(props.transactions);
    console.log(props.transactions.length === 0);
    return (
        <div>
            {props.transactions.map((transaction) => (
                <IonItem className="asset-preview" key={transaction.id}>
                    <IonLabel>Title: {transaction.metadata.assetTitle}</IonLabel>
                </IonItem>
            ))}
            <h2>Transactions List</h2>
        </div>
    );
}

export default UserAllTransactionsAccess;