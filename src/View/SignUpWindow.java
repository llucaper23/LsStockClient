package View;

import Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class SignUpWindow extends JFrame {

    private static String REGISTER = "REGISTER";
    private static String BACK = "BACK";

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
    private JButton buttonBack = new JButton("Back");

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

    /**
     * Procediment que configura la vista
     */
    private void configureWindow (){
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Procediment que construeix part de la vista
     * @param constraints GridBag que forma la vista
     * @param panelSignup JPanel que forma la vista
     */
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
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.add(buttonSignup);
        buttons.add(buttonBack);
        panelSignup.add(buttons, constraints);
    }

    /**
     * Procediment que registra el controlador
     * @param loginController Controlador corresponent
     */
    public void registrarControlador(LoginController loginController) {
        buttonSignup.setActionCommand(REGISTER);
        buttonSignup.addActionListener(loginController);
        buttonBack.setActionCommand(BACK);
        buttonBack.addActionListener(loginController);
    }

    /**
     * Funcio que agafa el nom
     * @return String amb el nom
     */
    public String getName(){
        return textUsernameS.getText();
    }

    /**
     * Funcio que agafa el mail
     * @return String amb el mail
     */
    public String getMail(){
        return textMailS.getText();
    }

    /**
     * Funcio que agafa el password
     * @return String amb el password
     */
    public String getPassword(){
        return String.valueOf(fieldPasswordS.getPassword());
    }

    /**
     * Funcio que agafa el password repetit
     * @return String amb el password repetit
     */
    public String getPasswordCheck(){
        return String.valueOf(fieldCheckPasswordS.getPassword());
    }

    /**
     * Funcio que mostra l'error
     * @param error String amb el error
     */
    public void mostraMissatgeError(String error){
        JOptionPane.showMessageDialog(null, error);
    }
}
