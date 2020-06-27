package com.chat.desktop.login;

import com.chat.desktop.service.AbstractFXWindow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class LoginScreen extends AbstractFXWindow {

    private static final String RES = "login/loginpage.fxml";

    @FXML
    private Text actionTarget;

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {

        actionTarget.setText("Sign in button pressed");
        setScreen(SignUpScreen.class);
    }

    @Override
    public void work() {

    }
}
