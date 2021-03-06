package Model.Network;

import Controller.LoginController;
import Controller.PrincipalController;
import Model.Company;
import Model.History;
import Model.Message;
import Model.UserCompany;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkListen extends Thread {

    private static final int REGISTER_REQUEST = 1;
    private static final int LOGIN_REQUEST = 2;
    private static final int UPDATE_MONEY = 3;
    private static final int BUY_SHARES = 4;
    private static final int SELL_SHARES = 5;
    private static final int USER_COMPANIES = 6;
    private static final int ALL_COMPANIES = 7;
    private static final int COMPANY_DETAIL = 8;
    private static final int LOGOUT = 9;

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean isOn;
    private PrincipalController pc;

    public NetworkListen(PrincipalController pc, LoginController lc, int port) {
        try {
            ServerSocket socket1 = new ServerSocket(port);
            socket = socket1.accept();
            ois = new ObjectInputStream(socket.getInputStream());
            this.pc = pc;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procediment que inicia el Network
     */
    public void startClientNetwork() {
        // iniciem la comunicacio amb el servidor
        isOn = true;
        this.start();
    }

    /**
     * Procediment que atura el Network
     */
    public void stopClientNetwork() {
        // aturem la comunicacio amb el servidor
        this.isOn = false;
        this.interrupt();
    }

    /**
     * Procediment run del Network que escolta els updates de totes les companyies.
     */
    public void run() {
        while (isOn) {
            try {
                Message message = (Message) ois.readObject();
                switch (message.getRequestType()) {
                    case ALL_COMPANIES:
                        ArrayList<Company> updatedCompanies = message.getCompanyList();
                        ArrayList<UserCompany> updatedUserCompanies = message.getUserCompanies();
                        ArrayList<History> histories = message.getHistories();
                        pc.updateCompanies(updatedCompanies, updatedUserCompanies, histories);
                        break;
                    case LOGOUT:
                        stopClientNetwork();
                        break;
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                // si hi ha algun problema s'atura la comunicacio amb el servidor
                stopClientNetwork();
            }
        }
    }
}
