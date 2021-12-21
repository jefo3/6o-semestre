package agenda.test.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;

import agenda.utils.Config;

@TestMethodOrder(OrderAnnotation.class)
class TestConfigUtil {

	private Config config;

	@BeforeEach
	public void setUp() throws Exception {
		this.config = new Config();
	}

	@AfterEach
	public void tearDown() throws Exception {
		this.config = null;
	}

	@Test
	@DisplayName("Testando retorno DRIVER de conexão com banco de dados")
	@Order(1)
	public void testGetDRIVER() {
		assertEquals("org.postgresql.Driver", this.config.getDRIVER());
	}

	@Test
	@DisplayName("Testando URL de retorno de conexão com banco de dados")
	@Order(2)
	public void testGetURL() {
		assertEquals("jdbc:postgresql://localhost:5432/agendatelefonica", this.config.getURL());
	}

	@Test
	@DisplayName("Testando retorno de USER de conexão com banco de dados")
	@Order(3)
	public void testGetUSER() {
		assertEquals("postgres", this.config.getUSER());
	}

	@Test
	@DisplayName("Testando retorno PASS de conexão com banco de dados")
	@Order(4)
	public void testGetPASS() {
		assertEquals("qwe123", this.config.getPASS());
	}

}
