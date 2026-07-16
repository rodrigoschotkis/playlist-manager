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
                    String name = promptString("Type the name for the new playlist: ");
                    if (manager.searchPlaylist(name) != null) {
                        System.out.println("Error. There is already a playlist called " + name);
                        break;
                    }
                    manager.createPlaylist(new Playlist(name));
                    System.out.println(name + " was successfully created.");
                    break;
                }
                case 3: {
                    System.out.println("--- YOUR PLAYLISTS ---");
                    displayListOfPlaylists();
                    break;
                }
                case 4: {
                    subMenuLibrary();
                    break;
                }
                case 5: {
                    break;
                }
            }
        } while (menuSel != 5);
    }

    public void subMenuPlaylists(Playlist playlist) {
        int subMenuSel = 0;
        do {
            subMenuSel = promptInt(managePlaylistsMenuText(playlist.getName()), 1, 6);
            switch (subMenuSel) {
                case 1: {
                    ArrayList<Song> songs = library.getSongs();
                    System.out.println("--- SONG LIBRARY ---");
                    if(!displayListOfSongsFull(songs)) {
                        break;
                    }
                    String title = promptString("Type the title of the song you'd like to add to " + playlist.getName() + ": ");
                    Song song = library.getSong(title);
                    if (song == null) {
                        System.out.println("There is no song called " + title + " in the song library.");
                        break;
                    }
                    playlist.addSong(song);
                    System.out.println(song.getSongTitle() + " was successfully added to " + playlist.getName());
                    break;
                }
                case 2: {
                    ArrayList<Song> songs = playlist.getSongs();
                    System.out.println("--- SONGS FROM " + playlist.getName() + " ---");
                    if (!displayListOfSongsNames(songs)) {
                        break;
                    }
                    String title = promptString("Type the title of the song you'd like to remove: ");
                    if (!playlist.removeSong(title)) {
                        System.out.println("There is no song named " + title + " in the playlist.");
                        break;
                    }
                    System.out.println(title + " was successfully removed from " + playlist.getName() + ".");
                    break;
                }
                case 3: {
                    String newName = promptString("Type the new name for the playlist: ");
                    if (manager.searchPlaylist(newName) != null) {
                        System.out.println("Error. There is already a playlist called " + newName);
                        break;
                    }
                    playlist.setName(newName);
                    System.out.println("The playlist was successfully renamed to: " + newName);
                    break;
                }
                case 4: {
                    manager.removePlaylist(playlist.getName());
                    System.out.println(playlist.getName() + " was successfully deleted.");
                    return;
                }
                case 5: {
                    System.out.println("--- SONGS FROM " + playlist.getName() + " ---");
                    ArrayList<Song> songs = playlist.getSongs();
                    if (!displayListOfSongsFull(songs)) {
                        break;
                    }
                    System.out.println("Duration of the playlist: " + formatDuration(playlist.getTotalDuration()));
                    break;
                }
                case 6: {
                    break;
                }
            }
        } while (subMenuSel != 6);
    }

    public void subMenuLibrary() {
        int subMenuSel = 0;
        do {
            subMenuSel = promptInt(manageLibrarySongsMenuText(), 1, 5);
            switch (subMenuSel) {
                case 1: {
                    Song song = promptForSong();
                    if (library.getSong(song.getSongTitle()) != null) {
                        System.out.println("Error. You can't add two songs with the same title.");
                        break;
                    }
                    library.addSong(song);
                    System.out.println(song.getSongTitle() + " was successfully added to your library.");
                    break;
                }
                case 2: {
                    ArrayList<Song> songs = library.getSongs();
                    System.out.println("--- SONG LIBRARY ---");
                    displayListOfSongsNames(songs);
                    String title = promptString("Type the title of the song you'd like to remove: ");
                    if (!library.removeSong(title)) {
                        System.out.println("There are no songs named " + title);
                        break;
                    }
                    System.out.println(title + " was successfully removed from your library.");
                    break;
                }
                case 3: {
                    ArrayList<Song> songs = library.getSongs();
                    System.out.println("--- SONG LIBRARY ---");
                    displayListOfSongsFull(songs);
                    break;
                }
                case 4: {
                    ArrayList<Song> songs = library.getSongs();
                    System.out.println("--- SONG LIBRARY ---");
                    displayListOfSongsNames(songs);
                    String title = promptString("Type the title of the song you'd like to edit: ");
                    Song song = library.getSong(title);
                    if (song == null) {
                        System.out.println("Error. There is no song with this title.");
                        break;
                    }
                    editSong(song);
                    break;
                }
                case 5: {
                    break;
                }
            }
        } while (subMenuSel != 5);
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

    public String editSongMenuText() {
        return """
                Which field would you like to edit?
                1. Title
                2. Artist
                3. Album
                4. Duration
                5. Genre
                6. Year
                7. Exit
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

    public String promptStringNoNumbers(String prompt) {
        while (true) {
            System.out.print(prompt);
            String userAnswer = scannerUI.nextLine();
            if (userAnswer.matches(".*\\d.*")) {
                System.out.println("This can't contain numbers.");
                continue;
            }
            if (!userAnswer.trim().isEmpty()) {
                return userAnswer;
            }
            System.out.println("This can't be empty.");
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

    public boolean displayListOfSongsNames(ArrayList<Song> songs) {
        if (songs.isEmpty()) {
            System.out.println("No songs to show.");
            return false;
        }
        int num = 1;
        for (Song s : songs) {
            System.out.println(num + ". " + s.getSongTitle());
            num++;
        }
        return true;
    }

    public boolean displayListOfSongsFull(ArrayList<Song> songs) {
        if (songs.isEmpty()) {
            System.out.println("No songs to show.");
            return false;
        }
        int num = 1;
        for (Song s : songs) {
            System.out.print(num + ". ");
            displaySong(s);
            num++;
        }
        return true;
    }

    public Playlist searchPlaylist() {
        System.out.println("--- YOUR PLAYLISTS ---");
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

    private Song promptForSong() {
        String songTitle = promptString("Type the title of the song: ");
        String artist = promptString("Type the name of the artist: ");
        String album = promptString("Type the name of the album: ");
        int duration = promptInt("Type the duration of the song (in seconds): ", 1, 10000);
        String genre = promptStringNoNumbers("Type the genre of the song: ");
        int year = promptInt("Type the year of the song: ", 1910, 2026);
        return new Song(songTitle, artist, album, duration, genre, year);
    }

    private void editSong(Song song) {
        int edit = 0;
        do {
            edit = promptInt(editSongMenuText(), 1, 7);
            switch (edit) {
                case 1: {
                    String songTitle = promptString("Type the new title of the song: ");
                    if (library.getSong(songTitle) != null) {
                        System.out.println("Error. You can't add two songs with the same title.");
                        break;
                    }
                    song.setSongTitle(songTitle);
                    System.out.println("The title was successfully changed to: " + songTitle);
                    break;
                }
                case 2: {
                    String artist = promptString("Type the new name of the artist: ");
                    song.setArtist(artist);
                    System.out.println("The Artist was successfully changed to: " + artist);
                    break;
                }
                case 3: {
                    String album = promptString("Type the new name of the album: ");
                    song.setAlbum(album);
                    System.out.println("The album was successfully changed to: " + album);
                    break;
                }
                case 4: {
                    int duration = promptInt("Type the new duration of the song (in seconds): ", 1, 10000);
                    song.setDuration(duration);
                    System.out.println("The duration was successfully changed to: " + formatDuration(duration));
                    break;
                }
                case 5: {
                    String genre = promptStringNoNumbers("Type the new genre of the song: ");
                    song.setGenre(genre);
                    System.out.println("The genre was successfully changed to: " + genre);
                    break;
                }
                case 6: {
                    int year = promptInt("Type the new year of the song: ", 1910, 2026);
                    song.setYear(year);
                    System.out.println("The year was successfully changed to: " + year);
                    break;
                }
                case 7:
                    break;
            }
        } while (edit != 7);
    }
}
