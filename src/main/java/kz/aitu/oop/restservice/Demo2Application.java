package kz.aitu.oop.restservice;

import kz.aitu.oop.restservice.dbconnection.DBConnection;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

	public static void main(String[] args) {

		SpringApplication.run(Demo2Application.class, args);
		try{
			DBConnection dbConnection = new DBConnection();
			dbConnection.getConnectionToDb();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
