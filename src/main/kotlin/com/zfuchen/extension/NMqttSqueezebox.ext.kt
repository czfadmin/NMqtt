package com.zfuchen.extension

import javafx.scene.Node
import javafx.scene.control.ContextMenu
import javafx.scene.layout.Pane
import tornadofx.SqueezeBox
import tornadofx.add
import tornadofx.fold


fun SqueezeBox.addFold(
    title: String,
    expanded: Boolean,
    icon: Node? = null,
    closeable: Boolean,
    ctxMenu: ContextMenu? = null,
    pane: Pane
) {
    fold(title, expanded = expanded, closeable = closeable) {
        contextMenu = ctxMenu
        add(pane)
    }
}