package MusicLibrary.dtos;

public record Album (int albumID, int artistID, String title, java.time.LocalDate releaseDate, int numberOfTracks) {
}
