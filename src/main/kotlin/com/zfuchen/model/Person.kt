package com.zfuchen.model
import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import tornadofx.getValue
import tornadofx.setValue
import java.time.LocalDate
import java.time.Period

class Person(id: Int, name: String, birthday: LocalDate?) {
    val idProperty = SimpleIntegerProperty(id)
    var id by idProperty
    val nameProperty = SimpleStringProperty(name)
    var name by nameProperty
    val birthdayProperty = SimpleObjectProperty(birthday)
    var birthday by birthdayProperty
    val age: Int get() = Period.between(birthday, LocalDate.now()).years
}
