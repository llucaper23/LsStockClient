package Model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class History implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int historyId;
    private float maxSharePrice;
    private float minSharePrice;
    private float openSharePrice;
    private float closeSharePrice;

    private Date date;
    private Time time;
    private int companyId;

    public History(int historyId, float maxSharePrice, float minSharePrice, float openSharePrice, float closeSharePrice, Date date, Time time, int companyId) {
        this.historyId = historyId;
        this.maxSharePrice = maxSharePrice;
        this.minSharePrice = minSharePrice;
        this.openSharePrice = openSharePrice;
        this.closeSharePrice = closeSharePrice;
        this.date = date;
        this.time = time;
        this.companyId = companyId;
    }

    public History(float maxSharePrice, float minSharePrice, float openSharePrice, float closeSharePrice, Date date, Time time, int companyId) {
        this.maxSharePrice = maxSharePrice;
        this.minSharePrice = minSharePrice;
        this.openSharePrice = openSharePrice;
        this.closeSharePrice = closeSharePrice;
        this.date = date;
        this.time = time;
        this.companyId = companyId;
    }

    public int getHistoryId() {
        return historyId;
    }

    public void setHistoryId(int historyId) {
        this.historyId = historyId;
    }

    public float getMaxSharePrice() {
        return maxSharePrice;
    }

    public void setMaxSharePrice(float maxSharePrice) {
        this.maxSharePrice = maxSharePrice;
    }

    public float getMinSharePrice() {
        return minSharePrice;
    }

    public void setMinSharePrice(float minSharePrice) {
        this.minSharePrice = minSharePrice;
    }

    public float getOpenSharePrice() {
        return openSharePrice;
    }

    public void setOpenSharePrice(float openSharePrice) {
        this.openSharePrice = openSharePrice;
    }

    public float getCloseSharePrice() {
        return closeSharePrice;
    }

    public void setCloseSharePrice(float closeSharePrice) {
        this.closeSharePrice = closeSharePrice;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return maxSharePrice +
                ", " + minSharePrice +
                ", " + openSharePrice +
                ", " + closeSharePrice +
                ", '" + date + '\'' +
                ", '" + time + '\'' +
                ", " + companyId;
    }
}