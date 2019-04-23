package com.zfuchen.ui.bean

import javafx.beans.property.SimpleStringProperty
import javafx.collections.FXCollections
import tornadofx.View
import tornadofx.combobox
import tornadofx.hbox

class InputCombobox : View("My View") {
    private var comboboxItems = FXCollections.observableArrayList<String>()
    override val root = hbox {
        combobox(values = comboboxItems) {
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
}
