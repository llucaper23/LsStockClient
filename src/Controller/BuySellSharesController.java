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

    public BuySellSharesController (CompanyStocksWindow companyStocksWindow, Network network, User user, Company company, UserCompany userCompany) {
        this.companyStocksWindow = companyStocksWindow;
        this.user = user;
        this.network = network;
        this.company = company;
        this.userCompany = userCompany;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        float moneyUser, sharePrice;
        int sharesToBuy, sharesToSell, userSharesCompany;

        moneyUser = user.getMoney();
        sharePrice = company.getSharePrice();
        sharesToBuy = companyStocksWindow.getNumAccionsComprar();
        sharesToSell = companyStocksWindow.getNumAccionsVendre();
        userSharesCompany = userCompany.getQuantity();

        switch (e.getActionCommand()){

            case "COMPRARACCIONS":

                if (sharePrice*sharesToBuy < moneyUser) {
                    //network.buyShares();
                } else {
                    //no te prou money. Missatge d'error
                }

                break;

            case "VENDREACCIONS":

                if (sharesToSell < userSharesCompany) {
                    // pot vendre accions
                } else {
                    //no pot vendre accions perquè no en té
                }


                break;
        }
    }
}
