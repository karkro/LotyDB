package main;

import model.Lot;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WyszukajLoty extends DBConnect {

    public WyszukajLoty() {
        super("SELECT * FROM loty ");
    }

    @Override
    protected void transformSQL(Map<String, Object> context) {
        // super.transformSQL(context);
        String wartoscSkad = (String) context.get("skad");
        String wartoscDokad = (String) context.get("dokad");

        String skad = "skad = '" + wartoscSkad + "'";
        String dokad = "dokad = '" + wartoscDokad + "'";

        if (wartoscSkad != null && wartoscDokad != null) {
            sql += " WHERE " + skad + " AND " + dokad;
        } else if (wartoscSkad != null) {
            sql += " WHERE " + skad;
        } else if (wartoscDokad != null) {
            sql += " WHERE " + dokad;
        }

    }


    @Override
    protected void process(Map<String, Object> context) throws SQLException {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

        List<Lot> loty = new ArrayList<>();



        while (resultSet.next()) {

            loty.add(new Lot(
                resultSet.getLong("id_lotu"),
                resultSet.getString("skad"),
                resultSet.getString("dokad"),
                resultSet.getDate("data_wylotu"),
                resultSet.getInt("cena"),
                resultSet.getInt("miejsca_biznes"),
                resultSet.getInt("miejsca_ekonomiczna")
            ));

        }

        context.put("wynik", loty);
    }


    public static void main(String[] args) {
        WyszukajLoty wyszukajLoty = new WyszukajLoty();
        Map<String, Object> context = new HashMap<>();
        context.put("skad", "London");
        //context.put("dokad", "Warszawa");

        wyszukajLoty.execute(context);
        List<Lot> loty =  (List<Lot>) context.get("wynik");

        for (Lot lot : loty) {
            System.out.println(lot);
        }

        System.out.println(wyszukajLoty.sql);
    }


}
