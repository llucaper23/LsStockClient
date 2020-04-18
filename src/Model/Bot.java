package Model;

import java.io.Serializable;

public class Bot implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int botId;
    private float buyPercentage;
    private float activationTime;
    private int companyId;

    public Bot (int botId, float buyPercentage, float activationTime, int companyId) {
        this.botId = botId;
        this.buyPercentage = buyPercentage;
        this.activationTime = activationTime;
        this.companyId = companyId;
    }

    public int getBotId() {
        return botId;
    }

    public void setBotId(int botId) {
        this.botId = botId;
    }

    public float getBuyPercentage() {
        return buyPercentage;
    }

    public void setBuyPercentage(float buyPercentage) {
        this.buyPercentage = buyPercentage;
    }

    public float getActivationTime() {
        return activationTime;
    }

    public void setActivationTime(float activationTime) {
        this.activationTime = activationTime;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return botId +
                ", " + buyPercentage +
                ", " + activationTime +
                ", " + companyId;
    }
}
