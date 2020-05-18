package Model;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class History implements Serializable {
    private static final long serialVersionUID = 12345L;
    private int history_id;
    private float max_share_price;
    private float min_share_price;
    private float open_share_price;
    private float close_share_price;
    private Date date;
    private Time time;
    private int companyId;

    public History(int history_id, float max_share_price, float min_share_price, float open_share_price, float close_share_price, Date date, Time time, int companyId) {
        this.history_id = history_id;
        this.max_share_price = max_share_price;
        this.min_share_price = min_share_price;
        this.open_share_price = open_share_price;
        this.close_share_price = close_share_price;
        this.date = date;
        this.time = time;
        this.companyId = companyId;
    }

    public History(float max_share_price, float min_share_price, float open_share_price, float close_share_price, Date date, Time time, int companyId) {
        this.max_share_price = max_share_price;
        this.min_share_price = min_share_price;
        this.open_share_price = open_share_price;
        this.close_share_price = close_share_price;
        this.date = date;
        this.time = time;
        this.companyId = companyId;
    }

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public float getMax_share_price() {
        return max_share_price;
    }

    public void setMax_share_price(float max_share_price) {
        this.max_share_price = max_share_price;
    }

    public float getMin_share_price() {
        return min_share_price;
    }

    public void setMin_share_price(float min_share_price) {
        this.min_share_price = min_share_price;
    }

    public float getOpen_share_price() {
        return open_share_price;
    }

    public void setOpen_share_price(float open_share_price) {
        this.open_share_price = open_share_price;
    }

    public float getClose_share_price() {
        return close_share_price;
    }

    public void setClose_share_price(float close_share_price) {
        this.close_share_price = close_share_price;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return max_share_price +
                ", " + min_share_price +
                ", " + open_share_price +
                ", " + close_share_price +
                ", '" + date + '\'' +
                ", '" + time + '\'' +
                ", " + companyId;
    }
}