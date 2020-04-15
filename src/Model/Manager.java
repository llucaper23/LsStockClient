package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Manager {

    private final String regex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";

    public Manager() {
    }

    public boolean comprovaPassword(String passwrod){
        Pattern p = Pattern.compile(regex, Pattern.MULTILINE);
        Matcher m = p.matcher(passwrod);
        return m.matches();
    }

    public boolean comprovaUser(User user){
        boolean ok = false;


        return ok;
    }


}
