import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String url = "jdbc:mysql://localhost/loja_virtual?useTimezone=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";
    public DataSource dataSource;

    public ConnectionFactory(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        comboPooledDataSource.setJdbcUrl(url);
        comboPooledDataSource.setUser(user);
        comboPooledDataSource.setPassword(password);

        this.dataSource = comboPooledDataSource;
    }
    public Connection criaConnection() throws SQLException {
        return this.dataSource.getConnection();
    }
}
