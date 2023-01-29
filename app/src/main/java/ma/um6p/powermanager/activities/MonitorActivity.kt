package ma.um6p.powermanager.activities

import android.annotation.SuppressLint
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.*
import android.text.Html
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ma.um6p.powermanager.dataTransferModels.dynamicInfo.DynamicInfoOriginal
import ma.um6p.powermanager.databinding.ActivityMonitorBinding
import ma.um6p.powermanager.models.App

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.abs

import ma.um6p.powermanager.dataTransferModels.summary.SummaryResponse
import ma.um6p.powermanager.dataTransferModels.summary.StoreFullSummaryModel
import ma.um6p.powermanager.retrofit.ApiInterface
import ma.um6p.powermanager.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MonitorActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMonitorBinding
    private var powerDemand = arrayListOf<Int>()
    private var execTime: Double? = null

    companion object {
        val TAG: String = MonitorActivity::class.java.simpleName
        const val IDLE_EXEC_TIME: Int = 600 // 10min
        lateinit var SELECTED_APP: App
        var START_TIME: Long? = null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonitorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val styledAppName = Html.fromHtml("<i>${SELECTED_APP.name}</i>", 0)
        supportActionBar?.title = "Monitoring { $styledAppName } ..."
    }

    override fun onStart() {
        super.onStart()

        var dinfo : DynamicInfoOriginal = DynamicInfoOriginal(
            "randomApp", "2022-10-31", "22",22.0,
            22,2.0,22.0,22,22,
            22.0,22,22, arrayOf(22),"22",
            "22","22")
        storeDynamicInfo(dinfo)

        printStaticInfo()
        dynamicInfo()



        // initialize a new intent filter instance
//        val filters = IntentFilter()
//        filters.addAction(Intent.ACTION_BATTERY_CHANGED)
//        filters.addAction(Intent.ACTION_TIME_TICK)
        // register the broadcast receiver
//        registerReceiver(receiver, filters)
    }

    private fun dynamicInfo(): Timer {
        val timer = Timer()
        hundleResults(timer)
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                val bm = applicationContext.getSystemService(BATTERY_SERVICE) as BatteryManager //an object
                val bs: Intent? = registerReceiver(
                    null,
                    IntentFilter(Intent.ACTION_BATTERY_CHANGED)
                )
                execTime = (System.currentTimeMillis() - START_TIME!!.toDouble()) / 1000
                val level = bs?.getIntExtra(BatteryManager.EXTRA_LEVEL, -199) // in percent
                val temperature = // temperature of battery
                    bs?.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -199)!!.toDouble() / 10
                val plugged = bs.getIntExtra(BatteryManager.EXTRA_PLUGGED, -199) //whether the phone in plugged or using battery
//                val status = bs.getIntExtra(BatteryManager.EXTRA_STATUS, -199)
                val voltage =
                    bs.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -199).toDouble() / 1000
                val current = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_NOW)
//                val avgCurrent = bm.getIntProperty(BatteryManager.BATTERY_PROPERTY_CURRENT_AVERAGE)
//                val energy = bm.getLongProperty(BatteryManager.BATTERY_PROPERTY_ENERGY_COUNTER)
//                if (startEnergy == null) {
//                    startEnergy = energy
//                }
                // print in main thread
                runOnUiThread {
                    printDynamicInfo(
                        execTime = execTime,
                        level = level,
                        temperature = temperature,
                        plugged = plugged,
                        voltage = voltage,
                        current = current,
                    )
                }




