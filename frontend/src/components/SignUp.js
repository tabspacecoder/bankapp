import axios from 'axios';
import React from 'react'
import { useState } from 'react';
import { Link } from 'react-router-dom';
import CustomHeader from './CustomHeader';
import { useNavigate} from 'react-router-dom';
import { enqueueSnackbar } from 'notistack';
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from '@mui/material';


const SignUp = () => {
  const [details,setDetails]=useState({name:"",email:"",password:"",bankId:window.localStorage.getItem("bankId")});
 const [resDetails,setResDetails]=useState({});
  const navigate=useNavigate();
  const handleSubmit=(e)=>{
    e.preventDefault();
    axios.post("http://localhost:8080/api/customer/create-user",details).then((res)=>{
      enqueueSnackbar("Created user Successfully",{variant:"success"});
  
      setResDetails(res.data);
      handleOpen();
    }).catch(err=>{
      console.log(err);
    })

  }
  const [open, setOpen] = useState(false);

  const handleOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    navigate("/login");
  };

  const handleConfirm = () => {
    handleClose();
  };
  
  return (
    <>

    <CustomHeader/>
    <div className="Auth-form-container">
    <form className="Auth-form">
      <div className="Auth-form-content">
        <h3 className="Auth-form-title">Sign Up</h3>
        <div className="form-group mt-3">
          <label>Name</label>
          <input
            type="text"
            className="form-control mt-1"
            placeholder="Enter Name"
            onChange={(choice) =>setDetails((prev) => ({ ...prev, name: choice.target.value }))}
          />
        </div>
        <div className="form-group mt-3">
          <label>Email</label>
          <input
            type="email"
            className="form-control mt-1"
            placeholder="Enter Email"
            onChange={(choice) =>setDetails((prev) => ({ ...prev, email: choice.target.value }))}
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
        <p>Already have an account ? <Link to="/login">login to account</Link></p>
      </div>
    </form>
  </div>
  <Dialog open={open} onClose={handleClose} keepMounted disableEscapeKeyDown>
        <DialogTitle>Your Details</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Customer ID : {resDetails.id}
          </DialogContentText>
          <DialogContentText>
            Name : {resDetails.name}
          </DialogContentText>
          <DialogContentText>
            E-Mail : {resDetails.email}
          </DialogContentText>
          <DialogContentText>
            Password : {resDetails.password}
          </DialogContentText>
          <DialogContentText>
            Bank : {window.localStorage.getItem("bankName")+" Bank"}
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button onClick={handleConfirm} color="primary" autoFocus>
            ok
          </Button>
        </DialogActions>
      </Dialog>
  </>
  )
}

export default SignUp