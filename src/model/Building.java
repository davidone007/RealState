package model;

public class Building {

    public static final int APARTMENTS_SIZE = 10;

    private String idBuilding;
    private String direction;
    private Apartment[] apartmentsBulding;

    /**
     * @param idBuilding
     * @param direction
     * @param numAparments
     */
    public Building(String idBuilding, String direction) {
        this.idBuilding = idBuilding;
        this.direction = direction;
        apartmentsBulding = new Apartment[APARTMENTS_SIZE];
    }

    public String addApartmentWithObject(Apartment newApartment) {

        String msj = "Capacidad maxima alcanzada, no se pueden agregar mas apartamentos";
        boolean isEmpty = false;
        for (int i = 0; i < APARTMENTS_SIZE && !isEmpty; i++) {
            if (apartmentsBulding[i] == null) {
                apartmentsBulding[i] = newApartment;
                isEmpty = true;
                msj = "Nuevo apartamento agregado";
            }
        }

        return msj;
    }

    public int searchApartmentById(String idApartment) {
        int pos = -1;
        boolean isFound = false;
        for (int i = 0; i < APARTMENTS_SIZE && !isFound; i++) {
            if (apartmentsBulding[i] != null) {
                if (apartmentsBulding[i].getIdApartment().equals(idApartment)) {
                    pos = i;
                    isFound = true;
                }
            }
        }

        return pos;
    }

    public int avaibleApartments() {
        int counter = 0;
        for (int i = 0; i < APARTMENTS_SIZE; i++) {
            if(apartmentsBulding[i] != null){
            if (apartmentsBulding[i].getTenantApartment() == null) {
                counter += 1;
            }
        }
    }
        return counter;
    }

    public double totalRenthMonth() {
        double totalRenthMonth = 0;
        for (int i = 0; i < APARTMENTS_SIZE; i++) {
            if(apartmentsBulding[i] != null){
            if (apartmentsBulding[i].getTenantApartment() != null) {
                totalRenthMonth += apartmentsBulding[i].getMonthlyRent();

            }
        }

        }

        return totalRenthMonth;
    }

    public int countRentedApartmentsByOwner(String idOwner) {
        int counter = 0;
        for (int i = 0; i < APARTMENTS_SIZE; i++) {
            if(apartmentsBulding[i] != null){
            if (apartmentsBulding[i].getTenantApartment() != null && apartmentsBulding[i].getOwnerApartment() != null) {
                if (apartmentsBulding[i].getOwnerApartment().getIdNum().equals(idOwner)) {
                    counter += 1;
                }
            }

        }
    }

        return counter;

    }

    public int countApartmentsOwner(String idOwner) {
        int counter = 0;
        for (int i = 0; i < APARTMENTS_SIZE; i++) {
            if(apartmentsBulding[i] != null){
            if (apartmentsBulding[i].getOwnerApartment() != null) {
                if (apartmentsBulding[i].getOwnerApartment().getIdNum().equals(idOwner)) {
                    counter += 1;
                }
            }
        }
    }

        return counter;

    }

    public double countTotalRenthMonthOwner(String idOwner) {
        double totalRent = 0;
        for (int i = 0; i < APARTMENTS_SIZE; i++) {
            if(apartmentsBulding[i] != null){
            if (apartmentsBulding[i].getOwnerApartment() != null) {
                if (apartmentsBulding[i].getOwnerApartment().getIdNum().equals(idOwner)) {
                    totalRent += apartmentsBulding[i].getMonthlyRent();
                }
            }
        }
    }

        return totalRent;

    }

    /**
     * @return String return the idBuilding
     */
    public String getIdBuilding() {
        return idBuilding;
    }

    /**
     * @param idBuilding the idBuilding to set
     */
    public void setIdBuilding(String idBuilding) {
        this.idBuilding = idBuilding;
    }

    /**
     * @return String return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return Apartment[] return the apartmentsBulding
     */
    public Apartment[] getApartmentsBulding() {
        return apartmentsBulding;
    }

    /**
     * @param apartmentsBulding the apartmentsBulding to set
     */
    public void setApartmentsBulding(Apartment[] apartmentsBulding) {
        this.apartmentsBulding = apartmentsBulding;
    }

}