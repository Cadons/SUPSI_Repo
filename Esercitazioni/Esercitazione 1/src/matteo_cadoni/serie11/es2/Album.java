package matteo_cadoni.serie11.es2;

import java.time.Year;

public class Album {
    private int number;
    private Year year;
    private  String name;
    private String artist;
    private String genre;
    private String subgenre;

    public Album(int number, Year year, String name, String artist, String genre, String subgenre) {
        this.number = number;
        this.year = year;
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.subgenre = subgenre;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSubgenre() {
        return subgenre;
    }

    public void setSubgenre(String subgenre) {
        this.subgenre = subgenre;
    }

    @Override
    public String toString() {
        return "Album{" +
                "number=" + number +
                ", year=" + year +
                ", name='" + name + '\'' +
                ", artist='" + artist + '\'' +
                ", genre='" + genre + '\'' +
                ", subgenre='" + subgenre + '\'' +
                '}';
    }
}
