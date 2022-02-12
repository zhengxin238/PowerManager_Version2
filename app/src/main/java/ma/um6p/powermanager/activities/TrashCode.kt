package ma.um6p.powermanager.activities

import androidx.appcompat.app.AppCompatActivity

class TrashCode : AppCompatActivity() {

    // initialize a new broadcast receiver instance
//    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
//        override fun onReceive(context: Context?, intent: Intent?) {
//            val bm = applicationContext.getSystemService(AppCompatActivity.BATTERY_SERVICE) as BatteryManager
//            val current = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)
//            val avgCurrent = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE)
//            val energy = bm.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER)
////            current = intent.getIntExtra(
////                BatteryManager.BATTERY_PROPERTY_CURRENT_NOW.toString(),
////                -1000
////            )
//            intent?.apply {
//                val batteryInfo = BatteryInfo(
//                    level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -199),
//                    health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, -199),
//                    temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -199),
//                    plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -199),
//                    status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -199),
//                    voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -199)
//                        .toDouble() / 1000,
//                    current = current,
//                    avgCurrent = avgCurrent,
//                    energy = energy,
//                )
//            }
//            MonitorActivity.START_TIME?.let {
//                val execTime = (System.currentTimeMillis() - it.toDouble()) / 1000
//                binding.monitorExecTime.text = "Execution time : $execTime seconds"
//            }
//            binding.monitorBatteryLevel.text = "Battery level : ${batteryInfo.level}%"
//            binding.monitorBatteryVoltage.text = "Battery voltage :${batteryInfo.voltage} V"
//            binding.monitorBatteryCurrent.text = "Battery current :${batteryInfo.current} µA"
//            binding.monitorPowerDemand.text =
//                "Power demand :${batteryInfo.current * batteryInfo.voltage} µWatts"
//            binding.monitorEnergy.text = "Energy consumption : ${batteryInfo.energy} nanowatt-hours"
//
//        }
//    }

}