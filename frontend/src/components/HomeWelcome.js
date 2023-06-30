import React from 'react'
import { Box } from '@mui/material'

const HomeWelcome = () => {
  return (
    <Box sx={{position:"fixed",top:"50%",left:"40%"}}>
      <h1 sx={{fontWeight: "bold"}}>Welcome to bank application!</h1>
    </Box>
  )
}

export default HomeWelcome