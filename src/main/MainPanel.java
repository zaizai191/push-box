package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

public class MainPanel extends JPanel implements KeyListener {

    int max = 50;
    int[][] map, mapRest;
    int manX, manY, boxNumber;
    Image[] images;
    ReadMap LevelMap;
    ReadMap LevelMapRest;
    int len = 40;
    public int level = 1;
    Stack stack = new Stack();

    MainPanel() {
        setBounds(15, 15, 800, 800);
        setBackground(Color.white);
        addKeyListener(this);
        images = new Image[10];
        images[0] = Toolkit.getDefaultToolkit().getImage("src/img/black.png");
        images[1] = Toolkit.getDefaultToolkit().getImage("src/img/wall.png");
        images[2] = Toolkit.getDefaultToolkit().getImage("src/img/ground.png");
        images[3] = Toolkit.getDefaultToolkit().getImage("src/img/box.png");
        images[4] = Toolkit.getDefaultToolkit().getImage("src/img/goal.png");
        images[5] = Toolkit.getDefaultToolkit().getImage("src/img/down.png");
        images[6] = Toolkit.getDefaultToolkit().getImage("src/img/left.png");
        images[7] = Toolkit.getDefaultToolkit().getImage("src/img/right.png");
        images[8] = Toolkit.getDefaultToolkit().getImage("src/img/up.png");
        images[9] = Toolkit.getDefaultToolkit().getImage("src/img/success.png");
        setVisible(true);
    }

    void PushBox(int i) {

        LevelMap = new ReadMap(i);
        LevelMapRest = new ReadMap(i);
        map = LevelMap.getmap();
        manX = LevelMap.getmanX();
        manY = LevelMap.getmanY();
        mapRest = LevelMapRest.getmap();
        repaint();

    }

    int maxLevel() {
        return max;
    }

