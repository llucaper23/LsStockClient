package Controller;

import Model.Manager;
import Model.Network.Network;
import Model.User;
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
                if (signupView.getPassword().equals(signupView.getPasswordCheck()) && manager.comprovaPassword(signupView.getPassword())) {
                    User user = new User(signupView.getName(), signupView.getMail(), signupView.getPassword(), 0, false);
                    //ok = network.registraUsuari(user); PROC PER REGISTRAR USUARI AMB EL SOCKET
                    if (ok) {
                        signupView.dispose();
                        loginView.setVisible(true);
                    } else {
                        signupView.mostraMissatgeError("Error with the info introduced");
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
}
