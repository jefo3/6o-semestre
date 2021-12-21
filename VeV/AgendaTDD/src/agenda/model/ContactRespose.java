package agenda.model;

public class ContactRespose {

	private int id;
	private String name;
	private int age;
	private String email;
	private String phone;
	private boolean isFavorite;

	public ContactRespose() {
		super();
	}

	public ContactRespose(int id, String name, int age, String email, String number, boolean isFavorite) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
		this.phone = number;
		this.isFavorite = isFavorite;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "id: " + this.getId() + "\nNome: " + this.getName() + "\nIdade: " + this.getAge() + "\nE-mail: "
				+ this.getEmail() + "\nNúmero: " + this.getPhone() + "\nFavotiro: "
				+ (this.isFavorite() == true ? "Sim" : "Não");
	}

}
