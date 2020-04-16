package Model.Network;


import Model.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Network {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

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

    public Network() {
        try {
            socket = new Socket("localhost", 34567);
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public boolean registraUsuari(User user) {
        try {
            oos.write(REGISTER_REQUEST);
            oos.flush();
            oos.writeObject(user);
            boolean infoOk = ois.readBoolean();
            if (!infoOk){
                System.out.println("Error to register");
                return false;
            }else{
                System.out.println("Register Succesful!");
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

    public User loginUsuari(User user){
        try {
            oos.write(LOGIN_REQUEST);
            oos.writeObject(user);
            boolean loginOk = ois.readBoolean();
            if (!loginOk){
                System.out.println("Error to login");
                return null;
            }else{
                try {
                    return (User) ois.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
