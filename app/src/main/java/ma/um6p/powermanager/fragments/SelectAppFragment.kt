package ma.um6p.powermanager.fragments

import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import ma.um6p.powermanager.R
import ma.um6p.powermanager.adapters.AppsAdapter
import ma.um6p.powermanager.databinding.FragmentSelectAppBinding
import ma.um6p.powermanager.models.App


class SelectAppFragment() : Fragment() { // private var apps: ArrayList<App>
    private lateinit var binding: FragmentSelectAppBinding

    companion object {
        val TAG: String = SelectAppFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val i = requireActivity().intent

        binding = FragmentSelectAppBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this.context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = layoutManager
        val adapter = AppsAdapter(this.requireContext(), getUserApps())
        binding.recyclerView.adapter = adapter
    }

    private fun getUserApps(): ArrayList<App> {
        val appList = ArrayList<App>()
        // add idle entry
        val idleName = "Measure Idle power"
        val idlePackageName = "Measure energy consumption in idle state"
        val idleIcon = ResourcesCompat.getDrawable(requireContext().resources, R.drawable.idle, null)
        appList.add(App(ApplicationInfo(), idleName, idlePackageName, idleIcon!!))
        val apps =
            requireActivity().packageManager.getInstalledApplications(PackageManager.GET_META_DATA)
        apps.forEach { appInfo ->
            if (appInfo.flags and ApplicationInfo.FLAG_SYSTEM == 0) {
                val name = appInfo.loadLabel(requireActivity().packageManager).toString()
                val packageName = appInfo.packageName
                val drawableIcon = appInfo.loadIcon(requireActivity().packageManager)
//                val drawableIcon = packageManager.getApplicationIcon(appInfo)
                appList.add(App(appInfo, name, packageName, drawableIcon))
            }
        }
        return appList
    }
}