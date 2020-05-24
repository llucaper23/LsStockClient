package Model.Network;

        import Model.*;
        import com.google.gson.Gson;
        import com.google.gson.stream.JsonReader;

        import java.io.FileReader;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.net.Socket;
        import java.util.ArrayList;
        import java.util.Collections;

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
    private static final int HISTORY = 8;
    private static final int LOGOUT = 9;
    private static final int SELL_SOME_SHARES = 10;
    private static final int FIVE_MIN_PRICE = 11;

    private int port;
    private NetworkConfiguration nc;

    public Network() {
        Gson gson = new Gson();
        String path = "data/config.json";

        //llegim JSON
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            this.nc = gson.fromJson(reader, NetworkConfiguration.class);
            socket = new Socket(nc.getServerIp(), nc.getServerPort());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio que envia el port que usara l'usuari
     */
    public void sendPort() {
        try {
            port = 10000 + (int)(Math.random() * ((65535 - 10000) + 1));
            oos.writeInt(port);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getPort() {
        return port;
    }

    /**
     * Funcio que envia una peticio de registre al servidor.
     * @param user User que enviem al servidor per registrar
     * @return boolean per si s'ha registrat correctament
     */
    public synchronized boolean registraUsuari(User user) {
        try {
            Message message = new Message(REGISTER_REQUEST, null, user, null, null, null, false, 0, null);
            oos.writeObject(message);
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error to register");
                return false;
            } else {
                System.out.println("Register Succesful!");
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     * Funcio que envia una peticio de login al servidor.
     * @param user User que enviem al servidor per fer login
     * @return User que retorna logged
     */
    public synchronized User loginUsuari(User user) {
        try {
            Message message = new Message(LOGIN_REQUEST, null, user, null, null, null, false, 0, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error to login");
                return null;
            } else {
                return message.getUser();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Procediment que envia la compra al servidor.
     * @param userCompany Companyia a comprar per l'usuari
     */
    public synchronized void buyShares (UserCompany userCompany) {
        try {
            Message message = new Message(BUY_SHARES, null, null, null, userCompany, null, false, 0, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error to buy shares");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    /**
     * Procediment que envia la venta al servidor.
     * @param userCompany Companyia a vendre per l'usuari
     */
    public synchronized void sellShares (UserCompany userCompany) {
        try {
            Message message = new Message(SELL_SHARES, null, null, null, userCompany, null, false, 0, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error to sell shares");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio que retorna ArrayList amb totes les companyies
     * @return ArrayList de companyies
     */
    public synchronized ArrayList<Company> getAllCompanies(){
        try {
            Message message = new Message(ALL_COMPANIES, null, null, null, null, null, false, 0, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error getting the user companies");
                return null;
            } else {
                return message.getCompanyList();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Funcio que retorna ArrayList amb totes les companyies de l'usuari.
     * @return ArrayList de companyies de l'usuari
     */
    public synchronized ArrayList<UserCompany> getUserCompanies() {

       try {
           Message message = new Message(USER_COMPANIES, null, null, null, null, null, false, 0, null);
           oos.writeObject(message);
           oos.flush();
           message = (Message) ois.readObject();
           if (!message.isOk()) {
               System.out.println("Error getting the user companies");
               return null;
           } else {
               return message.getUserCompanies();
           }
       } catch (IOException | ClassNotFoundException e) {
           e.printStackTrace();
           return null;
       }
   }

    /**
     * Funcio que envia una actualitzacio de diners de l'usuari.
     * @param actualUser User actual amb les dades canviades
     * @return boolean per si el update s'ha fet be.
     */
    public synchronized boolean setUpdateMoney (User actualUser) {
        try {
            Message message = new Message(UPDATE_MONEY, null, actualUser, null, null, null, false, 0, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error updating the money");
                return false;
            }else {
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
   }

    /**
     * Procediment que avisa al servidor que l'usuari ha fet logout.
     */
    public synchronized void logout() {
        try {
            Message message = new Message(LOGOUT, null, null, null, null, null, false, 0, null);
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Procediment que envia la venta parcial al servidor.
     * @param company Companyia a vendre per l'usuari
     * @param totalAccions Int amb el nombre de accions
     */
    public synchronized void sellSomeShares(Company company, int totalAccions) {
        try {
            Message message = new Message(SELL_SOME_SHARES, null, null, company, null, null, false, totalAccions, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error to sell shares");
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Funcio que demana l'historial d'una companyia
     * @param company Comapnyia ha demanar
     * @return ArryList amb l'historial
     */
    public synchronized ArrayList<History> getHistory (Company company) {
        try {
            Message message = new Message(HISTORY, null, null, company, null, null, false, 0, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error to get historic");
            }else{
                return message.getHistories();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Funcio que demana el preu historic de la companyia
     * @return ArrayList amb l'historial
     */
    public synchronized ArrayList<History> get5MinPrice(){
        try{
            Message message = new Message(FIVE_MIN_PRICE, null, null, null, null, null, false, 0, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()){
                System.out.println("Error to get 5 min price");
            }else{
                return message.getHistories();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Restart del Network
     */
    public void restart() {
        try {
            socket = new Socket(nc.getServerIp(), nc.getServerPort());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
