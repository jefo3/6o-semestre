package agenda.test.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import agenda.controller.ControllerContact;
import agenda.model.ContactRespose;

@TestMethodOrder(OrderAnnotation.class)
class TestControllerContact {

	private ControllerContact controller;

	@BeforeEach
	public void setUp() throws Exception {
		this.controller = new ControllerContact();
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.controller = null;
	}

	@Test
	@DisplayName("Método de teste para adicionar contato na agenda com sucesso")
	@Order(1)
	public void testAddContact() {
		final String nameContact = "José";
		final int ageContact = 25;
		final String emailContact = "jose@gmail.com";
		final String phoneContact = "(85)99110-0098";

		assertTrue(this.controller.addContact(nameContact, ageContact, emailContact, phoneContact));
	}

	@Test
	@DisplayName("Método de teste para adicionar contato na agenda com falha por e-mail duplo")
	@Order(2)
	public void testAddContactFailDoubleEmail() {
		final String nameContact = "José";
		final int ageContact = 25;
		final String emailContact = "jose@gmail.com";
		final String phoneContact = "(85)99110-0099";

		assertFalse(this.controller.addContact(nameContact, ageContact, emailContact, phoneContact));
	}

	@Test
	@DisplayName("Método de teste para adicionar contato na agenda com falha por telefone duplo")
	@Order(3)
	public void testAddContactFailDoublePhone() {
		final String nameContact = "José";
		final int ageContact = 25;
		final String emailContact = "jose2@gmail.com";
		final String phoneContact = "(85)99110-0098";

		assertFalse(this.controller.addContact(nameContact, ageContact, emailContact, phoneContact));
	}

	@Test
	@DisplayName("Método de teste para adicionar contato na agenda com falha por telefone e e-mail duplo")
	@Order(4)
	public void testAddContactFailDoublePhoneEmail() {
		final String nameContact = "José";
		final int ageContact = 25;
		final String emailContact = "jose@gmail.com";
		final String phoneContact = "(85)99110-0098";

		assertFalse(this.controller.addContact(nameContact, ageContact, emailContact, phoneContact));
	}

	@Test
	@DisplayName("Método de teste para contato favorito da agenda com sucesso")
	@Order(5)
	public void testeFavoriteContact() throws SQLException {
		final int idContactFavorite = this.controller.showContactByEmail("jose@gmail.com").getId();
		assertTrue(this.controller.favoriteContact(idContactFavorite));
	}

	@Test
	@DisplayName("Método de teste para contato favorito da agenda com falha por contato já é favorito")
	@Order(6)
	public void testeFavoriteContactFail() throws SQLException {
		final int idContactFavorite = this.controller.showContactByEmail("jose@gmail.com").getId();
		assertFalse(this.controller.favoriteContact(idContactFavorite));
	}

	@Test
	@DisplayName("Método de teste para atualizar o contato da agenda com sucesso, modificar o nome")
	@Order(7)
	public void testeUpdateNameContact() throws SQLException {
		final ContactRespose contactUpdate = this.controller.showContactByEmail("jose@gmail.com");
		final String lastEmailContactUpdate = contactUpdate.getEmail();
		final String lastphoneContactUpdate = contactUpdate.getPhone();
		contactUpdate.setName("José Silva");

		assertTrue(this.controller.updateContact(contactUpdate.getId(), contactUpdate.getName(), contactUpdate.getAge(),
				contactUpdate.getEmail(), contactUpdate.getPhone(), contactUpdate.isFavorite(), lastEmailContactUpdate,
				lastphoneContactUpdate));
	}

	@Test
	@DisplayName("Método de teste para atualizar o contato da agenda com sucesso, modificar o telefone")
	@Order(8)
	public void testeUpdatePhoneContact() throws SQLException {
		final String nameContact = "José Teste 2";
		final int ageContact = 25;
		final String emailContact = "jose.teste@gmail.com";
		final String phoneContact = "(85)99110-0011";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);

		final ContactRespose contactUpdate = this.controller.showContactByEmail(emailContact);
		final String lastEmailContactUpdate = contactUpdate.getEmail();
		final String lastphoneContactUpdate = contactUpdate.getPhone();
		contactUpdate.setPhone("(85)99110-0022");

