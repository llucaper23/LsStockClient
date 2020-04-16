package View;

import javax.swing.*;
import java.awt.*;

//haurà de rebre el nom del usuari, el saldo actual i les accions que té de totes les empreses
public class MyStocksWindow extends JFrame {

    private JLabel labelTitleMB = new JLabel("LA MEVA BORSA");
    private JLabel labelProfilePhoto;
    private JLabel labelProfileName = new JLabel("USER ");
    private JLabel labelSaldoActual = new JLabel("Saldo actual : ");
    private JLabel labelAfegirSaldo = new JLabel("Afegir saldo : ");

    private JLabel labelTotalSaldo = new JLabel("12363");

    private JTextField textAfegirSaldo = new JTextField(20);

    private JButton buttonAfegirSaldo = new JButton("Afegeix");

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

        labelProfileName = new JLabel(getProfileName()) ;
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


        //Saldo i saldo actual
        JPanel panelSaldo = new JPanel( new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        panelSaldo.setBackground(Color.BLACK);

        //constraints.insets = new Insets(10, 20, 10, 20);

        saldoActual(constraints, panelSaldo);
        afegirSaldo(constraints, panelSaldo);

        //Afegim al panelProfile

        panelProfile.add(panelSaldo);

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

        //############ "COLLAGE" ############

        constraints.insets = new Insets(10, 20, 10, 20);
        this.add(panelMevaBorsa);


    }

    private String getProfileName () {

        return "USER";
    }

    private ImageIcon getProfilePhoto () {

        Image profilePhotoPrev = new ImageIcon("/Users/macbook/Documents/UNIVERSITAT/4T ENGINYERIA/DPO/S2 PROJECTE/PROFILE.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);

        ImageIcon profilePhoto = new ImageIcon(profilePhotoPrev);

        return profilePhoto;

    }

    private JLabel getSaldo () {

        Font font = labelTotalSaldo.getFont();
        labelTotalSaldo.setFont(font.deriveFont(Font.BOLD, 16));
        labelTotalSaldo.setForeground (Color.WHITE);

        return labelTotalSaldo;
    }

    private void saldoActual(GridBagConstraints constraints, JPanel panelSaldo) {

        constraints.insets = new Insets(40, 20, 10, 20);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 0;
        constraints.gridy = 0;
        labelSaldoActual.setForeground (Color.GRAY);
        panelSaldo.add(labelSaldoActual, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panelSaldo.add(getSaldo(), constraints);


    }

    private void afegirSaldo(GridBagConstraints constraints, JPanel panelSaldo) {

        constraints.insets = new Insets(10, 20, 10, 20);

        constraints.anchor = GridBagConstraints.CENTER;

        constraints.gridx = 0;
        constraints.gridy = 1;
        labelAfegirSaldo.setForeground (Color.GRAY);
        panelSaldo.add(labelAfegirSaldo, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panelSaldo.add(textAfegirSaldo, constraints);

        constraints.insets = new Insets(10, 20, 30, 20);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panelSaldo.add(buttonAfegirSaldo, constraints);

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
                new MyStocksWindow().setVisible(true);
            }
        });
    }

}
