package View;

import Controller.PrincipalController;
import Model.Company;

import javax.swing.*;
import java.awt.*;

public class TodayStockLine extends JPanel {
    private static String SEE = "SEE";
    final int MAX_HEIGHT_SHARES = 600;
    final int MAX_WIDTH_SHARES = 50;

    private JLabel labelCompanyName = new JLabel(" ");
    private JLabel labelSharePrice = new JLabel(" ");
    private JLabel labelProfitLoss = new JLabel(" ");
    private JLabel labelChange = new JLabel(" ");
    private JLabel labelChangePercentage = new JLabel(" ");

    private JButton buttonSee = new JButton("SEE");


    public TodayStockLine(Company company) {

        JPanel panelBackground = new JPanel(new FlowLayout());
        panelBackground.setPreferredSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));
        panelBackground.setMaximumSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));

        labelCompanyName.setText(company.getCompanyName());
        labelSharePrice.setText(company.getSharePrice() + " €");
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

        buttonSee.putClientProperty("company_id", company.getCompanyId());
        //panelBackground
        panelBackground.add(panelCompanyName);
        panelBackground.add(panelShareInfo);
        panelBackground.add(buttonSee);

        panelBackground.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //"COLLAGE"
        this.add(panelBackground);

    }

    public void registraControlador(PrincipalController principalController) {
        buttonSee.setActionCommand(SEE);
        buttonSee.addActionListener(principalController);
    }


}
