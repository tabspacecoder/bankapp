
import * as React from 'react';
import AppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';

// import { useState } from 'react';
import { useLocation } from 'react-router-dom';

const CustomHeader=()=> {
  const location = useLocation();
  const path = location.pathname;
  // console.log(path);
  // const [isLoggedIn,setIsLoggedIn] = useState(false);
  // if(path.startsWith("/home")){
  //   setIsLoggedIn(true);
  // }
  // else if(path===''){
    
  // }

  return (
    <>
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
         
          <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
          {path==="/"?"Welcome":window.localStorage.getItem("bankName")+" Bank"}
          </Typography>
        </Toolbar>
      </AppBar>
    </Box>

    </>
  );
}

export default CustomHeader;