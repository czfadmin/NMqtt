package com.zfuchen.controllers

import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException


class ConnectController(private val client: MqttClient? = null, private var connectOpts: MqttConnectOptions? = null) {
    init {

        if (connectOpts == null) {
            connectOpts = MqttConnectOptions()
            connectOpts!!.isCleanSession = true


        }
    }

    fun connectClient(): Boolean {
        var isConnected = false
        try {
            println("Connecting to broker: " + client!!.serverURI)
            client.connect(this.connectOpts)
            println("Connected");
            println("Publishing message: " + client.clientId)
            isConnected = true
        } catch (me: MqttException) {
            println("reason " + me.reasonCode)
            println("msg " + me.message);
            println("loc " + me.localizedMessage)
            println("cause " + me.cause)
            println("excep $me")
            me.printStackTrace()
        }
        return isConnected

    }
}