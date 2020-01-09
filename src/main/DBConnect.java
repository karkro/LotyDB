package main;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

abstract public class DBConnect {

    private static final String DRIVER_CLASSNAME = "com.mysql.jdbc.Driver";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/lotyDB?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    /*static DataSource dataSource;

    static {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(DRIVER_CLASSNAME);
        basicDataSource.setUrl(JDBC_URL);
        basicDataSource.setUsername(USERNAME);
        basicDataSource.setPassword(PASSWORD);

        dataSource = basicDataSource;
    }*/

    protected String sql;
    protected Connection connection;
    private Statement statement;
    protected ResultSet resultSet;

    public DBConnect() {
        this("");
    }

    public DBConnect(String sql) {
        this.sql = sql;
    }

    public void execute(Map<String, Object> context) {
        try {
            connectToDatabase();
            transformSQL(context);
            executeSQL();
            process(context);
            close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        execute(new HashMap<>());
    }

    private void connectToDatabase() throws ClassNotFoundException, SQLException {
        Class.forName(DRIVER_CLASSNAME);
        connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }

    /*private void connectToDatabase() throws *//*ClassNotFoundException,*//* SQLException {
        connection = dataSource.getConnection();
    }*/

    protected void transformSQL(Map<String, Object> context) {}

    private void executeSQL() throws SQLException {

        statement = connection.createStatement();

        if (sql == null || sql.trim().equals("")) {
            return;
        }
        resultSet = statement.executeQuery(sql);
    }

    abstract protected void process(Map<String, Object> context) throws SQLException;

    private void close() throws SQLException {
        /*if (resultSet != null) {
            resultSet.close();
        }*/

        if (statement != null) {
            statement.close();
        }

        if (connection != null) {
            connection.close();
        }

        /*if (dataSource != null) {
            BasicDataSource basicDataSource = (BasicDataSource) dataSource;
            basicDataSource.close();
        }*/

    }

}