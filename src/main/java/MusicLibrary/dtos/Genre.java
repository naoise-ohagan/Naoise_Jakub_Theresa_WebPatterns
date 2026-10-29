package MusicLibrary.dtos;

public record Genre(int genreID, String name) {
    @Override
    public String toString() {
        return "Genre{" +
                "genreID=" + genreID +
                ", name='" + name + '\'' +
                '}';
    }
}
