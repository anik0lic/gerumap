package dsw.gerumap.app.maprepository.implementation;

import dsw.gerumap.app.maprepository.composite.MapNode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
import java.io.IOException;

@NoArgsConstructor
@Getter
@Setter
public abstract class Element extends MapNode {
    private Color color; // bilo transient
    private Color color2;
    private int stroke;

    public Element(String name, MapNode parent) {
        super(name, parent);
    }

    public Element(String name, MapNode parent, Color color, int stroke) {
        super(name, parent);
        this.color = color;
        this.stroke = stroke;
    }

    public void setColor(Color color) throws IOException {
        this.color = color;
        notify(this);
    }

    public void setStroke(int stroke) throws IOException {
        this.stroke = stroke;
        notify(this);
    }
}
