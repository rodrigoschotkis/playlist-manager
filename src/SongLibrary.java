import java.util.ArrayList;

public class SongLibrary {
    private ArrayList<Song> songLibrary = new ArrayList<>();

    public SongLibrary() {
        loadStartingSongs();
    }

    public void addSong(Song song) {
        songLibrary.add(song);
    }

    public boolean removeSong(String title) {
        for (Song s : songLibrary) {
            if (s.getSongTitle().equalsIgnoreCase(title)) {
                return songLibrary.remove(s);
            }
        }
        return false;
    }

    // to get the song to add to a playlist
    public Song getSong(String title) {
        for (Song s : songLibrary) {
            if (s.getSongTitle().equalsIgnoreCase(title)) {
                return s;
            }
        }
        return null;
    }

    public ArrayList<Song> getSongs() {
        return new ArrayList<>(songLibrary);
    }

    private void loadStartingSongs() {
        addSong(new Song("Bohemian Rhapsody", "Queen", "A Night at the Opera", 355, "Rock", 1975));
        addSong(new Song("Billie Jean", "Michael Jackson", "Thriller", 294, "Pop", 1982));
        addSong(new Song("Smells Like Teen Spirit", "Nirvana", "Nevermind", 301, "Grunge", 1991));
        addSong(new Song("Hotel California", "Eagles", "Hotel California", 391, "Rock", 1976));
        addSong(new Song("Lose Yourself", "Eminem", "8 Mile", 326, "Hip-Hop", 2002));
        addSong(new Song("Sweet Child O' Mine", "Guns N' Roses", "Appetite for Destruction", 356, "Rock", 1987));
        addSong(new Song("Stairway to Heaven", "Led Zeppelin", "Led Zeppelin IV", 482, "Rock", 1971));
        addSong(new Song("Rolling in the Deep", "Adele", "21", 228, "Pop", 2010));
        addSong(new Song("Take Five", "Dave Brubeck", "Time Out", 324, "Jazz", 1959));
        addSong(new Song("Wonderwall", "Oasis", "What's the Story Morning Glory", 258, "Rock", 1995));
        addSong(new Song("Superstition", "Stevie Wonder", "Talking Book", 265, "Funk", 1972));
        addSong(new Song("One", "Metallica", "...And Justice for All", 446, "Metal", 1988));
        addSong(new Song("No Woman No Cry", "Bob Marley", "Natty Dread", 427, "Reggae", 1974));
        addSong(new Song("Hey Ya!", "OutKast", "Speakerboxxx/The Love Below", 235, "Hip-Hop", 2003));
        addSong(new Song("Purple Rain", "Prince", "Purple Rain", 520, "Rock", 1984));
        addSong(new Song("Yesterday", "The Beatles", "Help!", 125, "Pop", 1965));
        addSong(new Song("Enter Sandman", "Metallica", "Metallica", 331, "Metal", 1991));
        addSong(new Song("Get Lucky", "Daft Punk", "Random Access Memories", 369, "Electronic", 2013));
        addSong(new Song("Johnny B. Goode", "Chuck Berry", "Chuck Berry Is on Top", 161, "Rock and Roll", 1958));
        addSong(new Song("Redbone", "Childish Gambino", "Awaken, My Love!", 327, "Funk", 2016));
    }
}
