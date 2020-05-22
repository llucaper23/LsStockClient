package View;

import Controller.PrincipalController;
import Model.History;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


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

    ArrayList<String> accions = new ArrayList<>();

    public CompanyStocksWindow () {

        configureView();

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


    }

    public void updateCompanyStocksWindow(ArrayList<History> histories) {

        panelGraficEspelmes.removeAll();
        panelInfo.removeAll();

        /*
        //panelGraficEspelmes
        historyToString(histories);
        CandleGraphView candlesGraph = new CandleGraphView(accions);
        panelGraficEspelmes.add(candlesGraph);

        panelGraficEspelmes.setPreferredSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));
        panelGraficEspelmes.setMaximumSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));

        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "COMPANY NAME: " + this.labelCompanyName);
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        panelGraficEspelmes.setBorder(title);


        //scrollPanel
        JScrollPane scrollPane = new JScrollPane(panelGraficEspelmes);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));
        scrollPane.setMaximumSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));

        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(), "COMPANY NAME: " + this.labelCompanyName);
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        scrollPane.setBorder(title);
         */

        //historyToString(histories);


        HistogramPanel panel = new HistogramPanel();

        panel.setPreferredSize(new Dimension(700, 450));
        panel.setMaximumSize(new Dimension(700, 450));

        System.out.println(histories);

        for (int i = 0; i < 10; i++) {

            //panel.addHistogramColumn(String.valueOf(histories.get(i).getTime()),  Math.abs(histories.get(i).getOpen_share_price()-histories.get(i).getClose_share_price()), Color.RED, histories.get(i));
            panel.addHistogramColumn(String.valueOf(histories.get(i).getTime()),  12, Color.RED, histories.get(i));
        }

        panel.layoutHistogram();


        panelGraficEspelmes.add(panel);
        panelGraficEspelmes.setPreferredSize(new Dimension(700, 450));
        panelGraficEspelmes.setMaximumSize(new Dimension(700, 450));


        //panelInfo


       /* JPanel panelcillo = new JPanel();
        panelcillo.setBackground(Color.RED);
        panelcillo.setPreferredSize(new Dimension(700, 450));
        panelcillo.setMaximumSize(new Dimension(700, 450));

        panelInfo.add(panelcillo);*/

        //panelInfo.add(panel);
        panelInfo.add(panelGraficEspelmes);
        panelInfo.add(panelShares);

        //AFEGIM A BACKGROUND

        panelBackground.add(panelInfo, BorderLayout.CENTER);


        //"COLLAGE"
        this.add(panelBackground);
        this.revalidate();
        this.repaint();
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

    public void mostraMissatgeError(String error) {
        JOptionPane.showMessageDialog(null, error);
    }


    /*public void historyToString(ArrayList <History> historial){

        System.out.println(historial);

        for (int i = 0; i< historial.size(); i++) {

            accions.add(String.valueOf(historial.get(i).getTime()));
            accions.add(String.valueOf(historial.get(i).getMax_share_price()));
            accions.add(String.valueOf(historial.get(i).getMin_share_price()));
            accions.add(String.valueOf(historial.get(i).getOpen_share_price()));
            accions.add(String.valueOf(historial.get(i).getClose_share_price()));

        }

    }*/

}