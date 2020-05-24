package View;

import Controller.LoginController;

import javax.swing.*;
import java.awt.*;

public class LoginWindow extends JFrame {

    private static String LOGIN = "LOGIN";
    private static String GO_TO_REGISTER = "GO_TO_REGISTER";

    private JLabel labelUsernameL = new JLabel("Username or Email");
    private JLabel labelPasswordL = new JLabel("Password");
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

        ImageIcon iLogo = new ImageIcon("data/LOGO.png");

        logo = new JLabel(iLogo);

        panelLogo.add(logo, BorderLayout.CENTER);


        //####### LOGIN #################################################

        // create a new panel with GridBagLayout manager
        JPanel panelLogin = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();



        //LOGIN
        login(constraints, panelLogin);

        // add the panels to this frame
        Container contentBackground = getContentPane();

        contentBackground.add(panelLogo, BorderLayout.PAGE_START);
        contentBackground.add(panelLogin, BorderLayout.CENTER);


        pack();
        setLocationRelativeTo(null);

    }

    /**
     * Procediment que configura la vista.
     */
    private void configureWindow (){
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    /**
     * Procediment que construeix part de la vista.
     * @param constraints GridBag del panell
     * @param panelLogin JPanel amb el login
     */
    public void login (GridBagConstraints constraints, JPanel panelLogin) {

        constraints.insets = new Insets(20, 15, 10, 15);

        constraints.anchor = GridBagConstraints.WEST;

        // add components to the panel
        constraints.gridx = 0;
        constraints.gridy = 0;
        panelLogin.add(labelUsernameL, constraints);

        constraints.insets = new Insets(0, 15, 5, 15);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panelLogin.add(textUsernameL, constraints);

        constraints.insets = new Insets(10, 15, 10, 15);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panelLogin.add(labelPasswordL, constraints);

        constraints.insets = new Insets(0, 15, 5, 15);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panelLogin.add(fieldPasswordL, constraints);

        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10, 15, 10, 15);

        constraints.gridx = 0;
        constraints.gridy = 4;
        panelLogin.add(buttonLogin, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        panelLogin.add(labelSignUp, constraints);

        constraints.insets = new Insets(3, 15, 30, 15);

        constraints.gridx = 0;
        constraints.gridy = 6;
        panelLogin.add(buttonSignUp, constraints);

    }

    /**
     * Funcio que comprova el format del mail.
     * @return boolean si es format corresponent.
     */
    public boolean isMail(){
        if(textUsernameL.getText().contains("@")){
            return true;
        }else {
            return false;
        }
    }

    /**
     * Funcio que agafa el nom del login.
     * @return String amb el nom
     */
    public String getName(){
        return textUsernameL.getText();
    }

    /**
     * Funcio que agafa el password
     * @return String amb el password
     */
    public String getPassword(){
        return String.valueOf(fieldPasswordL.getPassword());
    }

    /**
     * Funcio que registra el controlador de la vista.
     * @param loginController Controlador corresponent
     */
    public void registrarControlador(LoginController loginController) {
        buttonLogin.setActionCommand(LOGIN);
        buttonLogin.addActionListener(loginController);
        buttonSignUp.setActionCommand(GO_TO_REGISTER);
        buttonSignUp.addActionListener(loginController);
    }

    /**
     * Procediment que mostra el missatge d'error.
     * @param error String amb el error
     */
    public void mostraMissatgeError(String error){
        JOptionPane.showMessageDialog(null, error);
    }

}
