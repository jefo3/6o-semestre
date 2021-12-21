package agenda.test.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import agenda.model.ContactRespose;

@TestMethodOrder(OrderAnnotation.class)
class TestModelContactResponse {

	private ContactRespose contact;

	@BeforeEach
	public void setUp() throws Exception {
		this.contact = new ContactRespose();
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.contact = null;
	}

	@Test
	@DisplayName("Test 00: Testing ID field insert in object of model ContactResponse")
	@Order(0)
	public void testSetId() {
		final int idTesteInsert = 1;
		this.contact.setId(idTesteInsert);
		assertEquals(idTesteInsert, this.contact.getId());
	}

	@Test
	@DisplayName("Teste de inserção de campo de nome no objeto do modelo ContactResponse")
	@Order(1)
	public void testSetName() {
		final String nameTesteInsert = "José";
		this.contact.setName(nameTesteInsert);
		assertEquals(nameTesteInsert, this.contact.getName());
	}

	@Test
	@DisplayName("Teste de inserção de campo de idade no objeto do modelo ContactResponse")
	@Order(2)
	public void testSetAge() {
		final int ageTesteInsert = 23;
		this.contact.setAge(ageTesteInsert);
		assertEquals(ageTesteInsert, this.contact.getAge());
	}

	@Test
	@DisplayName("Testando a inserção do campo de e-mail no objeto do modelo ContactResponse")
	@Order(3)
	public void testSetEmail() {
		final String emailTesteInsert = "jose@gmail.com";
		this.contact.setEmail(emailTesteInsert);
		assertEquals(emailTesteInsert, this.contact.getEmail());
	}

	@Test
	@DisplayName("Testando a inserção do campo do telefone no objeto do modelo ContactResponse")
	@Order(4)
	public void testSetPhone() {
		final String phoneTesteInsert = "(85)91111-1111";
		this.contact.setPhone(phoneTesteInsert);
		assertEquals(phoneTesteInsert, this.contact.getPhone());
	}

	@Test
	@DisplayName("Testando a inserção de campo favorito no objeto do modelo ContactResponse")
	@Order(5)
	public void testSetFavorite() {
		final boolean favoriteTesteInsert = true;
		this.contact.setFavorite(favoriteTesteInsert);
		assertEquals(favoriteTesteInsert, this.contact.isFavorite());
	}

	@Test
	@DisplayName("Testar o método getId sem adicionar um valor ao atributo no objeto do modelo ContactResponse")
	@Order(6)
	public void testGetIdWithNullValue() {
		assertEquals(0, this.contact.getId());
	}

	@Test
	@DisplayName("Testar o método getName sem adicionar um valor ao atributo no objeto do modelo ContactResponse")
	@Order(7)
	public void testGetNameWithNullValue() {
		assertEquals(null, this.contact.getName());
	}

	@Test
	@DisplayName("Testar o método getAge sem adicionar um valor ao atributo no objeto do modelo ContactResponse")
	@Order(8)
	public void testGetAgeWithNullValue() {
		assertEquals(0, this.contact.getAge());
	}

	@Test
	@DisplayName("Testar o método getEmail sem adicionar um valor ao atributo no objeto do modelo ContactResponse")
	@Order(9)
	public void testGetEmailWithNullValue() {
		assertEquals(null, this.contact.getEmail());
	}

	@Test
	@DisplayName("Testar o método getPhone sem adicionar um valor ao atributo no objeto do modelo ContactResponse")
	@Order(10)
	public void testGetPhoneWithNullValue() {
		assertEquals(null, this.contact.getPhone());
	}

	@Test
	@DisplayName("Testar o método isFavorite sem adicionar um valor ao atributo no objeto do modelo ContactResponse")
	@Order(11)
	public void testIsFavoriteWithNullValue() {
		assertEquals(false, this.contact.isFavorite());
	}

	@Test
	@DisplayName("Método de teste toString no objeto do modelo ContactResponse")
	@Order(12)
	public void testToString() {
		final int idTestToString = 1;
		final String nameTestToString = "José";
		final int ageTestToString = 25;
		final String emailTestToString = "jose@gmail.com";
		final String phoneTestToString = "(85)92222-2222";

		this.contact.setId(idTestToString);
		this.contact.setName(nameTestToString);
		this.contact.setAge(ageTestToString);
		this.contact.setEmail(emailTestToString);
		this.contact.setPhone(phoneTestToString);

		final String toStringCompare = "id: " + idTestToString + "\nNome: " + nameTestToString + "\nIdade: "
				+ ageTestToString + "\nE-mail: " + emailTestToString + "\nNúmero: " + phoneTestToString
				+ "\nFavotiro: Não";
		assertEquals(toStringCompare, this.contact.toString());
	}

	@Test
	@DisplayName("Construtor do método de teste e contato permanente favorecido no objeto do modelo ContactResponse")
	@Order(13)
	public void testeMethodConstructor() {
		final int idTestToString = 1;
		final String nameTestToString = "José";
		final int ageTestToString = 25;
		final String emailTestToString = "jose@gmail.com";
		final String phoneTestToString = "(85)92222-2222";

		this.contact = new ContactRespose(idTestToString, nameTestToString, ageTestToString, emailTestToString,
				phoneTestToString, true);

		final String toStringCompare = "id: " + idTestToString + "\nNome: " + nameTestToString + "\nIdade: "
				+ ageTestToString + "\nE-mail: " + emailTestToString + "\nNúmero: " + phoneTestToString
				+ "\nFavotiro: Sim";
		assertEquals(toStringCompare, this.contact.toString());
	}
}
