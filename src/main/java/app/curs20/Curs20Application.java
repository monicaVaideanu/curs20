package app.curs20;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class Curs20Application {

    public static void main(String[] args) {
        SpringApplication.run(Curs20Application.class, args);
    }
}

