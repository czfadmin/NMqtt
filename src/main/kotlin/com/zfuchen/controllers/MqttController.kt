package com.zfuchen.controllers

import com.sun.org.apache.xpath.internal.operations.Bool
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence


class MqttController(
    private var broker: String,
    private var clientId: String,
    private var persistence: MemoryPersistence = MemoryPersistence(),
    private var connectOpts: MqttConnectOptions? = null,
    private var qos: Int = 2
) {
    private var content = "Message from MqttPublishSample"
    private lateinit var client:MqttClient
    init {

        if (connectOpts == null) {
            connectOpts = MqttConnectOptions()
            connectOpts!!.isCleanSession = true
        }
       client = MqttClient(this.broker,this.clientId,this.persistence)
    }

    fun connectClient(): Boolean {
        var isConnected = false
        try {
            println("Connecting to broker: " + client.serverURI)
            client.connect(this.connectOpts)
            println("Connected")
            isConnected = true
        } catch (me: MqttException) {
            println("reason " + me.reasonCode)
            println("msg " + me.message)
            println("loc " + me.localizedMessage)
            println("cause " + me.cause)
            println("excep $me")
            me.printStackTrace()
        }
        return isConnected
    }

    fun publish(topic: String = "MQTT Examples", message: String? = null) {
        if (message == null) content = message!!
        val msg = MqttMessage(content.toByteArray())
        msg.qos = qos
        client.publish(topic, msg)
    }

    fun disconnect() {
        this.client.disconnect()
        println("Disconnected")
    }

    fun setQos(qos: Int) {
        this.qos = qos
    }
    fun isConnected():Boolean{
        return this.client.isConnected
    }

}