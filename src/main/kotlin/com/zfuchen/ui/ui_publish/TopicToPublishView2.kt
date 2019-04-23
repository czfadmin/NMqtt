package com.zfuchen.ui.ui_publish

import com.zfuchen.model.Person
import com.zfuchen.util.NMqttClientHelper
import javafx.collections.FXCollections
import javafx.collections.ObservableList
import javafx.geometry.Insets
import javafx.scene.layout.Background
import javafx.scene.layout.BackgroundFill
import javafx.scene.layout.BorderPane
import javafx.scene.layout.CornerRadii
import javafx.scene.paint.Color
import tornadofx.*
import java.time.LocalDate

class TopicToPublishView2 : View("Topic To Publish") {
	init {
		primaryStage.width = 1180.0
		primaryStage.height = 720.0
		primaryStage.minHeight = 720.0
		primaryStage.maxWidth = 1180.0
		primaryStage.minWidth = 1180.0
	}
	
	private var comboboxItems = FXCollections.observableArrayList<String>()
	override val root = borderpane {
		top = addSomeForm(comboboxItems)
		left = scrollpane {
			label("Nothing here")
		}
		center = scrollpane {
					isFitToWidth = true
					isFitToHeight = true
					pannableProperty().value = true
					vbox(2) {
						tabpane {
							tab("All") {
								isClosable = false
								vbox(2) {
									form {
										fieldset {
											field {
												vbox(6) {
													label { text = "No messages" }
													line(30, 30, 1145, 30)
												}
											}
											hbox(8) {
												field {
													label("Topic")
													combobox(values = comboboxItems) {
														visibleRowCount = 5
														isEditable = true
														valueProperty().addListener { _, _, n ->
															if (!comboboxItems.contains(n)) {
																comboboxItems.add(n)
															}
														}
													}
												}
												field {
													label("Qos")
													combobox<String> {
														valueProperty().value =
																NMqttClientHelper.qosSelects[0]
														items = NMqttClientHelper.qosSelects
													}
												}
												field {
													label("Time")
													textfield { }
												}
											}
										}
										fieldset {
											vbox(2) {
												paddingProperty().value =
														Insets(2.0, .0, .0, .0)
												label("Data")
												tableview<Person> {
													fitToParentWidth()
													items = listOf(
															Person(1, "Samantha Stuart",
																	LocalDate.of(1981, 12, 4)),
															Person(2, "Tom Marks",
																	LocalDate.of(2001, 1, 23)),
															Person(3, "Stuart Gills",
																	LocalDate.of(1989, 5, 23)),
															Person(3, "Nicole Williams",
																	LocalDate.of(1998, 8, 11))
													).observable()
													column("ID", Person::id)
													column("Name", Person::name)
													column(
															"Birthday", Person::birthday)
												}
											}
										}
									}
								}
							}
						}
						squeezebox {
							multiselect = false
							fold("Received messages summary ", expanded = true,
									closeable = false) {
								stackpane {
									tableview<Person> {
										items = listOf(
												Person(1, "Samantha Stuart",
														LocalDate.of(1981, 12, 4)),
												Person(2, "Tom Marks",
														LocalDate.of(2001, 1, 23)),
												Person(3, "Stuart Gills",
														LocalDate.of(1989, 5, 23)),
												Person(3, "Nicole Williams",
														LocalDate.of(1998, 8, 11))
										).observable()
										column("ID", Person::id)
										column("Name", Person::name)
										column("Birthday", Person::birthday)
									}
								}
							}
						}
					}
		}
	}
}

private fun <T> BorderPane.addSomeForm(comboboxItems: ObservableList<T>): Form {
	return form {
		fieldset("Publish Message") {
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
						}
					}
				}
				
			}
		}
	}
}

