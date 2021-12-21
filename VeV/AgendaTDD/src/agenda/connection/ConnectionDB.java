package agenda.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import agenda.utils.Config;

public class ConnectionDB {

	private String url;
	private String user;
	private String pass;
	private Connection con;
	private final Config config = new Config();

	public ConnectionDB() {
		this.url = this.config.getURL();
		this.user = this.config.getUSER();
		this.pass = this.config.getPASS();

		try {
			Class.forName(this.config.getDRIVER());
			this.setCon(DriverManager.getConnection(this.url, this.user, this.pass));
		} catch (Exception e) {
			System.out.println("Falha ao conectar a aplicação com o Banco de Dados: " + e.getMessage());
		}
	}

	private void setCon(Connection con) {
		this.con = con;
	}

	public Connection getConnection() {
		return this.con;
	}

}
