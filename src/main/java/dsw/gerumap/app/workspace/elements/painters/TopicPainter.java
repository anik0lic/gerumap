package dsw.gerumap.app.workspace.elements.painters;

import dsw.gerumap.app.maprepository.implementation.Element;
import dsw.gerumap.app.workspace.elements.Topic;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.geom.Ellipse2D;

@Getter
@Setter
public class TopicPainter extends ElementPainter{

    public TopicPainter(Element element) {
        super(element);
    }

    @Override
    public void draw(Graphics g) {
        Topic topic = (Topic) super.getElement();
        Graphics2D graphics = (Graphics2D) g;

        Ellipse2D.Float ellipse = new Ellipse2D.Float(topic.getX() - 50, topic.getY() - 25, topic.getW(), topic.getL());
        setS(ellipse);

        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
        graphics.setColor(Color.WHITE);
        graphics.fill(getS());

        graphics.setColor(topic.getColor());
        graphics.setStroke(new BasicStroke(topic.getStroke()));
        graphics.draw(getS());

        graphics.drawString(topic.getName(), topic.getX() -20, topic.getY());
    }

    @Override
    public boolean elementAt(Point position) {
        return getS().contains(position);
    }
}
