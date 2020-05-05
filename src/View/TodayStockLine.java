package View;

import Model.Company;

import javax.swing.*;
import java.awt.*;

public class TodayStockLine extends JPanel {

    final int MAX_HEIGHT_SHARES = 700;
    final int MAX_WIDTH_SHARES = 50;

    private JLabel labelCompanyName = new JLabel(" ");
    private JLabel labelSharePrice = new JLabel(" ");
    private JLabel labelProfitLoss = new JLabel(" ");
    private JLabel labelChange = new JLabel(" ");
    private JLabel labelChangePercentage = new JLabel(" ");

    private JButton buttonSee = new JButton("SEE");


    public TodayStockLine(/*Company company*/) {

        JPanel panelBackground = new JPanel(new FlowLayout());
        panelBackground.setPreferredSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));
        panelBackground.setMaximumSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));

        //Dins dels setText hi aniran les variables del que ens passin per la funció
        //labelCompanyName.setText(company.getCompanyName());
        //labelSharePrice.setText(company.getSharePrice() + " €");
        labelCompanyName.setText("company.getCompanyName()");
        labelSharePrice.setText("getSharePrice" + " €");
        labelChange.setText(" +0.08 " + "€");
        labelChangePercentage.setText(" ( " + "+0.08" + " % )");

        //panelCompanyName
        JPanel panelCompanyName = new JPanel();

        panelCompanyName.add(labelCompanyName);

        //panelChange
        JPanel panelChange = new JPanel();
        panelChange.setLayout(new BoxLayout(panelChange, BoxLayout.X_AXIS));

        panelChange.add(labelChange);
        panelChange.add(labelChangePercentage);

        //panelShareInfo
        JPanel panelShareInfo = new JPanel();
        panelShareInfo.setLayout(new BoxLayout(panelShareInfo, BoxLayout.Y_AXIS));

        panelShareInfo.add(labelSharePrice);
        panelShareInfo.add(panelChange);

        //panelBackground
        panelBackground.add(panelCompanyName);
        panelBackground.add(panelShareInfo);
        panelBackground.add(buttonSee);

        //"COLLAGE"
        this.add(panelBackground);

    }


}
