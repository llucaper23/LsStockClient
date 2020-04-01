package view;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private JLabel labelUsernameL = new JLabel("Username or email: ");
    private JLabel labelPasswordL = new JLabel("Password: ");
    private JLabel labelSignUp = new JLabel("Don't you have an account? ");

    private JLabel logo ;

    private JTextField textUsernameL = new JTextField(20);

    private JPasswordField fieldPasswordL = new JPasswordField(20);

    private JButton buttonLogin = new JButton("Log in");
    private JButton buttonSignUp = new JButton("Sign up");

    public LoginWindow () {

        configureWindow();

        //####### LOGO ###########################################################

        JPanel panelLogo = new JPanel(new BorderLayout());

        ImageIcon iLogo = new ImageIcon("/Users/macbook/Documents/UNIVERSITAT/4T ENGINYERIA/DPO/S2 PROJECTE/LOGO.png");

        logo = new JLabel(iLogo);

        panelLogo.add(logo, BorderLayout.CENTER);


        //####### LOGIN #################################################

        // create a new panel with GridBagLayout manager
        JPanel panelLogin = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //constraints.insets = new Insets(10, 15, 10, 15);


        //LOGIN
        login(constraints, panelLogin);


        //########################################################################

        // add the panels to this frame
        Container contentBackground = getContentPane();

        contentBackground.add(panelLogo, BorderLayout.PAGE_START);
        contentBackground.add(panelLogin, BorderLayout.CENTER);


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

    public void login (GridBagConstraints constraints, JPanel panelLogin) {

        constraints.insets = new Insets(20, 15, 10, 15);

        constraints.anchor = GridBagConstraints.WEST;

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panelLogin.add(labelUsernameL, constraints);

        constraints.gridx = 1;
        constraints.gridy = 0;
        panelLogin.add(textUsernameL, constraints);

        constraints.insets = new Insets(10, 15, 10, 15);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelLogin.add(labelPasswordL, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panelLogin.add(fieldPasswordL, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.EAST;
        panelLogin.add(buttonLogin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.anchor = GridBagConstraints.CENTER;
        panelLogin.add(labelSignUp, constraints);

        constraints.insets = new Insets(3, 15, 20, 15);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.anchor = GridBagConstraints.CENTER;
        panelLogin.add(buttonSignUp, constraints);

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