import java.util.ArrayList;

public class PlaylistManager {
    private ArrayList<Playlist> playlists = new ArrayList<>();

    public void createPlaylist(Playlist playlist) {
        playlists.add(playlist);
    }

    public boolean removePlaylist(String name) {
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(name)) {
                playlists.remove(p);
                return true;
            }
        }
        return false;
    }

    public Playlist searchPlaylist(String name) {
        for (Playlist p : playlists) {
            if (p.getName().equalsIgnoreCase(name)) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Playlist> getPlaylists() {
        return new ArrayList<>(playlists);
    }
}
