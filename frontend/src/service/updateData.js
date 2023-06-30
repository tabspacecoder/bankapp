import axios from "axios";
export const updateData=(customerId,password)=>{
    axios.post(`http://localhost:8080/api/customer/fetch-all-details`,{
    customerId,
    password
}).then(res=>{
  console.log(res.data);
    window.localStorage.setItem("data",JSON.stringify(res.data));
  return true;
}).catch(err=>{
    console.log(err)
  return false;
})
};
