import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Connect {
    protected Connection c = null;

    protected Connection getConnecion() throws java.sql.SQLException {
        if (c == null) {
            try {
                c = DriverManager.getConnection("",
                        "", "");
                c.setAutoCommit(false);
                System.out.println(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return c;

        } else {
            return c;
        }

    }

    protected void closeConnection () {

        try {
            if (c == null){
                System.out.println("null1");
            }
            else {
                if (c.isClosed()) {
                    System.out.println("Closed");
                } else {
                    c.close();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
