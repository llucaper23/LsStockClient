package Controller;

import Model.Manager;
import Model.Network.Network;
import Model.User;
import View.CompanyStocksWindow;
import View.LoginWindow;
import View.SignUpWindow;
import View.TodayStockWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginController implements ActionListener {
    private Network network;

    private SignUpWindow signupView;
    private TodayStockWindow todayStockWindow;
    private LoginWindow loginView;
    private Manager manager;
    private boolean ok = true;
    private PrincipalController principalController;

    public LoginController(SignUpWindow signUpWindow, LoginWindow loginView, TodayStockWindow todayStockWindow, Manager manager, Network network, PrincipalController principalController) {
        this.signupView = signUpWindow;
        this.loginView = loginView;
        this.todayStockWindow = todayStockWindow;
        this.manager = manager;
        this.network = network;
        this.principalController = principalController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGIN":
                User aux;
                if (loginView.isMail()){
                    aux = new User("", loginView.getName(), getMD5(loginView.getPassword()), 0, false);
                }else {
                    aux = new User(loginView.getName(), "", getMD5(loginView.getPassword()), 0, false);
                }
                User user = network.loginUsuari(aux);
                if (user != null) {
                    System.out.println("entro login");
                    loginView.dispose();
                    todayStockWindow.setSaldoActualUser(user.getMoney());
                    manager.updateCompanies(network.getAllCompanies());
                    manager.updateUserCompanies(network.getUserCompanies());
                    principalController.update5minPrice();
                    todayStockWindow.setVisible(true);

                    manager.setActualUser(user);
                    principalController.setManager(manager);

                } else {
                    System.out.println("error");
                    loginView.mostraMissatgeError("Error al fer el LogIn");
                }
                break;

            case "REGISTER":
                User aux1 = new User(signupView.getName(), signupView.getMail(), signupView.getPassword(), 0, false);
                if (comprovaUser(aux1)) {
                    ok = network.registraUsuari(aux1);
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

        boolean check = true;

        if (!manager.checkEmail(user.getEmail())) {
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
            signupView.mostraMissatgeError("Password did not accomplish the requisits");
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
