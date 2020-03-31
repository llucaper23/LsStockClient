
package view;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private JLabel labelUsernameL = new JLabel("Nom usuari o correu: ");
    private JLabel labelPasswordL = new JLabel("Contrasenya: ");
    private JLabel labelUsernameS = new JLabel("Nom usuari: ");
    private JLabel labelMailS = new JLabel("Correu electrònic: ");
    private JLabel labelPasswordS = new JLabel("Contrasenya: ");
    private JLabel labelCheckPasswordS = new JLabel("Confirmació contrasenya: ");

    private JLabel logo ;

    private JTextField textUsernameL = new JTextField(20);
    private JTextField textUsernameS = new JTextField(20);
    private JTextField textMailS = new JTextField(20);

    private JPasswordField fieldPasswordL = new JPasswordField(20);
    private JPasswordField fieldPasswordS = new JPasswordField(20);
    private JPasswordField fieldCheckPasswordS = new JPasswordField(20);


    private JButton buttonLogin = new JButton("Log in");
    private JButton buttonSignup = new JButton("Sign up");

    public LoginWindow () {

        configureWindow();

        //####### BACKGROUND #####################################################




        //####### LOGO ###########################################################

        JPanel panelLogo = new JPanel(new BorderLayout());

        ImageIcon iLogo = new ImageIcon("/Users/macbook/Documents/UNIVERSITAT/4T ENGINYERIA/DPO/S2 PROJECTE/LOGO.png");

        logo = new JLabel(iLogo);

        panelLogo.add(logo, BorderLayout.CENTER);


        //####### LOGIN & SIGNUP #################################################

        // create a new panel with GridBagLayout manager
        JPanel panelLoginSignup = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.insets = new Insets(10, 15, 10, 15);


        //LOGIN
        login(constraints, panelLoginSignup);

        //SIGN UP
        signUp(constraints, panelLoginSignup);


        //########################################################################

        // add the panels to this frame
        Container contentBackground = getContentPane();

        contentBackground.add(panelLogo, BorderLayout.PAGE_START);
        contentBackground.add(panelLoginSignup, BorderLayout.CENTER);


        pack();
        setLocationRelativeTo(null);

    }

    private void configureWindow (){

        //setSize(500,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void login (GridBagConstraints constraints, JPanel panelLoginSignup) {

        constraints.anchor = GridBagConstraints.WEST;

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panelLoginSignup.add(labelUsernameL, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panelLoginSignup.add(textUsernameL, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelLoginSignup.add(labelPasswordL, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panelLoginSignup.add(fieldPasswordL, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panelLoginSignup.add(buttonLogin, constraints);

    }


    public void signUp (GridBagConstraints constraints, JPanel panelLoginSignup) {

        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 3;
        panelLoginSignup.add(labelUsernameS, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panelLoginSignup.add(textUsernameS, constraints);


        constraints.gridx = 0;
        constraints.gridy = 4;
        panelLoginSignup.add(labelMailS, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        panelLoginSignup.add(textMailS, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panelLoginSignup.add(labelPasswordS, constraints);

        constraints.gridx = 1;
        constraints.gridy = 5;
        panelLoginSignup.add(fieldPasswordS, constraints);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panelLoginSignup.add(labelCheckPasswordS, constraints);

        constraints.gridx = 1;
        constraints.gridy = 6;
        panelLoginSignup.add(fieldCheckPasswordS, constraints);

        constraints.gridx = 0;
        constraints.gridy = 7;
        constraints.gridwidth = 7;
        constraints.anchor = GridBagConstraints.EAST;
        panelLoginSignup.add(buttonSignup, constraints);

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
                new LoginWindow().setVisible(true);
            }
        });
    }


}

