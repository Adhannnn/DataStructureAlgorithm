/* 
 * Wahyu Ramadhan 
 * 001202300005
 * IT 2
 * Data Structure and Algorithm - Assignment 1
 * 
 * R.B. Wahyu <- Lecturer
*/

import javax.sound.sampled.*; // Java sound API Package
import java.io.File;
import java.io.IOException;

class SongNode { // For Node
    String title;
    String filepath;
    SongNode next;

    public SongNode(String title, String filepath) {
        this.title = title;
        this.filepath = filepath;
        this.next = null;
    }
}

class Playlist { // For Playlist
    private SongNode head;

    public Playlist() {
        this.head = null;
    }

    public void addSong(String title, String filepath) { // Add a music into playlist
        SongNode newSong = new SongNode(title, filepath);
        if (head == null) {
            head = newSong;
        } else {
            SongNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newSong;
        }
    }

    public void playSong() { // For playing a song in Playlist
        if (head == null) {
            System.out.println("Playlist is empty.");
            return;
        } 

        SongNode current = head;
        while (current != null) {
            System.out.println("Playing : " + current.title);
            playMusic(current.filepath);
            current = current.next;
        }
    }

    public void playMusic(String filepath) {  // For playing a music using Java sound API
        try {
            File musicFile = new File(filepath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
            Thread.sleep(clip.getMicrosecondLength() / 1000);
            clip.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e){
            e.printStackTrace();
        }
    }
}

public class LinkedList {
    public static void main(String[] args) {
        Playlist playlist = new Playlist();

        playlist.addSong("Waking Up Together With You - Ardhito Pramono", "music/wakingup.wav");
        playlist.addSong("Kupu Kupu - Tiara Andini", "music/kupukupu.wav");

        System.out.println("Starting playlist : ");
        playlist.playSong();
    }
}