package dsw.gerumap.app.gui.swing.workspace.elements;

import dsw.gerumap.app.maprepository.composite.MapNode;
import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.gui.swing.workspace.elements.painters.ConnectionPainter;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Topic extends Element{
    private int x, y;
    private int w = 100, l = 50;
    private transient List<ConnectionPainter> connectionList;

    public Topic(String name, MapNode parent, Color color, int stroke, int x, int y) {
        super(name, parent, color, stroke);
        connectionList = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public void setX(int x) throws IOException {
        this.x = x;
        notify(this);
    }

    public void setY(int y) throws IOException {
        this.y = y;
        notify(this);
    }
}
