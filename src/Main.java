import Controller.LoginController;
import Model.Manager;
import Model.Network.Network;
import View.CompanyStocksWindow;
import View.LoginWindow;
import View.SignUpWindow;

public class Main {
    public static void main(String[] args) {
        SignUpWindow signUpWindow = new SignUpWindow();
        LoginWindow loginView = new LoginWindow();
        CompanyStocksWindow companyStocksView = new CompanyStocksWindow();
        Manager manager = new Manager();
        Network network = new Network();
        LoginController loginController = new LoginController(signUpWindow, loginView, companyStocksView, manager, network);

        signUpWindow.registrarControlador(loginController);
        loginView.registrarControlador(loginController);
        loginView.setVisible(true);

    }
}
