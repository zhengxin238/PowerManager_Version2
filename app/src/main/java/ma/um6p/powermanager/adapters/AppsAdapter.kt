package ma.um6p.powermanager.adapters

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import ma.um6p.powermanager.R
import ma.um6p.powermanager.activities.MonitorActivity
import ma.um6p.powermanager.models.App

class AppsAdapter(val context: Context, private val apps: ArrayList<App>) :

    RecyclerView.Adapter<AppsAdapter.AppViewHolder>() {

    companion object {
        val TAG: String = AppsAdapter::class.java.simpleName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.list_app, parent, false)
        return AppViewHolder(view)
    }

    override fun getItemCount(): Int {

        return apps.size
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        holder.setData(app, position)
    }

    inner class AppViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var currentApp: App? = null
        private var currentPosition: Int = 0

        init {
            itemView.setOnClickListener {
                val killExcept = ArrayList<String>()
                killExcept.add("ma.um6p.powermanager")
                currentApp?.let { app ->
                    killExcept.add(app.packageName)
                    // Log.i(TAG, app.packageName)
                    // Log.i(TAG, app.toString())
                    killAllAppsExcept(killExcept)
                    context.startActivity(Intent(context, MonitorActivity::class.java))
                    MonitorActivity.SELECTED_APP = app
                    MonitorActivity.START_TIME = System.currentTimeMillis()
                    openApp(app.packageName)
                    (context as Activity).finish()
                }
            }

//            itemView.findViewById<ImageView>(R.id.card_icon).setOnClickListener {
//                currentApp?.let {
//                    Toast.makeText(context, "You selected ${currentApp!!.name}", Toast.LENGTH_SHORT)
//                        .show()
//                }
//            }
        }

        fun setData(app: App?, pos: Int) {
            app?.let {
                itemView.findViewById<TextView>(R.id.card_title).text = app.name
                itemView.findViewById<TextView>(R.id.card_package).text = app.packageName
                itemView.findViewById<ImageView>(R.id.card_icon).setImageDrawable(app.drawableIcon)
            }
            this.currentApp = app
            this.currentPosition = pos
        }
    }

    private fun killAllAppsExcept(packages: ArrayList<String>) {
        val amg: ActivityManager =
            context.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager
        // logBattery("BEFORE")
        for (app in apps) {
            if (packages.any { it == app.packageName }) {
                // Log.i(TAG, "Skip Killing ${app.packageName}")
                continue
            } else {
                amg.killBackgroundProcesses(app.packageName)
            }
        }
        // logBattery("AFTER")
    }

    private fun logBattery(txt: String) {
        val amg: ActivityManager =
            context.getSystemService(AppCompatActivity.ACTIVITY_SERVICE) as ActivityManager
        val memInfo = ActivityManager.MemoryInfo()
        amg.getMemoryInfo(memInfo)
        val availMemory = memInfo.availMem.toDouble() / (1024 * 1024 * 1024)
        val totalMemory = memInfo.totalMem.toDouble() / (1024 * 1024 * 1024)
        val usedMemory = totalMemory - availMemory
        val roundedAvailMemory = String.format("%.4f", availMemory).toDouble()
        val roundedTotalMemory = String.format("%.4f", totalMemory).toDouble()
        val roundedUsedMemory = String.format("%.4f", usedMemory).toDouble()

        Log.i("$TAG $txt", "Available RAM : $roundedAvailMemory GB")
        Log.i("$TAG $txt", "Used RAM      : $roundedUsedMemory GB")
        Log.i("$TAG $txt", "Total RAM     : $roundedTotalMemory GB")
    }

    private fun openApp(packageName: String) {

        try {
            val appIntent = context.packageManager.getLaunchIntentForPackage(packageName)
            context.startActivity(appIntent)
        } catch (e: NullPointerException) {
            Log.i(TAG, "openApp: IDLE STATE")
        }

    }

}