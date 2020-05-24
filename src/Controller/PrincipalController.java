package Controller;


import Model.*;
import Model.Network.Network;
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
    private LoginController loginController;

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
                network.restart();
                loginController.restartNetwork();
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
                int numAccions = companyStocksWindow.getNumAccionsComprar();
                if (numAccions > 0){
                    if ((manager.getActualCompany().getSharePrice()) * (numAccions) < (manager.getActualUser().getMoney())) {
                        UserCompany userCompany = new UserCompany(manager.getActualUser().getUserId(), manager.getActualCompany().getCompanyId(),numAccions, manager.getActualCompany().getSharePrice());
                        network.buyShares(userCompany);
                        float updatedMoney = manager.getActualUser().getMoney() - (numAccions*manager.getActualCompany().getSharePrice());
                        manager.getActualUser().setMoney(updatedMoney);
                        network.setUpdateMoney(manager.getActualUser());

                    } else {
                        System.out.println("Not enough money");
                        companyStocksWindow.mostraMissatgeError("Error. No es disposa de sufient saldo");
                    }
                }else{
                    companyStocksWindow.mostraMissatgeError("Error, s'ha de comprar un número enter positiu d'accions");
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
                int numVendre = companyStocksWindow.getNumAccionsVendre();
                if (numVendre > 0){
                    if (numVendre < totalAccions) {
                        network.sellSomeShares(manager.getActualCompany(), numVendre);
                        float updatedMoney = manager.getActualUser().getMoney() + (numVendre * manager.getActualCompany().getSharePrice());
                        manager.getActualUser().setMoney(updatedMoney);
                        network.setUpdateMoney(manager.getActualUser());
                        manager.updateUserCompanies(network.getUserCompanies());
                        companyStocksWindow.setSaldoActualUser(updatedMoney);
                    } else {
                        System.out.println("No shares");
                        companyStocksWindow.mostraMissatgeError("Error. Estàs intentant vendre més accions de les que disposes");
                    }
                }else{
                    companyStocksWindow.mostraMissatgeError("Error, s'ha de vendre un número enter positiu d'accions");
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
                if (saldo > 0){
                    User user = manager.getActualUser();
                    user.setMoney(user.getMoney() + saldo);
                    boolean ok = network.setUpdateMoney(user);
                    if(ok){
                        manager.setActualUser(user);
                        todayStockWindow.setSaldoActualUser(manager.getActualUser().getMoney());
                        myStocksWindow.setSaldoActualUser(manager.getActualUser().getMoney());
                    }
                }else{
                    myStocksWindow.mostraMissatgeError("Error al afegir saldo");
                }

                break;

            case "SEE":
                int auxId = Integer.parseInt (((JButton)e.getSource()).getClientProperty("company_id").toString());
                Company company = manager.getCompanieFromId(auxId);
                manager.setActualCompany(company);
                manager.setHistories(network.getHistory(company)); //aqui tens tot el historial fes el que vulguis
                companyStocksWindow.setName(manager.getActualCompany().getCompanyName());
                companyStocksWindow.updateCompanyStocksWindow(manager.getHistories());
                companyStocksWindow.setCompanyName(company.getCompanyName());
                companyStocksWindow.updateCompany(manager.getActualUser().getMoney(), company.getSharePrice());
                todayStockWindow.dispose();
                companyStocksWindow.setVisible(true);
        }
    }

    public Manager getManager() {
        return manager;
    }

    /**
     * Procediment que fa un update de les companyies que tenim a la vista actual
     * @param companiesList ArrayList de companyies
     * @param userCompanies ArrayList de companyies que te l'usuari
     * @param histories ArrayList de History amb l'historial
     */
    public void updateCompanies(ArrayList<Company> companiesList, ArrayList<UserCompany> userCompanies, ArrayList<History> histories) {
        manager.updateCompanies(companiesList);
        manager.updateUserCompanies(userCompanies);
        manager.setHistories(histories);
        if (companyStocksWindow.isVisible()) {
            manager.updateActualComapny();
            Company company = manager.getActualCompany();
            companyStocksWindow.updateCompany(manager.getActualUser().getMoney(), company.getSharePrice());
        }
        if (myStocksWindow.isVisible()) {
            manager.updateActualUserCompany();
            myStocksWindow.updateMyStocks(manager.getUserCompanies(), manager.getCompanies(), this);
        }
        update5minPrice();
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    /**
     * Procediment que crida el update de la vista TodayStockWindow
     */
    public void update5minPrice(){
        todayStockWindow.updateTodayStock(manager.getCompanies(), this, network.get5MinPrice());
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
