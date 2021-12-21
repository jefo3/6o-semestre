package agenda.view;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.List;

import agenda.controller.ControllerContact;
import agenda.model.ContactRespose;

public class ViewAgenda {

	private final Scanner in;
	private ControllerContact controllerContact;
	private boolean execMenu;
	private int chooseMenu;

	public ViewAgenda() throws SQLException {
		this.controllerContact = new ControllerContact();
		this.in = new Scanner(System.in);
		this.execMenu = true;
		this.chooseMenu = 0;
	}

	public void start() throws SQLException {
		this.execApplication();
	}

	private void setExecMenu(boolean execMenu) {
		this.execMenu = execMenu;
	}

	private boolean isExecMenu() {
		return this.execMenu;
	}

	private void setChooseMenu(int chooseMenu) {
		this.chooseMenu = chooseMenu;
	}

	private int getChooseMenu() {
		return this.chooseMenu;
	}

	private void execApplication() throws SQLException {
		while (this.isExecMenu()) {
			System.out.println("1 - Adicionar um contato a agenda");
			System.out.println("2 - Favoritar um contato da agenda");
			System.out.println("3 - Atualizar um contato da agenda");
			System.out.println("4 - Listar todos os contatos da agenda");
			System.out.println("5 - Desfavoritar um contato da agenda");
			System.out.println("6 - Deletar um contato da agenda pelo ID");
			System.out.println("7 - Deletar um contato da agenda pelo E-mail");
			System.out.println("8 - Deletar um contato da agenda pelo Número");
			System.out.println("9 - Exibir contato da agenda pelo ID");
			System.out.println("10 - Exibir contato da agenda pelo E-mail");
			System.out.println("11 - Exibir contato da agenda pelo Número");
			System.out.println("12 - Listar todos os contatos favoritados da agenda");
			System.out.println("13 - Listar todos os contatos não favoritados da agenda");
			System.out.println("14 - Sair da agenda");
			System.out.println("Digite a opção do menu que deseja: ");
			this.setChooseMenu(Integer.parseInt(in.nextLine()));

			switch (this.getChooseMenu()) {
			case 1: {
				System.out.println("################### Adicionar Contato ###################");
				System.out.println("Digite o nome do contato: ");
				String name = in.nextLine();

				System.out.println("Digite a idade do contato: ");
				int age = Integer.parseInt(in.nextLine());

				System.out.println("OBS: LEMBRANDO QUE E-MAILS SÃO UNICOS!)");
				System.out.println("Digite o e-mail do contato: ");
				String email = in.nextLine();

				System.out.println("SEGUINTO O FORMATO: (XX)9XXXX-XXXX");
				System.out.println("Digite número telefone do contato: ");
				String phone = in.nextLine();

				if (this.controllerContact.addContact(name, age, email, phone)) {
					System.out.println("Contato adicionado com sucesso a agenda!");
				} else {
					System.out.println("Erro ao adicionar este contato a agenda!");
				}

			}
				break;

			case 2: {
				System.out.println("################### Favoritar Contato ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContactsNoFavorites();

				if (contactList == null) {
					System.out.println("Não há contatos para serem favoritados!");
				} else {
					for (ContactRespose contact : contactList) {
						System.out.println("ID: " + contact.getId() + "\nNome: " + contact.getName() + "\nNúmero: "
								+ contact.getPhone());
						System.out.println("");
					}

					System.out.println("Digite o ID do contato que deseja favoritar: ");
					int idFavorite = Integer.parseInt(in.nextLine());

					if (this.controllerContact.favoriteContact(idFavorite)) {
						System.out.println("Contato favoritado com sucesso!");
					} else {
						System.out.println("Erro ao favoritar o contato!");
					}
				}
			}
				break;

			case 3: {
				System.out.println("################### Atualizar Contato ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContacts();

				for (ContactRespose contact : contactList) {
					System.out.println("ID: " + contact.getId() + "\nNome: " + contact.getName() + "\nNúmero: "
							+ contact.getPhone());
					System.out.println("");
				}

				System.out.println("Digite o ID do contato que deseja atualizar: ");
				int idUpdate = Integer.parseInt(in.nextLine());

				int idContactUpdate = 0;
				String nameContactUpdate = null;
				int ageContactUpdate = 0;
				String emailContactUpdate = null;
				String phoneContactUpdate = null;
				String lastEmailContactUpdate = null;
				String lastphoneContactUpdate = null;
				boolean favoriteContactUpdate = false;

				for (ContactRespose contact : contactList) {
					if (contact.getId() == idUpdate) {
						idContactUpdate = contact.getId();
						nameContactUpdate = contact.getName();
						ageContactUpdate = contact.getAge();
						emailContactUpdate = contact.getEmail();
						lastEmailContactUpdate = contact.getEmail();
						phoneContactUpdate = contact.getPhone();
						lastphoneContactUpdate = contact.getPhone();
						favoriteContactUpdate = contact.isFavorite();
					}
				}

				boolean execMenuUpdate = true;

				while (execMenuUpdate) {
					System.out.println("################### Contato ###################");
					System.out.println("Nome: " + nameContactUpdate + "\nIdade: " + ageContactUpdate + "\ne-mail: "
							+ emailContactUpdate + "\nNúmero: " + phoneContactUpdate);
					System.out.println("");
					System.out.println("1 - Atualizar o nome do contato");
					System.out.println("2 - Atualizar a idade do contato");
					System.out.println("3 - Atualizar o e-mail do contato");
					System.out.println("4 - Atualizar o número do contato");
					System.out.println("5 - Atualizar o contato");
					int chooseMenuUpdate = Integer.parseInt(in.nextLine());

					switch (chooseMenuUpdate) {
					case 1: {
						System.out.println("O nome atual do contato é: " + nameContactUpdate);
						System.out.println("Digite o novo nome do contato: ");
						nameContactUpdate = in.nextLine();
					}
						break;

					case 2: {
						System.out.println("A idade atual do contato é: " + ageContactUpdate);
						System.out.println("Digite a nova idade do contato: ");
						ageContactUpdate = Integer.parseInt(in.nextLine());
					}
						break;

					case 3: {
						System.out.println("O e-mail atual do contato é: " + emailContactUpdate);
						System.out.println("OBS: LEMBRANDO QUE E-MAILS SÃO UNICOS!)");
						System.out.println("Digite o novo e-mail do contato: ");
						emailContactUpdate = in.nextLine();
					}
						break;

					case 4: {
						System.out.println("O número atual do contato é: " + phoneContactUpdate);
						System.out.println("SEGUINTO O FORMATO: (XX)9XXXX-XXXX");
						System.out.println("Digite o novo número do contato: ");
						phoneContactUpdate = in.nextLine();
					}
						break;

					default: {
						execMenuUpdate = false;
					}
						break;
					}

				}

				if (this.controllerContact.updateContact(idContactUpdate, nameContactUpdate, ageContactUpdate,
						emailContactUpdate, phoneContactUpdate, favoriteContactUpdate, lastEmailContactUpdate,
						lastphoneContactUpdate)) {
					System.out.println("Contato atualizado com sucesso!");
				} else {
					System.out.println("Erro ao atualizar o contato!");
				}

			}
				break;

			case 4: {
				System.out.println("################### Listar Contatos ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContacts();

				if (contactList == null) {
					System.out.println("Não há contatos para serem listados!");
				} else {
					for (ContactRespose contact : contactList) {
						System.out.println(contact);
						System.out.println("");
					}
				}

			}
				break;

			case 5: {
				System.out.println("################### Desfavoritar Contato ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContactsFavorites();

				if (contactList == null) {
					System.out.println("Não há contatos para serem desfavoritados!");
				} else {
					for (ContactRespose contact : contactList) {
						System.out.println("ID: " + contact.getId() + "\nNome: " + contact.getName() + "\nNúmero: "
								+ contact.getPhone());
						System.out.println("");
					}

					System.out.println("Digite o ID do contato que deseja desfavoritar: ");
					int idDesfavorite = Integer.parseInt(in.nextLine());

					if (this.controllerContact.removeFavoriteContact(idDesfavorite)) {
						System.out.println("Contato desfavoritado com sucesso!");
					} else {
						System.out.println("Erro ao desfavoritar o contato!");
					}
				}

			}
				break;

			case 6: {
				System.out.println("################### Deletar Contato por ID ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContacts();

				for (ContactRespose contact : contactList) {
					System.out.println("ID: " + contact.getId() + "\nNome: " + contact.getName() + "\nNúmero: "
							+ contact.getPhone());
					System.out.println("");
				}

				System.out.println("Digite o ID do contato que deseja deletar: ");
				int idDelete = Integer.parseInt(in.nextLine());

				if (this.controllerContact.deleteContactById(idDelete)) {
					System.out.println("Contato deletado com sucesso!");
				} else {
					System.out.println("Erro ao deletadar o contato!");
				}

			}
				break;

			case 7: {
				System.out.println("################### Deletar Contato por E-mail ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContacts();

				for (ContactRespose contact : contactList) {
					System.out.println("ID: " + contact.getId() + "\nNome: " + contact.getName() + "\nNúmero: "
							+ contact.getPhone() + "\nE-mail: " + contact.getEmail());
					System.out.println("");
				}

				System.out.println("Digite o e-mail do contato que deseja deletar: ");
				String emailDelete = in.nextLine();

				if (this.controllerContact.deleteContactByEmail(emailDelete)) {
					System.out.println("Contato deletado com sucesso!");
				} else {
					System.out.println("Erro ao deletadar o contato!");
				}

			}
				break;

			case 8: {
				System.out.println("################### Deletar Contato por Número ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContacts();

				for (ContactRespose contact : contactList) {
					System.out.println("ID: " + contact.getId() + "\nNome: " + contact.getName() + "\nNúmero: "
							+ contact.getPhone());
					System.out.println("");
				}

				System.out.println("Digite o número do contato que deseja deletar: ");
				String phoneDelete = in.nextLine();

				if (this.controllerContact.deleteContactByPhone(phoneDelete)) {
					System.out.println("Contato deletado com sucesso!");
				} else {
					System.out.println("Erro ao deletadar o contato!");
				}

			}
				break;

			case 9: {
				System.out.println("################### Exibir Contato pelo ID ###################");
				System.out.println("Digite o ID do contato que deseja ver as informações: ");
				int idContact = Integer.parseInt(in.nextLine());

				ContactRespose contact = this.controllerContact.showContactById(idContact);

				if (contact == null) {
					System.out.println("Contato não encontrado por esse ID!");
				} else {
					System.out.println(contact);
				}

			}
				break;

			case 10: {
				System.out.println("################### Exibir Contato pelo E-mail ###################");
				System.out.println("Digite o e-mail do contato que deseja ver as informações: ");
				String emailContact = in.nextLine();

				ContactRespose contact = this.controllerContact.showContactByEmail(emailContact);

				if (contact == null) {
					System.out.println("Contato não encontrado por esse E-mail!");
				} else {
					System.out.println(contact);
				}

			}
				break;

			case 11: {
				System.out.println("################### Exibir Contato pelo Número ###################");
				System.out.println("SEGUINTO O FORMATO: (XX)9XXXX-XXXX");
				System.out.println("Digite o número do contato que deseja ver as informações: ");
				String phoneContact = in.nextLine();

				ContactRespose contact = this.controllerContact.showContactByPhone(phoneContact);

				if (contact == null) {
					System.out.println("Contato não encontrado por esse Número!");
				} else {
					System.out.println(contact);
				}

			}
				break;

			case 12: {
				System.out.println("################### Listar Contatos Favoritados ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContactsFavorites();

				if (contactList == null) {
					System.out.println("Não há contatos favoritados!");
				} else {
					for (ContactRespose contact : contactList) {
						System.out.println(contact);
						System.out.println("");
					}
				}

			}
				break;

			case 13: {
				System.out.println("################### Listar Contatos Desfavoritados ###################");

				List<ContactRespose> contactList = this.controllerContact.listAllContactsNoFavorites();

				if (contactList == null) {
					System.out.println("Não há contatos desfavoritados!");
				} else {
					for (ContactRespose contact : contactList) {
						System.out.println(contact);
						System.out.println("");
					}
				}

			}
				break;

			default: {
				this.controllerContact.closeConnection();
				this.setExecMenu(false);
			}
				break;
			}
		}
	}

}
