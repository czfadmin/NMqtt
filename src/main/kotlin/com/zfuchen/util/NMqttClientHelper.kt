package com.zfuchen.util

import javafx.collections.FXCollections

class NMqttClientHelper {
    companion object {
        val qosSelects = FXCollections.observableArrayList<String>(
            "0-Atleast Once", "1-Atleast Once", "2-Exactly Once"
        )!!
        val playloadType = FXCollections.observableArrayList(
            "String/JSON/XML/Characters",
            "Binary Array",
            "Octal Array",
            "Decimal Array",
            "Hex Array"
        )!!
        val inputFormatType = FXCollections.observableArrayList(
            "Input Format Type: Plain", "Input Format Type: Hex", "Input Format Type: Base64"
        )!!
        val publishType=FXCollections.observableArrayList(
            "Publish(default) ",
            "Publish with script",
            "save current message as script",
            "Restore recent message"
        )
    }
}