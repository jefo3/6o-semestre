package agenda.SQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import agenda.connection.ConnectionDB;
import agenda.model.ContactRequest;
import agenda.model.ContactRespose;
import agenda.utils.GenerateSQL;

public class ContactSQL {

	private final ConnectionDB connection;
	private final GenerateSQL generate;
	private final Statement stmt;

	public ContactSQL() throws SQLException {
		this.connection = new ConnectionDB();
		this.generate = new GenerateSQL();
		this.stmt = this.connection.getConnection().createStatement();
	}

	public boolean execSQLInsert(ContactRequest contact) {
		try {
			if (this.execSQLExistsContact(contact.getEmail(), contact.getPhone())) {
				int response = this.stmt.executeUpdate(this.generate.generateSQLInsertContact(contact.getName(),
						contact.getAge(), contact.getEmail(), contact.getPhone(), contact.isFavorite()));

				if (response > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println("Já há um contato com esse e-mail ou número");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Falha na inserção deste contato: " + e.getMessage());
			return false;
		}
	}

	private boolean execSQLExistsContact(String emailContact, String phoneContact) {
		try {
			ResultSet response = this.stmt
					.executeQuery(this.generate.generateSQLExistsContact(emailContact, phoneContact));
			int countContacts = 0;

			try {
				while (response.next()) {
					countContacts++;
				}
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}

			if (countContacts <= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Falha na busca deste contato: " + e.getMessage());
			return false;
		}
	}

	private boolean execSQLExistsContactByPhone(String phoneContact) {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLExistsContactByPhone(phoneContact));
			int countContacts = 0;

			try {
				while (response.next()) {
					countContacts++;
				}
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}

			if (countContacts <= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Falha na busca deste contato: " + e.getMessage());
			return false;
		}
	}

	private boolean execSQLExistsContactByEmail(String emailContact) {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLExistsContactByEmail(emailContact));
			int countContacts = 0;

			try {
				while (response.next()) {
					countContacts++;
				}
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}

			if (countContacts <= 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Falha na busca deste contato: " + e.getMessage());
			return false;
		}
	}

	public ResultSet execSQLListContacts() {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLListContacts());

			return response;
		} catch (Exception e) {
			System.out.println("Erro ao listas os contatos: " + e.getMessage());
			return null;
		}
	}

	public ResultSet execSQLListContactById(int id) {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLListContactById(id));

			return response;
		} catch (Exception e) {
			System.out.println("Erro ao listas o contato pelo ID: " + e.getMessage());
			return null;
		}
	}

	public ResultSet execSQLListContactByEmail(String email) {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLListContactByEmail(email));

			return response;
		} catch (Exception e) {
			System.out.println("Erro ao listas o contato pelo E-mail: " + e.getMessage());
			return null;
		}
	}

	public ResultSet execSQLListContactByPhone(String phone) {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLListContactByPhone(phone));

			return response;
		} catch (Exception e) {
			System.out.println("Erro ao listas o contato pelo número: " + e.getMessage());
			return null;
		}
	}

	public boolean execSQLFavoriteContact(int id) {
		try {
			if (!this.execSQLIsFavoriteContact(id)) {
				int response = this.stmt.executeUpdate(this.generate.generateSQLFavoriteContact(id));

				if (response > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println("O contato já está favoritado!");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao favoritar o contato: " + e.getMessage());
			return false;
		}
	}

	public boolean execSQLRemoveFavoriteContact(int id) {
		try {
			if (this.execSQLIsFavoriteContact(id)) {
				int response = this.stmt.executeUpdate(this.generate.generateSQLRemoveFavoriteContact(id));

				if (response > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println("O contato não está favoritado!");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao desfavoritar o contato: " + e.getMessage());
			return false;
		}
	}

	private boolean execSQLIsFavoriteContact(int id) {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLIsFavoriteContact(id));
			int countContacts = 0;

			try {
				while (response.next()) {
					countContacts++;
				}
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
			}

			if (countContacts > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao verificar se o contato é favorito: " + e.getMessage());
			return false;
		}
	}

	public ResultSet execSQLListContactsFavorites() {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLListContactsIsFavorite());

			return response;
		} catch (Exception e) {
			System.out.println("Erro ao listar os contatos favoritos: " + e.getMessage());
			return null;
		}
	}

	public ResultSet execSQLListContactsNoFavorites() {
		try {
			ResultSet response = this.stmt.executeQuery(this.generate.generateSQLListContactsNotFavorite());

			return response;
		} catch (Exception e) {
			System.out.println("Erro ao listar os contatos não favoritos: " + e.getMessage());
			return null;
		}
	}

	public boolean execSQLDeleteContactById(int id) {
		try {
			int response = this.stmt.executeUpdate(this.generate.generateSQLDeleteContactById(id));

			if (response > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao deletar o contato pelo ID: " + e.getMessage());
			return false;
		}
	}

	public boolean execSQLDeleteContactByEmail(String email) {
		try {
			int response = this.stmt.executeUpdate(this.generate.generateSQLDeleteContactByEmail(email));

			if (response > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao deletar o contato pelo E-mail: " + e.getMessage());
			return false;
		}
	}

	public boolean execSQLDeleteContactByPhone(String phone) {
		try {
			int response = this.stmt.executeUpdate(this.generate.generateSQLDeleteContactByPhone(phone));

			if (response > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao deletar o contato pelo número: " + e.getMessage());
			return false;
		}
	}

	public boolean execSQLUpdateContact(ContactRespose contact) {
		try {
			if (this.execSQLExistsContact(contact.getEmail(), contact.getPhone())) {
				int response = this.stmt.executeUpdate(this.generate.generateSQLUpdateContact(contact.getId(),
						contact.getName(), contact.getAge(), contact.getEmail(), contact.getPhone()));

				if (response > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println("Já há um cadastro com esse e-mail ou número");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o contato: " + e.getMessage());
			return false;
		}
	}

	public boolean execSQLUpdateContactRepeatEmail(ContactRespose contact) {
		try {
			if (this.execSQLExistsContactByPhone(contact.getPhone())) {
				int response = this.stmt.executeUpdate(this.generate.generateSQLUpdateContact(contact.getId(),
						contact.getName(), contact.getAge(), contact.getEmail(), contact.getPhone()));

				if (response > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println("Já há um cadastro com esse número");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o contato: " + e.getMessage());
			return false;
		}
	}

	public boolean execSQLUpdateContactRepeatPhone(ContactRespose contact) {
		try {
			if (this.execSQLExistsContactByEmail(contact.getEmail())) {
				int response = this.stmt.executeUpdate(this.generate.generateSQLUpdateContact(contact.getId(),
						contact.getName(), contact.getAge(), contact.getEmail(), contact.getPhone()));

				if (response > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				System.out.println("Já há um cadastro com esse e-mail");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o contato: " + e.getMessage());
			return false;
		}
	}

	public boolean execSQLUpdateContactRepeatEmailAndPhone(ContactRespose contact) {
		try {
			int response = this.stmt.executeUpdate(this.generate.generateSQLUpdateContact(contact.getId(),
					contact.getName(), contact.getAge(), contact.getEmail(), contact.getPhone()));

			if (response > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Erro ao atualizar o contato: " + e.getMessage());
			return false;
		}
	}

	public void closeConnection() throws SQLException {
		this.connection.getConnection().close();
	}

}
