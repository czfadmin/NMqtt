package com.zfuchen.ui.bean;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.beans.property.*;
import javafx.scene.control.Control;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

class InputComboboxer<T> extends Control {
    List<String> data = new ArrayList<>();
    // default style class, puts arrows on right, stacked vertically
    private final static String DEFAULT_STYLE_CLASS = "InputComboboxer";

    public InputComboboxer() {
        getStyleClass().add(DEFAULT_STYLE_CLASS);
//        setAccessibleRole(AccessibleRole.SPINNER);
        getEditor();
    }

    public final ReadOnlyObjectProperty<TextField> editorProperty() {
        if (editor == null) {
            editor = new ReadOnlyObjectWrapper<TextField>(this, "editor");
            textField = new ComboBoxListViewSkin.FakeFocusTextField();
            editor.set(textField);
        }
        return editor.getReadOnlyProperty();
    }

    private TextField textField;
    private ReadOnlyObjectWrapper<TextField> editor;

    public final TextField getEditor() {
        return editorProperty().get();
    }

    private void setText(T value) {
        String text = null;
    }

    private void commitEditorText() {
        if (!isEditable()) return;
        String text = getEditor().getText();
        SpinnerValueFactory<T> valueFactory = getValueFactory();
        if (valueFactory != null) {
            StringConverter<T> converter = valueFactory.getConverter();
            if (converter != null) {
                T value = converter.fromString(text);
                valueFactory.setValue(value);
            }
        }

    }

    private BooleanProperty editable;

    public final void setEditable(boolean value) {
        editableProperty().set(value);
    }

    public final boolean isEditable() {
        return editable == null || editable.get();
    }

    public final BooleanProperty editableProperty() {
        if (editable == null) {
            editable = new SimpleBooleanProperty(this, "editable", false);
        }
        return editable;
    }

    public final void setValueFactory(SpinnerValueFactory<T> value) {
        valueFactory.setValue(value);
    }

    public final SpinnerValueFactory<T> getValueFactory() {
        return valueFactory.get();
    }

    private ReadOnlyObjectWrapper<T> value = new ReadOnlyObjectWrapper<T>(this, "value");

    public final T getValue() {
        return value.get();
    }

    public final ReadOnlyObjectProperty<T> valueProperty() {
        return value;
    }

    public final ObjectProperty<SpinnerValueFactory<T>> valueFactoryProperty() {
        return valueFactory;
    }

    private ObjectProperty<SpinnerValueFactory<T>> valueFactory =
            new SimpleObjectProperty<SpinnerValueFactory<T>>(this, "valueFactory") {
                @Override
                protected void invalidated() {
                    value.unbind();

                    SpinnerValueFactory<T> newFactory = get();
                    if (newFactory != null) {
                        // this binding is what ensures the Spinner.valueProperty()
                        // properly represents the value in the value factory
                        value.bind(newFactory.valueProperty());
                    }
                }
            };

}