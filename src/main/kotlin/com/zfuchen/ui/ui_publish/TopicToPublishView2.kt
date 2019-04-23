package com.zfuchen.ui.ui_publish

import com.zfuchen.util.NMqttClientHelper
import javafx.collections.FXCollections
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import tornadofx.*

class TopicToPublishView2 : View("Topic To Publish") {
    init {
        primaryStage.width = 1096.0
        primaryStage.height = 720.0
        primaryStage.minHeight = 720.0
        primaryStage.maxWidth = 1180.0
        primaryStage.minWidth = 1180.0
    }

    private var comboboxItems = FXCollections.observableArrayList<String>()
    override val root = squeezebox {
        fold("Publish Message", expanded = true, closeable = false) {
            prefHeight = 12.0
            minHeight = 12.0
            maxHeight = 12.0
            form {
                fieldset {
                    hbox(10) {
                        vbox {
                            field("Topic") {
                                combobox(values = comboboxItems) {
                                    fitToParentWidth()
                                    visibleRowCount = 5
                                    isEditable = true
                                    valueProperty().addListener { _, _, n ->
                                        if (!comboboxItems.contains(n)) {
                                            comboboxItems.add(n)
                                            println(n)
                                        }
                                    }
                                }
                            }
                            field("Data") {
                                textfield {
                                    fitToParentWidth()
                                }
                            }
                        }
                        vbox {
                            hbox(2) {
                                field {
                                    label("Qos")
                                    combobox<String> {
                                        valueProperty().value = NMqttClientHelper.qosSelects[0]
                                        items = NMqttClientHelper.qosSelects
                                    }
                                }
                                field("Retained") {
                                    togglebutton {
                                        textFill = Color.WHITE
                                        background = Background(
                                            BackgroundFill(
                                                Color.GRAY,
                                                CornerRadii(50.0),
                                                null
                                            )
                                        )
                                        selectedProperty().value = false
                                        val stateText = selectedProperty().stringBinding {
                                            if (it == true) "ON" else "OFF"

                                        }
                                        textProperty().bind(stateText)
                                        selectedProperty().addListener { _, o, n ->
                                            if (n) {
                                                background = Background(
                                                    BackgroundFill(
                                                        Color.GREEN,
                                                        CornerRadii(50.0),
                                                        null
                                                    )
                                                )
                                            } else {
                                                background = Background(
                                                    BackgroundFill(
                                                        Color.GRAY,
                                                        CornerRadii(50.0),
                                                        null
                                                    )
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                            field {
                                combobox<String> {
                                    fitToParentWidth()
                                    valueProperty().value = NMqttClientHelper.inputFormatType[0]
                                    items = NMqttClientHelper.inputFormatType
                                }
                            }
                        }
                        vbox {
                            field("Publish Type") {
                                combobox<String> {
                                    valueProperty().value = NMqttClientHelper.publishType[0]
                                    items = NMqttClientHelper.publishType
                                }
                            }
                            field("Publish") {
                                button {
                                    //                                    println("Width:${this@squeezebox.prefWidth},Height:${this@squeezebox.prefHeight}")
                                }
                            }
                        }

                    }
                }
            }

        }
//        addFold("Scripted publication", expanded = false, closeable = true, pane = stackpane {
//            label("Nothing here")
//        })
        fold("Scripted publication", expanded = false, closeable = false) {
            contextMenu = contextmenu {
                item("Send Email").action {
                    //                    selectedItem?.apply { println("Sending Email to $name") }
                }
                item("Change Status").action {
                    //                    selectedItem?.apply { println("Changing Status for $name") }
                }
            }
            stackpane {
                label("Nothing here")
            }
        }
        fold("Subscriptions and received messages", expanded = true, closeable = false) {
            stackpane {
                label("Nothing here")
            }
        }
        fold("Received messages summary ", expanded = true, closeable = false) {
            stackpane {
                label("Nothing here")
            }
        }
    }
}

