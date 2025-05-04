package mvc;

import java.io.Serializable;

public class Model extends Publisher implements Serializable {

    private String modelName = null;
    private boolean unsavedChanges = false;

    public Model(String modelName){
        this.modelName = modelName;
    }

    public String getFileName(){
        return modelName;
    }

    public void setFileName(String modelName){
        this.modelName = modelName;
    }

    public boolean getUnsavedChanges(){
        return unsavedChanges;
    }

    public void setUnsavedChanges(boolean unsavedChanges){
        this.unsavedChanges = unsavedChanges;
    }

    public void changed(){
        unsavedChanges = true;
        notifySubscribers();
    }
}