		assertTrue(this.controller.updateContact(contactUpdate.getId(), contactUpdate.getName(), contactUpdate.getAge(),
				contactUpdate.getEmail(), contactUpdate.getPhone(), contactUpdate.isFavorite(), lastEmailContactUpdate,
				lastphoneContactUpdate));
	}

	@Test
	@DisplayName("Método de teste para atualizar contato de agenda com sucesso, modificar e-mail")
	@Order(9)
	public void testeUpdateEmailContact() throws SQLException {
		final String nameContact = "José Teste 3";
		final int ageContact = 25;
		final String emailContact = "jose.teste.3@gmail.com";
		final String phoneContact = "(85)99110-0033";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);

		final ContactRespose contactUpdate = this.controller.showContactByEmail(emailContact);
		final String lastEmailContactUpdate = contactUpdate.getEmail();
		final String lastphoneContactUpdate = contactUpdate.getPhone();
		contactUpdate.setEmail("jose.teste3@gmail.com");

		assertTrue(this.controller.updateContact(contactUpdate.getId(), contactUpdate.getName(), contactUpdate.getAge(),
				contactUpdate.getEmail(), contactUpdate.getPhone(), contactUpdate.isFavorite(), lastEmailContactUpdate,
				lastphoneContactUpdate));
	}

	@Test
	@DisplayName("Método de teste para atualizar contato de agenda com sucesso, modificar nome, email e telefone")
	@Order(10)
	public void testeUpdateNameEmailPhoneContact() throws SQLException {
		final String nameContact = "Maria da Glória";
		final int ageContact = 33;
		final String emailContact = "maria@gmail.com";
		final String phoneContact = "(85)99110-1234";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);

		final ContactRespose contactUpdate = this.controller.showContactByEmail(emailContact);
		final String lastEmailContactUpdate = contactUpdate.getEmail();
		final String lastphoneContactUpdate = contactUpdate.getPhone();
		contactUpdate.setName("Maria Glória");
		contactUpdate.setEmail("maria.gloria@gmail.com");
		contactUpdate.setPhone("(85)99110-1243");

		assertTrue(this.controller.updateContact(contactUpdate.getId(), contactUpdate.getName(), contactUpdate.getAge(),
				contactUpdate.getEmail(), contactUpdate.getPhone(), contactUpdate.isFavorite(), lastEmailContactUpdate,
				lastphoneContactUpdate));
	}

	@Test
	@DisplayName("O método de teste para atualizar o contato da agenda com falha, modificar o telefone por telefone já existe")
	@Order(11)
	public void testeUpdatePhoneContactFail() throws SQLException {
		final String nameContact = "José Teste 4";
		final int ageContact = 25;
		final String emailContact = "jose.teste.4@gmail.com";
		final String phoneContact = "(85)99110-0000";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);

		final ContactRespose contactUpdate = this.controller.showContactByEmail(emailContact);
		final String lastEmailContactUpdate = contactUpdate.getEmail();
		final String lastphoneContactUpdate = contactUpdate.getPhone();
		contactUpdate.setPhone("(85)99110-0098");

		assertFalse(this.controller.updateContact(contactUpdate.getId(), contactUpdate.getName(),
				contactUpdate.getAge(), contactUpdate.getEmail(), contactUpdate.getPhone(), contactUpdate.isFavorite(),
				lastEmailContactUpdate, lastphoneContactUpdate));
	}

	@Test
	@DisplayName("Método de teste para atualizar contato de agenda com falha, modificar e-mail por e-mail já existente")
	@Order(12)
	public void testeUpdateEmailFailContact() throws SQLException {
		final String nameContact = "José Teste 5";
		final int ageContact = 25;
		final String emailContact = "jose.teste.5@gmail.com";
		final String phoneContact = "(85)99110-0044";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);

		final ContactRespose contactUpdate = this.controller.showContactByEmail(emailContact);
		final String lastEmailContactUpdate = contactUpdate.getEmail();
		final String lastphoneContactUpdate = contactUpdate.getPhone();
		contactUpdate.setEmail("jose.teste.4@gmail.com");

		assertFalse(this.controller.updateContact(contactUpdate.getId(), contactUpdate.getName(),
				contactUpdate.getAge(), contactUpdate.getEmail(), contactUpdate.getPhone(), contactUpdate.isFavorite(),
				lastEmailContactUpdate, lastphoneContactUpdate));
	}

	@Test
	@DisplayName("Método de teste para atualizar contato de agenda com sucesso, modificar nome, email e telefone os dois dados já existem")
	@Order(13)
	public void testeUpdateNameEmailPhoneContactFail() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);

		final ContactRespose contactUpdate = this.controller.showContactByEmail(emailContact);
		final String lastEmailContactUpdate = contactUpdate.getEmail();
		final String lastphoneContactUpdate = contactUpdate.getPhone();
		contactUpdate.setName("Maria Rosário");
		contactUpdate.setEmail("maria.gloria@gmail.com");
		contactUpdate.setPhone("(85)99110-1243");

		assertFalse(this.controller.updateContact(contactUpdate.getId(), contactUpdate.getName(),
				contactUpdate.getAge(), contactUpdate.getEmail(), contactUpdate.getPhone(), contactUpdate.isFavorite(),
				lastEmailContactUpdate, lastphoneContactUpdate));
	}

	@Test
	@DisplayName("Método de teste para listar todos os contatos da agenda com a lista não vazia")
	@Order(14)
	public void testeListAllContacts() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		assertNotNull(this.controller.listAllContacts());
	}

	@Test
	@DisplayName("Método de teste para listar todos os contatos da agenda com a lista vazia")
	@Order(15)
	public void testeListEmptyContacts() throws SQLException {
		List<ContactRespose> contacts = this.controller.listAllContacts();

		for (ContactRespose contact : contacts) {
			this.controller.deleteContactById(contact.getId());
		}

		assertNull(this.controller.listAllContacts());
	}

	@Test
	@DisplayName("Método de teste para remover contato favorito da agenda com sucesso")
	@Order(16)
	public void testRemoveFavoriteContact() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);
		this.controller.favoriteContact(contact.getId());

		assertTrue(this.controller.removeFavoriteContact(contact.getId()));
	}

	@Test
	@DisplayName("Método de teste para remover contato favorito da agenda com falha")
	@Order(17)
	public void testRemoveFavoriteContactFail() throws SQLException {
		final String emailContact = "rosario@gmail.com";
		ContactRespose contact = this.controller.showContactByEmail(emailContact);

		assertFalse(this.controller.removeFavoriteContact(contact.getId()));
	}

	@Test
	@DisplayName("Método de teste para excluir contato da agenda por ID com sucesso")
	@Order(18)
	public void testDeleteContactID() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);

		assertTrue(this.controller.deleteContactById(contact.getId()));
	}

	@Test
	@DisplayName("Método de teste para excluir contato da agenda por ID com falha")
	@Order(19)
	public void testDeleteContactIDFail() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);
		this.controller.deleteContactById(contact.getId());

		assertFalse(this.controller.deleteContactById(contact.getId()));
	}

	@Test
	@DisplayName("Método de teste para excluir contato da agenda por e-mail com sucesso")
	@Order(20)
	public void testDeleteContactEmail() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);

		assertTrue(this.controller.deleteContactByEmail(contact.getEmail()));
	}

	@Test
	@DisplayName("Método de teste para excluir contato da agenda por e-mail com falha")
	@Order(21)
	public void testDeleteContactEmailFail() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);
		this.controller.deleteContactByEmail(contact.getEmail());

		assertFalse(this.controller.deleteContactByEmail(contact.getEmail()));
	}

	@Test
	@DisplayName("Método de teste para excluir contato da agenda por telefone com sucesso")
	@Order(22)
	public void testDeleteContactPhone() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);

		assertTrue(this.controller.deleteContactByPhone(contact.getPhone()));
	}

	@Test
	@DisplayName("Método de teste para excluir contato da agenda por telefone com falha")
	@Order(23)
	public void testDeleteContactEmailPhone() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);
		this.controller.deleteContactByPhone(contact.getPhone());

		assertFalse(this.controller.deleteContactByPhone(contact.getPhone()));
	}

	@Test
	@DisplayName("O método de teste mostra o contato da agenda por ID com sucesso")
	@Order(24)
	public void testShowContactId() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);

		assertNotNull(this.controller.showContactById(contact.getId()));
	}

	@Test
	@DisplayName("O método de teste mostra o contato da agenda por ID com falha")
	@Order(25)
	public void testShowContactIdFail() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);
		this.controller.deleteContactById(contact.getId());

		assertNull(this.controller.showContactById(contact.getId()));
	}

	@Test
	@DisplayName("O método de teste mostra o contato da agenda por e-mail com sucesso")
	@Order(26)
	public void testShowContactEmail() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);

		assertNotNull(this.controller.showContactByEmail(contact.getEmail()));
	}

	@Test
	@DisplayName("O método de teste mostra o contato da agenda por e-mail com falha")
	@Order(27)
	public void testShowContactEmailFail() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);
		this.controller.deleteContactById(contact.getId());

		assertNull(this.controller.showContactByEmail(contact.getEmail()));
	}

	@Test
	@DisplayName("Método de teste mostra contato de agenda por telefone com sucesso")
	@Order(28)
	public void testShowContactPhone() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);

		assertNotNull(this.controller.showContactByPhone(contact.getPhone()));
	}

	@Test
	@DisplayName("Método de teste mostra contato de agenda por telefone com falha")
	@Order(29)
	public void testShowContactPhoneFail() throws SQLException {
		final String nameContact = "Maria do Rosário";
		final int ageContact = 44;
		final String emailContact = "rosario@gmail.com";
		final String phoneContact = "(85)99110-7777";

		this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
		ContactRespose contact = this.controller.showContactByEmail(emailContact);
		this.controller.deleteContactById(contact.getId());

		assertNull(this.controller.showContactByPhone(contact.getPhone()));
	}

	@Test
	@DisplayName("Método de teste para listar todos os contatos favoritos da agenda com a lista não vazia")
	@Order(30)
	public void testAllContactsFavorites() throws SQLException {
		for (int i = 1; i < 10; i++) {
			final String nameContact = "Maria do Rosário " + i;
			final int ageContact = 44;
			final String emailContact = "rosario" + i + "@gmail.com";
			final String phoneContact = "(85)99110-777" + i;

			this.controller.addContact(nameContact, ageContact, emailContact, phoneContact);
			ContactRespose contact = this.controller.showContactByEmail(emailContact);
			this.controller.favoriteContact(contact.getId());
		}

		assertNotNull(this.controller.listAllContactsFavorites());
	}

	@Test
	@DisplayName("Método de teste para listar todos os contatos favoritos da agenda com a lista vazia")
	@Order(31)
	public void testAllContactsFavoritesFail() throws SQLException {
		for (int i = 1; i < 10; i++) {
			final String emailContact = "rosario" + i + "@gmail.com";

			ContactRespose contact = this.controller.showContactByEmail(emailContact);
			this.controller.removeFavoriteContact(contact.getId());
		}

		assertNull(this.controller.listAllContactsFavorites());
	}

	@Test
	@DisplayName("Método de teste para listar todos os contatos não favoritos da agenda com a lista não vazia")
	@Order(32)
	public void testAllContactsNotFavorites() throws SQLException {
		assertNotNull(this.controller.listAllContactsNoFavorites());
	}

	@Test
	@DisplayName("Método de teste para listar todos os contatos favoritos da agenda com a lista vazia")
	@Order(33)
	public void testAllContactsNotFavoritesFail() throws SQLException {
		for (int i = 1; i < 10; i++) {
			final String emailContact = "rosario" + i + "@gmail.com";

			ContactRespose contact = this.controller.showContactByEmail(emailContact);
			this.controller.favoriteContact(contact.getId());
		}

		assertNull(this.controller.listAllContactsNoFavorites());
	}

	@Test
	@DisplayName("Método de teste para desconectar banco de dados")
	@Order(34)
	public void testDisconnectDataBase() throws SQLException {
		this.controller.closeConnection();
	}
}
