package com.zfuchen.ui

import tornadofx.*

class TopMenuView : View("My View") {
    override val root = menubar {
        isUseSystemMenuBar = true
        menu("File") {
            menu("Connect") {
                item("Facebook").action { println("Connecting Facebook") }
                item("Twitter")
            }
            separator()
            item("Save")
            item("Quit", "Shortcut+Q").action {
                println("Quitting")
                System.exit(0)
            }
        }
        menu("Edit") {
            item("Copy")
            item("Paste")
        }
        menu("关于") {
            item("关于")
            item("帮助")
        }
    }

}
