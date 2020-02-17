package com.accenture.tgbots.service;

import com.accenture.tgbots.model.ProcessingResult;
import org.springframework.stereotype.Component;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        var query = "SELECT * FROM billionaires WHERE career = ?";

        List<String> res = new ArrayList<>();

        try {
            var con = DriverManager.getConnection(url, user, passwd);
            var st = con.prepareStatement(query);
            st.setString(1, args.iterator().next());
            var rs = st.executeQuery();

            while (rs.next()) {
                System.out.printf("%d %s", rs.getInt(1), rs.getString(2));
                res.add(rs.getString(2) + " " + rs.getString(3));
            }
            con.close();
        } catch (SQLException ex) {
            return new ProcessingResult(ex.getMessage());
        }

        return new ProcessingResult(res.isEmpty() ? Collections.singletonList("Результатов не найдено") : res);
    }
}