// call the function for memory and cpu here and get the return value

                  var dinfo : DynamicInfoOriginal = DynamicInfoOriginal(
                      SELECTED_APP.name, START_TIME.toString(),  execTime.toString(),2.0,
                      applicationContext.batteryCapacity!!.toInt(),temperature,voltage,current,(abs(current)*voltage).toInt(),
                      22.0,22,22, arrayOf(22),"22",
                      "22","22")
                  dinfo.appName = SELECTED_APP.name
                  dinfo.launchDate = START_TIME.toString()
                  dinfo.executionTime = execTime.toString()
                  if (level != null) {
                      dinfo.batteryStatistics = level.toDouble()
                  }
                  dinfo.batteryCapacity = applicationContext.batteryCapacity!!.toInt()
                  dinfo.batteryVoltage = voltage
                  dinfo.batteryCurrent = current
                  dinfo.batteryTemparature = temperature
                  dinfo.powerDemand = (abs(current)*voltage).toInt()
                  dinfo.energyConsumption = powerDemand.average() * execTime!!


        //          for (i in 0 until dinfo.cpuCores) {
         //             dinfo.cpuFrequencies[i] = lc[i]
         //         }
                val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
                val memInfo = ActivityManager.MemoryInfo()
                am.getMemoryInfo(memInfo)
                val availMemory = memInfo.availMem.toDouble() / (1024 * 1024 * 1024)
                val totalMemory = memInfo.totalMem.toDouble() / (1024 * 1024 * 1024)
                val usedMemory = totalMemory - availMemory
                val usage = (usedMemory * 100 / totalMemory).toInt()
                Log.i("MonitorActivity", "1111111111111111")
                Log.i("MonitorActivity",memInfo.toString())
                Log.i("MonitorActivity",availMemory.toString())

                Log.i("MonitorActivity",totalMemory.toString())
                Log.i("MonitorActivity",usage.toString())
                Log.i("MonitorActivity", "222222222222222222")
                dinfo.totalMemory = totalMemory.toString()
                dinfo.usedMemory = usedMemory.toString()
                dinfo.memoryStatistics = usage.toString()





                val n = Runtime.getRuntime().availableProcessors()
                val usages = IntArray(n)
                for (i in 0 until n) {
                    val coreInfo = getCoreFreq(i)
                    usages[i] = coreInfo[3]
                }
                val statistics = usages.average().toInt()

                dinfo.cpuStatistics =  statistics
                dinfo.cpuCores =  n
                dinfo.cpuFrequencies = usages.toTypedArray()



                storeDynamicInfo(dinfo) // this means this function will be called once a second

                var newSummary : StoreFullSummaryModel = StoreFullSummaryModel(SELECTED_APP.name,powerDemand.average(), powerDemand.average() * execTime!!,
                    execTime!!
                )
                storeSummaryInfo(newSummary)




                if (SELECTED_APP.name == "Measure Idle power" && execTime!! >= IDLE_EXEC_TIME) {
                    timer.cancel()
                    runOnUiThread { val ms = monitorSummary()
 /*                       val newStoreModel = StoreFullSummaryModel(SELECTED_APP.name,ms.avgPower,System.currentTimeMillis().toString(),energyJoules,START_TIME.toString())


                        //make a retrofit builder object implements the ApiInterface so that we can call the function predefined in the ApiInterface
                        val retrofitBuilderObject = RetrofitBuilder.buildData(ApiInterface::class.java)

                        //create one more variable to get the data from the retrofit builder
                        val requestCall = retrofitBuilderObject.addSummary(newStoreModel)

                        requestCall.enqueue(object : Callback<SummaryResponse?> {
                            override fun onResponse(
                                call: Call<SummaryResponse?>,
                                response: Response<SummaryResponse?>
                            ) {
                                val responseBody = response.body()!!
                                Log.i("MainActivity", "LogMessage"+response.body())

                            }

                            override fun onFailure(call: Call<SummaryResponse?>, t: Throwable) {
                                Log.i("MainActivity", "ErrorMessage"+t.message)
                            }
                        })*/



                    }
                }
            }
        }, 0, 1000) //put here time 1000 milliseconds=1 second
        return timer
    }

    @SuppressLint("SetTextI18n")
    private fun printDynamicInfo(
        execTime: Double?,
        level: Int?,
        temperature: Double?,
        plugged: Int?,
        voltage: Double?,
        current: Int?
    ) {

        execTime?.let { binding.monitorAppExec.text = "${it.toInt()} seconds" }
        level?.let { binding.monitorBatteryLevel.text = "$it%" }
        temperature?.let { binding.monitorBatteryTemp.text = "$it ºC" }
        plugged?.let {
            val status = when (it) {
                BatteryManager.BATTERY_PLUGGED_AC -> "Charging (AC)"
                BatteryManager.BATTERY_PLUGGED_USB -> "Charging (USB)"
                BatteryManager.BATTERY_PLUGGED_WIRELESS -> "Charging (Wireless)"
                else -> "Discharging"
            }
            binding.monitorBatteryStatus.text = status
        }
        voltage?.let { binding.monitorBatteryVoltage.text = "$it V" }
        current?.let {
            val absCurrent = abs(it)
            if (plugged == 0) {
                binding.monitorBatteryCurrent.text = "$absCurrent µA"
            } else {
                binding.monitorBatteryCurrent.text = "$absCurrent µA IN"
            }
        }
//        energy?.let { now ->
//            startEnergy?.let { start ->
//                val usedEnergy = start - now
//                val microEnergy = (usedEnergy / 1000).toInt()
//                binding.monitorEnergy.text = "$microEnergy µWh"
//            }
//        }
        if (current != null && voltage != null) {
            val power = (abs(current) * voltage).toInt()
            powerDemand.add(power)
//            val powerWatts = String.format("%.4f", (power / 1000000))
            if (plugged == 0) {
                binding.monitorPowerDemand.text = "$power µWatts"
//                binding.monitorPowerDemand.text = "$powerWatts Watts"
            } else {
                binding.monitorPowerDemand.text = "$power µWatts IN"
//                binding.monitorPowerDemand.text = "$powerWatts Watts IN"
            }
            // estimate energy
            val avgPower = powerDemand.average()
            val microEnergy = avgPower * execTime!!.toInt()
            binding.monitorEnergy.text = "${microEnergy.toInt()} µJ"
        }
        logCpus()
        logMemory()
    }

    private fun hundleResults(timer: Timer) {
        runOnUiThread {
            var doubleClick: Boolean? = false


            binding.summaryText.setOnClickListener {
                if (doubleClick!!) {
                    timer.cancel()
                    monitorSummary()
                }
                doubleClick = true
                Handler(Looper.getMainLooper()).postDelayed({ doubleClick = false }, 2000)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun printStaticInfo() {
        val cores = Runtime.getRuntime().availableProcessors()
        binding.monitorCpuCores.text = "$cores cores"
        binding.monitorAppName.text = SELECTED_APP.name
        START_TIME?.let { stamp ->
            val format = SimpleDateFormat("dd/MM HH:mm:ss")
            binding.monitorAppTime.text = format.format(Date(stamp))
        }
        applicationContext.batteryCapacity?.let {
            binding.monitorBatteryCapacity.text = "$it mAh"
        }
    }

    @SuppressLint("SetTextI18n")
    private fun monitorSummary() {
        val avgPower = powerDemand.average()
//        Log.i(TAG, powerDemand.toString())
//        Log.i(TAG, avgPower.toString())
        val avgPowerWatts = avgPower / 1000000
        val energyJoules = avgPowerWatts * execTime!!.toInt()
//        Log.i(TAG, avgPowerWatts.toString())
//        Log.i(TAG, energyJoules.toString())
        binding.monitorSummaryTime.text = "${execTime!!.toInt()} seconds"
        binding.monitorSummaryPower.text = "${avgPower.toInt()} µWatts"
        binding.monitorSummaryEnergy.text = "${energyJoules.toString().take(6)} J"
        binding.summaryText.visibility = View.INVISIBLE
        binding.summaryTable.visibility = View.VISIBLE

    }


    @SuppressLint("SetTextI18n")
    private fun logMemory(): Array<String>{

        val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        am.getMemoryInfo(memInfo)
        val availMemory = memInfo.availMem.toDouble() / (1024 * 1024 * 1024)
        val totalMemory = memInfo.totalMem.toDouble() / (1024 * 1024 * 1024)
        val usedMemory = totalMemory - availMemory
        val rTotalMemory = String.format("%.2f", totalMemory)
        val rUsedMemory = String.format("%.4f", usedMemory)
        val usage = (usedMemory * 100 / totalMemory).toInt()

        binding.monitorMemoryUsage.text = "$usage %"
        binding.monitorMemoryTotal.text = "$rTotalMemory GB"
        binding.monitorMemoryUsed.text = "$rUsedMemory GB"

        val arrayTest = arrayOf(usage.toString(), rTotalMemory, rUsedMemory)

        print(arrayTest)

        return arrayTest


    }

    @SuppressLint("SetTextI18n")
    private fun logCpus(freqMetric: String = "cur"):  IntArray {
        binding.monitorCpuFreqLeft.text = ""
        binding.monitorCpuFreqRight.text = ""
        val n = Runtime.getRuntime().availableProcessors()
        val usages = IntArray(n)
        for (i in 0 until n) {
            val coreInfo = getCoreFreq(i)
            usages[i] = coreInfo[3]
            if (coreInfo[0] >= 1000000) {
                val frq = String.format("%.2f", coreInfo[0].toDouble() / 1000000)
                if (i % 2 == 0) {
                    binding.monitorCpuFreqLeft.append("CPU $i: $frq Ghz (${coreInfo[3]}%)\n")
                } else {
                    binding.monitorCpuFreqRight.append("CPU $i: $frq Ghz (${coreInfo[3]}%)\n")
                }
            } else {
                val frq = (coreInfo[0] / 1000)
                if (i % 2 == 0) {
                    binding.monitorCpuFreqLeft.append("CPU $i: $frq Mhz (${coreInfo[3]}%)\n")
                } else {
                    binding.monitorCpuFreqRight.append("CPU $i: $frq Mhz (${coreInfo[3]}%)\n")
                }
            }
        }
        // print overall usage
        binding.monitorCpuUsge.text = "${usages.average().toInt()} %"

        return usages
    }

    private fun readIntegerFile(filePath: String): Int {
        return try {
            val reader = BufferedReader(
                InputStreamReader(FileInputStream(filePath)), 1000
            )
            val line: String = reader.readLine()
            reader.close()
            line.toInt()
        } catch (e: Exception) {
            0
        }
    }

    private fun getCoreFreq(id: Int): IntArray {
        val cur = readIntegerFile("/sys/devices/system/cpu/cpu$id/cpufreq/scaling_cur_freq")
        val min = readIntegerFile("/sys/devices/system/cpu/cpu$id/cpufreq/scaling_min_freq")
        val max = readIntegerFile("/sys/devices/system/cpu/cpu$id/cpufreq/scaling_max_freq")
        if (max < 0 || max - min < 0) {
            return intArrayOf(0, 0, 0, 0)
        }
//        val usage = (cur - min) * 100 / (max - min)
        val usage = cur * 100 / max
        return intArrayOf(cur, min, max, usage)
    }

    private val Context.batteryCapacity: Double?
        @SuppressLint("PrivateApi")
        get() {
            val powerProfileClass = "com.android.internal.os.PowerProfile"
            try {
                (Class.forName(powerProfileClass).getConstructor(Context::class.java)
                    .newInstance(this)).apply {

                        return Class.forName(powerProfileClass)
                            .getMethod("getAveragePower", String::class.java)
                            .invoke(this, "battery.capacity") as Double
                    }

            } catch (e: Exception) {
                e.printStackTrace()
            }
            return null
        }

    private fun storeDynamicInfo (newDynamicInfoOriginal: DynamicInfoOriginal){
       /* val newDynamicInfoOriginalexample = DynamicInfoOriginal(
            "randomApp", "2022-10-31", "22",22,
            22,2,22,22,22,
            22,22,22,22,22,
            22,22)*/

        //make a retrofit builder object implements the ApiInterface so that we can call the function predefined in the ApiInterface
        val retrofitBuilderObject = RetrofitBuilder.buildData(ApiInterface::class.java)

        //create one more variable to get the data from the retrofit builder
        val requestCall = retrofitBuilderObject.storeDynamicInfo(newDynamicInfoOriginal)

        requestCall.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val responseBody = response.body()!!
                Log.i("MonitorActivity", "LogMessage"+response.body())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.i("MonitorActivity", "ErrorMessage"+t.message)
            }
        })
    }

    private fun storeSummaryInfo(newSummary: StoreFullSummaryModel){

        val retrofitBuilderObject = RetrofitBuilder.buildData(ApiInterface::class.java)

        val requestCall = retrofitBuilderObject.addSummary(newSummary)

        requestCall.enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                val responseBody = response.body()!!
                Log.i("MonitorActivity", "LogMessage"+response.body())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.i("MonitorActivity", "ErrorMessage"+t.message)
            }
        })


    }



















}