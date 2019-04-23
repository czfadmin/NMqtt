//package com.zfuchen.ui
//
//import tornadofx.*
//
//class NMqttDrawerView : View("TornadoFX Info Browser") {
//	override val root = drawer {
//		item("Screencasts", expanded = true) {
//			webview {
//				prefWidth = 470.0
////				engine.userAgent = iPhoneUserAgent
////				engine.load(TornadoFXScreencastsURI)
//			}
//		}
//		item("Links") {
//			listview(links) {
//				cellFormat { link ->
//					graphic = hyperlink(link.name) {
//						setOnAction {
//							hostServices.showDocument(link.uri)
//						}
//					}
//				}
//			}
//		}
//		item("People") {
//			tableview(people) {
//				column("Name", Person::name)
//				column("Nick", Person::nick)
//			}
//		}
//	}
//
//	class Link(val name: String, val uri: String)
//	class Person(val name: String, val nick: String)
//
//	// Sample data variables left out (iPhoneUserAgent, TornadoFXScreencastsURI, people and links)
//}