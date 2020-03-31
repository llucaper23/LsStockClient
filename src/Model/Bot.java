package Model;

import java.io.Serializable;

public class Bot implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int bot_id;
    private float buy_Percentage;
    private float activation_Time;
    private int company_id;

    public Bot(int bot_id, float buy_Percentage, float activation_Time, int company_id) {
        this.bot_id = bot_id;
        this.buy_Percentage = buy_Percentage;
        this.activation_Time = activation_Time;
        this.company_id = company_id;
    }

    public int getBot_id() {
        return bot_id;
    }

    public void setBot_id(int bot_id) {
        this.bot_id = bot_id;
    }

    public float getBuy_Percentage() {
        return buy_Percentage;
    }

    public void setBuy_Percentage(float buy_Percentage) {
        this.buy_Percentage = buy_Percentage;
    }

    public float getActivation_Time() {
        return activation_Time;
    }

    public void setActivation_Time(float activation_Time) {
        this.activation_Time = activation_Time;
    }

    public int getCompany_id() {
        return company_id;
    }

    public void setCompany_id(int company_id) {
        this.company_id = company_id;
    }

    @Override
    public String toString() {
        return bot_id +
                ", " + buy_Percentage +
                ", " + activation_Time +
                ", " + company_id;
    }
}
