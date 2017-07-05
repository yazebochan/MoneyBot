import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by RomanNedz on 17.06.2017.
 */
public class StateDAO extends Connect {

    public String stateCheck(String uname) {
        Statement stmt = null;
        boolean exists = false;
        String state = "false";

        try {
            getConnecion();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT \"state\" FROM \"STATES\" where \"username\" = '" + uname + "\'");
            while (rs.next()) {
                state = rs.getString("State");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return state;
    }

    public void inputUserName(String uname) {
        Statement stmt = null;
        try {
            getConnecion();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "Insert into public.\"STATES\" (\"username\", \"state\") values ('" + uname +  "', 'GetBegin')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            closeConnection();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void updateStateGetValue(String uName) {
        Statement stmt = null;
        try {
            getConnecion();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "Update public.\"STATES\" set \"state\" = 'GetValue' where \"username\" = " + "\'" + uName + "\'";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateGetComment(String uName) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnecion();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "Update public.\"STATES\" set \"state\" = 'GetDate' where \"username\" = " + "\'" + uName + "\'";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("c = " +c);
    }

    public void updateGetBegin(String uName) {
        Statement stmt = null;
        try {
            Class.forName("org.postgresql.Driver");
            getConnecion();
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "Update public.\"STATES\" set \"state\" = 'GetBegin' where \"username\" = " + "\'" + uName + "\'";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}