package createDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestDB {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/ebookstore";
    private static final String DB_Driver = "org.postgresql.Driver";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASS = "mypass";

    public static void main(String[] args) {

        Connection c;
        Statement stmt;

        try {
            Class.forName(DB_Driver);
            c = DriverManager
                    .getConnection(DB_URL, DB_USERNAME, DB_PASS);
            c.setAutoCommit(false);
            System.out.println("-- Opened database successfully");

            //--------------- SELECT DATA ------------------
            stmt = c.createStatement();
            System.out.println("\n-- BOOKS");
            ResultSet rs = stmt.executeQuery( "SELECT * FROM Book;" );
            while ( rs.next() ) {
                String  idBook = rs.getString("idBook");
                String  available = rs.getString("available");
                String  genre = rs.getString("genre");
                String  publisher = rs.getString("publisher");
                String  author = rs.getString("author");
                String  title = rs.getString("title");
                String  description = rs.getString("description");
                String  price = rs.getString("price");
                String  amount = rs.getString("amount");
                String  datePublic = rs.getString("datePublic");
                System.out.println(String.format("idBook=%s available=%s genre=%s publisher=%s author=%s title=%s " +
                                "description=%s price=%s amount=%s datePublic=%s",
                        idBook,available,genre,publisher,author,title,description,price,amount,datePublic));
            }

            System.out.println("\n-- CLIENTS");
            rs = stmt.executeQuery( "SELECT * FROM Client;" );
            while ( rs.next() ) {
                String  idClient = rs.getString("idClient");
                String  cardNumber = rs.getString("cardNumber");
                String  phone = rs.getString("phone");
                String  firstName = rs.getString("firstName");
                String  lastName = rs.getString("lastName");
                System.out.println(String.format("idClient=%s cardNumber=%s phone=%s firstName=%s lastName=%s",
                        idClient,cardNumber,phone,firstName,lastName));
            }

            System.out.println("\n-- ORDERS");
            rs = stmt.executeQuery( "SELECT * FROM Orders;" );
            while ( rs.next() ) {
                String  idOrder = rs.getString("idOrder");
                String  statusOrder = rs.getString("statusOrder");
                String  client = rs.getString("client");
                String  dateExecution = rs.getString("dateExecution");
                String  buyBooks = rs.getString("buyBooks");
                String  orderPrice = rs.getString("orderPrice");
                System.out.println(String.format("idOrder=%s statusOrder=%s client=%s dateExecution=%s " +
                                "buyBooks=%s orderPrice=%s",
                        idOrder,statusOrder,client,dateExecution,buyBooks,orderPrice));
            }

            System.out.println("\n-- REQUESTS");
            rs = stmt.executeQuery( "SELECT * FROM Requests;" );
            while ( rs.next() ) {
                String  idRequest = rs.getString("idRequest");
                String  client = rs.getString("client");
                String  dateRequest = rs.getString("dateRequest");
                String  requestBooks = rs.getString("requestBooks");
                String  reqStatus = rs.getString("reqStatus");
                System.out.println(String.format("idRequest=%s client=%s dateRequest=%s requestBooks=%s reqStatus=%s",
                        idRequest,client,dateRequest,requestBooks,reqStatus));
            }

            rs.close();

            stmt.close();
            c.commit();
            System.out.println("-- Operation SELECT done successfully");

            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+ e.getMessage());
            System.exit(0);
        }
        System.out.println("-- All Operations done successfully");
    }
}
