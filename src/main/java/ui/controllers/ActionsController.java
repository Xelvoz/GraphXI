package ui.controllers;

import javafx.fxml.Initializable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ui.actions.AbstractAction;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ActionsController implements Initializable {
    @Autowired
    private CentralController centralController;
    private AbstractAction action;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
