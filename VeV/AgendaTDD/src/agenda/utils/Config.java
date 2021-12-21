package agenda.utils;

public class Config {

	private final String DRIVER = "org.postgresql.Driver";
	private final String URL = "jdbc:postgresql://localhost:5432/agendatelefonica";
	private final String USER = "postgres";
	private final String PASS = "qwe123";

	public Config() {
	}

	public String getDRIVER() {
		return this.DRIVER;
	}

	public String getURL() {
		return this.URL;
	}

	public String getUSER() {
		return this.USER;
	}

	public String getPASS() {
		return this.PASS;
	}

}
