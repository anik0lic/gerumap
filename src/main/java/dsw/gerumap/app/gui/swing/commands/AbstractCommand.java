package dsw.gerumap.app.gui.swing.commands;

import java.io.IOException;

public abstract class AbstractCommand {

    public abstract void doCommand() throws IOException;
    public abstract void undoCommand() throws IOException;

}
