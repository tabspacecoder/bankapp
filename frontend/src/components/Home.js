import * as React from 'react';
import Box from '@mui/material/Box';
import Drawer from '@mui/material/Drawer';
import AppBar from '@mui/material/AppBar';
import CssBaseline from '@mui/material/CssBaseline';
import Toolbar from '@mui/material/Toolbar';
import List from '@mui/material/List';
import CardGiftcardIcon from '@mui/icons-material/CardGiftcard';
import Typography from '@mui/material/Typography';
import Divider from '@mui/material/Divider';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import { HomeOutlined } from '@mui/icons-material';
import { useEffect,useState } from 'react';
import axios from 'axios';
import { enqueueSnackbar } from 'notistack';
import { CircularProgress,IconButton } from '@mui/material';
import ExitToAppIcon from '@mui/icons-material/ExitToApp';
import { Outlet } from "react-router-dom";
import { useNavigate } from 'react-router-dom';
import {updateData} from '../service/updateData.js';
import AtmIcon from '@mui/icons-material/Atm';
import AccountBalanceWalletIcon from '@mui/icons-material/AccountBalanceWallet';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import ReceiptIcon from '@mui/icons-material/Receipt';
import AttachMoneyIcon from '@mui/icons-material/AttachMoney';

const drawerWidth = 240;


export default function Home(props) {
  console.log(props);
    const [cust,setCust]=useState(null);
    const fetchAllDetails=()=>{
      const customerId=window.localStorage.getItem("customerId");
      const password=window.localStorage.getItem("password");
      if(updateData(customerId,password)){
        console.log(JSON.parse(window.localStorage.getItem("data")))
      }
      axios.post(`http://localhost:8080/api/customer/fetch-all-details`,{
      customerId,
      password
  }).then(res=>{
    console.log(res.data)
      setCust(res.data);
  }).catch(err=>{
    console.log(err);
      enqueueSnackbar(err.response.data.message,{variant:"error"});
  })
};
const navigate=useNavigate();
    useEffect(()=>fetchAllDetails(),[]);
    const handleOnClick=(path)=>{
      navigate(path);
    }
  return (
    
    
    <Box sx={{ display: 'flex' }}>
      {cust==null?<CircularProgress />:<>      <CssBaseline />
      <AppBar position="fixed" sx={{ zIndex: (theme) => theme.zIndex.drawer + 1 }}>
        <Toolbar sx={{display:"flex",justifyContent:"space-between"}}>
          <Typography variant="h6" noWrap component="div">
            Hi {cust.customer.name}!
          </Typography>
          <IconButton sx={{color:"white"}} onClick={()=>{
            window.localStorage.clear();
            navigate("/");
          }}>
            <ExitToAppIcon />
          </IconButton>
        </Toolbar>
      </AppBar>
      <Drawer
        variant="permanent"
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          [`& .MuiDrawer-paper`]: { width: drawerWidth, boxSizing: 'border-box' },
        }}
      >
        <Toolbar />
        <Box sx={{ overflow: 'auto' }}>
          <List>
          <ListItem disablePadding>
              <ListItemButton onClick={()=>{handleOnClick("home")}}>
                  <ListItemIcon>
                  <HomeOutlined/>
                  </ListItemIcon>
                  <ListItemText primary={"Home"} />
              </ListItemButton>
            </ListItem>
            <Divider/>
            <ListItem disablePadding>
              <ListItemButton onClick={()=>{handleOnClick("withdraw")}}>
                  <ListItemIcon>
                  <AtmIcon/>
                  </ListItemIcon>
                  <ListItemText primary={"Withdraw"} />
              </ListItemButton>
            </ListItem>
            <ListItem disablePadding>
              <ListItemButton onClick={()=>{handleOnClick("deposit")}}>
                  <ListItemIcon>
                  <AttachMoneyIcon/>
                  </ListItemIcon>
                  <ListItemText primary={"Deposit"} />
              </ListItemButton>
            </ListItem>
            <ListItem disablePadding >
              <ListItemButton onClick={()=>{handleOnClick("transfer")}}>
                  <ListItemIcon>
                  <CardGiftcardIcon/>
                  </ListItemIcon>
                  <ListItemText primary={"Transfer"} />
              </ListItemButton>
            </ListItem>
          </List>
          <Divider />
          <List>
          <ListItemButton onClick={()=>{handleOnClick("createAccount")}}>
              <ListItemIcon>
                <PersonAddIcon/>
              </ListItemIcon>
              <ListItemText primary={"Create Account"} />
            </ListItemButton>
            <ListItemButton onClick={()=>{handleOnClick("viewAccounts")}}>
              <ListItemIcon>
                <AccountBalanceWalletIcon/>
              </ListItemIcon>
              <ListItemText primary={"View Accounts"} />
            </ListItemButton>
            <ListItemButton onClick={()=>{handleOnClick("viewTransactions")}}>
              <ListItemIcon>
                <ReceiptIcon/>
              </ListItemIcon>
              <ListItemText primary={"View Transactions"} />
            </ListItemButton>
          </List>
        </Box>
      </Drawer>
      <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
        <Toolbar />
        {/* <Typography paragraph>
          Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod
          tempor incididunt ut labore et dolore magna aliqua. Rhoncus dolor purus non
          enim praesent elementum facilisis leo vel. Risus at ultrices mi tempus
          imperdiet. Semper risus in hendrerit gravida rutrum quisque non tellus.
          Convallis convallis tellus id interdum velit laoreet id donec ultrices.
          Odio morbi quis commodo odio aenean sed adipiscing. Amet nisl suscipit
          adipiscing bibendum est ultricies integer quis. Cursus euismod quis viverra
          nibh cras. Metus vulputate eu scelerisque felis imperdiet proin fermentum
          leo. Mauris commodo quis imperdiet massa tincidunt. Cras tincidunt lobortis
          feugiat vivamus at augue. At augue eget arcu dictum varius duis at
          consectetur lorem. Velit sed ullamcorper morbi tincidunt. Lorem donec massa
          sapien faucibus et molestie ac.
        </Typography>
        <Typography paragraph>
          Consequat mauris nunc congue nisi vitae suscipit. Fringilla est ullamcorper
          eget nulla facilisi etiam dignissim diam. Pulvinar elementum integer enim
          neque volutpat ac tincidunt. Ornare suspendisse sed nisi lacus sed viverra
          tellus. Purus sit amet volutpat consequat mauris. Elementum eu facilisis
          sed odio morbi. Euismod lacinia at quis risus sed vulputate odio. Morbi
          tincidunt ornare massa eget egestas purus viverra accumsan in. In hendrerit
          gravida rutrum quisque non tellus orci ac. Pellentesque nec nam aliquam sem
          et tortor. Habitant morbi tristique senectus et. Adipiscing elit duis
          tristique sollicitudin nibh sit. Ornare aenean euismod elementum nisi quis
          eleifend. Commodo viverra maecenas accumsan lacus vel facilisis. Nulla
          posuere sollicitudin aliquam ultrices sagittis orci a.
        </Typography> */}
        {/* <Box>{mugunth}</Box> */}
        {/* {props.children} */}
        <Outlet />
      </Box></>}
    </Box>
  );
}