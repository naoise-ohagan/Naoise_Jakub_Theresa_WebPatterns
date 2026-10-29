package MusicLibrary.dtos;

public record Artist( int artistID, String name, String description){

    @Override
    public String toString() {
        return "Artist: " + "artistID: ," + artistID + "name: , " + name + "description: " + description;
    }
}