    public void paint(Graphics graphics) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                graphics.drawImage(images[map[j][i]], i * len, j * len, this);
            }
        }

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font("楷体_2312", Font.BOLD, 30));
        graphics.drawString("第", 240, 40);
        graphics.drawString(String.valueOf(level), 310, 40);
        graphics.drawString("关", 360, 40);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            moveUp();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            moveDown();
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            moveLeft();
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            moveRight();
        }
        if (isWin()) {
            if (level == max) {
                JOptionPane.showMessageDialog(this, "恭喜您已通过最后一关");
            } else {
                String msg = "恭喜您通过第" + level + "关！\n是否进入到下一关";
                int type = JOptionPane.YES_NO_OPTION;
                String title = "恭喜过关！";
                int choice = 0;
                choice = JOptionPane.showConfirmDialog(null, msg, title, type);
                if (choice == 1) System.exit(0);
                else if (choice == 0) {
                    level++;
                    PushBox(level);
                }
            }
            stack.removeAllElements();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    boolean isStackEmpty() {
        return stack.isEmpty();
    }

    int withDraw() {
        return (Integer) stack.pop();
    }

    void remove() {
        stack.removeAllElements();
    }

    void moveUp() {
        if (map[manY - 1][manX] == 2 || map[manY - 1][manX] == 4) {
            if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                map[manY][manX] = 4;
            else map[manY][manX] = 2;
            map[manY - 1][manX] = 8;
            repaint();
            manY--;
            stack.push(10);
        } else if (map[manY - 1][manX] == 3) {
            if (map[manY - 2][manX] == 4) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY - 1][manX] = 8;
                map[manY - 2][manX] = 9;
                repaint();
                manY--;
                stack.push(11);
            } else if (map[manY - 2][manX] == 2) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY - 1][manX] = 8;
                map[manY - 2][manX] = 3;
                repaint();
                manY--;
                stack.push(11);
            } else {
                map[manY][manX] = 8;
                repaint();
            }
        } else if (map[manY - 1][manX] == 9) {
            if (map[manY - 2][manX] == 4) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY - 1][manX] = 8;
                map[manY - 2][manX] = 9;
                repaint();
                manY--;
                stack.push(11);
            } else if (map[manY - 2][manX] == 2) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY - 1][manX] = 8;
                map[manY - 2][manX] = 3;
                repaint();
                manY--;
                stack.push(11);
            } else {
                map[manY][manX] = 8;
                repaint();
            }
        }
        if (map[manY - 1][manX] == 1) {
            map[manY][manX] = 8;
            repaint();
        }
    }

    void moveDown() {
        if (map[manY + 1][manX] == 2 || map[manY + 1][manX] == 4) {
            if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                map[manY][manX] = 4;
            else map[manY][manX] = 2;
            map[manY + 1][manX] = 5;
            repaint();
            manY++;
            stack.push(20);
        } else if (map[manY + 1][manX] == 3) {
            if (map[manY + 2][manX] == 4) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY + 1][manX] = 5;
                map[manY + 2][manX] = 9;
                repaint();
                manY++;
                stack.push(21);
            } else if (map[manY + 2][manX] == 2) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY + 1][manX] = 5;
                map[manY + 2][manX] = 3;
                repaint();
                manY++;
                stack.push(21);
            } else {
                map[manY][manX] = 5;
                repaint();
            }
        } else if (map[manY + 1][manX] == 9) {
            if (map[manY + 2][manX] == 4) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY + 1][manX] = 5;
                map[manY + 2][manX] = 9;
                repaint();
                manY++;
                stack.push(21);
            } else if (map[manY + 2][manX] == 2) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY + 1][manX] = 5;
                map[manY + 2][manX] = 3;
                repaint();
                manY++;
                stack.push(21);
            } else {
                map[manY][manX] = 5;
                repaint();
            }
        } else if (map[manY + 1][manX] == 1) {
            map[manY][manX] = 5;
            repaint();
        }
    }

    void moveLeft() {
        if (map[manY][manX - 1] == 2 || map[manY][manX - 1] == 4) {
            if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                map[manY][manX] = 4;
            else map[manY][manX] = 2;
            map[manY][manX - 1] = 6;
            repaint();
            manX--;
            stack.push(30);
        } else if (map[manY][manX - 1] == 3) {
            if (map[manY][manX - 2] == 4) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY][manX - 1] = 6;
                map[manY][manX - 2] = 9;
                repaint();
                manX--;
                stack.push(31);
            } else if (map[manY][manX - 2] == 2) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY][manX - 1] = 6;
                map[manY][manX - 2] = 3;
                repaint();
                manX--;
                stack.push(31);
            } else {
                map[manY][manX] = 6;
                repaint();
            }
        } else if (map[manY][manX - 1] == 9) {
            if (map[manY][manX - 2] == 4) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY][manX - 1] = 6;
                map[manY][manX - 2] = 9;
                repaint();
                manX--;
                stack.push(31);
            } else if (map[manY][manX - 2] == 2) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY][manX - 1] = 6;
                map[manY][manX - 2] = 3;
                repaint();
                manX--;
                stack.push(31);
            } else {
                map[manY][manX] = 6;
                repaint();
            }
        } else if (map[manY][manX - 1] == 1) {
            map[manY][manX] = 6;
            repaint();
        }
    }

    void moveRight() {
        if (map[manY][manX + 1] == 2 || map[manY][manX + 1] == 4) {
            if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                map[manY][manX] = 4;
            else map[manY][manX] = 2;
            map[manY][manX + 1] = 7;
            repaint();
            manX++;
            stack.push(40);
        } else if (map[manY][manX + 1] == 3) {
            if (map[manY][manX + 2] == 4) {
                if (mapRest[manY][manX] == 4)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY][manX + 1] = 7;
                map[manY][manX + 2] = 9;
                repaint();
                manX++;
                stack.push(41);
            } else if (map[manY][manX + 2] == 2) {
                if (mapRest[manY][manX] == 4)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY][manX + 1] = 7;
                map[manY][manX + 2] = 3;
                repaint();
                manX++;
                stack.push(41);
            } else {
                map[manY][manX] = 7;
                repaint();
            }
        } else if (map[manY][manX + 1] == 9) {
            if (map[manY][manX + 2] == 4) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY][manX + 1] = 7;
                map[manY][manX + 2] = 9;
                repaint();
                manX++;
                stack.push(41);
            } else if (map[manY][manX + 2] == 2) {
                if (mapRest[manY][manX] == 4 || mapRest[manY][manX] == 9)
                    map[manY][manX] = 4;
                else map[manY][manX] = 2;
                map[manY][manX + 1] = 7;
                map[manY][manX + 2] = 3;
                repaint();
                manX++;
                stack.push(41);
            } else {
                map[manY][manX] = 7;
                repaint();
            }
        } else if (map[manY][manX + 1] == 1) {
            map[manY][manX] = 7;
            repaint();
        }
    }

    boolean isWin() {
        boolean flag = false;
        out:for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (mapRest[i][j]==4||mapRest[i][j]==9){
                    if (map[i][j]==9){
                        flag = true;
                    }else {
                        flag = false;
                        break out;
                    }
                }
            }
        }
        return flag;
    }

    void backUp(int t)
    {
        int n=t;
        if(n==10)
        {
            if(mapRest[manY][manX]==4||mapRest[manY][manX]==9)
            {
                map[manY][manX]=4;
            }
            else map[manY][manX]=2;
        }
        else if(n==11)
        {
            if(mapRest[manY][manX]==4||mapRest[manY][manX]==9)
            {
                map[manY][manX]=9;
            }
            else map[manY][manX]=3;
            if(mapRest[manY-1][manX]==4||mapRest[manY-1][manX]==9)
            {
                map[manY-1][manX]=4;
            }
            else map[manY-1][manX]=2;
        }
        map[manY+1][manX]=8;
        repaint();manY++;
    }

    void backDown(int t)
    {
        int n=t;
        if(n==20)
        {
            if(mapRest[manY][manX]==4||mapRest[manY][manX]==9)
            {
                map[manY][manX]=4;
            }
            else map[manY][manX]=2;
        }
        else if(n==21)
        {
            if(mapRest[manY][manX]==4||mapRest[manY][manX]==9)
            {
                map[manY][manX]=9;
            }
            else map[manY][manX]=3;
            if(mapRest[manY+1][manX]==4||mapRest[manY+1][manX]==9)
            {
                map[manY+1][manX]=4;
            }
            else map[manY+1][manX]=2;
        }
        map[manY-1][manX]=5;
        repaint();manY--;
    }

    void backLeft(int t)
    {
        int n=t;
        if(n==30)
        {
            if(mapRest[manY][manX]==4||mapRest[manY][manX]==9)
            {
                map[manY][manX]=4;
            }
            else map[manY][manX]=2;
        }
        else if(n==31)
        {
            if(mapRest[manY][manX]==4||mapRest[manY][manX]==9)
            {
                map[manY][manX]=9;
            }
            else map[manY][manX]=3;
            if(mapRest[manY][manX-1]==4||mapRest[manY][manX-1]==9)
            {
                map[manY][manX-1]=4;
            }
            else map[manY][manX-1]=2;
        }
        map[manY][manX+1]=6;
        repaint();manX++;
    }

    void backRight(int t)
    {
        int n=t;
        if(n==40)
        {
            if(mapRest[manY][manX]==4||mapRest[manY][manX]==9)
            {
                map[manY][manX]=4;
            }
            else map[manY][manX]=2;
        }
        else if(n==41)
        {
            if(mapRest[manY][manX]==4||mapRest[manY][manX]==9)
            {
                map[manY][manX]=9;
            }
            else map[manY][manX]=3;
            if(mapRest[manY][manX+1]==4||mapRest[manY][manX+1]==9)
            {
                map[manY][manX+1]=4;
            }
            else map[manY][manX+1]=2;
        }
        map[manY][manX-1]=7;
        repaint();manX--;
    }


}
