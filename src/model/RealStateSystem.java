package model;

public class RealStateSystem {

    public static final int SIZE_USERS = 20;
    public static final int SIZE_BUILDINGS = 5;

    private Person[] usersRealState;
    private Building[] buildingsRealState;

    public RealStateSystem() {

        usersRealState = new Person[SIZE_USERS];
        buildingsRealState = new Building[SIZE_BUILDINGS];
    }

    public String realMoneyRentOwner(String idOwner) {
        String msj = "";
        double totalRent = 0;
        int totalOwnerApartments = 0;
        int posOwner = searchPersonById(idOwner);
        if (posOwner != -1) {
            if (usersRealState[posOwner] instanceof Owner) {
                for (int i = 0; i < SIZE_BUILDINGS; i++) {
                    if (buildingsRealState[i] != null) {
                        totalRent += buildingsRealState[i].countTotalRenthMonthOwner(idOwner);
                        totalOwnerApartments = buildingsRealState[i].countApartmentsOwner(idOwner);
                    }
                }
                double totalRentLessAdmin = totalRent - (totalRent * 0.10);
                msj = "El dueño tiene en su poder " + totalOwnerApartments + "\n" +
                        "Sus arrendamientos generan " + totalRent +
                        "Por concepto de arrendamiento debe recibir (Recuerde que se le resta un 10% por administracion) "
                        + totalRentLessAdmin;

            } else {
                msj = "El id existe pero no es de un dueño";
            }
        } else {
            msj = "El id no existe";
        }

        return msj;
    }

    public String rentedApartmentsByOwner(String idOwner) {
        String msj = "";
        int counter = 0;
        int posOwner = searchPersonById(idOwner);
        if (posOwner != -1) {
            if (usersRealState[posOwner] instanceof Owner) {
                for (int i = 0; i < SIZE_BUILDINGS; i++) {
                    if (buildingsRealState[i] != null) {
                        counter += buildingsRealState[i].countRentedApartmentsByOwner(idOwner);
                        msj = "El dueño " + usersRealState[posOwner].getName() + " tiene rentado/s " + counter
                                + " apartamentos";
                    }
                }
            } else {
                msj = "El id existe pero no es de un dueño";
            }
        } else {
            msj = "El id no existe";
        }

        return msj;
    }

    public String itsRented(String idBuilding, String idApartment) {
        String msj = "No pudo hallarse el apartamento, revise los datos insertados";
        int posBuilding = searchBuildingById(idBuilding);
        if (posBuilding != -1) {
            int posApartment = buildingsRealState[posBuilding].searchApartmentById(idApartment);
            if (posApartment != -1) {
                if (buildingsRealState[posBuilding].getApartmentsBulding()[posApartment].getTenantApartment() == null) {
                    msj = "El apartamento SI esta disponible";
                } else {
                    msj = "El apartamento NO esta disponible";
                }

            }
        }

        return msj;
    }

    public String totalRenthMonth(String idBuilding) {
        String msj = "No se ha encontrado el edificio, revise los datos insertados";
        int posBuilding = searchBuildingById(idBuilding);
        if (posBuilding != -1) {
            double totalRenthMonth = buildingsRealState[posBuilding].totalRenthMonth();
            msj = "El valor total a pagar en los apartamentos alquilados es "
                    + totalRenthMonth;

        }

        return msj;

    }

    public String avaibleApartmentsBuilding(String idBuilding) {
        String msj = "No se ha encontrado el edificio, revise los datos insertados";
        int posBuilding = searchBuildingById(idBuilding);
        if (posBuilding != -1) {
            int avaibleApartments = buildingsRealState[posBuilding].avaibleApartments();
            msj = "Los apartamentos disponibles del edificio " + buildingsRealState[posBuilding].getIdBuilding()
                    + " son: " + avaibleApartments;

        }

        return msj;

    }

