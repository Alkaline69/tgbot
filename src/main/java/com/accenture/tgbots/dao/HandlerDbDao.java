package com.accenture.tgbots.dao;

import com.accenture.tgbots.model.Billionair;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.jooq.generated.tables.Billionaires.BILLIONAIRES;

public class HandlerDbDao {

    public List<Billionair> findBillionairsByCareer(String career) {
        try {
            return dsl()
                    .select(BILLIONAIRES.FIRST_NAME,
                            BILLIONAIRES.CAREER,
                            BILLIONAIRES.LAST_NAME)
                    .from(BILLIONAIRES)
                    .where(BILLIONAIRES.CAREER.eq(career))
                    .fetchInto(Billionair.class);
        } catch (SQLException e) {
            return null;
        }
    }

    //todo: connection pool
    private DSLContext dsl() throws SQLException {
        var url = "jdbc:h2:mem:testdb";
        var user = "sa";
        var passwd = "password";

        var con = DriverManager.getConnection(url, user, passwd);
        return DSL.using(con, SQLDialect.H2, new Settings().withRenderFormatted(true));
    }

}
