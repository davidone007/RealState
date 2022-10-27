package ui;

import java.util.Scanner;
import model.RealStateSystem;

public class Main {

	private Scanner reader;
	private RealStateSystem realStateSystem;

	/**
	 * Main: constructor of Main class
	 */
	public Main() {
		reader = new Scanner(System.in);
		realStateSystem = new RealStateSystem();

	}

	/**
	 * @return RealStateSystem return the realStateSystem
	 */
	public RealStateSystem getRealStateSystem() {
		return realStateSystem;
	}

	/**
	 * @param realStateSystem the realStateSystem to set
	 */
	public void setRealStateSystem(RealStateSystem realStateSystem) {
		this.realStateSystem = realStateSystem;
	}

	/**
	 * @param reader the reader to set
	 */
	public void setReader(Scanner reader) {
		this.reader = reader;
	}

	/**
	 * @return Scanner return the reader
	 */
	public Scanner getReader() {
		return reader;
	}

	public static void main(String[] args) {
		Main main = new Main();
		int option = 0;

		do {
			option = main.getOptionShowMenu();
			main.executeOption(option);

		} while (option != 0);

		main.getReader().close();
	}

	/**
	 * getOptionShoeMenu: Show the menu and get the option
	 * 
	 * @return option - int: The option chosen by the user
	 */
	public int getOptionShowMenu() {
		int option = 0;
		System.out.println("\n" + "<<<<< Bienvenido al menu de la inmboliaria >>>>>");
		System.out.println(
				"1. Registrar edificio\n" +
						"2. Añadir apartamento a edificio\n" +
						"3. Añadir arrendatario\n" +
						"4. Añadir propietario\n" +
						"5. Consultar apartamentos disponibles de un edificio\n" +
						"6. Consultar cantidad total mensual a pagar en apartamentos alquilados de un edificio\n" +
						"7. Consultar si un apartamento se encuentra disponible\n" +
						"8. Consultar cuantos apartamentos tiene arrendados un dueño\n" +
						"9. Consultar el valor de arrendamiento de un dueño\n" +
						"0. Salir ");

		option = validateIntegerOption();

		return option;
	}

	/**
	 * validateIntegerOption: This method checks if a number is an integer
	 * 
	 * @return option - int: Returns the entered number if it is an integer or
	 *         returns -1 if it is not an integer
	 */
	public int validateIntegerOption() {
		int option = 0;

		if (reader.hasNextInt()) {
			option = reader.nextInt();
		} else {
			reader.nextLine();
			option = -1;
		}

		return option;
	}

	public double validateDoubleOption() {
		double option = 0;

		if (reader.hasNextDouble()) {
			option = reader.nextDouble();
		} else {
			reader.nextLine();
			option = -1;
		}

		return option;
	}

	public void executeOption(int option) {

		switch (option) {
			case 1:
				uiAddBuilding();

				break;

			case 2:
				uiAddApartment();

				break;

			case 3:
				uiAddTenant();

				break;

			case 4:
				uiAddOwner();

				break;

			case 5:
				uiAvaibleApartmentsBuilding();

				break;

			case 6:
				uiATotalRenthMonthBuilding();

				break;

			case 7:
				uiItsRented();

				break;

			case 8:
				uiRentedApartmentsByOwner();

				break;

			case 9:
				uiMonthlyRentedApartmentsLessAdmin();

				break;

			case 10:

				break;

			case 11:

				break;

			case 12:

				break;

			case 0:
				System.out.println("Gracias por jugar");
				break;

			default:
				System.out.println("Opcion invalida");
				break;
		}
	}

	public void uiAddBuilding() {
		System.out.println("Escriba el identificador de su edificio");
		String idBuilding = reader.next();
		System.out.println("Escriba la direccion de su edificio");
		String direction = reader.next();
		String msj = "El id del edificio ya existe, recuerda que no se puede repetir";
		int buildingPos = realStateSystem.searchBuildingById(idBuilding);
		if (buildingPos == -1) {
			msj = realStateSystem.addBuildingToRealState(idBuilding, direction);
		}

		System.out.println(msj);

	}

	public void uiAddApartment() {
		String msj = "";
		System.out.println("Escriba el identificador de su apartamento");
		String idApartment = reader.next();
		System.out.println("Escriba el identificador del edificio donde quiere añadir el apartamento");
		String idBuilding = reader.next();
		System.out.println("Escriba el numero de piezas de su apartamento");
		int numRooms = validateIntegerOption();
		while (numRooms == -1) {
			reader.next();
			System.out.println("Este apartado no acepta letras, ni numeros decimales. Escriba un dato valido");
			numRooms = validateIntegerOption();
		}
		System.out.println("Escriba el numero de baños de su apartamento");
		int numBaths = validateIntegerOption();
		while (numBaths == -1) {
			reader.next();
			System.out.println("Este apartado no acepta letras, ni numeros decimales. Escriba un dato valido");
			numBaths = validateIntegerOption();
		}
		System.out.println("¿Su apartamento posee balcon? Seleccione una opcion" + "\n" + "1. Si" + "\n" + "2. No");
		int optionBalcony = validateIntegerOption();
		while (optionBalcony > 2 || optionBalcony < 1 || optionBalcony == -1) {
			System.out.println("Este apartado no acepta letras, ni numeros decimales. Escriba un dato valido");
			System.out.println("Recuerde escoger una opcion valida");
			optionBalcony = validateIntegerOption();
		}

		System.out.println("Escriba el valor de la renta mensual de su apartamento");
		double monthlyRent = validateDoubleOption();
		while (monthlyRent == -1) {
			reader.next();
			System.out.println("Este apartado no acepta letras. Escriba un dato valido");
			monthlyRent = validateDoubleOption();
		}

		msj = realStateSystem.addApartmentToBuildingRealState(idApartment, numRooms, numBaths, optionBalcony,
				monthlyRent, idBuilding);
		System.out.print(msj);
	}

