import Controller.LoginController;
import Model.Manager;
import View.LoginWindow;

public class Main {
    public static void main(String[] args) {

        LoginWindow loginView = new LoginWindow();
        Manager manager = new Manager();
        LoginController loginController = new LoginController(loginView, manager);

        loginView.registrarControlador(loginController);
        loginView.setVisible(true);
    }
}
