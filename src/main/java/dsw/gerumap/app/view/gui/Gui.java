package dsw.gerumap.app.view.gui;

import dsw.gerumap.app.commands.CommandManager;
import dsw.gerumap.app.observer.Subscriber;

public interface Gui extends Subscriber {

    void start();

    void disableUndoAction();
    void disableRedoAction();
    void enableUndoAction();
    void enableRedoAction();

    CommandManager getCommandManager();
}