	public void uiAddTenant() {
		System.out.println("Escriba el tipo identificador del arrendatario");
		String typeId = reader.next();
		System.out.println("Escriba el identificador de su arrendatario");
		String idNum = reader.next();
		String msj = "";
		int posTenant = realStateSystem.searchPersonById(idNum);

		if (posTenant == -1) {
			System.out.println("Escriba el nombre del arrendatario");
			String name = reader.next();
			System.out.println("Seleccione una opcion, para el tipo de numero del arrendatario");
			System.out.println(realStateSystem.listTypesPhones());
			int optionPhone = (validateIntegerOption() - 1);
			while (optionPhone > 4 || optionPhone < 0 || optionPhone == -2) {
				System.out.println("Este apartado no acepta letras, ni numeros decimales. Escriba un dato valido");
				System.out.println("Recuerde escoger una opcion valida");
				optionPhone = (validateIntegerOption()-1);
			}
			System.out.println("Escriba el numero de contacto del arrendatario");
			String phoneNum = reader.next();

			realStateSystem.addTenantToRealState(typeId, idNum, name, phoneNum, optionPhone);

			System.out.println(msj);

			System.out.println("\n" + "Escriba el id del apartamento del que va a ser arrendatario");
			String idApartment = reader.next();
			System.out.println("Escriba el id del edificio donde se encuentra el apartamento");
			String idBuilding = reader.next();
			msj = realStateSystem.selectTenantApartment(idApartment, idBuilding, idNum);
			System.out.println(msj);

		} else {
			msj = "El identificador de esta persona ya existe, por favor elige otro";
		}

	}

	public void uiAddOwner() {
		System.out.println("Escriba el tipo identificador del dueño");
		String typeId = reader.next();
		System.out.println("Escriba el identificador de su dueño");
		String idNum = reader.next();
		String msj = "";
		int option = 1;
		int posOwner = realStateSystem.searchPersonById(idNum);

		if (posOwner == -1) {
			System.out.println("Escriba el nombre del dueño");
			String name = reader.next();
			System.out.println("Seleccione una opcion, para el tipo de numero del dueño");
			System.out.println(realStateSystem.listTypesPhones());
			int optionPhone = (validateIntegerOption() - 1);
			while (optionPhone > 4 || optionPhone < 0 || optionPhone == -2) {
				System.out.println("Este apartado no acepta letras, ni numeros decimales. Escriba un dato valido");
				System.out.println("Recuerde escoger una opcion valida");
				optionPhone = (validateIntegerOption()-1);
			}
			System.out.println("Escriba el numero de contacto del dueño");
			String phoneNum = reader.next();
			System.out.println("Escriba el nombre del banco del dueño");
			String nameBank = reader.next();
			System.out.println("Escriba el numero de cuenta del banco del dueño");
			String numAccount = reader.next();

			realStateSystem.addOwnerToRealState(typeId, idNum, name, phoneNum, optionPhone, nameBank, numAccount);

			System.out.println(msj);

			do {
				System.out.println("\n" + "Escriba el id del apartamento del que es dueño");
				String idApartment = reader.next();
				System.out.println("Escriba el id del edificio donde se encuentra el apartamento");
				String idBuilding = reader.next();
				msj = realStateSystem.selectOwnerApartment(idApartment, idBuilding, idNum);
				System.out.println(msj);
				System.out.println("El señor es dueño de otro apartamento");
				System.out.println("1. Si " + "\n" + "2. No");
				option = validateIntegerOption();
				while (option > 2 || option < 1 || option == -1) {
					System.out.println("Este apartado no acepta letras, ni numeros decimales. Escriba un dato valido");
					System.out.println("Recuerde escoger una opcion valida");
					option = validateIntegerOption();
				}

			} while (option == 1);

		} else {
			msj = "El identificador de esta persona ya existe, por favor elige otro";
		}

	}

	public void uiAvaibleApartmentsBuilding() {
		System.out.println("Escriba el identificador del edificio que desea consultar");
		String idBuilding = reader.next();
		String msj = realStateSystem.avaibleApartmentsBuilding(idBuilding);
		System.out.println(msj);
	}

	public void uiATotalRenthMonthBuilding() {
		System.out.println("Escriba el identificador del edificio que desea consultar");
		String idBuilding = reader.next();
		String msj = realStateSystem.totalRenthMonth(idBuilding);
		System.out.println(msj);
	}

	public void uiItsRented(){
		System.out.println("Escriba el identificador del edificio que desea consultar");
		String idBuilding = reader.next();
		System.out.println("Escriba el identificador del apartamento que desea consultar");
		String idApartment = reader.next();
		String msj = realStateSystem.itsRented(idBuilding, idApartment);
		System.out.println(msj);
	}

	public void uiRentedApartmentsByOwner() {
		System.out.println("Escriba el identificador de su dueño");
		String idNum = reader.next();
		String msj = realStateSystem.rentedApartmentsByOwner(idNum);
		System.out.println(msj);
	}

	public void uiMonthlyRentedApartmentsLessAdmin() {
		System.out.println("Escriba el identificador de su dueño");
		String idNum = reader.next();
		String msj = realStateSystem.realMoneyRentOwner(idNum);
		System.out.println(msj);

	}

}
