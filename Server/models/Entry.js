const mongoose     = require('mongoose')
const Schema       = mongoose.Schema

const appInfoSchema = new Schema({
    title: {
        type: String,
        default: 'idle'
    },
    launchDate: {
        type: Date,
        default:Date.now
    }

})

const batterySchema  = new Schema({
    batteryStatistics: {
        type: Number
    },
    batteryCapacity: {
        type: Number
    },
    batteryTemparature: {
        type: Number
    },
    batteryVoltage: {
        type: Number
    },
    batteryCurrent: {
        type: Number
        
    },
    powerDemand: {
        type: Number
    },
    energyConsumption: {
        type: Number
    },
    timeOfEntry:{
        type: Date,
        default: Date.now
    }
}, {
    timestamps: true
})


const cpuInfoSchema = new Schema({
    cpuStatistics:{
        type: Number
    },
    cpuCores: {
        type: Number
    },
    cpuFrequencies: {
        type: Number
    }

})

const memoryInfoSchema = new Schema({
    memoryStatistics:{
        type: Number
    },
    totalMemory: {
        type: Number
    },
    usedMemory: {
        type: Number
    }

})

const entrySchema = new Schema({
    summaryID: {
        type: Number
    },
    createdAt: {
        type: Date,
        default:Date.now
    },
    appInfo:{
        type: appInfoSchema
    },
    batteryInfo: {
        type: batterySchema
    },
    cpuInfo: {
        type: cpuInfoSchema
    },
    memoryInfo: {
        type: memoryInfoSchema
    } 

}, {
    timestamps: true
} )

const AppInfo = mongoose.model('AppInfo', appInfoSchema)
module.exports = AppInfo

const Battery = mongoose.model('Battery', batterySchema)
module.exports = Battery

const CPUInfo = mongoose.model('CPUInfo', cpuInfoSchema)
module.exports = CPUInfo

const MemoryInfo = mongoose.model('MemoryInfo', memoryInfoSchema)
module.exports = MemoryInfo

const Entry = mongoose.model('Entry', entrySchema)
module.exports = Entry