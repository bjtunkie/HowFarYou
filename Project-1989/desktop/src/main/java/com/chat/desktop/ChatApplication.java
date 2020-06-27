package com.chat.desktop;


import com.chat.core.persistence.ResourceLoader;
import com.chat.core.util.JsonUtils;
import com.chat.desktop.service.App;
import com.chat.desktop.service.FXWindow;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;


public class ChatApplication extends Application implements FXWindow, EventHandler<WindowEvent> {
    private static final Logger logger = LoggerFactory.getLogger(ChatApplication.class);
    private App app;
    private Scene scene;

    @Override
    public void init() throws Exception {
        super.init();
        app = JsonUtils.fromJson(ResourceLoader.getResourceAsString("app/in_es.json"), App.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Image icon = new Image(ResourceLoader.getFromResource("icons/icon-128.png"));

        scene = new Scene(new Group(), 1080, 740);
        stage.setTitle(app.getAppName());
        stage.setScene(scene);
        stage.getIcons().add(icon);
        stage.setOnCloseRequest(this);
        setScreen(SplashScreen.class);
        stage.show();
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }

    public static void main(String... args) {
        ResourceLoader.init(ResourceLoader.MAIN, ChatApplication.class);
        launch(args);
    }

    private volatile FXWindow currentController;

    @Override
    public <T extends FXWindow> void setScreen(Class<T> clazz) {
        try {
            Field field = clazz.getDeclaredField("RES");
            field.setAccessible(true);
            String nameOfResource = (String) field.get(null);
            if (nameOfResource == null || nameOfResource.isEmpty()) {
                logger.info("The RES variable associated with " + clazz.getName() + " is empty");
            }
            InputStream res = ResourceLoader.getFromResource(nameOfResource);
            logger.info("Loading resource %s bonded with controller %s", nameOfResource, clazz.getName());
            FXMLLoader loader = new FXMLLoader();
            Pane pane = loader.load(res);
            currentController = loader.getController();
            currentController.onScreenSet(this, app);
            scene.setRoot(pane);
        } catch (NoSuchFieldException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onScreenSet(FXWindow from, Object... input) {
        throw new RuntimeException("The onScreenSet fn of Main class isn't used");
    }

    @Override
    public void handle(WindowEvent e) {
        EventType<WindowEvent> type = e.getEventType();

        if (type.equals(WindowEvent.WINDOW_CLOSE_REQUEST)) {
            currentController.onClose();
            System.exit(0);
        }

    }
}