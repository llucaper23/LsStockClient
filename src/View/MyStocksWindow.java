package View;

import Controller.PrincipalController;
import Model.Company;
import Model.User;
import Model.UserCompany;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.ArrayList;

public class MyStocksWindow extends JFrame {

    private static String LOGOUT = "LOGOUT";
    private static String BACK = "BACKMYSTOCK";
    private static String AFEGIRSALDO = "AFEGIRSALDO";

    private float actualUserMoney;

    final int MAX_HEIGHT_SHARES = 700;
    final int MAX_WIDTH_SHARES = 450;

    JPanel panelProfile = new JPanel();
    JPanel panelMevaBorsa = new JPanel(new BorderLayout());
    JPanel panelInfo = new JPanel( new FlowLayout());
    JPanel panelAfegirS = new JPanel();
    JPanel panelSaldoAct = new JPanel();
    JPanel panelShares = new JPanel();
    JPanel panelLines = new JPanel();

    Font font;

    private JLabel labelProfilePhoto;
    private JLabel labelProfileName = new JLabel(" ");
    private JLabel labelSaldoActual = new JLabel("Saldo actual : ");
    private JLabel labelAfegirSaldo = new JLabel("Afegir saldo : ");

    private JLabel labelTotalSaldo = new JLabel(" ");

    private JTextField textAfegirSaldo = new JTextField(20);

    private JButton buttonAfegirSaldo = new JButton("Afegeix");
    private JButton buttonLogOut = new JButton("LOG OUT");
    private JButton buttonBack = new JButton("BACK");

    public MyStocksWindow () {

        configureView();

        //############ USER PROFILE  - part dreta ############

        //Profile name
        font = labelProfileName.getFont();
        labelProfileName.setFont(font.deriveFont(Font.BOLD, 20));
        labelProfileName.setForeground (Color.BLACK);
        labelProfileName.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Profile photo
        panelProfile.setLayout(new BoxLayout(panelProfile, BoxLayout.Y_AXIS));
        labelProfilePhoto = new JLabel(getProfilePhoto());
        labelProfilePhoto.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Afegim al panelProfile
        panelProfile.add(labelProfilePhoto);
        panelProfile.add(Box.createRigidArea(new Dimension(10,20)));
        panelProfile.add(labelProfileName);


        //PabelSaldoActual
        panelSaldoAct.setLayout(new BoxLayout(panelSaldoAct, BoxLayout.X_AXIS));
        labelSaldoActual.setFont(font.deriveFont(Font.BOLD, 12));
        labelSaldoActual.setForeground (Color.BLACK);
        labelTotalSaldo.setFont(font.deriveFont(Font.BOLD, 15));
        labelTotalSaldo.setForeground (Color.BLACK);


        //PanelAfegirSaldo
        panelAfegirS.setLayout(new BoxLayout(panelAfegirS, BoxLayout.X_AXIS));

        labelAfegirSaldo.setFont(font.deriveFont(Font.BOLD, 12));
        labelAfegirSaldo.setForeground (Color.GRAY);

        panelAfegirS.add(labelAfegirSaldo);
        panelAfegirS.add(textAfegirSaldo);


        //Afegim al panelProfile
        panelProfile.add(panelAfegirS);
        panelProfile.add(buttonAfegirSaldo);

    }

    public void updateMyStocks(ArrayList<UserCompany> companies, ArrayList<Company> allCompanies, PrincipalController principalController){
        panelShares.removeAll();
        panelLines.removeAll();

        panelLines.setLayout(new BoxLayout(panelLines, BoxLayout.Y_AXIS));

        for (int i = 0; i < companies.size(); i++) {
            MyStocksLine line = new MyStocksLine(companies.get(i), allCompanies);
            line.registraControlador(principalController);
            panelLines.add(line);
        }

        JScrollPane scrollPane = new JScrollPane(panelLines);

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollPane.setAlignmentX(Component.RIGHT_ALIGNMENT);
        scrollPane.setPreferredSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));
        scrollPane.setMaximumSize(new Dimension(MAX_HEIGHT_SHARES, MAX_WIDTH_SHARES));

        TitledBorder title = BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(),"MY SHARES");
        title.setTitleJustification(TitledBorder.CENTER);
        title.setTitlePosition(TitledBorder.ABOVE_TOP);
        scrollPane.setBorder(title);

        panelShares.add(scrollPane);

        panelInfo.add(panelShares);
        panelInfo.add(panelProfile);

        panelMevaBorsa.add(panelInfo, BorderLayout.CENTER);
        panelMevaBorsa.revalidate();
        panelMevaBorsa.repaint();

        //"COLLAGE"
        this.add(panelMevaBorsa);
        this.revalidate();
        this.repaint();

    }


    private ImageIcon getProfilePhoto () {

        Image profilePhotoPrev = new ImageIcon("data/PROFILE.png").getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);

        ImageIcon profilePhoto = new ImageIcon(profilePhotoPrev);

        return profilePhoto;

    }

    public float getSaldoAfegir(){
        if (textAfegirSaldo.getText().equalsIgnoreCase("")){
            textAfegirSaldo.setText("");
            return 0;
        }else{
            try{
                float saldo = Float.parseFloat(textAfegirSaldo.getText());
                textAfegirSaldo.setText("");
                return saldo;
            }catch (NumberFormatException e){
                textAfegirSaldo.setText("");
                return 0;
            }

        }

    }

    public void setSaldoActualUser (float money) {
        actualUserMoney = money;
        labelTotalSaldo.setText(actualUserMoney + " €");

        panelSaldoAct.add(labelSaldoActual);
        panelSaldoAct.add(labelTotalSaldo);

        panelProfile.add(panelSaldoAct);
        textAfegirSaldo.setText("");

        panelProfile.add(buttonBack);
        panelProfile.add(buttonLogOut);
    }

    public void setUserName (User user) {
        labelProfileName.setText(user.getNickName());
    }

    private void configureView () {

        setSize(1100,500);
        setTitle("LS_STOCK");
        setLocationRelativeTo(null);
        setResizable(false);
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

    public void mostraMissatgeError(String error){
        JOptionPane.showMessageDialog(null, error);
    }
}

