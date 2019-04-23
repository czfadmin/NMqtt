package com.zfuchen.extension

import com.zfuchen.controllers.MqttController
import javafx.scene.control.Tab
import javafx.scene.control.TabPane
import javafx.scene.paint.Color
import tornadofx.UIComponent
import tornadofx.onDelete
import tornadofx.style
import tornadofx.tab
import java.beans.EventHandler

//class NMqttTabView(title:String) : View("My View") {
//    override val root = tab(title) {
//
//    }
//}
fun TabPane.addTab(title: String, controller: MqttController) {
//    var handler:EventHandler=EventHandler()
    tab(title) {
       this@tab.setOnCloseRequest() {
           println(it)
           controller.disconnect()
        }
        style {
            textFill= Color.GREEN

        }

    }
}

fun TabPane.addTab(component: UIComponent, closeable: AutoCloseable) {
    tab(component.titleProperty.value) {

    }
}

class NmqttTabView(title: String) : Tab(title) {


}