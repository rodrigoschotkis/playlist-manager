import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        PlaylistManager manager = new PlaylistManager();
        SongLibrary library = new SongLibrary();
        ConsoleUI ui = new ConsoleUI(scan, manager, library);
        ui.menu();
    }
}
