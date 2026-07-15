import java.util.ArrayList;

public class Playlist {
    private String name;
    private ArrayList<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public void addSong(Song song) {
        songs.add(song);
    }

    public boolean removeSong(String title) {
        for (Song s : songs) {
            if (s.getSongTitle().equalsIgnoreCase(title)) {
                songs.remove(s);
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    public int getTotalDuration() {
        int duration = 0;
        for (Song s : songs) {
            duration += s.getDuration();
        }
        return duration;
    }

    public void setName(String name) {
        this.name = name;
    }
}
