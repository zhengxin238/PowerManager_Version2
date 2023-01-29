const express   = require('express')   // imported package 'express'
const mongoose  = require('mongoose')
const morgan   = require('morgan')   
const bodyParser  = require('body-parser')
const app = express()  //execute package "express" 

const entryRoute = require('./routes/entry')

app.use(bodyParser.json())
app.use(morgan('dev'))
app.use(bodyParser.urlencoded({extended: true}))
app.use('/api/entry', entryRoute)

//import Routes


//connect to database
mongoose.connect('mongodb://localhost:27017/energydb', {useNewUrlParser: true, useUnifiedTopology: true})
const db = mongoose.connection

db.on('error', (err)=>{
    console.log(err)
})

db.once('open',()=>{
    console.log('db Connection Established')
})

const PORT = process.env.PORT || 3002

app.listen(PORT, ()=> {
    console.log (`Server is running on port ${PORT}`)
})