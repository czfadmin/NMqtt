package com.zfuchen.ui.ui_publish

import com.zfuchen.util.NMqttClientHelper.Companion.payloadType
import com.zfuchen.util.NMqttClientHelper.Companion.qosSelects
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.layout.Border
import javafx.scene.layout.BorderStroke
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.layout.Priority
import tornadofx.*

class TopictoPublishView : Fragment("My View") {
    init {

    }

    override val root = vbox {
//        primaryStage.width = 380.0
//        primaryStage.height = 600.0
//        primaryStage.maxWidth = 380.0
//        primaryStage.minWidth = 380.0
//        primaryStage.minHeight = 600.0
//        primaryStage.maxHeight = 600.0

        prefHeight=600.0
        prefWidth=360.0
//        minWidth=380.0
//        minHeight=600.0
//        maxHeight=600.0
//        maxWidth=380.0
        paddingProperty().value = Insets(10.0)
        borderpane {
            left = form {
                border = Border(BorderStroke(null, BorderStrokeStyle.SOLID, null, null))
                fieldset("Publisher", labelPosition = Orientation.VERTICAL) {

                    vbox(10) {
                        paddingProperty().value = Insets(10.0)
                        field("Topic to publish") {
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

                        checkbox("Retain") {

                        }

                        field("Playload Type") {
                            combobox<String> {
                                fitToParentWidth()
                                items = payloadType
                            }
                        }
                        vbox {
                            field("Playload") {
                                textarea {
                                    prefRowCount = 5
                                    prefColumnCount = 24
                                    prefWidth = 300.0
                                    vgrow = Priority.ALWAYS
                                }
                            }
                            hbox(10) {
                                button("Publish") {
                                    action {
                                        println("Publish something")
                                    }
                                }
                                button("取消") {
                                    action {
                                        this@TopictoPublishView.close()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



