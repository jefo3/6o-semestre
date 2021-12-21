package agenda.main;

import java.sql.SQLException;

import agenda.view.ViewAgenda;

public class MainApplication {

	public static void main(String[] args) throws SQLException {
		ViewAgenda view = new ViewAgenda();
		view.start();
	}

}
