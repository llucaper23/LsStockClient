package Controller;

import Model.Company;
import Model.Manager;
import Model.Network.Network;
import Model.User;
import Model.UserCompany;
import View.CompanyStocksWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuySellSharesController implements ActionListener{

    private Network network;
    private CompanyStocksWindow companyStocksWindow;
    private Company company;
    private User user;
    private UserCompany userCompany;
    private Manager manager;

    public BuySellSharesController (CompanyStocksWindow companyStocksWindow, Network network, User user, Company company, UserCompany userCompany, Manager manager) {
        this.companyStocksWindow = companyStocksWindow;
        this.user = user;
        this.network = network;
        this.company = company;
        this.userCompany = userCompany;
        this.manager = manager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {

            case "COMPRARACCIONS":

                //if (sharePrice * sharesToBuy) < moneyUser
                if ((manager.getActualCompany().getSharePrice()) * (companyStocksWindow.getNumAccionsComprar()) < (manager.getActualUser().getMoney())) {

                    network.buyShares(companyStocksWindow.getNumAccionsComprar());
                    float updatedMoney = manager.getActualUser().getMoney() - (companyStocksWindow.getNumAccionsComprar()*manager.getActualCompany().getSharePrice());
                    network.setUpdateMoney(updatedMoney);

                } else {
                    System.out.println("Not enough money");
                    companyStocksWindow.mostraMissatgeError("Error. No es disposa de sufient saldo");

                }

                break;

            case "VENDREACCIONS":

                //if (sharesToSell < userSharesActualCompany)
                if ((companyStocksWindow.getNumAccionsVendre()) < (userCompany.getQuantity())) {
                    network.sellShares(companyStocksWindow.getNumAccionsVendre());
                    float updatedMoney = manager.getActualUser().getMoney() + (companyStocksWindow.getNumAccionsVendre() * manager.getActualCompany().getSharePrice());
                    network.setUpdateMoney(updatedMoney);

                } else {
                    System.out.println("No shares");
                    companyStocksWindow.mostraMissatgeError("Error. Estàs intentant vendre més accions de les que disposes");

                }

                break;

        }
    }
}
