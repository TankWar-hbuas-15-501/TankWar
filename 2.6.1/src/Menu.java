import javafx.scene.layout.Pane;
import oracle.jrockit.jfr.JFR;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Menu {
    private JFrame menuFrame;

    private JPanel panelControl;
    Menu(){
        iniMenu();
    }
    void iniMenu() {
        menuFrame=new JFrame("坦克大战");
        menuFrame.setSize(1400,700);
        menuFrame.setLayout(new GridLayout(3,1));
        menuFrame.setVisible(true);
    }
    private static ImageIcon createImageIcon(String path,String description){
        URL imgURL=Menu.class.getResource(path);
        if(imgURL!=null)return new ImageIcon(imgURL,description);
        else{
            System.err.println("Couldn't find file:"+path);
            return null;
        }
    }
    public static void main(String[] args){
            Menu menu=new Menu();

    }
}
