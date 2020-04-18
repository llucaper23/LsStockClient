package Model;

import java.io.Serializable;

public class UserCompany implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int userCompanyId;
    private int userId;
    private int companyId;
    private int quantity;
    private float buyPrice;

    public UserCompany(int userCompanyId, int userId, int companyId, int quantity, float buyPrice) {
        this.userCompanyId = userCompanyId;
        this.userId = userId;
        this.companyId = companyId;
        this.quantity = quantity;
        this.buyPrice = buyPrice;
    }

    public int getUserCompanyId() {
        return userCompanyId;
    }

    public void setUserCompanyId(int userCompanyId) {
        this.userCompanyId = userCompanyId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    @Override
    public String toString() {
        return userCompanyId +
                ", " + userId +
                ", " + companyId +
                ", " + quantity +
                ", " + buyPrice;
    }
}
