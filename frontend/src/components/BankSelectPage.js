import React, { useState,useEffect } from 'react';
import CustomHeader from "./CustomHeader";
import {  useNavigate } from "react-router-dom";
import { Select,MenuItem, Button  } from '@mui/material';
import axios from 'axios';

// import { backendUrl } from "./constants.js";

const BankSelectPage = () => {

    
    const [banks,setBanks]=useState([]);
   
    // const fetchBankData=async ()=>{
    //     var res=await Axios.post(`http://localhost:8080/api/bank/fetch-banks`);
    //     var banks=res.data;
    //     setBanks(banks);
    //     return banks;
    //   }
    //  
    const fetchBankData=()=>{
        axios.post(`http://localhost:8080/api/bank/fetch-banks`).then(res=>{
          setBanks(res.data);
          console.log(res.data);
        })
      }
      useEffect(()=>{fetchBankData()},[]);
    const [selectedBankId,setSelectedBankId]=useState(window.localStorage.getItem("bankId")!=null?window.localStorage.getItem("bankId"):1);
    const navigate = useNavigate();
      const onSubmit=()=>{
        console.log("inside")
        window.localStorage.setItem("bankId",selectedBankId);
        for(let i=0;i<banks.length;i++){
            if(banks[i].id===selectedBankId){
                window.localStorage.setItem("bankName",banks[i].name);
                break;
            }
        }
        navigate('/login');
       
      }
  
      var items = banks.map((bank)=>{
        return <MenuItem key={bank.id} value={bank.id}>{bank.name}</MenuItem>
      } )

      const handleChange = (event) => {
        console.log(event.target.value);
        setSelectedBankId(event.target.value);
      };
        

    return (
      <>
      <CustomHeader/>
        <div
        style={{
            display: 'flex',
            alignItems: 'center',
            flexDirection:'column',
            justifyContent: 'center',
            height: '100vh',
          }}>
           <h2>Select a bank to proceed</h2>
            <Select
                labelId="bank-select-dropdown-label"
                id="bank-select-dropdown"
                // label="Bank"
                content='Select bank'
                value={selectedBankId}
                onChange={handleChange}
                sx={{
                       color:'black',
                       width: 250,
                       height: 50,
                       
                     }}
            >
                {items}
            </Select>
            <br/>
            <Button type="submit" onClick={onSubmit} variant="outlined" size="large">Proceed</Button>
            
            
            </div>
            </>
    );
}

export default BankSelectPage