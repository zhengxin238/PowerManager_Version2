const { response } = require('express')
const Summary    = require('../models/Summary')

// Show the list of all summaries
/*here it will take the request of comming bodies, 
it will provide the response and if everything
is OK, it will find the next entry */
const indexSummary = (req, res, next) =>{
    Summary.find() // return all the Summary from the database
    .then (response => {
        res.json({
            response
        }) 
    }) /**if the quiry is OK it will return the response */
    .catch(error => {
        res.json({
            message :'An error occured!'
        })
    }) /** if it finds any error, it will return a message */
}


const querySummaryDataWithAppTItleAndStartTime /*appTitle & startTime */ = (req, res, next) =>{
    let appTitle = req.body.appTitle
    let startTime = req.body.startTime
    
    Summary.find({'appTitle': appTitle, 'startTime': startTime}) // return all filtered Entry from the database
    .then (response => {
        res.json({
            response
        }) 
    }) /**if the quiry is OK it will return the response */
    .catch(error => {
        res.json({
            message :'An error occured!'
        })
    }) /** if it finds any error, it will return a message */
}


const storeFullSummary = (req, res, next) => {
    let summary = new Summary({

            appTitle: req.body.appTitle,
           // startTime: req.body.startTime,
           // endTime:req.body.endTime,
            executionTime:req.body.executionTime,
            averagePower: req.body.averagePower,
            energyConsumption:req.body.energyConsumption
    })
    summary.save()
    .then(response => {
        res.json({
            message: 'Summary saved successfully'
            })
    })  
    .catch(error => {
        res.json({
            message: ' an error occured'
        })

    })

}


const updateFullSummaryWithID = (req, res, next) => {

    let summaryId = req.body.summaryID
    
    let updateSummaryData = {
        appTitle: req.body.appTitle,
        startTime: req.body.startTime,
        endTime:req.body.endTime,
        averagePower: req.body.averagePower,
        energyConsumption:req.body.energyConsumption
    }

    Summary.findByIdAndUpdate(summaryId, {$set: updateSummaryData})
    .then(response => {
        res.json({
            message: 'Entry updated successfully'
            })
    })  
    .catch(error => {
        res.json({
            message: ' an error occured'
        })

    })

}


module.exports = {indexSummary,querySummaryDataWithAppTItleAndStartTime,storeFullSummary, updateFullSummaryWithID}