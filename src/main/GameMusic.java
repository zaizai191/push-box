package main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class GameMusic {
    public String name="19.wav";
    public Clip clip;
    boolean flag = false;
    public void setMusic(String e){this.name=e;}
    public void playGameMusic(){
        try
        {
            clip = AudioSystem.getClip();
            String path = "/musics/"+name;
            BufferedInputStream bufferedInputStream = new BufferedInputStream(this.getClass().getResourceAsStream(path));
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bufferedInputStream);
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            flag = true;
            System.out.println("音乐文件已经打开");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("播放错误！");
        }
    }
    public void stopMusic()//停止播放
    {
        clip.stop();
        flag = false;

    }
    public void playMusic()//播放
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
        flag = true;

    }
}

