package Model;

import java.io.Serializable;

public class Company implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int companyId;
    private String companyName;
    private float sharePrice;

    public Company(String companyName, float sharePrice) {
        this.companyName = companyName;
        this.sharePrice = sharePrice;
    }

    public Company(int companyId, String companyName, float sharePrice) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.sharePrice = sharePrice;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public float getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(float sharePrice) {
        this.sharePrice = sharePrice;
    }

    @Override
    public String toString() {
        return companyName + '\'' +
                ", " + sharePrice;
    }
}
