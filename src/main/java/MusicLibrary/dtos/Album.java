package MusicLibrary.dtos;

public record Album (int albumID, int artistID, String title, java.time.LocalDate releaseDate, int numberOfTracks) {
    @Override
    public String toString() {
        return "Album{" +
                "albumID=" + albumID +
                ", artistID=" + artistID +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", numberOfTracks=" + numberOfTracks +
                '}';
    }
}
