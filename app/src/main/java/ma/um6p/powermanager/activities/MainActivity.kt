package ma.um6p.powermanager.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ma.um6p.powermanager.databinding.ActivityMainBinding
import ma.um6p.powermanager.fragments.SelectAppFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    companion object {
        val TAG: String = MainActivity::class.java.simpleName
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        supportActionBar?.title = "My new title" // set the top title
//        val title = actionBar.getTitle().toString() // get the title
//        actionBar.hide() // or even hide the actionbar
    }

    override fun onResume() {
        super.onResume()
        // Load fragment
        supportFragmentManager.beginTransaction()
            .replace(binding.navContainer.id, SelectAppFragment()).commit()
        // remove loading message
        binding.loadingApps.text = ""
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}