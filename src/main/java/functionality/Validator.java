package functionality;

public class Validator {

    private boolean validated;

    /*
    Check for empty String entries on loginValidation
    * */
    private boolean validateLogin(String textOne, String textTwo) {

        return validated = !textOne.equals("") && !textTwo.equals("");
    }

    /*
    * Check for empty String entries on RegisterValidation
    * */
    private boolean validateRegistrationText(String textOne, String textTwo, String textThree, String textFour, String textFive){

        return validated = !textOne.equals("")
                && !textTwo.equals("")
                && !textThree.equals("")
                && !textFour.equals("")
                && !textFive.equals("");
    }
    /*
    * Validate the passwords match from the two textfield's
    * */
    private boolean validatePasswordsMatch(String passwordOne, String passwordTwo){

        return validated = passwordOne.equals(passwordTwo);
    }
}
