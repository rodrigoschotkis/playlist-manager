public class Song {
    private String songTitle;
    private String artist;
    private String album;
    private int duration;
    private String genre;
    private int year;

    public Song(String songTitle, String artist, String album, int duration, String genre, int year) {
        this.songTitle = songTitle;
        this.artist = artist;
        this.album = album;
        this.duration = duration;
        this.genre = genre;
        this.year = year;
    }

    // Getters
    public String getSongTitle() {
        return this.songTitle;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getAlbum() {
        return this.album;
    }

    public int getDuration() {
        return this.duration;
    }

    public String getGenre() {
        return this.genre;
    }

    public int getYear() {
        return this.year;
    }

    // Setters
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
