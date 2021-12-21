package agenda.controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import agenda.SQL.ContactSQL;
import agenda.model.ContactRequest;
import agenda.model.ContactRespose;

public class ControllerContact {

	private ContactSQL contactSQL;

	public ControllerContact() throws SQLException {
		this.contactSQL = new ContactSQL();
	}

	public boolean addContact(String name, int age, String email, String phone) {
		ContactRequest contact = new ContactRequest(name, age, email, phone);

		return this.contactSQL.execSQLInsert(contact);
	}

	public boolean favoriteContact(int id) {
		return this.contactSQL.execSQLFavoriteContact(id);
	}

	public boolean updateContact(int id, String name, int age, String email, String phone, boolean isFavorite,
			String lastEmailContactUpdate, String lastphoneContactUpdate) {
		ContactRespose contact = new ContactRespose(id, name, age, email, phone, isFavorite);
		boolean responseView = false;

		if (lastEmailContactUpdate != email && lastphoneContactUpdate != phone) {
			responseView = this.contactSQL.execSQLUpdateContact(contact);
		} else if (lastEmailContactUpdate == email && lastphoneContactUpdate != phone) {
			responseView = this.contactSQL.execSQLUpdateContactRepeatEmail(contact);
		} else if (lastphoneContactUpdate == phone && lastEmailContactUpdate != email) {
			responseView = this.contactSQL.execSQLUpdateContactRepeatPhone(contact);
		} else if (lastEmailContactUpdate == email && lastphoneContactUpdate == phone) {
			responseView = this.contactSQL.execSQLUpdateContactRepeatEmailAndPhone(contact);
		}

		return responseView;
	}

	public List<ContactRespose> listAllContacts() throws SQLException {
		List<ContactRespose> contacts;
		ResultSet response = this.contactSQL.execSQLListContacts();

		if (response.isBeforeFirst()) {
			contacts = new ArrayList<ContactRespose>();

			while (response.next()) {
				contacts.add(new ContactRespose(response.getInt("id"), response.getString("name"),
						response.getInt("age"), response.getString("email"), response.getString("phone"),
						response.getBoolean("favorite")));
			}
		} else {
			contacts = null;
		}

		return contacts;
	}

	public boolean removeFavoriteContact(int id) {
		return this.contactSQL.execSQLRemoveFavoriteContact(id);
	}

	public boolean deleteContactById(int id) {
		return this.contactSQL.execSQLDeleteContactById(id);
	}

	public boolean deleteContactByEmail(String email) {
		return this.contactSQL.execSQLDeleteContactByEmail(email);
	}

	public boolean deleteContactByPhone(String phone) {
		return this.contactSQL.execSQLDeleteContactByPhone(phone);
	}

	public ContactRespose showContactById(int id) throws SQLException {
		ResultSet response = this.contactSQL.execSQLListContactById(id);

		if (!response.isBeforeFirst()) {
			return null;
		} else {
			response.next();
			ContactRespose contact = new ContactRespose(response.getInt("id"), response.getString("name"),
					response.getInt("age"), response.getString("email"), response.getString("phone"),
					response.getBoolean("favorite"));

			return contact;
		}
	}

	public ContactRespose showContactByEmail(String email) throws SQLException {
		ResultSet response = this.contactSQL.execSQLListContactByEmail(email);

		if (!response.isBeforeFirst()) {
			return null;
		} else {
			response.next();
			ContactRespose contact = new ContactRespose(response.getInt("id"), response.getString("name"),
					response.getInt("age"), response.getString("email"), response.getString("phone"),
					response.getBoolean("favorite"));

			return contact;
		}
	}

	public ContactRespose showContactByPhone(String phone) throws SQLException {
		ResultSet response = this.contactSQL.execSQLListContactByPhone(phone);

		if (!response.isBeforeFirst()) {
			return null;
		} else {
			response.next();
			ContactRespose contact = new ContactRespose(response.getInt("id"), response.getString("name"),
					response.getInt("age"), response.getString("email"), response.getString("phone"),
					response.getBoolean("favorite"));

			return contact;
		}
	}

	public List<ContactRespose> listAllContactsFavorites() throws SQLException {
		List<ContactRespose> contacts;
		ResultSet response = this.contactSQL.execSQLListContactsFavorites();

		if (response.isBeforeFirst()) {
			contacts = new ArrayList<ContactRespose>();

			while (response.next()) {
				contacts.add(new ContactRespose(response.getInt("id"), response.getString("name"),
						response.getInt("age"), response.getString("email"), response.getString("phone"),
						response.getBoolean("favorite")));
			}
		} else {
			contacts = null;
		}

		return contacts;
	}

	public List<ContactRespose> listAllContactsNoFavorites() throws SQLException {
		List<ContactRespose> contacts;
		ResultSet response = this.contactSQL.execSQLListContactsNoFavorites();

		if (response.isBeforeFirst()) {
			contacts = new ArrayList<ContactRespose>();

			while (response.next()) {
				contacts.add(new ContactRespose(response.getInt("id"), response.getString("name"),
						response.getInt("age"), response.getString("email"), response.getString("phone"),
						response.getBoolean("favorite")));
			}
		} else {
			contacts = null;
		}

		return contacts;
	}

	public void closeConnection() throws SQLException {
		this.contactSQL.closeConnection();
	}

}
