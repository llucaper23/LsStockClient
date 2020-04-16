package Controller;

import Model.Manager;
import Model.Network.Network;
import Model.User;
import View.CompanyStocksWindow;
import View.LoginWindow;
import View.SignUpWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements ActionListener {
    private Network network;

    private SignUpWindow signupView;
    private CompanyStocksWindow companyStocksView;
    private LoginWindow loginView;
    private Manager manager;
    private boolean ok = true;

    public LoginController(SignUpWindow signUpWindow, LoginWindow loginView, CompanyStocksWindow companyStocksView, Manager manager, Network network) {
        this.signupView = signUpWindow;
        this.loginView = loginView;
        this.companyStocksView = companyStocksView;
        this.manager = manager;
        this.network = network;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGIN":
                User aux = new User(signupView.getName(), signupView.getMail(), getMD5(signupView.getPassword()), 0, false);
                User user = network.loginUsuari(aux);
                if (user != null) {
                    loginView.dispose();
                    //OBRIR FINESTRA PRINCIPAL
                    companyStocksView.setVisible(true);

                } else {
                    System.out.println("error");
                }
                break;
            case "REGISTER":
                User aux1 = new User(signupView.getName(), signupView.getMail(), signupView.getPassword(), 0, false);
                if (comprovaUser(aux1)) {
                    User user1 = aux1;
                    ok = network.registraUsuari(user1);
                    if (ok) {
                        signupView.dispose();
                        loginView.setVisible(true);
                    } else {
                        signupView.mostraMissatgeError("Error checking the info");
                    }
                }
                break;
            case "GO_TO_REGISTER":
                loginView.dispose();
                signupView.setVisible(true);
                break;
        }
    }

    public boolean comprovaUser(User user) {
        boolean check = false;
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        // El email a validar
        String email = user.getEmail();

        Matcher mather = pattern.matcher(email);

        if (!mather.find()) {
            signupView.mostraMissatgeError("Mail not valid");
            check = false;
        }

        if (check){
            if (signupView.getPassword().equals(signupView.getPasswordCheck())) {
                check = manager.checkPassword(signupView.getPassword());
            } else {
                signupView.mostraMissatgeError("Passwords must be equals");
                check = false;
            }
        }

        if (check){
            user.setPassword(getMD5(signupView.getPassword()));
        }else{
            signupView.mostraMissatgeError("Password not accomplish the requisits");
        }
        return check;
    }

    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
