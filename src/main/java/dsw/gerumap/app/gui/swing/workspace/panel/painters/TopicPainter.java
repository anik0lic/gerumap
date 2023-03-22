package dsw.gerumap.app.gui.swing.workspace.panel.painters;

import dsw.gerumap.app.gui.swing.maprepository.implementation.Element;
import dsw.gerumap.app.gui.swing.workspace.panel.Topic;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@Getter
@Setter
public class TopicPainter extends ElementPainter{
    private Shape s;
    private Element element;
    private Topic topic;

    public TopicPainter(Element element) {
        this.element = element;
        topic = (Topic)this.element;
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;

        graphics.setColor(element.getColor());
        graphics.setStroke(new BasicStroke(element.getStroke()));

        s = new Ellipse2D.Float(topic.getX()-50, topic.getY()-25, topic.getW(), topic.getL());
        graphics.draw(getS());

        if (element instanceof Topic){
            Topic el = (Topic)element;
            g.drawString(el.getName(), (int)el.getX()-20,
                    (int)el.getY());
        }
    }

    @Override
    public boolean elementAt(Point position) {
        return getS().contains(position);
    }
}
