package view;

import javax.swing.*;
import java.awt.*;

//haurà de rebre el nom de la companya, el preu de les accions i el saldo actual
//On hi ha la taula hi va el gràfic d'espelmes

public class CompanyStocksWindow extends JFrame {


    private JLabel labelCompanyName = new JLabel(" ");
    private JLabel labelSaldoActual = new JLabel("SALDO ACTUAL: ");
    private JLabel labelTotalSaldo = new JLabel(" ");
    private JLabel labelSharePrice = new JLabel(" ");

    private JButton buttonComprarAccions = new JButton("COMPRAR ACCIONS");
    private JButton buttonVendreAccions = new JButton("VENDRE ACCIONS");

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

        labelTotalSaldo.setText("1234.56" + " €");
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

        panelShares.add(panelSA);


        //panelGraficEspelmes - ara mateix hi ha una taula que s'haura de canviar pel gràfic

        JPanel panelGraficEspelmes = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table);

        scrollPane.getViewport().setBackground(Color.WHITE);

        panelGraficEspelmes.add(scrollPane);


        //panelInfo

        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.X_AXIS));

        panelInfo.add(panelGraficEspelmes);
        panelInfo.add(panelShares);

        //AFEGIM A BACKGROUND

        panelBackground.add(panelInfo, BorderLayout.CENTER);


        //"COLLAGE"

        this.add(panelBackground);


    }

    private void configureView () {

        setSize(900,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        // set look and feel to the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CompanyStocksWindow().setVisible(true);
            }
        });
    }
}