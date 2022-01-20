import { IonContent } from "@ionic/react";
import { useState } from "react";
import { useParams } from "react-router-dom";
import BigchainDBConnection from "../bigchaindb/Connection";
import AssetDetailAccess from "../components/AssetDetailAccess";
import MetadataDetailAccess from "../components/MetadataDetailAccess";

type id = {
    id: string
}

var initUserPublicKey:any;
var currAssetTitle:string = "";

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
                currAssetTitle = assets[0].data.token.title;
                console.log(currAssetTitle);
            });
        await BigchainDBConnection.searchMetadata(id)
            .then(metadata => {
                initUserPublicKey = metadata[0].metadata.preOwner[0].account.publicKey;
                console.log(initUserPublicKey);
            });
        await BigchainDBConnection.searchMetadata(initUserPublicKey)
            .then(searchResults => {
                console.log(searchResults);
                setMetadata(searchResults.filter(e => e.metadata.assetTitle === currAssetTitle));
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
