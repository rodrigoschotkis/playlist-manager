import java.util.Scanner;

public class ConsoleUI {
    private Scanner scannerUI;

    public void menu() {

    }

    public String mainMenuText() {
        return """
                1. Manage my playlists
                2. Create new playlist
                3. View my playlists
                4. Manage songs from the library
                5. Quit
                Choose an option: \s""";
    }

    public ConsoleUI(Scanner scannerUI) {
        this.scannerUI = scannerUI;
    }

    public String formatDuration(int duration) {
        int minutes = duration / 60;
        int seconds = duration % 60;
        return String.format("%d:%02d", minutes, seconds);
    }

    public void displaySong(Song song) {
        System.out.println(song.getSongTitle() + " by " + song.getArtist() +
                " (" + song.getAlbum() + ", " + song.getYear() + ")" +
                " [" + song.getGenre() + "] - " + formatDuration(song.getDuration()));
    }
}
