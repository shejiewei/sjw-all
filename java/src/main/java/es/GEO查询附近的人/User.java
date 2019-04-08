package es.GEO查询附近的人;

/**
 * Created by shejiewei on 2019/4/8.
 */
public class User {


    private int i;
    private String name;
    private  double dlat;
    private  double dlon;

    public User(int i, String s, Double dlat, Double dlon) {
        this.i=i;
        this.name=s;
        this.dlat=dlat;
        this.dlon=dlon;
    }


    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDlat() {
        return dlat;
    }

    public void setDlat(double dlat) {
        this.dlat = dlat;
    }

    public double getDlon() {
        return dlon;
    }

    public void setDlon(double dlon) {
        this.dlon = dlon;
    }
}
