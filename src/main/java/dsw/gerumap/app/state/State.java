package dsw.gerumap.app.state;

import dsw.gerumap.app.gui.swing.workspace.MapView;

import java.io.IOException;

public abstract class State {
    public abstract void misKliknut(int x, int y, MapView map) throws IOException;

    public abstract void misOtpusten(int x, int y, MapView map) throws IOException;

    public abstract void misPovucen(int x, int y, MapView map) throws IOException;
}
