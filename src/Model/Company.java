package Model;

import java.io.Serializable;

public class Company implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int company_id;
    private String company_name;
    private float share_price;

    public Company(int company_id, String company_name, float share_price) {
        this.company_id = company_id;
        this.company_name = company_name;
        this.share_price = share_price;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public float getShare_price() {
        return share_price;
    }

    public void setShare_price(float share_price) {
        this.share_price = share_price;
    }

    @Override
    public String toString() {
        return company_id +
                ", '" + company_name + '\'' +
                ", " + share_price;
    }
}
