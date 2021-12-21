package agenda.test.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import agenda.utils.GenerateSQL;

@TestMethodOrder(OrderAnnotation.class)
class TestGenerateSQLUtil {

	private GenerateSQL generate;

	@BeforeEach
	public void setUp() throws Exception {
		this.generate = new GenerateSQL();
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.generate = null;
	}

	@Test
	@DisplayName("Testando o retorno de SQL para listar todos os contatos")
	@Order(1)
	public void testGenerateSQLListContacts() {
		final String SQL = "SELECT * FROM contact";
		assertEquals(SQL, this.generate.generateSQLListContacts());
	}

	@Test
	@DisplayName("Testando o SQL de retorno para listar todos os favoritos dos contatos")
	@Order(2)
	public void testGenerateSQLListContactsIsFavorite() {
		final String SQL = "SELECT * FROM contact WHERE favorite = TRUE";
		assertEquals(SQL, this.generate.generateSQLListContactsIsFavorite());
	}

	@Test
	@DisplayName("Testando o SQL de retorno para listar todos os contatos não favoritos")
	@Order(3)
	public void testGenerateSQLListContactsNotFavorite() {
		final String SQL = "SELECT * FROM contact WHERE favorite = FALSE";
		assertEquals(SQL, this.generate.generateSQLListContactsNotFavorite());
	}

	@Test
	@DisplayName("Testando retorno de SQL para contato existente")
	@Order(4)
	public void testGenerateSQLExistsContact() {
		String emailContact = "joao@gmail.com";
		final String phoneContact = "(85)91111-1111";
		final String SQL = "SELECT * FROM contact WHERE email = '" + emailContact + "' OR phone = '" + phoneContact
				+ "'";
		assertEquals(SQL, this.generate.generateSQLExistsContact(emailContact, phoneContact));
	}

	@Test
	@DisplayName("Testando retorno de SQL para contato existente por telefone")
	@Order(5)
	public void testGenerateSQLExistsContactByPhone() {
		final String phoneContact = "(85)91111-1111";
		final String SQL = "SELECT * FROM contact WHERE phone = '" + phoneContact + "'";
		assertEquals(SQL, this.generate.generateSQLExistsContactByPhone(phoneContact));
	}

	@Test
	@DisplayName("Testando retorno de SQL para contato existente por e-mail")
	@Order(6)
	public void testGenerateSQLExistsContactByEmail() {
		final String emailContact = "(85)91111-1111";
		final String SQL = "SELECT * FROM contact WHERE email = '" + emailContact + "'";
		assertEquals(SQL, this.generate.generateSQLExistsContactByEmail(emailContact));
	}

	@Test
	@DisplayName("Testando SQL de retorno para inserir contato")
	@Order(7)
	public void testGenerateSQLInsertContact() {
		final String nameContact = "joão";
		final int ageContact = 24;
		final String emailContact = "joao@gmail.com";
		final String phoneContact = "(85)91111-1111";
		final boolean favoriteContact = false;
		final String SQL = "INSERT INTO contact (name, age, email, phone, favorite) values ('" + nameContact + "', "
				+ ageContact + ", '" + emailContact + "', '" + phoneContact + "', " + favoriteContact + ");";
		assertEquals(SQL, this.generate.generateSQLInsertContact(nameContact, ageContact, emailContact, phoneContact,
				favoriteContact));
	}

	@Test
	@DisplayName("Testando o retorno de SQL para o contato da lista por id")
	@Order(8)
	public void testGenerateSQLListContactById() {
		final int id = 10;
		final String SQL = "SELECT * FROM contact WHERE id = " + id + ";";
		assertEquals(SQL, this.generate.generateSQLListContactById(id));
	}

	@Test
	@DisplayName("Testando o retorno de SQL para o contato da lista por e-mail")
	@Order(9)
	public void testGenerateSQLListContactByEmail() {
		final String email = "maria@gmail.com";
		final String SQL = "SELECT * FROM contact WHERE email = '" + email + "';";
		assertEquals(SQL, this.generate.generateSQLListContactByEmail(email));
	}

	@Test
	@DisplayName("Testando o retorno de SQL para o contato da lista por telefone")
	@Order(10)
	public void testGenerateSQLListContactByPhone() {
		final String phone = "(85)91111-1111";
		final String SQL = "SELECT * FROM contact WHERE phone = '" + phone + "';";
		assertEquals(SQL, this.generate.generateSQLListContactByPhone(phone));
	}

	@Test
	@DisplayName("Testando o retorno de SQL para contato favorito")
	@Order(11)
	public void testGenerateSQLFavoriteContact() {
		final int id = 10;
		final String SQL = "UPDATE contact SET favorite = TRUE WHERE id = " + id + ";";
		assertEquals(SQL, this.generate.generateSQLFavoriteContact(id));
	}

	@Test
	@DisplayName("Testando o retorno de SQL para remover o contato favorito")
	@Order(12)
	public void testGenerateSQLRemoveFavoriteContact() {
		final int id = 10;
		final String SQL = "UPDATE contact SET favorite = FALSE WHERE id = " + id + ";";
		assertEquals(SQL, this.generate.generateSQLRemoveFavoriteContact(id));
	}

	@Test
	@DisplayName("Testar o retorno de SQL para verificar o contato é o favorito")
	@Order(13)
	public void testGenerateSQLIsFavoriteContact() {
		final int id = 10;
		final String SQL = "SELECT * FROM contact WHERE id = " + id + " AND favorite = TRUE;";
		assertEquals(SQL, this.generate.generateSQLIsFavoriteContact(id));
	}

	@Test
	@DisplayName("Testando o retorno de SQL para excluir contato por id")
	@Order(14)
	public void testGenerateSQLDeleteContactById() {
		final int id = 1;
		final String SQL = "DELETE FROM contact WHERE id = " + id + ";";
		assertEquals(SQL, this.generate.generateSQLDeleteContactById(id));
	}

	@Test
	@DisplayName("Teste de retorno de SQL para exclusão de contato por e-mail")
	@Order(15)
	public void testGenerateSQLDeleteContactByEmail() {
		final String email = "maria@gmail.com";
		final String SQL = "DELETE FROM contact WHERE email = '" + email + "';";
		assertEquals(SQL, this.generate.generateSQLDeleteContactByEmail(email));
	}

	@Test
	@DisplayName("Testando o SQL de retorno para excluir contato por telefone")
	@Order(16)
	public void testGenerateSQLDeleteContactByPhone() {
		final String phone = "maria@gmail.com";
		final String SQL = "DELETE FROM contact WHERE phone = '" + phone + "';";
		assertEquals(SQL, this.generate.generateSQLDeleteContactByPhone(phone));
	}

	@Test
	@DisplayName("Testando SQL de retorno para contato de atualização")
	@Order(17)
	public void testGenerateSQLUpdateContact() {
		final int idContact = 1;
		final String nameContact = "maria";
		final int ageContact = 10;
		final String emailContact = "maria@gmail.com";
		final String phoneContact = "(85)91111-1111";
		final String SQL = "UPDATE contact SET name = '" + nameContact + "', age = " + ageContact + ", email = '"
				+ emailContact + "', phone = '" + phoneContact + "' WHERE id = " + idContact + ";";
		assertEquals(SQL,
				this.generate.generateSQLUpdateContact(idContact, nameContact, ageContact, emailContact, phoneContact));
	}

}
