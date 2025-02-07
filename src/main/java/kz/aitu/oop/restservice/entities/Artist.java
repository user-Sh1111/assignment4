package kz.aitu.oop.restservice.entities;

public class Artist {
    private int id;
    private String name;
    private int birthYear;
    private String nationality;

    public Artist() {}

    public Artist(int id, String name, int birthYear, String nationality) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
        this.nationality = nationality;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public String getNationality() { return nationality; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    @Override
    public String toString() {
        return "Artist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}