package ma.um6p.powermanager.models

data class BatteryInfo(
    var level: Int,
    var health: Int,
    var temperature: Int,
    var plugged: Int,
    var status: Int,
    var voltage: Double, // Current battery voltage level.
    var current: Int, // Instantaneous battery current in microamperes.
    var avgCurrent: Int, // Average battery current in microamperes.
    var energy: Long, // Battery remaining energy in nanowatt-hours.
//    var power: Double, // battery power usage in micro-watts.
)
