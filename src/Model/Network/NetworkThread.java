package Model.Network;

import java.io.ObjectInputStream;
import java.net.Socket;

public class NetworkThread extends Thread {

    private Socket socket;

    private static final int REGISTER_REQUEST = 1;
    private static final int LOGIN_REQUEST = 2;
    private static final int UPDATE_MONEY = 3;
    private static final int BUY_SHARES = 4;
    private static final int SELL_SHARES = 5;
    private static final int USER_COMPANIES = 6;
    private static final int ALL_COMPANIES = 7;
    private static final int COMPANY_DETAIL = 8;
    private static final int LOGOUT = 9;

    private boolean running;
    private ObjectInputStream ois;

    public void startServerConnection(){

    }

    public void run(){

    }
}
