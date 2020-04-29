package View;

import Controller.BuySellSharesController;
import Controller.PrincipalController;

import javax.swing.*;
import java.awt.*;

//haurà de rebre el nom de la companya, el preu de les accions i el saldo actual
//On hi ha la taula hi va el gràfic d'espelmes

public class CompanyStocksWindow extends JFrame {

    private static String LOGOUT = "LOGOUT";
    private static String BACK = "BACKCOMPANY";
    private static String COMPRARACCIONS = "COMPRARACCIONS";
    private static String VENDREACCIONS = "VENDREACCIONS";


    private JLabel labelCompanyName = new JLabel(" ");
    private JLabel labelSaldoActual = new JLabel("SALDO ACTUAL: ");
    private JLabel labelTotalSaldo = new JLabel(" ");
    private JLabel labelSharePrice = new JLabel(" ");

    private JButton buttonComprarAccions = new JButton("COMPRAR ACCIONS");
    private JButton buttonVendreAccions = new JButton("VENDRE ACCIONS");
    private JButton buttonLogOut = new JButton("LOG OUT");
    private JButton buttonBack = new JButton("BACK");

    private JTextField textComprarAccions = new JTextField(10);
    private JTextField textVendreAccions = new JTextField(10);

    JTable table = new JTable(4, 4);

    public CompanyStocksWindow () {

        configureView();

        JPanel panelBackground = new JPanel(new BorderLayout());


        //CAPÇELERA
        labelCompanyName.setText("COMPANY NAME");

        panelBackground.add(labelCompanyName, BorderLayout.NORTH);

        //panelShares -> panel dreta -> compra/venta d'accions

        textComprarAccions.setMaximumSize( textComprarAccions.getPreferredSize() );
        textVendreAccions.setMaximumSize( textVendreAccions.getPreferredSize() );
        buttonComprarAccions.setMaximumSize(buttonComprarAccions.getPreferredSize());
        buttonVendreAccions.setMaximumSize(buttonComprarAccions.getPreferredSize());

        labelTotalSaldo.setText(saldoActualUser() + "€");
        labelSharePrice.setText("321.98"+ " €");

        JPanel panelShares = new JPanel();
        panelShares.setLayout(new BoxLayout(panelShares, BoxLayout.Y_AXIS));


        panelShares.add(labelSharePrice);

        JPanel panelCA = new JPanel();
        panelCA.setLayout(new BoxLayout(panelCA, BoxLayout.X_AXIS));

        panelCA.add(textComprarAccions);
        panelCA.add(buttonComprarAccions);

        JPanel panelVA = new JPanel();
        panelVA.setLayout(new BoxLayout(panelVA, BoxLayout.X_AXIS));

        panelVA.add(textVendreAccions);
        panelVA.add(buttonVendreAccions);

        panelShares.add(panelCA);
        panelShares.add(panelVA);

        JPanel panelSA = new JPanel();
        panelSA.setLayout(new BoxLayout(panelSA, BoxLayout.X_AXIS));

        panelSA.add(labelSaldoActual);
        panelSA.add(labelTotalSaldo);

        panelShares.add(labelSharePrice);
        panelShares.add(Box.createRigidArea(new Dimension(10,40)));
        panelShares.add(panelCA);
        panelShares.add(Box.createRigidArea(new Dimension(10,10)));
        panelShares.add(panelVA);
        panelShares.add(Box.createRigidArea(new Dimension(10,30)));

        panelShares.add(panelSA);
        panelShares.add(buttonLogOut);
        panelShares.add(buttonBack);


        //panelGraficEspelmes - ara mateix hi ha una taula que s'haura de canviar pel gràfic

        JPanel panelGraficEspelmes = new JPanel();
        panelGraficEspelmes.setLayout(new BoxLayout(panelGraficEspelmes, BoxLayout.X_AXIS));
        panelGraficEspelmes.setBackground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(Color.WHITE);

        panelGraficEspelmes.add(scrollPane);


        //panelInfo

        JPanel panelInfo = new JPanel( new FlowLayout());

        panelInfo.setBackground(Color.BLACK);

        panelInfo.add(panelGraficEspelmes);
        panelInfo.add(panelShares);

        //AFEGIM A BACKGROUND

        panelBackground.add(panelInfo, BorderLayout.CENTER);


        //"COLLAGE"

        this.add(panelBackground);

    }

    public int getNumAccionsComprar() {
        return Integer.valueOf(textComprarAccions.getText());
    }

    public int getNumAccionsVendre() {
        return Integer.valueOf(textVendreAccions.getText());
    }

    public String saldoActualUser (/*saldo que te lusuari*/) { //es un setter
       return String.valueOf("1234.5" /*el que ens passi la funcio*/);
    }

    private void configureView () {

        setSize(900,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void registraControlador(PrincipalController principalController){

        buttonLogOut.setActionCommand(LOGOUT);
        buttonLogOut.addActionListener(principalController);
        buttonBack.setActionCommand(BACK);
        buttonBack.addActionListener(principalController);

        /*buttonComprarAccions.setActionCommand(COMPRARACCIONS);
        buttonComprarAccions.addActionListener(BuySellSharesController);
        buttonVendreAccions.setActionCommand(VENDREACCIONS);
        buttonVendreAccions.addActionListener(BuySellSharesController);*/

    }
}