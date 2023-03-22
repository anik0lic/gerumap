package dsw.gerumap.app.core;

import dsw.gerumap.app.gui.swing.commands.CommandManager;
import dsw.gerumap.app.observer.Subscriber;

public interface Gui extends Subscriber {

    void start();

    void disableUndoAction();
    void disableRedoAction();
    void enableUndoAction();
    void enableRedoAction();

    CommandManager getCommandManager();
}
