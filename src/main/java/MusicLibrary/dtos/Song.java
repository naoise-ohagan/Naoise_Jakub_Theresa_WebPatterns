package MusicLibrary.dtos;

public record Song(int songID, int artistID, int albumID, int genreID, String title) {
    @Override
    public String toString() {
        return "Song{" +
                "songID=" + songID +
                ", artistID=" + artistID +
                ", albumID=" + albumID +
                ", genreID=" + genreID +
                ", title='" + title + '\'' +
                '}';
    }
}
