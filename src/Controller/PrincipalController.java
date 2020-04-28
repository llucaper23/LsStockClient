package Controller;

import Model.Manager;
import Model.Network.Network;
import View.CompanyStocksWindow;
import View.LoginWindow;
import View.MyStocksWindow;
import View.TodayStockWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalController implements ActionListener {

    private Network network;
    private LoginWindow loginWindow;
    private CompanyStocksWindow companyStocksWindow;
    private Manager manager;
    private MyStocksWindow myStocksWindow;
    private TodayStockWindow todayStockWindow;

    public PrincipalController(Network network, LoginWindow loginView, CompanyStocksWindow companyStocksView, Manager manager, MyStocksWindow myStocksWindow, TodayStockWindow todayStockWindow) {
        this.manager = manager;
        this.network = network;
        this.loginWindow = loginView;
        this.companyStocksWindow = companyStocksView;
        this.myStocksWindow = myStocksWindow;
        this.todayStockWindow = todayStockWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "LOGOUT":
                network.logout();
                companyStocksWindow.dispose();
                myStocksWindow.dispose();
                todayStockWindow.dispose();
                loginWindow.setVisible(true);
                break;

            case "MYACTIONS":
                todayStockWindow.dispose();
                myStocksWindow.setVisible(true);
                break;

            case "COMPRARACCIONS":

                companyStocksWindow.setVisible(true);
                break;

            case "VENDREACCIONS":

                companyStocksWindow.setVisible(true);
                break;
        }
    }
}
