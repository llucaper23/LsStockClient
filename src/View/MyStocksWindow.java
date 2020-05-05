package View;

import Controller.PrincipalController;

import javax.swing.*;
import java.awt.*;

//haurà de rebre el nom del usuari, el saldo actual i les accions que té de totes les empreses
public class MyStocksWindow extends JFrame {

    private static String LOGOUT = "LOGOUT";
    private static String BACK = "BACKMYSTOCK";
    private static String AFEGIRSALDO = "AFEGIRSALDO";

    private float actualUserMoney;

    private JLabel labelTitleMB = new JLabel("LA MEVA BORSA");
    private JLabel labelProfilePhoto;
    private JLabel labelProfileName = new JLabel(" ");
    private JLabel labelSaldoActual = new JLabel("Saldo actual : ");
    private JLabel labelAfegirSaldo = new JLabel("Afegir saldo : ");

    private JLabel labelTotalSaldo = new JLabel(" ");

    private JTextField textAfegirSaldo = new JTextField(20);

    private JButton buttonAfegirSaldo = new JButton("Afegeix");
    private JButton buttonLogOut = new JButton("LOG OUT");
    private JButton buttonBack = new JButton("BACK");

    JTable table = new JTable(4, 4);

    public MyStocksWindow () {

        configureView();

        //JPanel panelBackground = new JPanel(new BorderLayout());
        //panelBackground.setBackground(Color.BLACK);

        JPanel panelMevaBorsa = new JPanel(new BorderLayout());
        panelMevaBorsa.setBackground(Color.BLACK);

        JPanel panelInfo = new JPanel( new FlowLayout());
        panelInfo.setBackground(Color.BLACK);

        //JPanel panelInfo = new JPanel();
        //panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.X_AXIS));
        //panelInfo.setBackground(Color.BLACK);


        //############ USER PROFILE ############

        //Profile photo & user name
        JPanel panelProfile = new JPanel();
        panelProfile.setLayout(new BoxLayout(panelProfile, BoxLayout.Y_AXIS));
        panelProfile.setBackground(Color.BLACK);

        labelProfileName.setText("USER");
        Font font = labelProfileName.getFont();
        labelProfileName.setFont(font.deriveFont(Font.BOLD, 20));
        labelProfileName.setForeground (Color.WHITE);
        labelProfileName.setAlignmentX(Component.CENTER_ALIGNMENT);


        labelProfilePhoto = new JLabel(getProfilePhoto());
        labelProfilePhoto.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Afegim al panelProfile
        panelProfile.add(labelProfilePhoto);
        panelProfile.add(Box.createRigidArea(new Dimension(10,20)));
        panelProfile.add(labelProfileName);


        //PanelSaldoActual
        JPanel panelSaldoAct = new JPanel();
        panelSaldoAct.setLayout(new BoxLayout(panelSaldoAct, BoxLayout.X_AXIS));
        panelSaldoAct.setBackground(Color.BLACK);

        labelSaldoActual.setFont(font.deriveFont(Font.BOLD, 12));
        labelSaldoActual.setForeground (Color.GRAY);

        labelTotalSaldo.setText(actualUserMoney + " €");
        labelTotalSaldo.setFont(font.deriveFont(Font.BOLD, 15));
        labelTotalSaldo.setForeground (Color.WHITE);

        panelSaldoAct.add(labelSaldoActual);
        panelSaldoAct.add(labelTotalSaldo);


        //PanelAfegirSaldo
        JPanel panelAfegirS = new JPanel();
        panelAfegirS.setLayout(new BoxLayout(panelAfegirS, BoxLayout.X_AXIS));
        panelAfegirS.setBackground(Color.BLACK);

        labelAfegirSaldo.setFont(font.deriveFont(Font.BOLD, 12));
        labelAfegirSaldo.setForeground (Color.GRAY);

        panelAfegirS.add(labelAfegirSaldo);
        panelAfegirS.add(textAfegirSaldo);


        //Afegim al panelProfile
        panelProfile.add(panelSaldoAct);
        panelProfile.add(panelAfegirS);
        panelProfile.add(buttonAfegirSaldo);
        panelProfile.add(buttonBack);
        panelProfile.add(buttonLogOut);


        //############ TAULA ############

        JPanel panelTaula = new JPanel();
        panelTaula.setLayout(new BoxLayout(panelTaula, BoxLayout.X_AXIS));
        panelTaula.setBackground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        //table.setFillsViewportHeight(true);
        scrollPane.getViewport().setBackground(Color.WHITE);
        panelTaula.add(scrollPane);


        //Afegim al panelInfo
        panelInfo.add(panelTaula);
        panelInfo.add(panelProfile);


        panelMevaBorsa.add(panelInfo, BorderLayout.CENTER);


        //############ TÍTOL ############
        labelTitleMB.setFont(font.deriveFont(Font.BOLD, 40));
        labelTitleMB.setForeground (Color.WHITE);
        panelMevaBorsa.add(labelTitleMB, BorderLayout.NORTH);

        this.add(panelMevaBorsa);


    }


    private ImageIcon getProfilePhoto () {

        Image profilePhotoPrev = new ImageIcon("/Users/macbook/Documents/UNIVERSITAT/4T ENGINYERIA/DPO/S2 PROJECTE/PROFILE.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);

        ImageIcon profilePhoto = new ImageIcon(profilePhotoPrev);

        return profilePhoto;

    }

    public float getSaldoAfegir(){
        return Float.valueOf(textAfegirSaldo.getText());
    }

    public void setSaldoActualUser (float money) {
        actualUserMoney = money;
    }

    private void configureView () {

        setSize(900,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }



    public void registraControlador(PrincipalController principalController) {
        buttonLogOut.setActionCommand(LOGOUT);
        buttonLogOut.addActionListener(principalController);
        buttonBack.setActionCommand(BACK);
        buttonBack.addActionListener(principalController);
        buttonAfegirSaldo.setActionCommand(AFEGIRSALDO);
        buttonAfegirSaldo.addActionListener(principalController);
    }
}
