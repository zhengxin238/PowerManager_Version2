const express = require('express')
const router = express.Router()

const EntryController = require('../controllers/EntryController')
const SummaryController = require('../controllers/SummaryController')
const DynamicInfoController = require('../controllers/DynamicInfoController')


router.get('/entrylist', EntryController.index)//
//router.get('/queryentry', EntryController.queryEntryData)//
router.post('/showEntryByAppTitle', EntryController.showEntryByAppTitle)//
router.post('/storeFullEntry', EntryController.storeFullEntry)//
router.post('/updateFullEntryWithID', EntryController.updateFullEntryWithID)//
router.post('/showEntryBetweenDate', EntryController.showEntryBetweenDate)
router.post('/showEntryBySummaryId', EntryController.showEntryBySummaryId)
router.post('/delete', EntryController.destroy)
router.post('/updateFullEntryWithEntryID',EntryController.updateFullEntryWithEntryID)

router.get('/summarylist', SummaryController.indexSummary)//
router.post('/querySummaryDataWithAppTItleAndStartTime', SummaryController.querySummaryDataWithAppTItleAndStartTime)//
router.post('/storeFullSummary', SummaryController.storeFullSummary)//
router.post('/updateFullSummaryWithID', SummaryController.updateFullSummaryWithID)//

router.post('/storeDynamicInfo',DynamicInfoController.storeDynamicInfo)
router.post('/storeFullSummaryInfo',DynamicInfoController.storeFullSummaryInfo)



// {
//     index, showEntryByAppTitle, storeFullEntry, updateFullEntryWithTitleAndDate,pushBatteryDataByAppTitle, destroy
// }

// here we need to add routes  

//this route gets back all the data
// router.get('/', async (req, res) => {/**here we do not need to specify the route, it is specified in the route-middleware of app.js */
//     try{
//         const entryData = await Entry.find()
//         res.json({entryData})
//     }catch(err){
//         res.json({message: err})
//     }
// })

// this route submit an entry
// router.post('/', async (req, res) => {
//     const newEntry = new Entry({
//         appInfo: {
//             title: req.body.appInfo.title,
//             launchDate: req.body.appInfo.launchDate,
//             executionTime: req.body.appInfo.executionTime},
        
//         batteryInfo: [{
//             batteryStatistics: req.body.batteryInfo.batteryStatistics,
//             batteryCapacity: req.body.batteryInfo.batteryCapacity,
//             batteryTemparature: req.body.batteryInfo.batteryTemparature,
//             batteryVoltage: req.body.batteryInfo.batteryVoltage,
//             batteryCurrent: req.body.batteryInfo.batteryCurrent,
//             powerDemand: req.body.batteryInfo.powerDemand,
//             energyConsumption: req.body.batteryInfo.energyConsumption,

//         }],
//         cpuInfo: {
//             cpuStatistics: req.body.cpuInfo.cpuStatistics,
//             cpuCores: req.body.cpuInfo.cpuCores,
//             cpuFrequencies: req.body.cpuInfo.cpuFrequencies
//         },
//         memoryInfo: {
//             memoryStatistics: req.body.memoryInfo.memoryStatistics,
//             totalMemory: req.body.memoryInfo.totalMemory,
//             usedMemory: req.body.memoryInfo.usedMemory
//         }
//     })
//     try{
//         const savedEntry = await newEntry.save()
//         res.json(savedEntry)
//     }catch(err){
//         res.json({message: err})
//     }   
//     })


module.exports = router