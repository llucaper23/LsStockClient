import Controller.LoginController;
import Controller.PrincipalController;
import Model.Manager;
import Model.Network.Network;
import View.*;

public class Main {
    public static void main(String[] args) {
        SignUpWindow signUpWindow = new SignUpWindow();
        LoginWindow loginView = new LoginWindow();
        CompanyStocksWindow companyStocksView = new CompanyStocksWindow();
        MyStocksWindow myStocksWindow = new MyStocksWindow();
        TodayStockWindow todayStockWindow = new TodayStockWindow();
        Manager manager = new Manager();
        Network network = new Network();
        LoginController loginController = new LoginController(signUpWindow, loginView, todayStockWindow, manager, network);
        PrincipalController principalController = new PrincipalController(network, loginView, companyStocksView, manager, myStocksWindow, todayStockWindow);


        signUpWindow.registrarControlador(loginController);
        loginView.registrarControlador(loginController);
        companyStocksView.registraControlador(principalController);
        myStocksWindow.registraControlador(principalController);
        todayStockWindow.registraControlador(principalController);
        loginView.setVisible(true);

    }
}
