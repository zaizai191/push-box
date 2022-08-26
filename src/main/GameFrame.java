package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener, ItemListener {

    JButton buttonRestart, buttonWithDraw, buttonPreGame, buttonNextGame, buttonChoose, buttonFirst, buttonLast, buttonMusic;
    MenuItem reStart = new MenuItem("重新开始");
    MenuItem withDraw = new MenuItem("悔一步");
    MenuItem preGame = new MenuItem("上一关");
    MenuItem nextGame = new MenuItem("下一关");
    MenuItem choose = new MenuItem("选关");
    MenuItem exit = new MenuItem("退出");
    MenuItem music1 = new MenuItem("music1");
    MenuItem music2 = new MenuItem("music2");
    MenuItem music3 = new MenuItem("music3");
    MenuItem music4 = new MenuItem("music4");
    MenuItem about = new MenuItem("关于");
    JComboBox chooseMusic = new JComboBox();
    MainPanel panel;
    GameMusic gameMusic;

    public GameFrame(JFrame frame, int id) {

        super("推箱子");
        setSize(920, 900);
        setVisible(true);
        setResizable(false);
        setLocation(300, 20);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.black);
        Menu choice = new Menu("选项");
        choice.add(reStart);
        choice.add(withDraw);
        choice.add(preGame);
        choice.add(nextGame);
        choice.add(choose);
        choice.addSeparator();
        choice.add(exit);
        reStart.addActionListener(this);
        preGame.addActionListener(this);
        nextGame.addActionListener(this);
        choose.addActionListener(this);
        exit.addActionListener(this);
        withDraw.addActionListener(this);
        Menu setMusic = new Menu("设置音乐");
        setMusic.add(music1);
        setMusic.add(music2);
        setMusic.add(music3);
        setMusic.add(music4);
        music1.addActionListener(this);
        music2.addActionListener(this);
        music3.addActionListener(this);
        music4.addActionListener(this);
        Menu help = new Menu("帮助");
        help.add(about);
        about.addActionListener(this);
        MenuBar bar = new MenuBar();
        bar.add(choice);
        bar.add(setMusic);
        bar.add(help);
        setMenuBar(bar);

        music1.setEnabled(false);
        buttonRestart = new JButton("重玩");
        buttonWithDraw = new JButton("悔一步");
        buttonPreGame = new JButton("上一关");
        buttonNextGame = new JButton("下一关");
        buttonChoose = new JButton("选关");
        buttonFirst = new JButton("第１关");
        buttonLast = new JButton("最终关");
        buttonMusic = new JButton("音乐关");
        add(buttonRestart);
        add(buttonWithDraw);
        add(buttonPreGame);
        add(buttonNextGame);
        add(buttonChoose);
        add(buttonFirst);
        add(buttonLast);
        add(buttonMusic);
        buttonRestart.setBounds(825, 100, 80, 30);
        buttonRestart.addActionListener(this);
        buttonWithDraw.setBounds(825, 150, 80, 30);
        buttonWithDraw.addActionListener(this);
        buttonPreGame.setBounds(825, 200, 80, 30);
        buttonPreGame.addActionListener(this);
        buttonNextGame.setBounds(825, 250, 80, 30);
        buttonNextGame.addActionListener(this);
        buttonChoose.setBounds(825, 300, 80, 30);
        buttonChoose.addActionListener(this);
        buttonFirst.setBounds(825, 350, 80, 30);
        buttonFirst.addActionListener(this);
        buttonLast.setBounds(825, 400, 80, 30);
        buttonLast.addActionListener(this);
        buttonMusic.setBounds(825, 450, 80, 30);
        buttonMusic.addActionListener(this);
        chooseMusic.setBounds(825, 530, 80, 20);
        chooseMusic.addItem("music1");
        chooseMusic.addItem("music2");
        chooseMusic.addItem("music3");
        chooseMusic.addItem("music4");
        chooseMusic.addItemListener(this);
        container.add(chooseMusic);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.setVisible(true);
                gameMusic.stopMusic();
            }
        });
        gameMusic = new GameMusic();
        gameMusic.playGameMusic();
        panel = new MainPanel();
        add(panel);
        panel.PushBox(panel.level);
        panel.requestFocus();
        validate();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonRestart || e.getSource() == reStart) {
            panel.PushBox(panel.level);
            panel.requestFocus();
            panel.remove();
        } else if (e.getSource() == buttonPreGame || e.getSource() == preGame) {
            panel.level--;
            if (panel.level < 1) {
                panel.level++;
                JOptionPane.showMessageDialog(this, "本关是第一关");
                panel.requestFocus();
            } else {
                panel.PushBox(panel.level);
                panel.requestFocus();
            }
            panel.remove();
        } else if (e.getSource() == buttonNextGame || e.getSource() == nextGame) {
            panel.level++;
            if (panel.level > panel.maxLevel()) {
                panel.level--;
                JOptionPane.showMessageDialog(this, "本关已是最后一关");
                panel.requestFocus();
            } else {
                panel.PushBox(panel.level);
                panel.requestFocus();
            }
            panel.remove();
        } else if (e.getSource() == exit) System.exit(0);
        else if (e.getSource() == about) {
            String message = "最经典的推箱子游戏，类似的游戏你一定早就玩过。要控制搬运工上下左右移动，来将箱子推到指定地点\n" +
                    "记得几年前，《推箱子》在PC机上刮起了一股不小的益智类游戏的旋风，现在许多资深玩家也都对《推箱子》赞不绝口，可见有深度的益智类游戏是非常受大家欢迎的。\n" +
                    "推箱子游戏1981年由日本人今林宏行首创，是在1982年12月由Thinking Rabbit 公司首次发行，名“仓库番”。箱子只可以推, 不可以拉, 而且一次只能推动一个，胜利条件就是把所有的箱子都推到目的地。\n" +
                    "推箱子游戏是一种老少皆宜的益智游戏，既可以开发青少年学生的智力，又可以防止老年痴呆症，全家一起攻关还可以促进家庭和睦，何乐而不为？";
            JOptionPane.showMessageDialog(this, message);
        } else if (e.getSource() == buttonChoose || e.getSource() == choose) {
            String lel = JOptionPane.showInputDialog(this, "请输入您要转到的关卡号：(1~50)");
            panel.level = Integer.parseInt(lel);
            panel.remove();
            if (panel.level > panel.maxLevel() || panel.level < 1) {
                JOptionPane.showMessageDialog(this, "没有这一关！！！");
                panel.requestFocus();
            } else {
                panel.PushBox(panel.level);
                panel.requestFocus();
            }
        } else if (e.getSource() == buttonFirst) {
            panel.level = 1;
            panel.PushBox(panel.level);
            panel.requestFocus();
            panel.remove();
        } else if (e.getSource() == buttonLast) {
            panel.level = panel.maxLevel();
            panel.PushBox(panel.level);
            panel.requestFocus();
            panel.remove();
        } else if (e.getSource() == buttonMusic) {
            if (gameMusic.flag) {
                gameMusic.stopMusic();
                buttonMusic.setLabel("音乐开");
            } else {
                gameMusic.playMusic();
                buttonMusic.setLabel("音乐关");
            }
            panel.requestFocus();
        } else if (e.getSource() == buttonWithDraw || e.getSource() == withDraw) {
            if (panel.isStackEmpty()) JOptionPane.showMessageDialog(this, "您还未移动！！！");
            else {
                switch (panel.withDraw()) {
                    case 10:
                        panel.backUp(10);
                        break;
                    case 11:
                        panel.backUp(11);
                        break;
                    case 20:
                        panel.backDown(20);
                        break;
                    case 21:
                        panel.backDown(21);
                        break;
                    case 30:
                        panel.backLeft(30);
                        break;
                    case 31:
                        panel.backLeft(31);
                        break;
                    case 40:
                        panel.backRight(40);
                        break;
                    case 41:
                        panel.backRight(41);
                        break;
                }
            }
            panel.requestFocus();
        } else if (e.getSource() == music1) {
            chooseMusic.setSelectedIndex(0);
        } else if (e.getSource() == music2) {
            chooseMusic.setSelectedIndex(1);
        } else if (e.getSource() == music3) {
            chooseMusic.setSelectedIndex(2);
        } else if (e.getSource() == music4) {
            chooseMusic.setSelectedIndex(3);
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        int musicFlag = chooseMusic.getSelectedIndex();
        switch (musicFlag) {
            case 0:
                gameMusic.stopMusic();
                gameMusic.setMusic("19.wav");
                gameMusic.playGameMusic();
                buttonMusic.setLabel("音乐关");
                music1.setEnabled(false);
                music2.setEnabled(true);
                music3.setEnabled(true);
                music4.setEnabled(true);
                panel.requestFocus();

                break;
            case 1:

                gameMusic.stopMusic();
                gameMusic.setMusic("love.wav");
                gameMusic.playGameMusic();
                buttonMusic.setLabel("音乐关");
                music1.setEnabled(true);
                music2.setEnabled(false);
                music3.setEnabled(true);
                music4.setEnabled(true);
                panel.requestFocus();

                break;
            case 2:

                gameMusic.stopMusic();
                gameMusic.setMusic("dream.wav");
                gameMusic.playGameMusic();
                buttonMusic.setLabel("音乐关");
                music1.setEnabled(true);
                music2.setEnabled(true);
                music3.setEnabled(false);
                music4.setEnabled(true);
                panel.requestFocus();

                break;
            case 3:
                gameMusic.stopMusic();
                gameMusic.setMusic("basketball.wav");
                gameMusic.playGameMusic();
                buttonMusic.setLabel("音乐关");
                music1.setEnabled(true);
                music2.setEnabled(true);
                music3.setEnabled(true);
                music4.setEnabled(false);
                panel.requestFocus();
                break;
        }
    }


}
