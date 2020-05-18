//339. .executeQuery() and using Constants
package com.shobaki;

import java.sql.*;

public class Main {
    public static final String DB_NAME = "testjava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:D:\\JavaUdemy2020\\TestDB\\" + DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {


        try {

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text, " +
                    COLUMN_PHONE + " integer, " +
                    COLUMN_EMAIL + " text)");

//            statement.execute("insert into " + TABLE_CONTACTS +
//                    " (" + COLUMN_NAME + ", " +
//                    COLUMN_PHONE + ", " +
//                    COLUMN_EMAIL + ") " +
//                    "values ('Fido', 9038, 'dog@email.com')");

            insertContact(statement, "Tim", 6545678, "tim@email.com");
            insertContact(statement, "Joe", 45632, "joe@anywhere.com");
            insertContact(statement, "Jane", 4829484, "jane@somewhere.com");
            insertContact(statement, "Fido", 9038, "dog@email.com");


            statement.execute("update " + TABLE_CONTACTS + " set " +
                    COLUMN_PHONE + "=5566789" +
                    " where " + COLUMN_NAME + "='Jane'");

            statement.execute("delete from " + TABLE_CONTACTS +
                    " where " + COLUMN_NAME + "='Joe'");

            ResultSet results = statement.executeQuery("select * from " + TABLE_CONTACTS);

            while (results.next()) {
                System.out.println(results.getString(COLUMN_NAME) + " " +
                        results.getInt(COLUMN_PHONE) + " " +
                        results.getString(COLUMN_EMAIL));
            }
            results.close();
            statement.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException{
        statement.execute("insert into " + TABLE_CONTACTS +
                " (" + COLUMN_NAME + ", " +
                COLUMN_PHONE + ", " +
                COLUMN_EMAIL + ") " +
                "values ('" + name + "', " + phone + ", '" + email +"')");
    }
}


// 1

//package com.shobaki;
//
//import java.sql.*;
//
//public class Main {
//
//    public static void main(String[] args) {
//
//        //this way will close the connection automatically
////	    try (Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\JavaUdemy2020\\TestDB\\testjava.db");
////             Statement statement = conn.createStatement();)   {
////            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
//
//        //this way is old and is better if you close the connection manually
//        try {
//
//            Connection conn = DriverManager.getConnection("jdbc:sqlite:D:\\JavaUdemy2020\\TestDB\\testjava.db");
////            conn.setAutoCommit(false); //if you don't want make changes to the database
//            Statement statement = conn.createStatement();
////            statement.execute("CREATE TABLE contacts (name TEXT, phone INTEGER, email TEXT)");
//            statement.execute("CREATE TABLE IF NOT EXISTS contacts " +
//                    " (name TEXT, phone INTEGER, email TEXT)");
////            statement.execute("insert into contacts (name, phone, email) " +
////                    "values ('Tim', 6545678, 'tim@email.com')");
////            statement.execute("insert into contacts (name, phone, email) " +
////                    "values ('Joe', 45632, 'joe@anywhere.com')");
////            statement.execute("insert into contacts (name, phone, email) " +
////                    "values ('Jane', 4829484, 'jane@somwhere.com')");
////            statement.execute("insert into contacts (name, phone, email) " +
////                    "values ('Fido', 9038, 'dog@email.com')");
//
////            statement.execute("update contacts set phone=5566789 where name='Jane'");
////            statement.execute("delete from contacts where name='Joe'");
//
//
////            statement.execute("select * from contacts");
////            ResultSet results = statement.getResultSet();
//
//            ResultSet results = statement.executeQuery("select * from contacts");
//
//            while(results.next()){
//                System.out.println(results.getString("name")+
//                                    " " + results.getInt("phone")
//                                    + " " + results.getString("email"));
//            }
//            results.close();
//            statement.close();
//            conn.close();
//
//        } catch (SQLException e) {
//            System.out.println("Something went wrong: " + e.getMessage());
//        }
//    }
//}
