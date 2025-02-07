package kz.aitu.oop.restservice.entities;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;


public class ArtGallery {
    private String name;
    private String location;
    private final ArrayList<Artwork> artworks;

    public ArtGallery(String name, String location) {
        this.name = name;
        this.location = location;
        this.artworks = new ArrayList<>();
    }

    public String getName() {return name; }

    public void setName(String name) {this.name = name; }

    public String getLocation() {return location; }

    public void setLocation(String location) {this.location = location; }

    public void addArtwork(Artwork artwork) {artworks.add(artwork); }

    public void readArtGallery() {
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("The number of artworks: " + Artwork.getCount());
    }

    public void listOfArtworks() {
        System.out.println("List of artworks: ");
        for (Artwork artwork : artworks) {
            System.out.println("~~ " + artwork.getTitle());
        }
    }

    public void sortArtworksByPrice() {
        artworks.sort(Comparator.comparingDouble(Artwork::getPrice));
    }

    @Override
    public String toString() {
        return "ArtGallery: " + name + ", Location: " + location + ", Number of Artworks: " + artworks.size();
    }
}