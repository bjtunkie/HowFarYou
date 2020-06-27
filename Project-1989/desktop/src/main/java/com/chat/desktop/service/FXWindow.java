package com.chat.desktop.service;

public interface FXWindow {

    <T extends FXWindow> void setScreen(Class<T> clazz);

    default void onScreenSet(FXWindow from, Object... input) {

    }

    default void onClose() {

    }
}
