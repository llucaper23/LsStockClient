package Model.Network;

import Controller.LoginController;
import Controller.PrincipalController;
import Model.Company;
import Model.Message;

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

    public NetworkListen(PrincipalController pc, LoginController lc, int port) {
        try {
            ServerSocket socket1 = new ServerSocket(port);
            socket = socket1.accept();
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isOn() {
        return isOn;
    }

    public void startClientNetwork() {
        // iniciem la comunicacio amb el servidor
        isOn = true;
        this.start();
    }

    public void stopClientNetwork() {
        // aturem la comunicacio amb el servidor
        this.isOn = false;
        this.interrupt();
    }

    public void run() {
        while (isOn) {
            try {
                Message message = (Message) ois.readObject();
                switch (message.getRequestType()) {
                    case ALL_COMPANIES:
                        ArrayList<Company> updatedCompanies = message.getCompanyList();
                        System.out.println("");
                        //cridar pc per actualitzar vista
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
