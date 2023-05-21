package dsw.gerumap.app.commands;

import dsw.gerumap.app.core.ApplicationFramework;
import dsw.gerumap.app.view.frame.MainFrame;
import lombok.Getter;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command) throws IOException {
        while (currentCommand < commands.size()) {
            commands.remove(currentCommand);
        }
        commands.add(command);
        doCommand();
    }

    public void doCommand () throws IOException {
         if (currentCommand < commands.size()) {
             commands.get(currentCommand++).doCommand();
             MainFrame.getInstance().getProjectExplorerTreeView().update(this);
//             SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTreeView());
             ApplicationFramework.getInstance().getGui().enableUndoAction();
         }
         if (currentCommand == commands.size()) {
             ApplicationFramework.getInstance().getGui().disableRedoAction();
         }
    }

    public void undoCommand() throws IOException {
        if (currentCommand > 0) {
            ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
            MainFrame.getInstance().getProjectExplorerTreeView().update(this);
//            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getMapTree().getTreeView());
        }

        if (currentCommand == 0) {
            ApplicationFramework.getInstance().getGui().disableUndoAction();
        }
    }

}
