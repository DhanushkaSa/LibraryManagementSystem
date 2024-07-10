package entity;

public class MemberEntity {
    private String Member_Id;
    private String FirstName;
    private String LastName;
    private String Address;
    private Integer Age;
    private String Telephone;

    public MemberEntity() {
        
    }

    public MemberEntity(String member_Id, String firstName, String lastName, String address, Integer age,
            String telephone) {
        Member_Id = member_Id;
        FirstName = firstName;
        LastName = lastName;
        Address = address;
        Age = age;
        Telephone = telephone;
    }

    public String getMember_Id() {
        return Member_Id;
    }

    public void setMember_Id(String member_Id) {
        Member_Id = member_Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    @Override
    public String toString() {
        return "MemberEntity [Member_Id=" + Member_Id + ", FirstName=" + FirstName + ", LastName=" + LastName
                + ", Address=" + Address + ", Age=" + Age + ", Telephone=" + Telephone + "]";
    }

    
}
