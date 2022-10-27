package model;

public class Apartment {

    private Owner ownerApartment;
    private Tenant tenantApartment;
    private String idApartment;
    private int numRooms;
    private int numBaths;
    private boolean hasBalcony;
    private double monthlyRent;

    /**
     * @param ownerApartment
     * @param tenantApartment
     * @param idApartment
     * @param numRooms
     * @param numBaths
     * @param hasBalcony
     * @param monthlyRent
     */
    public Apartment(String idApartment, int numRooms, int numBaths,
            int optionBalcony, double monthlyRent) {
        this.idApartment = idApartment;
        this.numRooms = numRooms;
        this.numBaths = numBaths;
        this.hasBalcony = hasBalcony(optionBalcony);
        this.monthlyRent = monthlyRent;
        ownerApartment = null;
        tenantApartment = null;
    }

    public boolean hasBalcony(int optionBalcony) {

        if (optionBalcony == 1) {
            hasBalcony = true;
        } else if (optionBalcony == 2) {
            hasBalcony = false;
        }

        return hasBalcony;

    }

    /**
     * @return Owner return the ownerApartment
     */
    public Owner getOwnerApartment() {
        return ownerApartment;
    }

    /**
     * @param ownerApartment the ownerApartment to set
     */
    public void setOwnerApartment(Owner ownerApartment) {
        this.ownerApartment = ownerApartment;
    }

    /**
     * @return Tenant return the tenantApartment
     */
    public Tenant getTenantApartment() {
        return tenantApartment;
    }

    /**
     * @param tenantApartment the tenantApartment to set
     */
    public void setTenantApartment(Tenant tenantApartment) {
        this.tenantApartment = tenantApartment;
    }

    /**
     * @return String return the idApartment
     */
    public String getIdApartment() {
        return idApartment;
    }

    /**
     * @param idApartment the idApartment to set
     */
    public void setIdApartment(String idApartment) {
        this.idApartment = idApartment;
    }

    /**
     * @return int return the numRooms
     */
    public int getNumRooms() {
        return numRooms;
    }

    /**
     * @param numRooms the numRooms to set
     */
    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    /**
     * @return int return the numBaths
     */
    public int getNumBaths() {
        return numBaths;
    }

    /**
     * @param numBaths the numBaths to set
     */
    public void setNumBaths(int numBaths) {
        this.numBaths = numBaths;
    }

    /**
     * @return boolean return the hasBalcony
     */
    public boolean isHasBalcony() {
        return hasBalcony;
    }

    /**
     * @param hasBalcony the hasBalcony to set
     */
    public void setHasBalcony(boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    /**
     * @return double return the monthlyRent
     */
    public double getMonthlyRent() {
        return monthlyRent;
    }

    /**
     * @param monthlyRent the monthlyRent to set
     */
    public void setMonthlyRent(double monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

}
