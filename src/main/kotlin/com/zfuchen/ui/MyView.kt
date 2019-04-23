package com.zfuchen.ui

import tornadofx.*

class MyView : View("NMqtt Client") {

    private val topView = find(TopMenuView::class)
    private var centerView = find(NMqttCenterView::class)
    private val bottomView = find(BottomStatusView::class)

    init {
        primaryStage.width = 720.0
        primaryStage.height = 480.0

    }

    override val root = borderpane {
        top = topView.root
        center = centerView.root
        bottom = bottomView.root
        contextmenu {
            item("Send Email").action {
                //                selectedItem?.apply { println("Sending Email to $name") }
            }
            item("Change Status").action {
                //                selectedItem?.apply { println("Changing Status for $name") }
            }
        }
    }
}
