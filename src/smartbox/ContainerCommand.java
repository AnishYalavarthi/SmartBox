package smartbox;

import mvc.*;

public class ContainerCommand extends Command {
    String cmd;

    public ContainerCommand(Model model, String cmd) {
        super(model);
        this.cmd = cmd;
    }

    @Override
    public void execute() throws Exception {
        Container container = (Container) model;
        String component;
        switch (cmd) {
            case "Add":
                component = Utilities.ask("Component name?");
                container.addComponent(component);
                break;
            case "Rem":
                component = Utilities.ask("Component name?");
                container.remComponent(component);
                break;
            case "Run":
                component = Utilities.ask("Component name?");
                container.launch(component);
                break;
        }

    }
}
