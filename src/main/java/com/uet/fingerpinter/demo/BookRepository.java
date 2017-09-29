package com.uet.fingerpinter.demo;

import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class BookRepository {
    private DSLContext db;

    public BookRepository() {
        String user = "root";
        String password = "az09az09";
        String url = "jdbc:mysql://localhost:3306/location_indoor?serverTimezone=UTC";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            this.db = DSL.using(conn, SQLDialect.MYSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DSLContext getDb() {
        return db;
    }
}
