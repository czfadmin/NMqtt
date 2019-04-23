package com.zfuchen.ui.ui_publish
import com.zfuchen.util.NMqttClientHelper.Companion.playloadType
import com.zfuchen.util.NMqttClientHelper.Companion.qosSelects
import javafx.geometry.Insets
import javafx.geometry.Orientation
import javafx.scene.layout.Border
import javafx.scene.layout.BorderStroke
import javafx.scene.layout.BorderStrokeStyle
import javafx.scene.layout.Priority
import tornadofx.*

class TopicToPublishView : Fragment("My View") {

    override val root = vbox {
        prefHeight=600.0
        prefWidth=360.0
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
                                valueProperty().value= qosSelects[0]
                                items = qosSelects
                            }
                        }

                        checkbox("Retain") {

                        }

                        field("Playload Type") {
                            combobox<String> {
                                fitToParentWidth()
                                items = playloadType
                                valueProperty().value=playloadType[0]
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
                                        this@TopicToPublishView.close()
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



