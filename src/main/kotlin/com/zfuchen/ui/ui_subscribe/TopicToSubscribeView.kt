package com.zfuchen.ui.ui_subscribe

import com.zfuchen.util.NMqttClientHelper.Companion.qosSelects
import javafx.geometry.Insets
import javafx.geometry.Orientation
import tornadofx.*

class TopicToSubscribeView : View("TopicToSubscribeView") {
    override val root =
        form {
            fieldset("Topic to subscribe", labelPosition = Orientation.VERTICAL) {
                minWidth = primaryStage.minWidth / 2
                minHeight = primaryStage.minHeight / 2
                vbox(10) {
                    paddingProperty().value = Insets(10.0)
                    field("Topic to subscribe") {
                        textfield {
                            fitToParentWidth()
                        }
                    }
                    field("QoS") {
                        combobox<String> {
                            fitToParentWidth()
                            items = qosSelects
                        }
                    }
                    button("Subscribe") {
                        action {
                            println("Subscribe something")
                        }
                    }

                }

            }
        }

}
