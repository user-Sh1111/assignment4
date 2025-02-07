package kz.aitu.oop.restservice.entities;

public class Artwork {
    private String title;
    private int age;
    private double price;
    private Artist artist;
    private static int count = 0;

    public Artwork(String title, int age, double price, Artist artist) {
        this.title = title;
        this.age = age;
        this.price = price;
        this.artist = artist;
        count++;
    }

    public Artwork() {}

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Artist getArtist() { return artist; }
    public void setArtist(Artist artist) { this.artist = artist; }

    public static int getCount() { return count; }

    public void readArtwork() {
        System.out.println("Title: " + title);
        System.out.println("Age of artwork: " + age);
        System.out.println("Price: $" + price);
        System.out.println("Artist: " + artist.getName());
    }

    @Override
    public String toString() {
        return "Artwork: " + title + ", Age: " + age + ", Price: $" + price + ", Artist: " + artist.getName();
    }
}
