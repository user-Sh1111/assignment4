package kz.aitu.oop.restservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.aitu.oop.restservice.dbconnection.DBConnection;
import kz.aitu.oop.restservice.entities.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Connection;

@RestController
@RequestMapping("/api")
public class MyController {

    @Autowired
    private ObjectMapper oMapper;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello World";
    }

    @PostMapping("/secific")
    public String listener1(@RequestParam String name) {
        Artist arts1 = new Artist();
        arts1.setName(name);
        arts1.setBirthYear(1848);
        arts1.setNationality("French");
        String jsonData = null;
        try {
            jsonData = oMapper.writeValueAsString(arts1);
        } catch (JsonProcessingException e) {
            System.out.println("Some error with student");
        }
        DBConnection con = new DBConnection();
        try {
            con.getConnectionToDb();
        } catch (Exception e) {
            System.out.println("Some error with student in db con");
            throw new RuntimeException(e);
        }
        return jsonData;
    }

    @GetMapping("/hello/artist")
    public String muArtistListener() {
        String jsonText = null;
        Artist arts1 = new Artist(1, "Artist1", 1908, "kazakh");
        try {
            jsonText = oMapper.writeValueAsString(arts1);
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong " + e.toString());
        }
        return jsonText;
    }

    @PostMapping("/hello/customartist")
    public String myCustomArtistListener(@RequestParam int id, @RequestParam String name, @RequestParam int birthYear, @RequestParam String nationality) {
        String jsonText = null;
        Artist arts2 = new Artist(id, name, birthYear, nationality);
        try {
            jsonText = oMapper.writeValueAsString(arts2);
        } catch (JsonProcessingException e) {
            System.out.println("Something went wrong " + e.toString());
        }
        return jsonText;
    }

    @GetMapping("/index/allArtists")
    public String listener2() {
        DBConnection myConnection = new DBConnection();
        Connection con = null;
        ArrayList<Artist> artists = new ArrayList<>();
        try {
            con = DBConnection.connect();
            artists = myConnection.getAllArtistsRS(con);
        } catch (Exception e) {
            System.out.println(" EXCEPTION 1");
        }

        String jsonData = null;
        try {
            jsonData = oMapper.writeValueAsString(artists);
        } catch (JsonProcessingException e) {
            System.out.println("Some error with student");
        }

        return jsonData;
    }

    @PostMapping("/index/findArtist")
    public String listener3(@RequestParam String name) {
        DBConnection myConnection = new DBConnection();
        Connection con = null;
        Artist a1 = null;
        try {
            con = myConnection.connect();
            //a1= myConnection.findArtistByName(con, name);
        } catch (Exception e) {
            System.out.println(" EXCEPTION 1");
        }
        try {
            //con myConnection.connect();
            a1 = myConnection.findArtistByName(con, name);
        } catch (Exception e) {
            System.out.println(" EXCEPTION 2");
        }

        String jsonData = null;
        try {
            jsonData = oMapper.writeValueAsString(a1);
        } catch (JsonProcessingException e) {
            System.out.println("Some error with artist");
        }
        return jsonData;
    }


    @PostMapping("/index/createArtist")
    public String createStudent(@RequestParam int id, @RequestParam String name, @RequestParam int birthyear, @RequestParam String nationality) {
        DBConnection myConnection = new DBConnection();
        Connection con = null;
        Artist a1 = new Artist(id, name, birthyear, nationality);
        String jsonData = null;

        try {
            con = myConnection.connect();
            //myConnection.createStudent(con,s1);
        } catch (Exception e) {
            System.out.println(" EXCEPTION 1");
        }
        try {
            jsonData = oMapper.writeValueAsString(myConnection.createArtist(con, a1));
        } catch (JsonProcessingException e) {
            System.out.println("Some error with artist");
        } catch (SQLException e2) {
            System.out.println("Some error with sql artist");
        }

        return jsonData;
    }


    @PostMapping("/index/updateArtist")
    public String updateStudent(@RequestParam String name, @RequestParam String newName, @RequestParam int newbirthYear, @RequestParam String newNationality) {
    DBConnection myConnection = new DBConnection();
    Connection con = null;
    Artist a1 = null;
    String jsonData = null;
    try {
        con = myConnection.connect();
        a1 = myConnection.findArtistByName(con, name);
        String oldName=  a1.getName();
        System.out.println(a1);
        a1.setName(newName);
        a1.setBirthYear(newbirthYear);
        a1.setNationality(newNationality);
        System.out.println(a1);
        con =  myConnection.connect();
        myConnection.updateArtist(con,a1, oldName);
    }catch(Exception e){
        System.out.println(" EXCEPTION 1");
    }
    try{
        jsonData = oMapper.writeValueAsString(a1);
    }catch (JsonProcessingException e){
        System.out.println("Some error with student");
    }

    return jsonData;
    }




    @PostMapping("/index/deleteArtist")
    public String deleteArtist(@RequestParam String name) {
        DBConnection myConnection = new DBConnection();
        Connection con = null;
        Artist a1 = null;
        String jsonData = null;
        try {
            con = myConnection.connect();
            a1 = myConnection.findArtistByName(con, name);
            System.out.println(a1);
            con = myConnection.connect();
            myConnection.deleteArtist(con, a1);
        }catch(Exception e){
            System.out.println(" EXCEPTION 1");
        }
        try{
            jsonData = oMapper.writeValueAsString(a1);
        }catch (JsonProcessingException e){
            System.out.println("Some error with delete operation");
        }

        return jsonData;
    }


}