package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame implements ActionListener, ItemListener {

    JButton buttonRestart, buttonWithDraw, buttonPreGame, buttonNextGame, buttonChoose, buttonFirst, buttonLast, buttonMusic;
    MenuItem reStart = new MenuItem("���¿�ʼ");
    MenuItem withDraw = new MenuItem("��һ��");
    MenuItem preGame = new MenuItem("��һ��");
    MenuItem nextGame = new MenuItem("��һ��");
    MenuItem choose = new MenuItem("ѡ��");
    MenuItem exit = new MenuItem("�˳�");
    MenuItem music1 = new MenuItem("music1");
    MenuItem music2 = new MenuItem("music2");
    MenuItem music3 = new MenuItem("music3");
    MenuItem music4 = new MenuItem("music4");
    MenuItem about = new MenuItem("����");
    JComboBox chooseMusic = new JComboBox();
    MainPanel panel;
    GameMusic gameMusic;

    public GameFrame(JFrame frame, int id) {

        super("������");
        setSize(920, 900);
        setVisible(true);
        setResizable(false);
        setLocation(300, 20);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.black);
        Menu choice = new Menu("ѡ��");
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
        Menu setMusic = new Menu("��������");
        setMusic.add(music1);
        setMusic.add(music2);
        setMusic.add(music3);
        setMusic.add(music4);
        music1.addActionListener(this);
        music2.addActionListener(this);
        music3.addActionListener(this);
        music4.addActionListener(this);
        Menu help = new Menu("����");
        help.add(about);
        about.addActionListener(this);
        MenuBar bar = new MenuBar();
        bar.add(choice);
        bar.add(setMusic);
        bar.add(help);
        setMenuBar(bar);

        music1.setEnabled(false);
        buttonRestart = new JButton("����");
        buttonWithDraw = new JButton("��һ��");
        buttonPreGame = new JButton("��һ��");
        buttonNextGame = new JButton("��һ��");
        buttonChoose = new JButton("ѡ��");
        buttonFirst = new JButton("�ڣ���");
        buttonLast = new JButton("���չ�");
        buttonMusic = new JButton("���ֹ�");
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
                JOptionPane.showMessageDialog(this, "�����ǵ�һ��");
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
                JOptionPane.showMessageDialog(this, "�����������һ��");
                panel.requestFocus();
            } else {
                panel.PushBox(panel.level);
                panel.requestFocus();
            }
            panel.remove();
        } else if (e.getSource() == exit) System.exit(0);
        else if (e.getSource() == about) {
            String message = "������������Ϸ�����Ƶ���Ϸ��һ����������Ҫ���ư��˹����������ƶ������������Ƶ�ָ���ص�\n" +
                    "�ǵü���ǰ���������ӡ���PC���Ϲ�����һ�ɲ�С����������Ϸ�����磬��������������Ҳ���ԡ������ӡ��޲����ڣ��ɼ�����ȵ���������Ϸ�Ƿǳ��ܴ�һ�ӭ�ġ�\n" +
                    "��������Ϸ1981�����ձ��˽��ֺ����״�������1982��12����Thinking Rabbit ��˾�״η��У������ֿⷬ��������ֻ������, ��������, ����һ��ֻ���ƶ�һ����ʤ���������ǰ����е����Ӷ��Ƶ�Ŀ�ĵء�\n" +
                    "��������Ϸ��һ�����ٽ��˵�������Ϸ���ȿ��Կ���������ѧ�����������ֿ��Է�ֹ����մ�֢��ȫ��һ�𹥹ػ����Դٽ���ͥ���������ֶ���Ϊ��";
            JOptionPane.showMessageDialog(this, message);
        } else if (e.getSource() == buttonChoose || e.getSource() == choose) {
            String lel = JOptionPane.showInputDialog(this, "��������Ҫת���Ĺؿ��ţ�(1~50)");
            panel.level = Integer.parseInt(lel);
            panel.remove();
            if (panel.level > panel.maxLevel() || panel.level < 1) {
                JOptionPane.showMessageDialog(this, "û����һ�أ�����");
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
                buttonMusic.setLabel("���ֿ�");
            } else {
                gameMusic.playMusic();
                buttonMusic.setLabel("���ֹ�");
            }
            panel.requestFocus();
        } else if (e.getSource() == buttonWithDraw || e.getSource() == withDraw) {
            if (panel.isStackEmpty()) JOptionPane.showMessageDialog(this, "����δ�ƶ�������");
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
                buttonMusic.setLabel("���ֹ�");
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
                buttonMusic.setLabel("���ֹ�");
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
                buttonMusic.setLabel("���ֹ�");
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
                buttonMusic.setLabel("���ֹ�");
                music1.setEnabled(true);
                music2.setEnabled(true);
                music3.setEnabled(true);
                music4.setEnabled(false);
                panel.requestFocus();
                break;
        }
    }


}
