package functionality;

public class Validator {

    private boolean validated;

    /*
    Check for empty String entries on loginValidation
    * */
    public boolean validateLogin(String textOne, String textTwo) {

        return validated = !textOne.equals("") && !textTwo.equals("");
    }
    /*
    * Check for empty String entries on RegisterValidation
    * */
    public boolean validateRegistrationText(String textOne, String textTwo, String textThree, String textFour){

        return validated = !textOne.equals("")
                && !textTwo.equals("")
                && !textThree.equals("")
                && !textFour.equals("");
    }
    /*
    * Validate the passwords match from the two textfield's
    * */
    public boolean validatePasswordsMatch(String passwordOne, String passwordTwo){

        return validated = passwordOne.equals(passwordTwo);
    }
}
