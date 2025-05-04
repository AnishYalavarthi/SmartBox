package smartbox;

import java.util.*;

import mvc.*;


public class Container extends Model {

    private Map<Class<?>, Component> providedInterfaces = new HashMap<Class<?>, Component>();
    private Map<Class<?>, Component> requiredInterfaces = new HashMap<Class<?>, Component>();
    private Map<String, Component> components = new HashMap<String, Component>();

    public Container() {
        super("Container");
    }

    public Collection<Component> getComponents() {
        return components.values();
    }

    public void addComponent(String name) throws Exception {
        String qualName = "smartbox.components." +  name;
        Object obj = Class.forName(qualName).newInstance();
        addComponent((Component)obj);
    }


    private void addComponent(Component component) throws Exception {
        component.setContainer(this);
        components.put(component.name, component);
        for(Class<?> intf: component.getProvidedInterfaces()) {
            providedInterfaces.put(intf,  component);
        }
        for(Class<?> intf: component.getRequiredInterfaces()) {
            requiredInterfaces.put(intf,  component);
        }
        findProviders();
        changed();
    }

    public void remComponent(String name) throws Exception {
        Component component = components.get(name);
        components.remove(name);
        for(Class<?> intf: component.getProvidedInterfaces()) {
            for(Component client: components.values()) {
                if (client.getRequiredInterfaces().contains(intf)) {
                    client.setProvider(intf,  null);
                    requiredInterfaces.put(intf, client);
                }
            }
        }
        changed();
    }

    // each time we add a new component we try to connect as many clients and providers as we can:
    private void findProviders() throws Exception {
        Set<Class<?>> reqInterfaces = requiredInterfaces.keySet();
        for(Class<?> intf: reqInterfaces) {
            Component client = requiredInterfaces.get(intf);
            Component provider = providedInterfaces.get(intf);
            if (client != null && provider != null) {
                client.setProvider(intf,  provider);
                requiredInterfaces.put(intf, null);
            }
        }
    }

    public void launch(String name) throws Exception {
        try {
            Component c = components.get(name);
                if (c != null && c.getProvidedInterfaces().contains(App.class)){
                    App a = (App) c;
                    a.main();
                }
            }
        catch (Exception e) {
            mvc.Utilities.error(e);
            e.printStackTrace();
        }
    }

    // needed by File/Open to restore component.fields
    public void initContainer(){
        for(Component c: components.values()) c.initComponent();
        changed();
    }

}