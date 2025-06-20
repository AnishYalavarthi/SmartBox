package mvc;

public interface AppFactory {
    public Model makeModel();

    public View makeView(Model m);

    public String getTitle();

    public String[] getHelp();

    public String about();

    public String[] getEditCommands();

    public Command makeEditCommmand(Model model, String type, Object source);
}