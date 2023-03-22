package dsw.gerumap.app.gui.swing.workspace.panel.painters;

import dsw.gerumap.app.gui.swing.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Line2D;

@Getter
@Setter
public class ConnectionPainter extends ElementPainter {
    private Shape s;
    private Element element;
    private Point pos1;
    private Point pos2;

    public ConnectionPainter(Point pos1, Point pos2, Element element) {
        this.element = element;
        this.pos1 = pos1;
        this.pos2 = pos2;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(element.getColor());

        s = new Line2D.Float(pos1.x, pos1.y, pos2.x, pos2.y);

        graphics.setStroke(new BasicStroke(2));
        graphics.draw(getS());
    }

    @Override
    public boolean elementAt(Point position) {
        return getS().contains(position);
    }
}
