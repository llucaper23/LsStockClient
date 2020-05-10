package View;

import Controller.PrincipalController;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

//haurà de rebre el nom de la companya, el preu de les accions i el saldo actual
//On hi ha la taula hi va el gràfic d'espelmes

public class CompanyStocksWindow extends JFrame {

    final int MAX_HEIGHT_SHARES = 700;
    final int MAX_WIDTH_SHARES = 400;

    private static String LOGOUT = "LOGOUT";
    private static String BACK = "BACKCOMPANY";
    private static String COMPRARACCIONS = "COMPRARACCIONS";
    private static String VENDREACCIONS = "VENDREACCIONS";

    JPanel panelBackground = new JPanel(new BorderLayout());
    JPanel panelShares = new JPanel();
    JPanel panelCA = new JPanel();
    JPanel panelVA = new JPanel();
    JPanel panelSA = new JPanel();
    JPanel panelGraficEspelmes = new JPanel();
    JPanel panelInfo = new JPanel(new FlowLayout());

    Font font;

    private JLabel labelCompanyName = new JLabel(" ");
    private JLabel labelSaldoActual = new JLabel("Saldo actual : ");
    private JLabel labelTotalSaldo = new JLabel(" ");
    private JLabel labelSharePrice = new JLabel(" ");

    private JButton buttonComprarAccions = new JButton("COMPRAR ACCIONS");
    private JButton buttonVendreAccions = new JButton("VENDRE ACCIONS");
    private JButton buttonLogOut = new JButton("LOG OUT");
    private JButton buttonBack = new JButton("BACK");

    private JTextField textComprarAccions = new JTextField(10);
    private JTextField textVendreAccions = new JTextField(10);

    public CompanyStocksWindow () {

        configureView();

        //CAPÇELERA

        panelBackground.add(labelCompanyName, BorderLayout.NORTH);

        //panelShares -> panel dreta -> compra/venta d'accions

        textComprarAccions.setMaximumSize( textComprarAccions.getPreferredSize() );
        textVendreAccions.setMaximumSize( textVendreAccions.getPreferredSize() );
        buttonComprarAccions.setMaximumSize(buttonComprarAccions.getPreferredSize());
        buttonVendreAccions.setMaximumSize(buttonComprarAccions.getPreferredSize());


        panelShares.setLayout(new BoxLayout(panelShares, BoxLayout.Y_AXIS));

        font = labelSaldoActual.getFont();
        labelTotalSaldo.setFont(font.deriveFont(Font.BOLD, 15));
        labelSaldoActual.setFont(font.deriveFont(Font.BOLD, 12));

        panelShares.add(labelSharePrice);

        panelCA.setLayout(new BoxLayout(panelCA, BoxLayout.X_AXIS));
        panelCA.add(textComprarAccions);
        panelCA.add(buttonComprarAccions);

        panelVA.setLayout(new BoxLayout(panelVA, BoxLayout.X_AXIS));
        panelVA.add(textVendreAccions);
        panelVA.add(buttonVendreAccions);

        panelShares.add(panelCA);
        panelShares.add(panelVA);

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


        //panelGraficEspelmes


        //scrollPanel
        JScrollPane scrollPane = new JScrollPane(panelGraficEspelmes);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));
        scrollPane.setMaximumSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));

        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "COMPANY NAME");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        scrollPane.setBorder(title);

        //panelInfo

        panelInfo.add(scrollPane);
        panelInfo.add(panelShares);

        //AFEGIM A BACKGROUND

        panelBackground.add(panelInfo, BorderLayout.CENTER);


        //"COLLAGE"
        this.add(panelBackground);

    }

    private void configureView () {

        setSize(1100,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public int getNumAccionsComprar() {
        return Integer.parseInt(textComprarAccions.getText());
    }

    public int getNumAccionsVendre() {
        if (textVendreAccions.getText().equalsIgnoreCase("")){
            return 0;
        }else{
            return Integer.parseInt(textVendreAccions.getText());
        }
    }

    public void setSaldoActualUser (float money) {
        labelTotalSaldo.setText(money + " €");
    }

    public void setCompanyName (String companyName) {
        labelCompanyName.setText(companyName);
    }

    public void updateCompany(float money, float priceShare) {
        labelTotalSaldo.setText(money + " €");
        labelSharePrice.setText(priceShare + "€");

    }

    public void registraControlador(PrincipalController principalController) {

        buttonLogOut.setActionCommand(LOGOUT);
        buttonLogOut.addActionListener(principalController);
        buttonBack.setActionCommand(BACK);
        buttonBack.addActionListener(principalController);
        buttonComprarAccions.setActionCommand(COMPRARACCIONS);
        buttonComprarAccions.addActionListener(principalController);
        buttonVendreAccions.setActionCommand(VENDREACCIONS);
        buttonVendreAccions.addActionListener(principalController);
    }

    public void mostraMissatgeError(String error){
        JOptionPane.showMessageDialog(null, error);
    }
}