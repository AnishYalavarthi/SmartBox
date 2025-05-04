package smartbox;

import mvc.*;

public class ContainerFactory implements AppFactory {

    @Override
    public Model makeModel(){
        return new Container();
    };

    @Override
    public View makeView(Model m) {
        return new ContainerView((Container)m);
    }

    @Override
    public String getTitle() {
        return "Smart Box";
    }

    @Override
    public String[] getHelp() {
        return new String[]{"Add: loads new component into container",
                            "Rem: removes provided component from container",
                            "Run: runs provided component application"};
    }

    @Override
    public String about() {
        return "Anish Yalavarthi, 5/1/2025";
    }

    @Override
    public String[] getEditCommands() {
        return new String[]{"Add", "Rem", "Run"};
    }

    @Override
    public Command makeEditCommmand(Model model, String type, Object source) {
        return new ContainerCommand(model, type);
    }
}
