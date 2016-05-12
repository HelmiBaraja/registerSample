package test.sample.com.myapplication;

/**
 * Created by HelmiHasan on 11/05/2016.
 */
public class UserInfo {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHandphoneNo() {
        return handphoneNo;
    }

    public void setHandphoneNo(String handphoneNo) {
        this.handphoneNo = handphoneNo;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    private String name;

    public UserInfo(String name, String emailAddress, String password, String handphoneNo, String dateOfBirth, String gender) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
        this.handphoneNo = handphoneNo;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", password='" + password + '\'' +
                ", handphoneNo='" + handphoneNo + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    private String emailAddress;
    private String password;
    private String handphoneNo;
    private String dateOfBirth;
    private String gender;
}
