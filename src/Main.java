import Controller.LoginController;
import Model.Manager;
import Model.Network.Network;
import View.LoginWindow;

public class Main {
    public static void main(String[] args) {

        LoginWindow loginView = new LoginWindow();
        Manager manager = new Manager();
        Network network = new Network();
        LoginController loginController = new LoginController(loginView, manager, network);

        loginView.registrarControlador(loginController);
        loginView.setVisible(true);
    }
}
