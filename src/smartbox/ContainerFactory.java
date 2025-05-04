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
        //change
        return new String[0];
    }

    @Override
    public String about() {
        return "Anish Yalavarthi, 5/1/2025";
    }

    @Override
    public String[] getEditCommands() {
        //change
        return new String[0];
    }

    @Override
    public Command makeEditCommmand(Model model, String type, Object source) {
        //change
        return null;
    }
}
