package dsw.gerumap.app.workspace.elements.painters;

import dsw.gerumap.app.workspace.elements.Connection;
import dsw.gerumap.app.maprepository.implementation.Element;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

@Getter
@Setter
public class ConnectionPainter extends ElementPainter {

    public ConnectionPainter(Element element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Connection connection = (Connection)super.getElement();
        connection.setNewCoordinates();
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(Color.GRAY);

        Line2D.Float line = new Line2D.Float(connection.getXF(), connection.getYF(), connection.getXS(), connection.getYS());
        setS(line);
        graphics.draw(getS());
    }

    @Override
    public boolean elementAt(Point position) {
        return getS().contains(position);
    }
}
