import foundation.TankClient;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;

public class Menu {
    private JFrame menuFrame;//菜单框架

    private JPanel panelMainMenu = new JPanel();//主菜单容器
    private JPanel panelModelSelect=new JPanel();//模式选择容器
    private JPanel cards=new JPanel(new CardLayout());//主容器类，主要用于界面切换
    private Box boxVer = Box.createVerticalBox(), boxHor = Box.createHorizontalBox();

    Menu() {
        iniMenu();
    }

    void addButton(String text, String icon) {//添加按钮，并设置文字和图片
        JButton temp = new JButton(text, createImageIcon(icon, ""));
        panelMainMenu.add(temp);
        menuFrame.add(panelMainMenu);
    }

    void addButton(String text) {//添加按钮，并设置文字
        JButton temp = new JButton(text);

        panelMainMenu.add(temp);
        menuFrame.add(panelMainMenu);
    }
public class ActEatChickenMode extends JFrame implements ActionListener{
        @Override
    public void actionPerformed(ActionEvent e){//事件监听，吃鸡模式按键监听
            String[] t = new String[1];
            menuFrame.setVisible(false);
            TankClient.main(t);//调用游戏
        }
}
public class ActNormalMode extends JFrame implements ActionListener{
        @Override
    public void actionPerformed(ActionEvent e){//事件监听，普通模式按键监听
            String[] t = new String[1];
            menuFrame.setVisible(false);
            TankClient.main(t);//调用 游戏
            TankClient.mode = false;//
        }
}
public class ActModelSelect extends JFrame implements ActionListener{
        @Override
    public void actionPerformed(ActionEvent e){
            JButton buttonEatChicken = new JButton("吃鸡模式");
            buttonEatChicken.addActionListener(new ActEatChickenMode());
            JButton buttonNormal = new JButton("普通模式");
            buttonNormal.addActionListener(new ActNormalMode());
            panelModelSelect.add(buttonEatChicken);
            panelModelSelect.add(buttonNormal);
            CardLayout c1=(CardLayout)(cards.getLayout()) ;
            c1.show(cards,"modelSelect");
        }

}
    void iniMenu() {


        menuFrame = new JFrame("坦克大战");
        menuFrame.setLocation(300, 50);
        menuFrame.setSize(400, 400);
        menuFrame.setLayout(new GridLayout());
        boxHor.setSize(200, 50);
        boxHor.add(boxVer);
        boxVer.add(Box.createRigidArea(new Dimension(100, 20)));
        //boxVer.add(Box.createVerticalGlue());

        JButton buttonModeSelect=new JButton("模式选择");
        buttonModeSelect.addActionListener(new ActModelSelect());
        boxVer.add(buttonModeSelect);

        boxVer.add(Box.createVerticalStrut(30));
        boxVer.add(new JButton("难度选择"));
        boxVer.add(Box.createVerticalStrut(30));
        boxVer.add(new JButton("退出"));
        panelMainMenu.add(boxHor);
        cards.add(panelMainMenu,"mainMenu");
        cards.add(panelModelSelect,"modelSelect");

        CardLayout c1=(CardLayout)(cards.getLayout()) ;
        c1.show(cards,"mainMenu");


        menuFrame.add(cards);

        //menuFrame.add(panelMainMenu);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }

    private static ImageIcon createImageIcon(String path, String description) {//创建图片资源
        URL imgURL = Menu.class.getResource(path);
        if (imgURL != null) return new ImageIcon(imgURL, description);
        else {
            System.err.println("Couldn't find file:" + path);
            return null;
        }
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
    }
}
