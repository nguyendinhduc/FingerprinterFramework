package com.uet.fingerpinter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class FingerPrinterApplication {

    public static void main(String[] args) {
//        URI dbUri = null;
//        try {
//            dbUri = new URI("postgres://chawivvgwubflf:nJWpvmif4_ZFGUhcQVDgjmuig1@ec2-54-197-241-24.compute-1.amazonaws.com:5432/d102lmkpsa31tp");
//            String username = dbUri.getUserInfo().split(":")[0];
//            String password = dbUri.getUserInfo().split(":")[1];
//            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath()+"?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
//
//            Connection connection = DriverManager.getConnection(dbUrl, username, password);
//            String schema = connection.getSchema();
//            System.out.println("schemma: " + schema);
//            String sql = "INSERT INTO building(name, detail) " +
//                    "VALUES ('Mahnaz', 'Fatma')";
//            Statement statement = connection.createStatement();
//            statement.execute(sql);
//            connection.close();
//            System.out.println("sucess");
//        } catch (URISyntaxException | SQLException e) {
//            e.printStackTrace();
//        }


        SpringApplication.run(FingerPrinterApplication.class, args);
    }
}