    public String addBuildingToRealState(String idBuilding, String direction) {
        String msj = "No se pudo agregar el edificio, no hay espacio suficiente";
        if (searchBuildingById(idBuilding) == -1) {
            Building newBuilding = new Building(idBuilding, direction);
            boolean isEmpty = false;
            for (int i = 0; i < SIZE_BUILDINGS && !isEmpty; i++) {
                if (buildingsRealState[i] == null) {
                    buildingsRealState[i] = newBuilding;
                    isEmpty = true;
                    msj = "Edificio agregado correctamente";
                }
            }
        } else {
            msj = "El id del edificio ya existe recuerda que no se puede repetir";
        }

        return msj;
    }

    public String addApartmentToBuildingRealState(String idApartment, int numRooms, int numBaths,
            int optionBalcony, double monthlyRent, String idBuilding) {
        int posBuilding = searchBuildingById(idBuilding);

        String msj = "No se ha encontrado el edificio donde quieres añadir el apartamento";
        if (posBuilding != -1) {
            int posApartment = buildingsRealState[posBuilding].searchApartmentById(idApartment);
            if (posApartment == -1) {
                Apartment newApartment = new Apartment(idApartment, numRooms, numBaths, optionBalcony, monthlyRent);
                msj = buildingsRealState[posBuilding].addApartmentWithObject(newApartment);

            } else {
                msj = "El identificador del apartamento ya existe en el edificio por favor elija otro";

            }

        }
        return msj;
    }

    public String addTenantToRealState(String typeId, String idNum, String name, String phoneNum, int optionPhone) {
        String msj = "No se pudo agregar el arrendatario, no hay espacio suficiente";

        Person newTenant = new Tenant(typeId, idNum, name, phoneNum, optionPhone);
        boolean isEmpty = false;
        for (int i = 0; i < SIZE_USERS && !isEmpty; i++) {
            if (usersRealState[i] == null) {
                usersRealState[i] = newTenant;
                isEmpty = true;
                msj = "Arrendatario agregado correctamente";
            }
        }

        return msj;
    }

    public String listTypesPhones() {
        TypePhone typePhone[] = TypePhone.values();
        String msj = "Tipo de contacto: ";
        for (int i = 0; i < typePhone.length; i++) {
            msj += "\n" + (i + 1) + ") " + typePhone[i];

        }

        return msj;
    }

    public String addOwnerToRealState(String typeId, String idNum, String name, String phoneNum, int optionPhone,
            String nameBank, String numAccount) {

        String msj = "No se pudo agregar el dueño, no hay espacio suficiente";

        Person newOwner = new Owner(typeId, idNum, name, phoneNum, optionPhone, nameBank, numAccount);
        boolean isEmpty = false;
        for (int i = 0; i < SIZE_USERS && !isEmpty; i++) {
            if (usersRealState[i] == null) {
                usersRealState[i] = newOwner;
                isEmpty = true;
                msj = "Dueño agregado correctamente";
            }
        }

        return msj;
    }

    public String selectOwnerApartment(String idApartment, String idBuilding, String idOwner) {
        String msj = "No se pudo añadir el dueño, no se ha encontrado el apartamento";
        int posOwner = searchPersonById(idOwner);
        int posBuilding = searchBuildingById(idBuilding);
        int counter = 0;

        if (posBuilding != -1 && posOwner != -1) {
            int posApartment = buildingsRealState[posBuilding].searchApartmentById(idApartment);
            if(posApartment != -1){
            if (buildingsRealState[posBuilding].getApartmentsBulding()[posApartment].getOwnerApartment() == null) {
                Person owner = usersRealState[posOwner];
                buildingsRealState[posBuilding].getApartmentsBulding()[posApartment]
                        .setOwnerApartment((Owner) owner);
                msj = "Persona registrada correctamente como propietaria del apartamento";
            } else {
                msj = "El apartamento solicitado ya posee un dueño o no fue encontrado, asegurese de ingresar bien los datos";
                for (int i = 0; i < SIZE_BUILDINGS; i++) {
                    if (buildingsRealState[i] != null) {
                        counter += buildingsRealState[i].countRentedApartmentsByOwner(idOwner);
                    }
                }
                if (counter == 0) {
                    usersRealState[posOwner] = null;
                }

            }
        }

        } else {
            msj = "No se pudo añadir el dueño debido a que los datos ingresados de la informacion del edificio y apartamento fueron invalidos, revisa los datos ingresados y vuelve a intentarlo ";
            for (int i = 0; i < SIZE_BUILDINGS; i++) {
                if (buildingsRealState[i] != null) {
                    counter += buildingsRealState[i].countRentedApartmentsByOwner(idOwner);
                }
            }
            if (counter == 0) {
                usersRealState[posOwner] = null;
            }
        }

        return msj;

    }

