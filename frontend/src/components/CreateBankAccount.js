import React, { useEffect,useState } from 'react'
import { Select,MenuItem, CircularProgress  } from '@mui/material';
import axios from 'axios';
import { enqueueSnackbar } from 'notistack';
import Box from '@mui/material/Box';

const CreateBankAccount = () => {
const [amount,setAmount] = useState(0);
const [cust,setCust]=useState(null);

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

const handleOnSubmit=(e)=>{
  e.preventDefault();
  axios.post(`http://localhost:8080/api/account/create-account`,{
  customerId:window.localStorage.getItem("customerId"),
  password:window.localStorage.getItem("password"),
  balance:amount,
  bankId:window.localStorage.getItem("bankId")
}).then(res=>{
    console.log(res);
  enqueueSnackbar(`Successfully created! ,ID : ${res.data.id}` ,{variant:"success"});
  setAmount(null);
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
          <h3 className="Auth-form-title">Create new bank account</h3>
          <div className="form-group mt-3">
            <label>Initial Balance</label>
            <input
              type="number"
              className="form-control mt-1"
              placeholder="Initial Balance"
              onChange={(choice) =>setAmount(choice.target.value)}
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

export default CreateBankAccount;