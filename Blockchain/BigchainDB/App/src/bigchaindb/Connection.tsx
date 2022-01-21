import * as BigchainDB from 'bigchaindb-driver';

const API_PATH = 'https://test.ipdb.io/api/v1/'
const BigchainDBConnection = new BigchainDB.Connection(API_PATH);

export default BigchainDBConnection;