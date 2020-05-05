package View;

import Controller.PrincipalController;
import Model.Company;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

//se li ha de passar tota la info de les diferents accions de totes les empreses

public class TodayStockWindow extends JFrame {

    private static String MYACTIONS = "MYACTIONS";
    private static String LOGOUT = "LOGOUT";

    final int MAX_HEIGHT_SHARES = 700;
    final int MAX_WIDTH_SHARES = 400;

    private float actualUserMoney;
    private ArrayList<Company> companies = new ArrayList<Company>();

    JPanel panelLines = new JPanel();
    JPanel panelShares = new JPanel();
    JPanel panelCentral = new JPanel();
    JPanel panelSaldo = new JPanel();

    private JLabel labelSaldoActual = new JLabel("SALDO ACTUAL: ");
    private JLabel labelTotalSaldo = new JLabel(" ");

    private JButton buttonMevaBorsa = new JButton("LA MEVA BORSA");
    private JButton buttonLogOut = new JButton("LOG OUT");
    //private JButton buttonBack = new JButton("BACK");

    public TodayStockWindow () {

        configureView();

        //panelSaldo
        panelSaldo.setLayout(new BoxLayout(panelSaldo, BoxLayout.X_AXIS));
        panelSaldo.setBackground(Color.BLACK);

        Font font = labelTotalSaldo.getFont();
        labelTotalSaldo.setFont(font.deriveFont(Font.BOLD, 16));
        labelTotalSaldo.setForeground (Color.WHITE);
        labelTotalSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Font font = labelSaldoActual.getFont();
        labelSaldoActual.setFont(font.deriveFont(Font.BOLD, 12));
        labelSaldoActual.setForeground (Color.GRAY);
        labelSaldoActual.setAlignmentX(Component.CENTER_ALIGNMENT);

        setSaldoActualUser(actualUserMoney);
        updateTodayStock();
    }

    public void setSaldoActualUser (float money) {
        actualUserMoney = money;
        labelTotalSaldo.setText(actualUserMoney + " €");
        panelSaldo.add(labelSaldoActual);
        panelSaldo.add(labelTotalSaldo);
    }

    public void setCompanies (ArrayList<Company> companies) {
        this.companies = companies;
    }

    private void configureView () {

        setSize(900,600);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void updateTodayStock(){
        panelShares.setBackground(Color.BLACK);
        panelLines.setLayout(new BoxLayout(panelLines, BoxLayout.Y_AXIS));

        for (int i = 0; i < companies.size(); i++) {
            TodayStockLine line = new TodayStockLine(companies.get(i));
            panelLines.add(line);
        }

        JScrollPane scrollPane = new JScrollPane(panelLines);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setBackground(Color.BLACK);
        scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));
        scrollPane.setMaximumSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));

        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),"TODAY SHARES");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        scrollPane.setBorder(title);

        panelShares.add(scrollPane);

        //panelCentral
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));
        panelCentral.setBackground(Color.BLACK);

        panelCentral.add(panelShares);
        panelCentral.add(buttonMevaBorsa);
        panelCentral.add(buttonLogOut);
        panelCentral.add(panelSaldo);

        //"COLLAGE"
        this.add(panelCentral);
    }

    public void registraControlador(PrincipalController principalController) {
        buttonLogOut.setActionCommand(LOGOUT);
        buttonLogOut.addActionListener(principalController);
        buttonMevaBorsa.setActionCommand(MYACTIONS);
        buttonMevaBorsa.addActionListener(principalController);
    }
}