package model;

public abstract class Person {

    private String typeId;
    private String idNum;
    private String name;
    private String phoneNum;
    private TypePhone typePhone;

    /**
     * @param typeId
     * @param idNum
     * @param name
     * @param phoneNum
     * @param typePhone
     */
    public Person(String typeId, String idNum, String name, String phoneNum, int optionPhone) {
        this.typeId = typeId;
        this.idNum = idNum;
        this.name = name;
        this.phoneNum = phoneNum;
        this.typePhone = TypePhone.values()[optionPhone];
    }

    /**
     * @return String return the typeId
     */
    public String getTypeId() {
        return typeId;
    }

    /**
     * @param typeId the typeId to set
     */
    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    /**
     * @return String return the idNum
     */
    public String getIdNum() {
        return idNum;
    }

    /**
     * @param idNum the idNum to set
     */
    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    /**
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the phoneNum
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     * @param phoneNum the phoneNum to set
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * @return TypePhone return the typePhone
     */
    public TypePhone getTypePhone() {
        return typePhone;
    }

    /**
     * @param typePhone the typePhone to set
     */
    public void setTypePhone(TypePhone typePhone) {
        this.typePhone = typePhone;
    }

}
