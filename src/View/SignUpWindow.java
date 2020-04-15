package View;

import Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class SignUpWindow extends JFrame {

    private static String REGISTER = "REGISTER";

    private JLabel labelUsernameS = new JLabel("Username");
    private JLabel labelMailS = new JLabel("Email");
    private JLabel labelPasswordS = new JLabel("Password");
    private JLabel labelCheckPasswordS = new JLabel("Password confirmation");

    private JLabel logo ;

    private JTextField textUsernameS = new JTextField(20);
    private JTextField textMailS = new JTextField(20);

    private JPasswordField fieldPasswordS = new JPasswordField(20);
    private JPasswordField fieldCheckPasswordS = new JPasswordField(20);

    private JButton buttonSignup = new JButton("Sign up");

    public SignUpWindow () {

        configureWindow();

        //####### LOGO ###########################################################

        JPanel panelLogo = new JPanel(new BorderLayout());

        ImageIcon iLogo = new ImageIcon("data/LOGO.png");

        logo = new JLabel(iLogo);

        panelLogo.add(logo, BorderLayout.CENTER);


        //####### SIGNUP #################################################

        // create a new panel with GridBagLayout manager
        JPanel panelSignup = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //constraints.insets = new Insets(10, 15, 10, 15);

        //SIGN UP
        signUp(constraints, panelSignup);


        //########################################################################

        // add the panels to this frame
        Container contentBackground = getContentPane();

        contentBackground.add(panelLogo, BorderLayout.PAGE_START);
        contentBackground.add(panelSignup, BorderLayout.CENTER);


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

    public void signUp (GridBagConstraints constraints, JPanel panelSignup) {

        constraints.insets = new Insets(20, 15, 10, 15);

        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelSignup.add(labelUsernameS, constraints);

        constraints.insets = new Insets(0, 15, 5, 15);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panelSignup.add(textUsernameS, constraints);

        constraints.insets = new Insets(10, 15, 10, 15);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panelSignup.add(labelMailS, constraints);

        constraints.insets = new Insets(0, 15, 5, 15);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panelSignup.add(textMailS, constraints);

        constraints.insets = new Insets(10, 15, 10, 15);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panelSignup.add(labelPasswordS, constraints);

        constraints.insets = new Insets(0, 15, 5, 15);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panelSignup.add(fieldPasswordS, constraints);

        constraints.insets = new Insets(10, 15, 10, 15);

        constraints.gridx = 0;
        constraints.gridy = 7;
        panelSignup.add(labelCheckPasswordS, constraints);

        constraints.insets = new Insets(0, 15, 5, 15);

        constraints.gridx = 0;
        constraints.gridy = 8;
        panelSignup.add(fieldCheckPasswordS, constraints);

        constraints.insets = new Insets(10, 15, 30, 15);

        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 5;
        constraints.anchor = GridBagConstraints.CENTER;
        panelSignup.add(buttonSignup, constraints);

    }

    public void registrarControlador(LoginController loginController) {
        buttonSignup.setActionCommand(REGISTER);
        buttonSignup.addActionListener(loginController);
    }

    public String getName(){
        return textUsernameS.getText();
    }
    public String getMail(){
        return textMailS.getText();
    }
    public String getPassword(){
        return String.valueOf(fieldPasswordS.getPassword());
    }
    public String getPasswordCheck(){
        return String.valueOf(fieldCheckPasswordS.getPassword());
    }


}