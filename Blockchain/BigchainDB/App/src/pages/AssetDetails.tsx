import { IonContent } from "@ionic/react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import BigchainDBConnection from "../bigchaindb/Connection";
import AssetDetailAccess from "../components/AssetDetailAccess";

type id = {
    id: string
}

const AssetDetails = () => {
    const { id } = useParams<id>();
    const [assets, setAssets] = useState<any[]>([]);

    const searchAssetDetails = async () => {
        await BigchainDBConnection.searchAssets(id)
            .then(assets => {
                setAssets(assets);
            });
    }

    searchAssetDetails();

    return (
        <IonContent>
            <AssetDetailAccess assets={assets}></AssetDetailAccess>
        </IonContent>
    );
}

export default AssetDetails;
