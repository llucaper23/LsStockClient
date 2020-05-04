package Model.Network;


        import Controller.LoginController;
        import Model.*;
        import com.google.gson.Gson;
        import com.google.gson.stream.JsonReader;

        import java.io.FileReader;
        import java.io.IOException;
        import java.io.ObjectInputStream;
        import java.io.ObjectOutputStream;
        import java.net.Socket;
        import java.rmi.registry.Registry;
        import java.util.ArrayList;

public class Network extends Thread {

    private Socket socket;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private boolean isOn;

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
    private NetworkConfiguration nc;

    public Network() {
        Gson gson = new Gson();
        String path = "data/config.json";

        //llegim JSON
        try {
            JsonReader reader = new JsonReader(new FileReader(path));
            this.nc = gson.fromJson(reader, NetworkConfiguration.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            socket = new Socket(nc.getServerIp(), nc.getServerPort());
            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

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
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                // si hi ha algun problema satura la comunicacio amb el servidor
                stopClientNetwork();
            }
        }
        stopClientNetwork();
    }


    public boolean registraUsuari(User user) {
        try {
            Message message = new Message(REGISTER_REQUEST, null, user, false, null, null, null);
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

    public User loginUsuari(User user) {
        try {
            Message message = new Message(LOGIN_REQUEST, null, user, false, null, null, null);
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


    public void buyShares (UserCompany userCompany) {
        try {
            Message message = new Message(BUY_SHARES, null, null, false, null, userCompany, null);
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

    public void sellShares (UserCompany userCompany) {
        try {
            Message message = new Message(SELL_SHARES, null, null, false, null, userCompany, null);
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

    public ArrayList<Company> getAllCompanies(){
        try {
            Message message = new Message(ALL_COMPANIES, null, null, false, null, null,null);
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

   public ArrayList<UserCompany> getUserCompanies() {

       try {
           Message message = new Message(USER_COMPANIES, null, null, false, null, null,null);
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

    public void setUpdateMoney (User actualUser) {
        try {
            Message message = new Message(UPDATE_MONEY, null, actualUser, false, null, null, null);
            oos.writeObject(message);
            oos.flush();
            message = (Message) ois.readObject();
            if (!message.isOk()) {
                System.out.println("Error updating the money");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void logout() {
        try {
            Message message = new Message(LOGOUT, null, null, false, null, null, null);
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
