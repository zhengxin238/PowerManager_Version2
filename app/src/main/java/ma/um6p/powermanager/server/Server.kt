package ma.um6p.powermanager.server

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Server(var host: String, var port: String, var context: AppCompatActivity) {


    fun connect() {
        val conn = "Connection to $host:$port ..."
        Toast.makeText(context, conn, Toast.LENGTH_LONG).show()
    }
}