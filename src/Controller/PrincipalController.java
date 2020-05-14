package Controller;


import Model.Company;
import Model.Manager;
import Model.Network.Network;
import Model.User;
import Model.UserCompany;
import View.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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

                usersMoney = manager.getActualUser().getMoney();
                myStocksWindow.setSaldoActualUser(usersMoney);
                todayStockWindow.setSaldoActualUser(usersMoney);
                companyStocksWindow.setSaldoActualUser(usersMoney);

                myStocksWindow.setUserName(manager.getActualUser());
                myStocksWindow.updateMyStocks(manager.getUserCompanies(), manager.getCompanies(), this);

                myStocksWindow.setVisible(true);

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

                if ((manager.getActualCompany().getSharePrice()) * (companyStocksWindow.getNumAccionsComprar()) < (manager.getActualUser().getMoney())) {
                    UserCompany userCompany = new UserCompany(manager.getActualUser().getUserId(), manager.getActualCompany().getCompanyId(),companyStocksWindow.getNumAccionsComprar(), manager.getActualCompany().getSharePrice());
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
                int totalAccions = 0;
                ArrayList<UserCompany> userCompanies = new ArrayList<>();
                for (int i = 0; i < manager.getUserCompanies().size(); i++) {
                    if (manager.getUserCompanies().get(i).getCompanyId() == manager.getActualCompany().getCompanyId()){
                        totalAccions += manager.getUserCompanies().get(i).getQuantity();
                        userCompanies.add(manager.getUserCompanies().get(i));
                    }
                }
                if (companyStocksWindow.getNumAccionsVendre() < totalAccions) {
                    network.sellSomeShares(manager.getActualCompany(), totalAccions);
                    float updatedMoney = manager.getActualUser().getMoney() + (companyStocksWindow.getNumAccionsVendre() * manager.getActualCompany().getSharePrice());
                    manager.getActualUser().setMoney(updatedMoney);
                    network.setUpdateMoney(manager.getActualUser());
                    manager.updateUserCompanies(network.getUserCompanies());
                } else {
                    System.out.println("No shares");
                    companyStocksWindow.mostraMissatgeError("Error. Estàs intentant vendre més accions de les que disposes");
                }

                break;

            case "SELL":
                int ucId = Integer.parseInt (((JButton)e.getSource()).getClientProperty("usercompany_id").toString());
                UserCompany aux = manager.findUserCompanyById(ucId);
                manager.setActualUserCompany(aux);
                network.sellShares(aux);

                float updatedMoney = manager.getActualUser().getMoney() + (manager.getActualUserCompany().getQuantity() * manager.getCompanieFromId(aux.getCompanyId()).getSharePrice());
                manager.getActualUser().setMoney(updatedMoney);
                network.setUpdateMoney(manager.getActualUser());
                manager.updateUserCompanies(network.getUserCompanies());
                myStocksWindow.setSaldoActualUser(updatedMoney);
                myStocksWindow.updateMyStocks(manager.getUserCompanies(), manager.getCompanies(), this);
                break;

            case "AFEGIRSALDO":
                float saldo = myStocksWindow.getSaldoAfegir();
                User user = manager.getActualUser();
                user.setMoney(user.getMoney() + saldo);
                boolean ok = network.setUpdateMoney(user);
                if(ok){
                    manager.setActualUser(user);
                    todayStockWindow.setSaldoActualUser(manager.getActualUser().getMoney());
                    myStocksWindow.setSaldoActualUser(manager.getActualUser().getMoney());
                }
                break;

            case "SEE":
                int auxId = Integer.parseInt (((JButton)e.getSource()).getClientProperty("company_id").toString());
                Company company = manager.getCompanieFromId(auxId);
                manager.setActualCompany(company);
                companyStocksWindow.setCompanyName(company.getCompanyName());
                companyStocksWindow.updateCompany(manager.getActualUser().getMoney(), company.getSharePrice());
                todayStockWindow.dispose();
                companyStocksWindow.setVisible(true);
        }
    }


    public Manager getManager() {
        return manager;
    }

    public void updateCompanies(ArrayList<Company> companiesList, ArrayList<UserCompany> userCompanies) {
        manager.updateCompanies(companiesList);
        manager.updateUserCompanies(userCompanies);
        if (companyStocksWindow.isVisible()) {
            manager.updateActualComapny();
            Company company = manager.getActualCompany();
            companyStocksWindow.updateCompany(manager.getActualUser().getMoney(), company.getSharePrice());
        }
        if (myStocksWindow.isVisible()) {
            manager.updateActualUserCompany();
            myStocksWindow.updateMyStocks(manager.getUserCompanies(), manager.getCompanies(), this);
        }
        todayStockWindow.updateTodayStock(companiesList, this);
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }
}
