package in.harsh.otpcontactsapp;

/**
 * Created by harsh singh on 12-07-2017.
 */

public class Contacts {

    private String firstname , lastname , mobile ;



    public Contacts(String firstname , String lastname ,String mobile){

        this.setFirstname(firstname);

        this.setLastname(lastname);

        this.setMobile(mobile);









    }




    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }




}
