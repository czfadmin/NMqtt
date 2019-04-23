package com.zfuchen.ui

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.layout.VBox
import tornadofx.*

class CreateMqttClientView : View("Create MQTT Client") {
    private var clientNameProperty = SimpleStringProperty()
    private var clientIdProperty = SimpleStringProperty()
    private var qosProperty = SimpleStringProperty()

    init {
        primaryStage.width = 720.0
        primaryStage.height = 480.0
    }

    override val root = form {
        fieldset {
            field {
                vbox {
                    hbox {
                        alignmentProperty().value = Pos.CENTER_LEFT

                        button("Back") {
                            action {
                                replaceWith<NMqttCenterView>()
                            }
                        }
                        label("MQTT CLIENT SETTINGS") {
                            paddingProperty().value = Insets(0.0,0.0,5.0,5.0)
                        }
                    }
                    vbox {
                        createVbox("Create MQTT client:", clientNameProperty)
                        createVbox("Client id:", clientIdProperty)
                        createVbox("QOS:", qosProperty)
                    }
                }

            }

            hbox {
                button("Save") {
                    action {

                    }
                }

            }
        }
    }
}

fun VBox.createVbox(labelContent: String, property: SimpleStringProperty): VBox {
    return vbox {

        label(labelContent){
            paddingProperty().value = Insets(5.0,0.0,5.0,5.0)
        }
        textfield(property)
    }
}
