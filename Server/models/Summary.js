const mongoose     = require('mongoose')
const Schema       = mongoose.Schema

const summarySchema = new Schema({
    appTitle: {
        type: String,
        default: 'idle'
    },
/*    startTime: {
        type: Date,
        default:null
    },
    endTime: {
        type: Date,
        default:null
    },*/
    averagePower: {
        type: Number,
        default:0
    },
    energyConsumption: {
        type: Number,
        default:0
    },

    executionTime: {
        type: Number,
        default:0
    }

})

const Summary = mongoose.model('Summary', summarySchema)
module.exports = Summary