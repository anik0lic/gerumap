package dsw.gerumap.app.view.gui;

import dsw.gerumap.app.commands.CommandManager;
import dsw.gerumap.app.message.Message;
import dsw.gerumap.app.view.frame.MainFrame;

import javax.swing.*;

public class SwingGui implements Gui {

    private MainFrame instance;
    private CommandManager commandManager;

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        commandManager = new CommandManager();

        disableRedoAction();
        disableUndoAction();
        instance.setVisible(true);
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }

    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }

    @Override
    public CommandManager getCommandManager() {
        return commandManager;
    }

    @Override
    public void update(Object object) {
        JOptionPane.showMessageDialog(MainFrame.getInstance(), ((Message)object), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
