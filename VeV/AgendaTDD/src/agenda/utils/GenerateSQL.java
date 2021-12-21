package agenda.utils;

public class GenerateSQL {

	private String SQL;

	public GenerateSQL() {
		this.SQL = null;
	}

	private void setSQL(String SQL) {
		this.SQL = SQL;
	}

	private String getSQL() {
		return this.SQL;
	}

	public String generateSQLListContacts() {
		this.setSQL("SELECT * FROM contact");
		return this.getSQL();
	}

	public String generateSQLListContactsIsFavorite() {
		this.setSQL("SELECT * FROM contact WHERE favorite = TRUE");
		return this.getSQL();
	}

	public String generateSQLListContactsNotFavorite() {
		this.setSQL("SELECT * FROM contact WHERE favorite = FALSE");
		return this.getSQL();
	}

	public String generateSQLExistsContact(String emailContact, String phoneContact) {
		this.setSQL("SELECT * FROM contact WHERE email = '" + emailContact + "' OR phone = '" + phoneContact + "'");
		return this.getSQL();
	}

	public String generateSQLExistsContactByPhone(String phoneContact) {
		this.setSQL("SELECT * FROM contact WHERE phone = '" + phoneContact + "'");
		return this.getSQL();
	}

	public String generateSQLExistsContactByEmail(String emailContact) {
		this.setSQL("SELECT * FROM contact WHERE email = '" + emailContact + "'");
		return this.getSQL();
	}

	public String generateSQLInsertContact(String nameContact, int ageContact, String emailContact, String phoneContact,
			boolean favoriteContact) {
		this.setSQL("INSERT INTO contact (name, age, email, phone, favorite) values ('" + nameContact + "', "
				+ ageContact + ", '" + emailContact + "', '" + phoneContact + "', " + favoriteContact + ");");
		return this.getSQL();
	}

	public String generateSQLListContactById(int id) {
		this.setSQL("SELECT * FROM contact WHERE id = " + id + ";");
		return this.getSQL();
	}

	public String generateSQLListContactByEmail(String email) {
		this.setSQL("SELECT * FROM contact WHERE email = '" + email + "';");
		return this.getSQL();
	}

	public String generateSQLListContactByPhone(String phone) {
		this.setSQL("SELECT * FROM contact WHERE phone = '" + phone + "';");
		return this.getSQL();
	}

	public String generateSQLFavoriteContact(int id) {
		this.setSQL("UPDATE contact SET favorite = TRUE WHERE id = " + id + ";");
		return this.getSQL();
	}

	public String generateSQLRemoveFavoriteContact(int id) {
		this.setSQL("UPDATE contact SET favorite = FALSE WHERE id = " + id + ";");
		return this.getSQL();
	}

	public String generateSQLIsFavoriteContact(int id) {
		this.setSQL("SELECT * FROM contact WHERE id = " + id + " AND favorite = TRUE;");
		return this.getSQL();
	}

	public String generateSQLDeleteContactById(int id) {
		this.setSQL("DELETE FROM contact WHERE id = " + id + ";");
		return this.getSQL();
	}

	public String generateSQLDeleteContactByEmail(String email) {
		this.setSQL("DELETE FROM contact WHERE email = '" + email + "';");
		return this.getSQL();
	}

	public String generateSQLDeleteContactByPhone(String phone) {
		this.setSQL("DELETE FROM contact WHERE phone = '" + phone + "';");
		return this.getSQL();
	}

	public String generateSQLUpdateContact(int idContact, String nameContact, int ageContact, String emailContact,
			String phoneContact) {
		this.setSQL("UPDATE contact SET name = '" + nameContact + "', age = " + ageContact + ", email = '"
				+ emailContact + "', phone = '" + phoneContact + "' WHERE id = " + idContact + ";");
		return this.getSQL();
	}

}
