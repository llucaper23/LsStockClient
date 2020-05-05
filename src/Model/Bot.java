package Model;

import java.io.Serializable;

public class Bot implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int botId;
    private float buyPercentage;
    private float activationTime;
    private int companyId;
    private boolean isActive;

    public Bot (int botId, float buyPercentage, float activationTime, int companyId, boolean isActive) {
        this.botId = botId;
        this.buyPercentage = buyPercentage;
        this.activationTime = activationTime;
        this.companyId = companyId;
        this.isActive = isActive;
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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return buyPercentage +
                ", " + activationTime +
                ", " + companyId +
                ", " + isActive;
    }
}
