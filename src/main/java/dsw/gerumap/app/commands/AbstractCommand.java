package dsw.gerumap.app.commands;

import java.io.IOException;

public abstract class AbstractCommand {

    public abstract void doCommand() throws IOException;
    public abstract void undoCommand() throws IOException;

}
