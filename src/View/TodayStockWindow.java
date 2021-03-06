package View;

import Controller.PrincipalController;
import Model.Company;
import Model.History;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;


public class TodayStockWindow extends JFrame {

    private static String MYACTIONS = "MYACTIONS";
    private static String LOGOUT = "LOGOUT";

    final int MAX_HEIGHT_SHARES = 700;
    final int MAX_WIDTH_SHARES = 400;

    JPanel panelLines = new JPanel();
    JPanel panelShares = new JPanel();
    JPanel panelCentral = new JPanel();
    JPanel panelSaldo = new JPanel();

    private JLabel labelSaldoActual = new JLabel("SALDO ACTUAL: ");
    private JLabel labelTotalSaldo = new JLabel(" ");

    private JButton buttonMevaBorsa = new JButton("LA MEVA BORSA");
    private JButton buttonLogOut = new JButton("LOG OUT");

    public TodayStockWindow () {

        configureView();

        //panelSaldo
        panelSaldo.setLayout(new BoxLayout(panelSaldo, BoxLayout.X_AXIS));

        Font font = labelTotalSaldo.getFont();
        labelTotalSaldo.setFont(font.deriveFont(Font.BOLD, 16));
        labelTotalSaldo.setForeground (Color.BLACK);
        labelTotalSaldo.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelSaldoActual.setFont(font.deriveFont(Font.BOLD, 12));
        labelSaldoActual.setForeground (Color.GRAY);
        labelSaldoActual.setAlignmentX(Component.CENTER_ALIGNMENT);

    }

    /**
     * Procediment que actualitza el saldo del usuari
     * @param actualUserMoney float amb els diners
     */
    public void setSaldoActualUser (float actualUserMoney) {
        labelTotalSaldo.setText(actualUserMoney + " €");
        panelSaldo.add(labelSaldoActual);
        panelSaldo.add(labelTotalSaldo);
    }

    /**
     * Funcio que configura la vista
     */
    private void configureView () {

        setSize(1100,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Prcediment que actualitza la vista
     * @param companies ArrayList amb les companyies
     * @param principalController Controlador de la vista
     * @param histories ArrayList amb el historic
     */
    public void updateTodayStock(ArrayList<Company> companies, PrincipalController principalController, ArrayList<History> histories){
        panelShares.removeAll();
        panelLines.removeAll();

        panelLines.setLayout(new BoxLayout(panelLines, BoxLayout.Y_AXIS));

        for (int i = 0; i < companies.size(); i++) {
            TodayStockLine line;
            if (histories != null){
                if (histories.size() >= i + 1){
                    line = new TodayStockLine(companies.get(i), histories.get(i));
                }else{
                    line = new TodayStockLine(companies.get(i), null);
                }
            }else{
                line = new TodayStockLine(companies.get(i), null);
            }


            line.registraControlador(principalController);
            panelLines.add(line);
        }

        JScrollPane scrollPane = new JScrollPane(panelLines);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

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

        panelCentral.add(panelShares);
        panelCentral.add(buttonMevaBorsa);
        panelCentral.add(buttonLogOut);
        panelCentral.add(panelSaldo);
        panelCentral.revalidate();
        panelCentral.repaint();

        //"COLLAGE"
        this.add(panelCentral);
        this.revalidate();
        this.repaint();
    }

    /**
     * Procediment que registra el controlador
     * @param principalController Controlador corresponent
     */
    public void registraControlador(PrincipalController principalController) {
        buttonLogOut.setActionCommand(LOGOUT);
        buttonLogOut.addActionListener(principalController);
        buttonMevaBorsa.setActionCommand(MYACTIONS);
        buttonMevaBorsa.addActionListener(principalController);
    }
}