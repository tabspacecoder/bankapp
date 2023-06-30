import React, { useEffect,useState } from 'react'
import { Select,MenuItem, CircularProgress  } from '@mui/material';
import axios from 'axios';
import { enqueueSnackbar } from 'notistack';
import Box from '@mui/material/Box';
const Transfer = () => {
  const [amount,setAmount] = useState(0);
  const [cust,setCust]=useState(null);
  const [selectedAccId,setSelectedAccId]=useState(null);
  const [enteredId,setEnteredId]=useState(null);
  
  const customerId=window.localStorage.getItem("customerId");
  const password = window.localStorage.getItem("password");
  const fetchAllDetails=()=>{
    axios.post("http://localhost:8080/api/customer/fetch-all-details",{
    customerId:window.localStorage.getItem("customerId"),
    password:window.localStorage.getItem("password")
  }).then(res=>{
  console.log(res.data)
    setCust(res.data);
  }).catch(err=>{
  console.log(err);
    enqueueSnackbar("fetch all details",{variant:"error"});
  });
  }
  useEffect(()=>{fetchAllDetails()},[])
  const handleChange=(event)=>{
  setSelectedAccId(event.target.value);
  }
  
  const handleOnSubmit=(e)=>{
    e.preventDefault();
    axios.post(`http://localhost:8080/api/account/transfer-amount`,{
    customerId:window.localStorage.getItem("customerId"),
    password:window.localStorage.getItem("password"),
    amount:amount,
    withdrawAccountId:selectedAccId,
    depositAccountId:enteredId
  }).then(res=>{
    enqueueSnackbar("Successfully transfered!",{variant:"success"});
    setAmount(null);
    setSelectedAccId(null);
  }).catch(err=>{
  console.log(err);
    enqueueSnackbar(err.response.data.message,{variant:"error"});
  });
  }
  
  
    return (
      <>
      {
        cust==null?<Box sx={{position:"fixed",top:"50%",left:"40%"}}><CircularProgress/></Box>:    <div className="body-container" sx={{
          display: 'flex',
          flexDirection: 'column',
        }}>
        <form className="Auth-form">
          <div className="Auth-form-content">
            <h3 className="Auth-form-title">Transfer</h3>
            <Select
                  labelId="accounts-select-dropdown"
                  id="accounts-select-dropdown"
            
                  content='Select Account'
                  value={selectedAccId}
                  onChange={handleChange}
                  sx={{
                         color:'black',
                         width: 250,
                         height: 50,
                         
                       }}
              >
                  {cust.accounts.map((acc)=>{
      return <MenuItem key={acc.id} value={acc.id}>Account ID : {acc.id} - balance : {acc.balance}</MenuItem>
    } )}
              </Select>
            <div className="form-group mt-3">
              <label>Transfer amount</label>
              <input
                type="number"
                className="form-control mt-1"
                placeholder="Enter Amount"
                onChange={(choice) =>setAmount(choice.target.value)}
              />
            </div>
            <div className="form-group mt-3">
              <label>Account ID To Transfer</label>
              <input
                type="number"
                className="form-control mt-1"
                placeholder="Enter Id To Transfer"
                onChange={(choice) =>setEnteredId(choice.target.value)}
              />
            </div>
            <div className="d-grid gap-2 mt-3">
              <button className="btn btn-primary" onClick={handleOnSubmit}>
                Submit
              </button>
            </div>
          </div>
          
        </form>
        
      </div>
      }
      </>
    )
  
  }

export default Transfer