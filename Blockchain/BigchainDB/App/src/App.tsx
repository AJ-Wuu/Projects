import { Redirect, Route } from 'react-router-dom';
import {
  IonApp,
  IonIcon,
  IonLabel,
  IonRouterOutlet,
  IonTabBar,
  IonTabButton,
  IonTabs,
  setupIonicReact
} from '@ionic/react';
import { IonReactRouter } from '@ionic/react-router';
import { home, create, train, logIn } from 'ionicons/icons';
import MyAssets from './pages/MyAssets'
import CreateTransaction from './pages/CreateTransaction';
import TransferTransaction from './pages/TransferTransaction';

/* Core CSS required for Ionic components to work properly */
import '@ionic/react/css/core.css';

/* Basic CSS for apps built with Ionic */
import '@ionic/react/css/normalize.css';
import '@ionic/react/css/structure.css';
import '@ionic/react/css/typography.css';

/* Optional CSS utils that can be commented out */
import '@ionic/react/css/padding.css';
import '@ionic/react/css/float-elements.css';
import '@ionic/react/css/text-alignment.css';
import '@ionic/react/css/text-transformation.css';
import '@ionic/react/css/flex-utils.css';
import '@ionic/react/css/display.css';

/* Theme variables */
import './theme/variables.css';
import LogIn from './pages/LogIn';
import NotFound from './pages/404NotFound';
import AssetDetails from './pages/AssetDetails';
import CategoryDefinition from './pages/CategoryDefinition';
import Home from './pages/Home';
import MyTransactions from './pages/MyTransactions';

setupIonicReact();

const App: React.FC = () => (
  <IonApp>
    <IonReactRouter>
      <IonTabs>
        <IonRouterOutlet>
          <Route exact path="/home"><Home /></Route>
          <Route exact path="/myassets"><MyAssets /></Route>
          <Route exact path="/mytransactions"><MyTransactions /></Route>
          <Route exact path="/"><Redirect to="/login" /></Route>
          <Route exact path="/createtransaction"><CreateTransaction /></Route>
          <Route exact path="/transfertransaction"><TransferTransaction /></Route>
          <Route exact path="/login"><LogIn /></Route>
          <Route path="/assets/:id"><AssetDetails /></Route>
          <Route exact path="/categorydefinition"><CategoryDefinition /></Route>
          <Route exact path="/404"><NotFound /></Route>
          <Route render={() => <Redirect to="/404" />} />
        </IonRouterOutlet>
        <IonTabBar slot="bottom">
          <IonTabButton tab="login" href="/login" selected={false}>
            <IonIcon icon={logIn} />
            <IonLabel>Log In</IonLabel>
          </IonTabButton>
          <IonTabButton tab="home" href="/home" selected={false}>
            <IonIcon icon={home} />
            <IonLabel>Home</IonLabel>
          </IonTabButton>
          <IonTabButton tab="create" href="/createtransaction">
            <IonIcon icon={create} />
            <IonLabel>Create</IonLabel>
          </IonTabButton>
          <IonTabButton tab="transfer" href="/transfertransaction">
            <IonIcon icon={train} />
            <IonLabel>Transfer</IonLabel>
          </IonTabButton>
        </IonTabBar>
      </IonTabs>
    </IonReactRouter>
  </IonApp>
);

export default App;