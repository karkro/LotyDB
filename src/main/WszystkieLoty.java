package main;

import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.Map;

public class WszystkieLoty extends DBConnect {


    private WszystkieLoty() {
        super("SELECT * FROM loty");
    }

    @Override
    protected void process(Map<String, Object> context) throws SQLException {

        NumberFormat formater = NumberFormat.getCurrencyInstance();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("skad") + "-" +
                    resultSet.getString("dokad") + ", " +
                    resultSet.getDate("data_wylotu") + " " +
                    formater.format(resultSet.getInt("cena")) + "zł");
        }
    }

    public static void main(String[] args) {
        new WszystkieLoty().execute();
    }

}
