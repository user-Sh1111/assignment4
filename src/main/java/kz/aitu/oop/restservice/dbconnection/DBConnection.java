package kz.aitu.oop.restservice.dbconnection;
import kz.aitu.oop.restservice.entities.Artist;

import java.sql.*;
import java.util.ArrayList;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/artgallerydb";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public void getConnectionToDb() throws Exception{
        Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        System.out.println("Connection Established successfully");
        String query = "SELECT * FROM public.artist";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        String name = rs.getString("name");
        System.out.println(name);
        st.close();
        con.close();
        System.out.println("Connection Closed....");

    }


    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public int closeConn (Connection con) throws SQLException {
        if(con!=null) {
            con.close();
            System.out.println("Connection Closed....");
            return 0;
        }
        System.out.println("Connection is null....");
        return 1;
    }

    public ArrayList<Artist> getAllArtistsRS (Connection con) throws SQLException {
        String query = "select * from public.artist";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        ArrayList<Artist> artists = new ArrayList<>();
        while (rs.next()){
            Artist artist = new Artist();
            artist.setId(rs.getInt("id"));
            artist.setName(rs.getString("name"));
            artist.setBirthYear(rs.getInt( "birthyear"));
            artist.setNationality(rs.getString("nationality"));
            artists.add(artist);
        }
        st.close();
        closeConn(con);
        return artists;
    }


    public Artist findArtistByName(Connection con, String name) throws SQLException {
    String query = "select * from public.artist where name = ?";
    PreparedStatement st = con.prepareStatement(query);
    st.setString(1, name);
    ResultSet rs = st.executeQuery();
    ArrayList<Artist> artists = new ArrayList<>();
    Artist artist = new Artist();

    while (rs.next()){
        artist.setId(rs.getInt("id"));
        artist.setName(rs.getString("name"));
        artist.setBirthYear(rs.getInt( "birthyear"));
        artist.setNationality(rs.getString("nationality"));
        artists.add(artist);
    }

    st.close();
    closeConn (con);
    return artist;
    }


    public Artist createArtist(Connection con, Artist a) throws SQLException {

        String query="INSERT INTO public.artist (id, name, birthyear, nationality) VALUES (?,?,?,?)";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt(1,a.getId());
        st.setString(2,a.getName());
        st.setInt(3, a.getBirthYear());
        st.setString(4, a.getNationality());
        //ResultSet rs = st.executeQuery();
        int success = st.executeUpdate();

        st.close();
        closeConn(con);
        if(success > 0){
            System.out.println("Artist is added");
            return a;
        }
        return null;

    }


    public Artist updateArtist(Connection con, Artist a, String oldName) throws SQLException {
        String query="UPDATE public.artist SET id = ?, name = ?, birthyear = ?, nationality = ? WHERE name = ?";
        PreparedStatement st = con.prepareStatement(query);
        st.setInt( 1,a.getId());
        st.setString( 2, a.getName());
        st.setInt(3, a.getBirthYear());
        st.setString(4, a.getNationality());
        st.setString(  5, oldName);

        int success = st.executeUpdate();
        st.close();
        closeConn(con);
        if (success > 0){
            System.out.println("Artist is updated ");
            return a;
        }
        return null;
    }


    public Artist deleteArtist (Connection con, Artist a) throws SQLException {
        String query ="DELETE FROM public.artist WHERE name=?"; // query to be run
        PreparedStatement st = con.prepareStatement(query);
        st.setString( 1,a.getName());
        int success = st.executeUpdate();
        st.close();
        closeConn(con);
        if (success > 0){
            System.out.println("Artist is deleted");
            return a;
        }

        return null;
    }


}