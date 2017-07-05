import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class TableDAO extends Connect {

    public void inputId() {
        Statement stmt = null;
        try {
            getConnecion();
            c.setAutoCommit(false);
            Integer max = Integer.valueOf(selectMaxId())+1;
            stmt = c.createStatement();
            String sql = "INSERT INTO public.\"moneyTable\" (\"id\") " + "VALUES (" + max + ")";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void inputUserName(String uname) {
        Statement stmt = null;
        try {
            getConnecion();
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "Update public.\"moneyTable\" set \"username\" = '" + uname +  "' where \"id\" = (SELECT MAX (\"id\") from \"moneyTable\")";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }
    }

    public void inputDate(String date, String uname) {
        Statement stmt = null;
        try {
            getConnecion();
            c.setAutoCommit(false);
            Integer max = Integer.valueOf(selectMaxId())+1;
            stmt = c.createStatement();
            String sql = "Update public.\"moneyTable\" set \"date\" = '" + date + "' where \"id\" = (SELECT MAX(\"id\") from \"moneyTable\" where \"username\" = '" + uname + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println( e.getClass().getName()+": "+ e.getMessage() );
        }

    }

    public void updateGetValue(Integer value, String uname) {
        Statement stmt = null;
        try {
            getConnecion();
            c.setAutoCommit(false);
            Integer max = Integer.valueOf(selectMaxId()) + 1;
            stmt = c.createStatement();
            String sql = "Update public.\"moneyTable\" set \"value\" = " + value + " where \"id\" = (SELECT MAX(\"id\") from \"moneyTable\" where \"username\" = '" + uname + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("c1 = " + c);
    }

    public void updateGetLastComment(String comment, String userName) {
        Statement stmt = null;
        try {
            getConnecion();
            c.setAutoCommit(false);

            stmt = c.createStatement();
            String sql = "Update public.\"moneyTable\" SET \"comment\" = '" + comment + "' where \"id\" = (SELECT max(\"id\") from \"moneyTable\" where \"username\" = '" + userName + "')";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public Integer selectMaxId() {
        Statement stmt = null;
        Integer max = 0;
        String tmp = "null";

        try {
            getConnecion();
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT CAST(MAX(\"id\") as INT) FROM \"moneyTable\"");
            while (rs.next()) {
                max = Integer.valueOf(rs.getString("max"));
            }
            System.out.println(tmp);
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        return max;
    }
}