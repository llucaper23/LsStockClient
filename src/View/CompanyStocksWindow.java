package View;

import Controller.PrincipalController;
import Model.History;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CompanyStocksWindow extends JFrame {

    private String name = "";
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

        JLabel labelSaldoActual = new JLabel("Saldo actual : ");
        font = labelSaldoActual.getFont();
        labelTotalSaldo.setFont(font.deriveFont(Font.BOLD, 15));
        labelSaldoActual.setFont(font.deriveFont(Font.BOLD, 12));
        labelSharePrice.setFont(font.deriveFont(Font.BOLD, 17));

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
        panelShares.add(Box.createRigidArea(new Dimension(10,40)));
        panelShares.add(buttonBack);
        panelShares.add(buttonLogOut);

    }

    /**
     * Procediment que actualitza
     * @param histories ArrayList amb el historic d'una companyia.
     */
    public void updateCompanyStocksWindow(ArrayList<History> histories) {

        panelGraficEspelmes.removeAll();
        panelInfo.removeAll();

        historyToString(histories);
        float maxA = 0, minA = 10000;

        for (int i = 1; i < accions.size(); i++) {
            if (!(i%5 == 0)){
                if(Float.parseFloat(accions.get(i)) > maxA) {
                    maxA = Float.parseFloat(accions.get(i));
                }
                if(Float.parseFloat(accions.get(i)) < minA) {
                    minA = Float.parseFloat(accions.get(i));
                }
            }
        }

        HistogramPanel panel = new HistogramPanel(name);
        panel.setPreferredSize(new Dimension(700, 450));
        panel.setMaximumSize(new Dimension(700, 450));
        for (int i = 0; i < 10; i++) {
            if (histories != null){
                if (histories.size() >= i + 1){
                    if (histories.get(i).getOpenSharePrice() > histories.get(i).getCloseSharePrice()) {
                        panel.addHistogramColumn(histories.get(i), minA, maxA);
                    } else {
                        panel.addHistogramColumn(histories.get(i), minA, maxA);
                    }
                }else{
                    panel.addHistogramColumn(null, minA, maxA);
                }
            }else{
                panel.addHistogramColumn(null, minA, maxA);
            }
        }

        panel.layoutHistogram();
        panelGraficEspelmes.add(panel);
        panelGraficEspelmes.setPreferredSize(new Dimension(700, 450));
        panelGraficEspelmes.setMaximumSize(new Dimension(700, 450));
        panelInfo.add(panelGraficEspelmes);
        panelInfo.add(panelShares);
        //AFEGIM A BACKGROUND

        panelBackground.add(panelInfo, BorderLayout.CENTER);


        //"COLLAGE"
        this.add(panelBackground);
        this.revalidate();
        this.repaint();
    }

    /**
     * Procediment que configura la vista.
     */
    private void configureView () {

        setSize(1100,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Funcio que agafa les accions a comprar.
     * @return int amb les accions.
     */
    public int getNumAccionsComprar() {
        try{
            int numBuy = Integer.parseInt(textComprarAccions.getText());
            textComprarAccions.setText("");
            return numBuy;
        }catch (NumberFormatException e){
            textComprarAccions.setText("");
            return 0;
        }
    }

    /**
     * Funcio que agafa les accions a vendre
     * @return int amb les accions a vendre
     */
    public int getNumAccionsVendre() {
        if (textVendreAccions.getText().equalsIgnoreCase("")){
            textVendreAccions.setText("");
            return 0;
        }else{
            try{
                int numVendre = Integer.parseInt(textVendreAccions.getText());
                textVendreAccions.setText("");
                return numVendre;
            }catch (NumberFormatException e){
                textVendreAccions.setText("");
                return 0;
            }
        }
    }

    /**
     * Procediment que acutalitza el saldo de l'usuari per pantalla.
     * @param money float amb la quantitat de diners.
     */
    public void setSaldoActualUser (float money) {
        labelTotalSaldo.setText(money + " €");
    }

    /**
     * Procediment que posa el nom de la companyia
     * @param companyName String amb el nom de la companyia
     */
    public void setCompanyName (String companyName) {
        labelCompanyName.setText(companyName);
    }

    /**
     * Procediment que actualitza una companyia.
     * @param money float amb els diners
     * @param priceShare float amb el preu per accio
     */
    public void updateCompany(float money, float priceShare) {
        labelTotalSaldo.setText(money + " €");
        labelSharePrice.setText(priceShare + "€");

    }

    /**
     * Procediment que registra el controlador
     * @param principalController Controlador corresponent
     */
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

    /**
     * Procediment que mostra el missatge d'error
     * @param error String amb el tipus d'error
     */
    public void mostraMissatgeError(String error) {
        JOptionPane.showMessageDialog(null, error);
    }

    /**
     * Procediment que passa a String el historic
     * @param historial ArraList amb els historics d'una companyia
     */
    public void historyToString(ArrayList <History> historial){

        accions.clear();
        for (int i = 0; i< historial.size(); i++) {
            accions.add(String.valueOf(historial.get(i).getTime()));
            accions.add(String.valueOf(historial.get(i).getMaxSharePrice()));
            accions.add(String.valueOf(historial.get(i).getMinSharePrice()));
            accions.add(String.valueOf(historial.get(i).getOpenSharePrice()));
            accions.add(String.valueOf(historial.get(i).getCloseSharePrice()));
        }

    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}