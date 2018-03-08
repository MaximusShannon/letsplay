package functionality;

public class Validator {

    private boolean validated;

    /*
    Check for empty String entries on loginValidation
    * */
    private boolean validateLogin(String textOne, String textTwo) {

        if(textOne.equals("")){
            validated = false;
        }
        if(textTwo.equals("")){
            validated = false;
        }else {
            validated = true;
        }

        return validated;
    }
}
