package mvc;

import java.awt.event.ActionListener;
import java.io.*;
import java.util.Random;
import javax.swing.*;

public class Utilities {

    // asks user a yes/no question
    public static boolean confirm(String query) {
        int result = JOptionPane.showConfirmDialog(null,
                query, "choose one", JOptionPane.YES_NO_OPTION);
        return result == 0;
    }

    // asks user for info
    public static String ask(String query) {
        return JOptionPane.showInputDialog(null, query);
    }

    // tells user some info
    public static void inform(String info) {
        JOptionPane.showMessageDialog(null,info);
    }

    // tells user a lot of info
    public static void inform(String[] items) {
        String helpString = "";
        for(int i = 0; i < items.length; i++) {
            helpString = helpString + "\n" + items[i];
        }
        inform(helpString);
    }

    // tells user about an error
    public static void error(String gripe) {
        JOptionPane.showMessageDialog(null,
                gripe,
                "OOPS!",
                JOptionPane.ERROR_MESSAGE);
    }

    // tells user about an exception
    public static void error(Exception gripe) {
        gripe.printStackTrace();
        JOptionPane.showMessageDialog(null,
                gripe.getMessage(),
                "OOPS!",
                JOptionPane.ERROR_MESSAGE);
    }

    // asks user to save changes
    public static void saveChanges(Model model) {
        if (model.getUnsavedChanges() &&
                !Utilities.confirm("current model has unsaved changes, continue?")) {
            try {
                Utilities.save(model, false);
            } catch (IOException i){
                Utilities.inform("Invalid file");
            }
        }
    }

    // asks user for a file name
    public static String getFileName(String fName, Boolean open) {
        JFileChooser chooser = new JFileChooser();
        String result = null;
        if (fName != null) {
            // open chooser in directory of fName
            chooser.setCurrentDirectory(new File(fName));
        }
        if (open) {
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                result= chooser.getSelectedFile().getPath();
            }
        } else {
            int returnVal = chooser.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                result= chooser.getSelectedFile().getPath();
            }
        }
        return result;
    }

    public static String savedFile = "";

    public static void save(Model model, Boolean saveAs) throws IOException{
        if (savedFile.isEmpty() || saveAs) {
            savedFile = getFileName((String) null, false);
        }
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(savedFile));
        os.writeObject(model);
        os.close();
    }

    public static Model open(View view) throws IOException, ClassNotFoundException {
        String fName = getFileName(null, true);
        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
        Model model = (Model) is.readObject();
        view.setModel(model);
        is.close();
        return model;
    }

    // simple menu maker
    public static JMenu makeMenu(String name, String[] items, ActionListener handler) {
        JMenu result = new JMenu(name);
        for(int i = 0; i < items.length; i++) {
            JMenuItem item = new JMenuItem(items[i]);
            item.addActionListener(handler);
            result.add(item);
        }
        return result;
    }

    // random number generator
    public static Random rng = new Random(System.currentTimeMillis());

    public static void log(String msg) {
        System.out.println(msg); // for now
    }

    private static int nextID = 100;
    public static int getID() {
        return nextID++;
    }

}