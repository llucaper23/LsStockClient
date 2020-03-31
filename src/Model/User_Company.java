package Model;

import java.io.Serializable;

public class User_Company implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int user_company_id;
    private int user_id;
    private int company_id;
    private int quantity;
    private float buy_price;

    public User_Company(int user_company_id, int user_id, int company_id, int quantity, float buy_price) {
        this.user_company_id = user_company_id;
        this.user_id = user_id;
        this.company_id = company_id;
        this.quantity = quantity;
        this.buy_price = buy_price;
    }

    public int getUser_company_id() {
        return user_company_id;
    }

    public void setUser_company_id(int user_company_id) {
        this.user_company_id = user_company_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(float buy_price) {
        this.buy_price = buy_price;
    }

    @Override
    public String toString() {
        return user_company_id +
                ", " + user_id +
                ", " + company_id +
                ", " + quantity +
                ", " + buy_price;
    }
}
