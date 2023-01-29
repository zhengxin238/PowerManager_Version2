const mongoose     = require('mongoose')
const Schema       = mongoose.Schema



const dynamicInfoSchema = new Schema({
    appName: {
        type: String,
        default: 'idle'
    },
    launchDate: {
        type: String,
        default:"unknown"
    },
    executionTime:{
        type: String,
        default: "0"
    },

    batteryStatistics: {
        type: Number,
        default: 0
    },
    batteryCapacity: {
        type: Number,
        default: 0
    },
    batteryTemparature: {
        type: Number,
        default: 0
    },
    batteryVoltage: {
        type: Number,
        default: 0
    },
    batteryCurrent: {
        type: Number,
        default: 0
        
    },
    powerDemand: {
        type: Number,
        default: 0
    },
    energyConsumption: {
        type: Number,
        default: 0
    },
    cpuStatistics:{
        type: Number,
        default: 0
    },
    cpuCores: {
        type: Number,
        default: 0
    },
    cpuFrequencies: {
        type: [Number],
        default: [0]
    },    
    memoryStatistics:{
        type: String,
        default: "0"
    },
    totalMemory: {
        type: String,
        default: "0"
    },
    usedMemory: {
        type: String,
        default: "0"
    }

})




const DynamicInfo = mongoose.model('DynamicInfo ', dynamicInfoSchema)
module.exports = DynamicInfo

