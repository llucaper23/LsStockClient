package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {
    private final String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    /**
     * Funcio que comprova si el password compleix els requeriments
     * @param passwrod es el password a comprovar
     */

    public boolean checkPassword(String passwrod){
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(passwrod);
        boolean ok = m.matches();
        return ok;
    }

}
