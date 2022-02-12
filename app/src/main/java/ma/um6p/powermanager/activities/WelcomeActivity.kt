package ma.um6p.powermanager.activities

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import ma.um6p.powermanager.databinding.WelcomeMainBinding
import ma.um6p.powermanager.server.Server


class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: WelcomeMainBinding

    companion object {
        val TAG: String = WelcomeActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WelcomeMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStart() {
        super.onStart()
        // connectBtn clicked
        binding.connectBtn.setOnClickListener {
//            val txt = binding.serverField.text
//            if (!txt.isNullOrEmpty()) {
//                Toast.makeText(this, "Connecting to address: $txt ", Toast.LENGTH_LONG).show()
//                connectToServer(it)
//                startActivity(Intent(it.context, MainActivity::class.java))
//                finish()
//            } else {
//                Toast.makeText(this, "Please provide a valid address", Toast.LENGTH_LONG).show()
//            }


//            val shell = Shell("sh")
//            val result = shell.run("cat /sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq")
//            if (result.isSuccess) {
//                binding.battery.text = result.stdout()
//            } else {
//                binding.battery.text = "ERR: ${result.stderr()}"
//
//            }


//            /sys/devices/system/cpu/cpu" + coreIndex + "/cpufreq/scaling_cur_freq
        }
        // monitorBtn clicked
        binding.monitorBtn.setOnClickListener {
            startActivity(Intent(it.context, MainActivity::class.java))
            finish()
        }

    }

    fun connectToServer(view: View) {
        val server = Server("192.168.1.2", port = "8080", this)
        server.connect()
    }

    private fun isAppRunning(): Boolean {
        val services =
            (getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager).runningAppProcesses
        return false
    }


}