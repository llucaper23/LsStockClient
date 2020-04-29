package Controller;

import Model.Company;
import Model.Manager;
import Model.Network.Network;
import Model.User;
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

    public BuySellSharesController (CompanyStocksWindow companyStocksWindow, Network network, User user, Company company) {
        this.companyStocksWindow = companyStocksWindow;
        this.user = user;
        this.network = network;
        this.company = company;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        float moneyUser, sharePrice;
        int numAccionsComprar, numAccionsVendre;

        moneyUser = user.getMoney();
        sharePrice = company.getSharePrice();
        numAccionsComprar = companyStocksWindow.getNumAccionsComprar();
        numAccionsVendre = companyStocksWindow.getNumAccionsVendre();

        switch (e.getActionCommand()){

            case "COMPRARACCIONS":


                break;

            case "VENDREACCIONS":


                break;
        }
    }
}
