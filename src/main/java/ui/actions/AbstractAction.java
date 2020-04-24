package ui.actions;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public abstract class AbstractAction {

    public abstract EventHandler<MouseEvent> onDragEntered();

    public abstract EventHandler<MouseEvent> onDragExited();

    public abstract EventHandler<MouseEvent> onClick();

    public abstract EventHandler<MouseEvent> onPressed();
}
