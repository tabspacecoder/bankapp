import React from 'react';
import { useState } from 'react';
import axios from 'axios';
import { useSnackbar } from 'notistack';
import { Link } from 'react-router-dom';
import {  useNavigate } from "react-router-dom";
import CustomHeader from './CustomHeader';

const Login = () => {
  const navigate=useNavigate();
const [details,setDetails]=useState({id:0,password:""});
const { enqueueSnackbar } = useSnackbar();
const handleSubmit=(e)=>{
  e.preventDefault();
    console.log(details)
    axios.post("http://localhost:8080/api/customer/login",details)
    .then((res)=>{
        if(res.status===200){
          console.log(res.data);
          window.localStorage.setItem("customerId",details.id);
          window.localStorage.setItem("password",details.password);
            enqueueSnackbar(res.data.message,{variant:"success"});
            navigate("/home");
        }
        else if(res.status===400){
          console.log(res.data);
            enqueueSnackbar(res.data.message,{variant:"error"});
        }
        else {
          console.log(res.data);
            enqueueSnackbar(res.data.message,{variant:"error"});
        }
    }
    ).catch(err=>{
      console.log(err);
      enqueueSnackbar(err.response.data.message,{variant:"error"});
    })
}

  return (<>
  <CustomHeader/>
    <div className="Auth-form-container" sx={{
      display: 'flex',
      flexDirection: 'column',
    }}>
    <form className="Auth-form">
      <div className="Auth-form-content">
        <h3 className="Auth-form-title">Sign In</h3>
        <div className="form-group mt-3">
          <label>Customer ID</label>
          <input
            type="number"
            className="form-control mt-1"
            placeholder="Enter Customer ID"
            onChange={(choice) =>setDetails((prev) => ({ ...prev, id: choice.target.value }))}
          />
        </div>
        <div className="form-group mt-3">
          <label>Password</label>
          <input
            type="text"
            className="form-control mt-1"
            placeholder="Enter password"
            onChange={(choice) =>setDetails((prev) => ({ ...prev, password: choice.target.value }))}
          />
        </div>
        <div className="d-grid gap-2 mt-3">
          <button className="btn btn-primary" onClick={handleSubmit}>
            Submit
          </button>
        </div>
        <br/>
        <p>Don't have an account ? <Link to="/signup">Create a new one</Link></p>
      </div>
      
    </form>
    
  </div>
  </>
  )
}

export default Login