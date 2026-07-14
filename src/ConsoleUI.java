import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleUI {
    private Scanner scannerUI;
    private PlaylistManager manager;
    private SongLibrary library;

    public ConsoleUI(Scanner scannerUI, PlaylistManager manager, SongLibrary library) {
        this.scannerUI = scannerUI;
        this.manager = manager;
        this.library = library;
    }

    public void menu() {
        int menuSel = 0;
        do {
            menuSel = promptInt(mainMenuText(), 1, 5);
            switch (menuSel) {
                case 1: {
                    Playlist playlistSelected = searchPlaylist();
                    if (playlistSelected == null) {
                        break;
                    }
                    subMenuPlaylists(playlistSelected);
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5:
                    break;
            }
        } while (menuSel != 5);
    }

    public void subMenuPlaylists(Playlist playlist) {
        int subMenuSel = 0;
        do {
            subMenuSel = promptInt(managePlaylistsMenuText(playlist.getName()), 1, 6);
            switch (subMenuSel) {
                case 1: {
                    break;
                }
                case 2: {
                    break;
                }
                case 3: {
                    break;
                }
                case 4: {
                    break;
                }
                case 5: {
                    break;
                }
                case 6: {
                    break;
                }
            }
        } while (subMenuSel != 6);
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

    public String managePlaylistsMenuText(String playlistName) {
        return "--- MANAGING: " + playlistName + " ---\n" + """
                1. Add song
                2. Remove song
                3. Rename playlist
                4. Delete playlist
                5. View playlist
                6. Return
                Choose an option: \s""";
    }

    public String manageLibrarySongsMenuText() {
        return """
                --- MANAGING MUSIC LIBRARY ---
                1. Add song
                2. Remove song
                3. View songs
                4. Edit song
                5. Return
                Choose an option: \s""";
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

    public int promptInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scannerUI.nextLine();
            try {
                int number = Integer.parseInt(input);
                if (number < min || number > max) {
                    System.out.println("The number should be between " + min + " and " + max + ".");
                    continue;
                }
                return number;
            } catch (NumberFormatException e) {
                System.out.println("That's not a valid number.");
            }
        }
    }

    public String promptString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String userAnswer = scannerUI.nextLine();
            if (!userAnswer.trim().isEmpty()) {
                return userAnswer;
            }
            System.out.println("This can't be empty.");
        }
    }

    public boolean displayListOfPlaylists() {
        ArrayList<Playlist> p = manager.getPlaylists();
        if (p.isEmpty()) {
            System.out.println("You don't have any playlist");
            return false;
        }
        int num = 1;
        for (Playlist pl : p) {
            System.out.println(num + ". " + pl.getName());
            num++;
        }
        return true;
    }

    public Playlist searchPlaylist() {
        if (!displayListOfPlaylists()) {
            return null;
        }
        String name = promptString("Please type the name of the playlist you'd like to manage: ");
        Playlist found = manager.searchPlaylist(name);
        if (found == null) {
            System.out.println("There is no playlist called: " + name);
            return null;
        }
        return found;
    }


}
