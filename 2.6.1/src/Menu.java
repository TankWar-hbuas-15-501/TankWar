import javafx.scene.layout.Pane;
import oracle.jrockit.jfr.JFR;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Menu {
    private JFrame menuFrame;

    private JPanel panelControl=new JPanel();
    private Box boxVer=Box.createVerticalBox(),boxHor=Box.createHorizontalBox();
    Menu(){
        iniMenu();
    }
    void addButton(String text,String icon){
        JButton temp=new JButton(text,createImageIcon(icon,""));
        panelControl.add(temp);
        menuFrame.add(panelControl);
    }
    void addButton(String text){
        JButton temp=new JButton(text);

        panelControl.add(temp);
        menuFrame.add(panelControl);
    }
    void iniMenu() {

        menuFrame=new JFrame("坦克大战");
        menuFrame.setSize(400,400);
        menuFrame.setLayout(new GridLayout());
        boxHor.setSize(200,50);
        boxHor.add(boxVer);
       boxVer.add(Box.createRigidArea(new Dimension(100,20)));
       //boxVer.add(Box.createVerticalGlue());
       boxVer.add(new JButton("开始游戏"));
        boxVer.add(Box.createVerticalStrut(30));
       boxVer.add(new JButton("难度选择"));
        boxVer.add(Box.createVerticalStrut(30));
       boxVer.add(new JButton("退出"));

        menuFrame.add(boxHor);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
