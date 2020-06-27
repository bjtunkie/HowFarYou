package com.chat.desktop.service;

import com.chat.core.persistence.ResourceLoader;
import com.chat.core.util.JsonUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    private String appName;
    private String sendBtn;

    public String getAppName() {
        return appName;
    }

    public String getSendBtn() {
        return sendBtn;
    }

    public App() {
        appName = "Lunar Crater";
        sendBtn = "Send";
    }

    public void writeToFile(String nameOfFile) {
        String json = JsonUtils.toJson(this);

        Path path = ResourceLoader.getAbsolutePath(nameOfFile);
        try {
            Files.writeString(path, json, Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
