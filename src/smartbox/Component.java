package smartbox;

import java.util.*;
import java.io.Serializable;
import java.lang.reflect.*;



public class Component implements Serializable {

    private Set<Class<?>> requiredInterfaces;
    private Set<Class<?>> providedInterfaces;
    private transient Map<Class<?>, Field> fields; // transient because Field not serializable
    protected Container container;
    protected String name;

    public Component() {
        fields = new HashMap<Class<?>, Field>();
        providedInterfaces = new HashSet<Class<?>>();
        requiredInterfaces = new HashSet<Class<?>>();
        computeRequiredInterfaces();
        computeProvidedInterfaces();
        container = null;
        name = this.getClass().getSimpleName();
    }

    // add needed getters & setters
    public void setContainer(Container container) {
        this.container = container;
    }

    public Set<Class<?>> getProvidedInterfaces() {
        return providedInterfaces;
    }

    public Set<Class<?>> getRequiredInterfaces() {
        return requiredInterfaces;
    }

    public String toString() { return name; }

    // initializes fields and requiredInterfaces
    public void computeRequiredInterfaces() {
        Field[] fieldArray = this.getClass().getDeclaredFields();
        for(int i = 0; i < fieldArray.length; i++) {
            if (fieldArray[i].getType().isInterface()){
                fields.put(fieldArray[i].getType(), fieldArray[i]);
                requiredInterfaces.add(fieldArray[i].getType());
            }
        }
    }

    // initializes provided interfaces
    public void computeProvidedInterfaces() {
        Class<?>[] interfaces = this.getClass().getInterfaces();
        providedInterfaces.addAll(Arrays.asList(interfaces));
    }

    // set the field of this object to the provider
    public void setProvider(Class<?> intf, Component provider) throws Exception {
        Field field = fields.get(intf);
        if (field != null) {
            field.setAccessible(true);
            field.set(this, provider);
        }
    }

    // needed by file/open
    public void initComponent() {
        fields = new HashMap<Class<?>, Field>();
        computeProvidedInterfaces();
        computeRequiredInterfaces();
    }

}