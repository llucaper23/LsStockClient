package Controller;

import Model.Manager;
import Model.Network.Network;
import Model.UserRegister;
import View.LoginWindow;
import View.SignUpWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController implements ActionListener {
    private Network network;

    private SignUpWindow signupView;
    private LoginWindow loginView;
    private Manager manager;

    private boolean ok = true;

    public LoginController(LoginWindow loginView, Manager manager) {
        this.loginView = loginView;
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGIN":
                break;
            case "REGISTER":
                UserRegister userRegister = getInfoRegister();
                System.out.print(userRegister.getName() + userRegister.getMail() + userRegister.getPassword());

                if (userRegister != null){
                    //ok = network.registraUsuari(userRegister); PROC PER REGISTRAR USUARI AMB EL SOCKET
                    if (ok) {
                        signupView.dispose();
                        loginView.setVisible(true);
                    }
                }
                break;
            case "GO_TO_REGISTER":
                loginView.dispose();
                signupView = new SignUpWindow();
                signupView.registrarControlador(this);
                signupView.setVisible(true);
                break;
        }
    }

    private UserRegister getInfoRegister() {
        UserRegister aux = new UserRegister();

        aux.setName(signupView.getName());
        aux.setMail(signupView.getMail());
        aux.setPassword(signupView.getPassword());
        aux.setPassword2(signupView.getPasswordCheck());

        if (aux.getPassword().equals(aux.getPassword2()) && manager.comprovaPassword(aux.getPassword())){
            return aux;
        }
        return null;
    }

}