    public String selectTenantApartment(String idApartment, String idBuilding, String idTenant) {
        String msj = "No se pudo agregar al arrendatario a un apartamento, revise los datos insertados";
        int posTenant = searchPersonById(idTenant);
        int posBuilding = searchApartmentInBuildingIdRealState(idApartment);
        if (posBuilding != -1 && posTenant != -1) {
            int posApartment = buildingsRealState[posBuilding].searchApartmentById(idApartment);
            if (posApartment != -1) {
                if (buildingsRealState[posBuilding].getApartmentsBulding()[posApartment].getTenantApartment() == null) {
                    Person tenant = usersRealState[posTenant];
                    buildingsRealState[posBuilding].getApartmentsBulding()[posApartment]
                            .setTenantApartment((Tenant) tenant);
                    msj = "Persona registrada correctamente como arrendataria del apartamento";
                } else {
                    msj = "El apartamento solicitado ya posee un arrendatario, la persona registrada pasara a ser la nueva arrendataria del apartamento";
                    Person tenant = usersRealState[posTenant];
                    buildingsRealState[posBuilding].getApartmentsBulding()[posApartment]
                            .setTenantApartment((Tenant) tenant);
                }
            } else {
                usersRealState[posTenant] = null;
                msj = "No se pudo añadir el arrendatario debido a que los datos ingresados de la informacion del edificio y apartamento fueron invalidos, revisa los datos ingresados y vuelve a intentarlo ";
            }

        } else {
            usersRealState[posTenant] = null;
            msj = "No se pudo añadir el arrendatario debido a que los datos ingresados de la informacion del edificio y apartamento fueron invalidos, revisa los datos ingresados y vuelve a intentarlo ";

        }

        return msj;

    }

    public int searchApartmentInBuildingIdRealState(String idApartment) {
        int pos = -1;
        boolean isFound = false;
        for (int i = 0; i < SIZE_BUILDINGS && !isFound; i++) {
            if (buildingsRealState[i] != null) {
                if (buildingsRealState[i].searchApartmentById(idApartment) != -1) {
                    pos = i;
                    isFound = true;
                }
            }
        }

        return pos;
    }

    public int searchPersonById(String idNum) {
        int pos = -1;
        boolean isFound = false;
        for (int i = 0; i < SIZE_USERS && !isFound; i++) {
            if (usersRealState[i] != null) {
                if (usersRealState[i].getIdNum().equals(idNum)) {
                    pos = i;
                    isFound = true;
                }
            }
        }

        return pos;
    }

    public int searchBuildingById(String idBuilding) {
        int pos = -1;
        boolean isFound = false;
        for (int i = 0; i < SIZE_BUILDINGS && !isFound; i++) {
            if (buildingsRealState[i] != null) {
                if (buildingsRealState[i].getIdBuilding().equals(idBuilding)) {
                    pos = i;
                    isFound = true;
                }
            }
        }

        return pos;
    }

    /**
     * @return Person[] return the usersRealState
     */
    public Person[] getUsersRealState() {
        return usersRealState;
    }

    /**
     * @param usersRealState the usersRealState to set
     */
    public void setUsersRealState(Person[] usersRealState) {
        this.usersRealState = usersRealState;
    }

    /**
     * @return Building[] return the buildingsRealState
     */
    public Building[] getBuildingsRealState() {
        return buildingsRealState;
    }

    /**
     * @param buildingsRealState the buildingsRealState to set
     */
    public void setBuildingsRealState(Building[] buildingsRealState) {
        this.buildingsRealState = buildingsRealState;
    }

}
