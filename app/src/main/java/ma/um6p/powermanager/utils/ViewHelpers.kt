package ma.um6p.powermanager.utils

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout

fun createLinearLayout(context: Context, orientation: String = "V"): LinearLayout {
    val linearLayout = LinearLayout(context)
    val linearParams = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.WRAP_CONTENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    if (orientation.lowercase() == "h") {
        linearLayout.orientation = LinearLayout.HORIZONTAL
    } else {
        linearLayout.orientation = LinearLayout.VERTICAL
    }
    linearLayout.layoutParams = linearParams
    return linearLayout
}