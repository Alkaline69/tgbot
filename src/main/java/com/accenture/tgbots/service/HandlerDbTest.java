package com.accenture.tgbots.service;

import com.accenture.tgbots.model.ProcessingResult;
import org.jooq.*;
import org.jooq.conf.Settings;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.jooq.generated.tables.Billionaires.BILLIONAIRES;

@Component
public class HandlerDbTest implements CommandHandler {
    @Override
    public boolean isSuitable(String text) {
        return "/db".equals(text);
    }

    @Override
    public String getDescription() {
        return "/db [фильтр] Проверка взаимодействия с БД";
    }

    @Override
    public ProcessingResult process(List<String> args) {
        var url = "jdbc:h2:mem:testdb";
        var user = "sa";
        var passwd = "password";

        List<String> res = new ArrayList<>();

        try (var con = DriverManager.getConnection(url, user, passwd)) {
            DSLContext dsl = DSL.using(con, SQLDialect.H2, new Settings().withRenderFormatted(true));
            Result<Record2<String, String>> byCareer = dsl
                    .select(BILLIONAIRES.FIRST_NAME, BILLIONAIRES.LAST_NAME)
                    .from(BILLIONAIRES)
                    .where(BILLIONAIRES.CAREER.eq(args.iterator().next()))
                    .fetch();
            for (Record row : byCareer) {
                res.add(row.getValue(BILLIONAIRES.FIRST_NAME) + " " + row.getValue(BILLIONAIRES.LAST_NAME) );
            }
        } catch (SQLException ex) {
            return new ProcessingResult(ex.getMessage());
        }

        return new ProcessingResult(res.isEmpty() ? Collections.singletonList("Результатов не найдено") : res);
    }
}
