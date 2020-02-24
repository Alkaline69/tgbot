package com.accenture.tgbots.dao;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.SQLException;

public class AbstractHandlerDao {

    //todo: connection pool
    protected DSLContext dsl() throws SQLException {
        var url = "jdbc:h2:mem:testdb";
        var user = "sa";
        var passwd = "password";

        var con = DriverManager.getConnection(url, user, passwd);
        return DSL.using(con, SQLDialect.H2, new Settings().withRenderFormatted(true));
    }

}
