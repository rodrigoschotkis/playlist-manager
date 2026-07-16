# Playlist Manager

A console-based music playlist manager written in Java. Browse a song library,
create playlists, and manage both.

## Features

- Pre-loaded song library (title, artist, album, duration, genre, year)
- Create, rename, and delete playlists
- Add songs from the library to a playlist, or remove them
- View a playlist with its total duration
- Add, remove, and edit songs in the library
- Input validation on every prompt

## Structure

- `Song` — a single song
- `Playlist` — one playlist and its songs
- `PlaylistManager` — manages all playlists
- `SongLibrary` — the song catalog
- `ConsoleUI` — all user interaction
- `Main` — creates the objects and starts the app

## How to Run

1. Clone the repository
2. Open in IntelliJ (or compile with `javac`)
3. Run `Main.java`

## Notes

I built this to practice structuring a Java program properly: keeping the data 
and logic classes free of any printing, putting all user interaction in one class, 
and writing reusable input helpers instead of repeating validation loops.