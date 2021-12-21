package agenda.model;

public class ContactRequest {

	private String name;
	private int age;
	private String email;
	private String phone;
	private boolean isFavorite;

	public ContactRequest() {
		super();
	}

	public ContactRequest(String name, int age, String email, String number) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = number;
		this.isFavorite = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	@Override
	public String toString() {
		return "Nome: " + this.getName() + "\nIdade: " + this.getAge() + "\nE-mail: " + this.getEmail() + "\nNúmero: "
				+ this.getPhone() + "\nFavotiro: " + (this.isFavorite() == true ? "Sim" : "Não");
	}

}
