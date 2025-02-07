package kz.aitu.oop.restservice.entities;


public class main {
    public static void main(String[] args) {
        Artist artist1 = new Artist(1, "Leonardo Da Vinci", 1452, "Italian");
        Artwork artwork1 = new Artwork("Mona Lisa", 500, 870000000.0, artist1);
        Artwork artwork2 = new Artwork("The Last Supper", 530, 1000000000.0, artist1);
        Artwork artwork3 = new Artwork("Leda and the Swan", 522, 52887500.0, artist1);

        ArtGallery artGallery = new ArtGallery("Louvre", "Paris, France");
        artGallery.addArtwork(artwork1);
        artGallery.addArtwork(artwork2);
        artGallery.addArtwork(artwork3);

        artGallery.readArtGallery();
        artGallery.listOfArtworks();
        System.out.println();




        artwork1.readArtwork();
        System.out.println();

        artwork2.readArtwork();
        System.out.println();

        artwork3.readArtwork();
        System.out.println();

        artGallery.sortArtworksByPrice();
        System.out.println("Sorted by price: ");
        artGallery.listOfArtworks();


        Artist a1 = new Artist(1, "Shino", 2006, "Kazakhstan");
        Artist a2 = new Artist(1, "Shino", 2006, "Kazakhstan");

        System.out.println("HashCode a1: " + a1.hashCode());
        System.out.println("HashCode a2: " + a2.hashCode());
        System.out.println("a1 equals a2: " + a1.equals(a2));





    }
}