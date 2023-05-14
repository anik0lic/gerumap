package dsw.gerumap.app.workspace.elements.painters;

import dsw.gerumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;

@Getter
@Setter
@NoArgsConstructor
public abstract class ElementPainter{

    private Element element;
    private Shape s;

    protected ElementPainter(Element element){
        this.element = element;
    }

    public abstract void draw (Graphics g);

    public abstract boolean elementAt (Point position);

}
