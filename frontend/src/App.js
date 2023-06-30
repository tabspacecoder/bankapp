import { Route,  Routes,useRoutes} from "react-router-dom";
import BankSelectPage from "./components/BankSelectPage";
import Login from "./components/Login";
import SignUp from "./components/SignUp";
import Home from "./components/Home";
import HomeWelcome from "./components/HomeWelcome";
import Deposit from "./components/Deposit";
import Transfer from "./components/Transfer";
import "bootstrap/dist/css/bootstrap.min.css"
import './App.css';
import Withdraw from "./components/Withdraw";
import CreateBankAccount from "./components/CreateBankAccount"
import ViewAccounts from "./components/ViewAccounts";
import ViewTransactions from "./components/ViewTransactions";
// import { useEffect } from "react";




function App() {
//   const location = useLocation();
//   const currentPath = location.pathname;
  // useEffect(()=>{console.log(currentPath)},[currentPath])
  let ret=useRoutes([
    {path:"/" ,element:<BankSelectPage />},
    {path:"login" ,element:<Login />},
    {path:"signup" ,element:<SignUp />},
    {element:<Home />,
    children: [
      { path: 'home', element: <HomeWelcome/> },
      { path: 'deposit', element: <Deposit/> },
      { path: 'withdraw', element: <Withdraw/> },
      { path: 'transfer', element: <Transfer/> },
      { path: 'createAccount', element: <CreateBankAccount/> },
      { path: 'viewAccounts', element: <ViewAccounts/> },
      { path: 'viewTransactions', element: <ViewTransactions/> },

    ]},
  ]);
  // return (
  //   <>
 
  //   <Routes>
  //     <Route path="/" element={<BankSelectPage />}/>
  //     <Route path="login" element={<Login/>}/>
  //     <Route path="signup" element={<SignUp/>}/>
  //     <Route path="home" element={<Home/>}>
  //       <Route index component={<Home />} content={mug}/>
  //       <Route path="account" element={<Home mugunth={<h1>sdcs</h1>} />}/>
  //     </Route>
  //   </Routes>
  //   </>
  // );
  return ret;
}

export default App;
