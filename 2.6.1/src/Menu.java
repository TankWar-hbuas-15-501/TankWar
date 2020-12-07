
import foundation.TankClient;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import javax.swing.*;
/*
@author: wangquanliu
@time: 2020/12/7
@describe: 实现程序的菜单项
@see:   panelMainMenu     主菜单容器
        panelModelSelect 模式选择容器
        cards            主容器类，主要用于界面切换
 */
public class Menu {
    private JFrame menuFrame;//菜单框架

    private JPanel panelMainMenu = new JPanel();
    private JPanel panelModelSelect=new JPanel();
    private JPanel cards=new JPanel(new CardLayout());

    Menu() {
        iniMenu();
    }

    /*
    @param: String 按键上的文本
    @param: String 按键上显示图片的路径
    @describe: 在按键上显示文本和图片
    @return: void
     */
    void addButton(String text, String icon) {
        JButton temp = new JButton(text, createImageIcon(icon, ""));
        panelMainMenu.add(temp);
        menuFrame.add(panelMainMenu);
    }
        /*
        @param: String 按键上的文本
        @describe: 在按键上显示文本
        @return: void
         */
    void addButton(String text) {
        JButton temp = new JButton(text);
        panelMainMenu.add(temp);
        menuFrame.add(panelMainMenu);
    }
    public class ActEatChickenMode extends JFrame implements ActionListener{
        @Override
        /*
         @param:    ActionEvent 动作事件
         @describe:事件监听，吃鸡模式按键监听
         @return:   void
         */
    public void actionPerformed(ActionEvent e){
            String[] t = new String[1];
            menuFrame.setVisible(false);
            TankClient.main(t);//调用游戏
        }
}
public class ActNormalMode extends JFrame implements ActionListener{
        @Override
        /*
         @param:    ActionEvent 动作事件
         @describe:事件监听，普通模式按键监听
         @return:   void
         */
    public void actionPerformed(ActionEvent e){
            String[] t = new String[1];
            menuFrame.setVisible(false);
            TankClient.main(t);//调用 游戏
            TankClient.mode = false;
        }
}
public class ActModelSelect extends JFrame implements ActionListener{
        @Override
        /*
         @param:    ActionEvent 动作事件
         @describe:事件监听，模式选择按键监听
         @return:   void
         */
    public void actionPerformed(ActionEvent e){
            Box temp=Box.createVerticalBox();
            temp.add(Box.createVerticalStrut(70));//设置间距
            JButton buttonEatChicken = new JButton("吃鸡模式");
            buttonEatChicken.addActionListener(new ActEatChickenMode());
            JButton buttonNormal = new JButton("普通模式");
            buttonNormal.addActionListener(new ActNormalMode());
            temp.add(buttonEatChicken);//添加按键
            temp.add(Box.createVerticalStrut(30));//设置间距
            temp.add(buttonNormal);//添加按键
            JButton buttonBack=new JButton("返回");

            panelModelSelect.add(temp);
            //panelModelSelect.add(buttonNormal);
            CardLayout c1=(CardLayout)(cards.getLayout()) ;
            c1.show(cards,"modelSelect");
        }

}

    void iniMenu() {
        Box boxVer = Box.createVerticalBox(), boxHor = Box.createHorizontalBox();
        menuFrame = new JFrame("坦克大战");
        menuFrame.setLocation(300, 50);
        menuFrame.setSize(400, 400);
        menuFrame.setLayout(new GridLayout());
        boxHor.setSize(200, 50);
        boxHor.add(boxVer);
        boxVer.add(Box.createRigidArea(new Dimension(30, 0)));
        //boxVer.add(Box.createVerticalGlue());
        boxVer.add(Box.createVerticalStrut(50));
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
    /*
            @param:    String 图片路径
            @param:    String 图片描述
            @describe: 读取path的图片路径,成功返回资源，失败返回null
            @return:   ImageIcon
            */
    private static ImageIcon createImageIcon(String path, String description) {
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
