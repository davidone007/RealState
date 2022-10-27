package model;

public class Owner extends Person {

    private String numAccount;
    private String nameBank;

    public Owner(String typeId, String idNum, String name, String phoneNum, int optionPhone, String nameBank,
            String numAccount) {
        super(typeId, idNum, name, phoneNum, optionPhone);
        this.nameBank = nameBank;
        this.numAccount = numAccount;
        this.nameBank = nameBank;
        this.numAccount = numAccount;

    }

    /**
     * @return String return the numAccount
     */
    public String getNumAccount() {
        return numAccount;
    }

    /**
     * @param numAccount the numAccount to set
     */
    public void setNumAccount(String numAccount) {
        this.numAccount = numAccount;
    }

    /**
     * @return String return the nameBank
     */
    public String getNameBank() {
        return nameBank;
    }

    /**
     * @param nameBank the nameBank to set
     */
    public void setNameBank(String nameBank) {
        this.nameBank = nameBank;
    }

}