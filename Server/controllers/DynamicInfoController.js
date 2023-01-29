const { response } = require('express')
const DynamicInfo    = require('../models/DynamicInfo')

const index = (req, res, next) =>{
    DynamicInfo.find() // return all the DynamicInfo from the database
    .then (response => {
        res.json({
            response
        }) 
    }) /**if the quiry is OK it will return the response */
    .catch(error => {
        res.json({
            message :'An error occured!'
        })
    } )}


const storeDynamicInfo = (req, res, next) => {
    let dynamicInfoStore = new DynamicInfo({
    
            appName: req.body.appName,
            launchDate: req.body.launchDate,
            executionTime:req.body.executionTime,
            batteryStatistics: req.body.batteryStatistics,
            batteryCapacity: req.body.batteryCapacity,
            batteryTemparature: req.body.batteryTemparature,
            batteryVoltage: req.body.batteryVoltage,
            batteryCurrent:req.body.batteryCurrent,
            powerDemand: req.body.powerDemand,
            energyConsumption: req.body.energyConsumption,
            cpuStatistics: req.body.cpuStatistics,
            cpuCores: req.body.cpuCores,
            cpuFrequencies: req.body.cpuFrequencies,    
            memoryStatistics: req.body.memoryStatistics,
            totalMemory: req.body.totalMemory,
            usedMemory: req.body.usedMemory
        })
        dynamicInfoStore.save()
        .then(response => {
            res.json({
                message: 'DynamicInfo saved successfully'
                })
        })  
        .catch(error => {
            res.json({
                message: ' an error occured'
            })
    
        })
    }



const storeFullSummaryInfo = (req, res, next) => {
        let summaryInfo = new SummaryInfo({
    
            energyConsumption:req.body.energyConsumption,
            averagePower: req.body.averagePower,
            executionTime: req.body.executionTime
                
        })
        summaryInfo.save()
        .then(response => {
            res.json({
                message: 'SummaryInfo saved successfully'
                })
        })  
        .catch(error => {
            res.json({
                message: ' an error occured'
            })
    
        })
    
    }

    
module.exports = {
    index, storeDynamicInfo,storeFullSummaryInfo
}
