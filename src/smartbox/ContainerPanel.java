package smartbox;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;

import javax.swing.*;
import mvc.*;

public class ContainerPanel extends AppPanel {
    java.awt.List components;
    public ContainerPanel(AppFactory factory) {
        super(factory);
        String[] directions = factory.getEditCommands();
        for (String direction : directions) {
            JButton button = new JButton(direction);
            button.addActionListener(this);
            controlPanel.add(button);
        }
    }

    // this override needed to re-initialize component fields table:
    @Override
    public void setModel(Model m) {
        super.setModel(m);
        ((Container) m).initContainer();
    }

    public static void main(String[] args) {
        AppPanel panel = new ContainerPanel(new ContainerFactory());
        panel.display();
    }
}