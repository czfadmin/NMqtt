package com.zfuchen.ui

import com.zfuchen.controllers.ConnectController
import tornadofx.*
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import org.eclipse.paho.client.mqttv3.MqttClient


class NMqttCenterView : View("NMqttCenter View") {
    var topic = "MQTT Examples"
    var content = "Message from MqttPublishSample"
    var qos = 2
    private var broker = "tcp://iot.eclipse.org:1883"
    private var clientId = "JavaSample"
    private var persistence = MemoryPersistence()
    private var isConnected = false
    private var sampleClient: MqttClient = MqttClient(broker, clientId, persistence)
    private val clientController: ConnectController = ConnectController(sampleClient)
    override val root = tabpane {
        tab("控制面板") {
            button("Connect Mqtt Server") {
                action {
                    runAsync {
                        isConnected = clientController.connectClient()
                    } ui {
                        this@tab.apply {
                            label {
                                text = (if (isConnected) {
                                    "连接上MQtt服务!"
                                } else "连接失败!")
                            }
                        }
                    }
                }
            }
        }
    }
}
