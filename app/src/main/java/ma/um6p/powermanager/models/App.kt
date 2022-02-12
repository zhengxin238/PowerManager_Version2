package ma.um6p.powermanager.models

import android.content.pm.ApplicationInfo
import android.graphics.drawable.Drawable

data class App(
    var app: ApplicationInfo,
    var name: String,
    var packageName: String,
    var drawableIcon: Drawable,
)