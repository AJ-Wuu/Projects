import { IonContent } from "@ionic/react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import BigchainDBConnection from "../bigchaindb/Connection";
import AssetDetailAccess from "../components/AssetDetailAccess";
import MetadataDetailAccess from "../components/MetadataDetailAccess";

type id = {
    id: string
}

const AssetDetails: React.FC = () => {
    const { id } = useParams<id>();
    const [isFirstTime, setIsFirstTime] = useState(true);
    const [assets, setAssets] = useState<any[]>([]);
    const [metadata, setMetadata] = useState<any[]>([]);

    const searchAssetDetails = async () => {
        setIsFirstTime(false);
        await BigchainDBConnection.searchAssets(id)
            .then(assets => {
                console.log(assets);
                setAssets(assets);
            });
        await BigchainDBConnection.searchMetadata(id)
            .then(metadata => {
                console.log(metadata);
                setMetadata(metadata);
            });        
    }

    isFirstTime && searchAssetDetails();
    return (
        <IonContent>
            <AssetDetailAccess assets={assets}></AssetDetailAccess>
            <MetadataDetailAccess metadata={metadata}></MetadataDetailAccess>
        </IonContent>
    );
}

export default AssetDetails;
