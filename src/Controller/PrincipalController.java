package Controller;


import Model.Manager;
import Model.Network.Network;
import Model.User;
import Model.UserCompany;
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
        float usersMoney;

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

                usersMoney = manager.getActualUser().getMoney();
                myStocksWindow.setSaldoActualUser(usersMoney);
                todayStockWindow.setSaldoActualUser(usersMoney);
                companyStocksWindow.setSaldoActualUser(usersMoney);

                break;

            case "BACKMYSTOCK":
                myStocksWindow.dispose();
                todayStockWindow.setVisible(true);

                usersMoney = manager.getActualUser().getMoney();
                myStocksWindow.setSaldoActualUser(usersMoney);
                todayStockWindow.setSaldoActualUser(usersMoney);
                companyStocksWindow.setSaldoActualUser(usersMoney);

                break;

            case "BACKCOMPANY":
                companyStocksWindow.dispose();
                todayStockWindow.setVisible(true);

                usersMoney = manager.getActualUser().getMoney();
                myStocksWindow.setSaldoActualUser(usersMoney);
                todayStockWindow.setSaldoActualUser(usersMoney);
                companyStocksWindow.setSaldoActualUser(usersMoney);

                break;

            case "COMPRARACCIONS":

                //if (sharePrice * sharesToBuy) < moneyUser
                if ((manager.getActualCompany().getSharePrice()) * (companyStocksWindow.getNumAccionsComprar()) < (manager.getActualUser().getMoney())) {
                    UserCompany userCompany = new UserCompany(manager.getActualUser().getUser_id(), manager.getActualCompany().getCompanyId(),companyStocksWindow.getNumAccionsComprar(), manager.getActualCompany().getSharePrice());
                    network.buyShares(userCompany);
                    float updatedMoney = manager.getActualUser().getMoney() - (companyStocksWindow.getNumAccionsComprar()*manager.getActualCompany().getSharePrice());
                    manager.getActualUser().setMoney(updatedMoney);
                    network.setUpdateMoney(manager.getActualUser());

                } else {
                    System.out.println("Not enough money");
                    companyStocksWindow.mostraMissatgeError("Error. No es disposa de sufient saldo");

                }

                break;

            case "VENDREACCIONS":

                //if (sharesToSell < userSharesActualCompany)
                if ((companyStocksWindow.getNumAccionsVendre()) < (manager.getActualUserCompanyShares().getQuantity())) {
                    UserCompany userCompany = new UserCompany(manager.getActualUser().getUser_id(), manager.getActualCompany().getCompanyId(),companyStocksWindow.getNumAccionsVendre(), manager.getActualCompany().getSharePrice());
                    network.sellShares(userCompany);
                    float updatedMoney = manager.getActualUser().getMoney() + (companyStocksWindow.getNumAccionsVendre() * manager.getActualCompany().getSharePrice());
                    manager.getActualUser().setMoney(updatedMoney);
                    network.setUpdateMoney(manager.getActualUser());

                } else {
                    System.out.println("No shares");
                    companyStocksWindow.mostraMissatgeError("Error. Estàs intentant vendre més accions de les que disposes");

                }

                break;

            case "AFEGIRSALDO":
                float saldo = myStocksWindow.getSaldoAfegir();
                User user = manager.getActualUser();
                user.setMoney(user.getMoney() + saldo);
                boolean ok = network.setUpdateMoney(user);
                if(ok){
                    manager.setActualUser(user);
                    todayStockWindow.setSaldoActualUser(manager.getActualUser().getMoney());
                }
                break;


        }
    }


    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
