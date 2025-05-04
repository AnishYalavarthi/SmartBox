package mvc;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

public class View extends JPanel implements Subscriber{

    protected Model model;

    public View(Model model) {
        this.model = model;
        model.subscribe(this);
        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setBackground(Color.LIGHT_GRAY);
    }

    public void update() {
        repaint();
    }

    public void setModel(Model model) {
        this.model.unsubscribe(this);
        this.model = model;
        this.model.subscribe(this);
        repaint();
    }

}