package main;

import model.Lot;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class AktualizujLoty extends DBConnect {

    @Override
    protected void process(Map<String, Object> context) throws SQLException {
        Lot lot = (Lot) context.get("lot");
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE loty SET skad = ?, dokad = ?, data_wylotu = ?, cena = ?" + " WHERE id_lotu = ?");
        preparedStatement.setString(1, lot.getSkad());
        preparedStatement.setString(2, lot.getDokad());
        preparedStatement.setDate(3, new Date(lot.getDataWylotu().getTime()));
        preparedStatement.setInt(4, lot.getCena());
        preparedStatement.setInt(5, lot.getId());

        preparedStatement.execute();
    }

    public static void main(String[] args) throws ParseException {
        AktualizujLoty aktualizujLoty = new AktualizujLoty();
        Map<String, Object> context = new HashMap<>();
        Lot lot = new Lot(1, "Tokyo", "Dubai",
                new SimpleDateFormat("yyyy-MM-dd").parse("2020-03-15"),
                2500, 30, 140);
        context.put("lot", lot);
        aktualizujLoty.execute(context);
    }

}
