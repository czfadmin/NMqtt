package com.zfuchen

import com.zfuchen.ui.MyView
import tornadofx.App
import tornadofx.reloadStylesheetsOnFocus
//import tornadofx.FX.Companion.reloadStylesheetsOnFocus
import tornadofx.reloadViewsOnFocus

class MyApp: App(MyView::class){
    init {
        reloadStylesheetsOnFocus()
        reloadViewsOnFocus()
    }
}