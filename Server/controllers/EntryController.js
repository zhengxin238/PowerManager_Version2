const { response } = require('express')
const Entry    = require('../models/Entry')
const Summary    = require('../models/Summary')

// Show the list of all entries
/*here it will take the request of comming bodies, 
it will provide the response and if everything 
is OK, it will find the next entry */
const index = (req, res, next) =>{
    Entry.find() // return all the Entry from the database
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


// const queryEntryData = (req, res, next) =>{
//     let title = req.query.title
    
//     Entry.find({ 'appInfo.title': title}) // return all filtered Entry from the database
//     .then (response => {
//         res.json({
//             response
//         }) 
//     }) /**if the quiry is OK it will return the response */
//     .catch(error => {
//         res.json({
//             message :'An error occured!'
//         })
//     }) /** if it finds any error, it will return a message */
// }

/* a function that will return a single Entry according to 
the entryID,that we will find in the request body, e.g. we must name the id of an Entry "entryID" in the request json data*/
// const showById = (req, res, next) =>{
//     let entryID = req.body.entryID
//     Entry.findById(entryID)
//     .then(response =>{
//         res.json({
//             response
//         })
//     })
//     .catch(error => {
//         res.json({
//             message: ' An error occured'
//         })
//     })
// }


/* a function that will return all Entry according to 
the 'appInfo.title',that we will find in the request body*/
const showEntryByAppTitle = (req, res, next) =>{
    let title = req.body.appInfo.title
    Entry.find({ 'appInfo.title': title })
    .then(response =>{
        res.json({
            response
        })
    })
    .catch(error => {
        res.json({
            message: ' An error occured'
        })
    })
}

const showEntryBySummaryId = (req, res, next) =>{
    let summaryId = req.body.summaryID
    Entry.find({'summaryID': summaryId })
    .then(response =>{
        res.json({
            response
        })
    })
    .catch(error => {
        res.json({
            message: ' An error occured'
        })
    })
}

const showEntryBetweenDate = (req, res, next) =>{
    let DateMax = req.body.createdMax
    let DateMin = req.body.createdMin
    Entry.find({ 'createdAt':{ $gte: DateMin,  $lte: DateMax } }).sort({'createdAt': 1 })
    .then(response =>{
        res.json({
            response
        })
    })
    .catch(error => {
        res.json({
            message: ' An error occured'
        })
    })
}



const storeFullEntry = (req, res, next) => {
    let entry = new Entry({

        summaryID: req.body.summaryID,
        createdAt: req.body.createdAt,
        appInfo: {
            title: req.body.appInfo.title,
            launchDate: req.body.appInfo.launchDate},
        
        batteryInfo: {
            batteryStatistics: req.body.batteryInfo.batteryStatistics,
            batteryCapacity: req.body.batteryInfo.batteryCapacity,
            batteryTemparature: req.body.batteryInfo.batteryTemparature,
            batteryVoltage: req.body.batteryInfo.batteryVoltage,
            batteryCurrent: req.body.batteryInfo.batteryCurrent,
            powerDemand: req.body.batteryInfo.powerDemand,
            energyConsumption: req.body.batteryInfo.energyConsumption},
        cpuInfo: {
            cpuStatistics: req.body.cpuInfo.cpuStatistics,
            cpuCores: req.body.cpuInfo.cpuCores,
            cpuFrequencies: req.body.cpuInfo.cpuFrequencies
        },
        memoryInfo: {
            memoryStatistics: req.body.memoryInfo.memoryStatistics,
            totalMemory: req.body.memoryInfo.totalMemory,
            usedMemory: req.body.memoryInfo.usedMemory
        }
    })
    entry.save()
    .then(response => {
        res.json({
            message: 'Entry saved successfully'
            })
    })  
    .catch(error => {
        res.json({
            message: ' an error occured'
        })

    })

}

const updateFullEntryWithID = (req, res, next) => {

    let entryID = req.body.appInfo.entryID

    let updateData = {
        summaryID: req.body.summaryID,
        createdAt: req.body.createdAt,
        appInfo: {
            title: req.body.appInfo.title,
            launchDate: req.body.appInfo.launchDate},
        
        batteryInfo: {
            batteryStatistics: req.body.batteryInfo.batteryStatistics,
            batteryCapacity: req.body.batteryInfo.batteryCapacity,
            batteryTemparature: req.body.batteryInfo.batteryTemparature,
            batteryVoltage: req.body.batteryInfo.batteryVoltage,
            batteryCurrent: req.body.batteryInfo.batteryCurrent,
            powerDemand: req.body.batteryInfo.powerDemand,
            energyConsumption: req.body.batteryInfo.energyConsumption,

        },
        cpuInfo: {
            cpuStatistics: req.body.cpuInfo.cpuStatistics,
            cpuCores: req.body.cpuInfo.cpuCores,
            cpuFrequencies: req.body.cpuInfo.cpuFrequencies
        },
        memoryInfo: {
            memoryStatistics: req.body.memoryInfo.memoryStatistics,
            totalMemory: req.body.memoryInfo.totalMemory,
            usedMemory: req.body.memoryInfo.usedMemory
        }
    }
    //let fileredEntry = Entry.find(filter)
    // fileredEntry.update($set: updateData)
    //Entry.updateOne(filter, updateData)
    Entry.findByIdAndUpdate(entryID, {$set: updateData})
    .then(response => {
        res.json({
            message: 'Entry updated successfully'
            })
    })  
    .catch(errpr => {
        res.json({
            message: ' an error occured'
        })

    })

}

const updateFullEntryWithEntryID = (req, res, next) => {

    let entryID = req.body.EntryID

    let updateData = {
        summaryID: req.body.summaryID,
        createdAt: req.body.createdAt,
        appInfo: {
            title: req.body.appInfo.title,
            launchDate: req.body.appInfo.launchDate},
        
        batteryInfo: {
            batteryStatistics: req.body.batteryInfo.batteryStatistics,
            batteryCapacity: req.body.batteryInfo.batteryCapacity,
            batteryTemparature: req.body.batteryInfo.batteryTemparature,
            batteryVoltage: req.body.batteryInfo.batteryVoltage,
            batteryCurrent: req.body.batteryInfo.batteryCurrent,
            powerDemand: req.body.batteryInfo.powerDemand,
            energyConsumption: req.body.batteryInfo.energyConsumption,

        },
        cpuInfo: {
            cpuStatistics: req.body.cpuInfo.cpuStatistics,
            cpuCores: req.body.cpuInfo.cpuCores,
            cpuFrequencies: req.body.cpuInfo.cpuFrequencies
        },
        memoryInfo: {
            memoryStatistics: req.body.memoryInfo.memoryStatistics,
            totalMemory: req.body.memoryInfo.totalMemory,
            usedMemory: req.body.memoryInfo.usedMemory
        }
    }
    //let fileredEntry = Entry.find(filter)
    // fileredEntry.update($set: updateData)
    //Entry.updateOne(filter, updateData)
    Entry.findByIdAndUpdate(entryID, {$set: updateData})
    .then(response => {
        res.json({
            message: 'Entry updated successfully'
            })
    })  
    .catch(errpr => {
        res.json({
            message: ' an error occured'
        })

    })

}


// const pushBatteryDataByAppTitle = (req, res, next) =>{
//     let title = req.body.appInfo.title
//     const filter = { 'appInfo.title': title };
//     let updateData = {
//         batteryStatistics: req.body.batteryInfo.batteryStatistics,
//         batteryCapacity: req.body.batteryInfo.batteryCapacity,
//         batteryTemparature: req.body.batteryInfo.batteryTemparature,
//         batteryVoltage: req.body.batteryInfo.batteryVoltage,
//         batteryCurrent: req.body.batteryInfo.batteryCurrent,
//         powerDemand: req.body.batteryInfo.powerDemand,
//         energyConsumption: req.body.batteryInfo.energyConsumption,
//     }

//     let fileredEntry = Entry.find(filter)
//     fileredEntry.batteryInfo.update(updateData)
//     .then(() => {
//         res.json({
//             message: 'battery data updated successfully!'
//         })
//     })
//     .catch(error => {
//         res.json({
//             message:'An error occured'
//         })
//     })
// }

const destroy = (req, res, next) => {
    let entryID = req.body.entryID

    Entry.findByIdAndRemove(entryID)
    .then(() => {
        res.json({
            message: 'Employee deleted successfully!'
        })
    })
    .catch(error => {
        res.json({
            message: 'An error occured'
        })
    })
}


module.exports = {
    index, showEntryByAppTitle,showEntryBetweenDate, storeFullEntry, updateFullEntryWithID,updateFullEntryWithEntryID,/*pushBatteryDataByAppTitle,*/ destroy, showEntryBySummaryId
}


