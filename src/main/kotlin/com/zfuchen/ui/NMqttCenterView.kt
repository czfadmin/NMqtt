package com.zfuchen.ui

import com.zfuchen.controllers.MqttController
import com.zfuchen.ui.ui_publish.TopicToPublishView
import com.zfuchen.ui.ui_publish.TopicToPublishView2
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleStringProperty
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.scene.control.Tab
import javafx.stage.StageStyle
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import tornadofx.*


class NMqttCenterView : View("NMqttCenter View") {
    private var conent = SimpleStringProperty()
    private var broker = "tcp://iot.eclipse.org:1883"
    private var clientId = "JavaSample"
    private var persistence = MemoryPersistence()
    private var isConnected = false
    private lateinit var tabs: ObservableList<Tab>
    private val clientController: MqttController = MqttController(broker, clientId, persistence)
    private var connectBtnDisableProperty = SimpleBooleanProperty()
    private var disconnectBtnDisableProperty = SimpleBooleanProperty()
    private var hasConnected: Boolean = false

    init {
        primaryStage.width = 720.0
        primaryStage.height = 480.0
    }

    override val root = tabpane {
        tab("控制面板") {
            isClosable = false
            this@NMqttCenterView.tabs = tabs
            hbox {
                paddingProperty().value = Insets(10.0,10.0,5.0,5.0)
                button("Create MQTT Client") {
                    action {
                        replaceWith<CreateMqttClientView>()
                    }
                }
                button("断开连接") {
                    disableProperty().bind(disconnectBtnDisableProperty)
                    disconnectBtnDisableProperty.value = !hasConnected
                    action {
                        connectBtnDisableProperty.value = false
                        disconnectBtnDisableProperty.value = true
                        hasConnected = false
                        clientController.disconnect()
                        if (tabs.size > 1) {
                            tabs.last().close()
                        }
                    }
                }
                button("Test Mqtt Client"){
                    action {
                        find<TopicToPublishView>().openModal(StageStyle.UTILITY,resizable = false)
                    }

                }
            }
        }
        tab("MQTT 客户端"){
            add(TopicToPublishView2())
        }

    }

}

