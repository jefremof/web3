package ru.jefremov.prog;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "points", schema = "s368164")
public class ResultBean implements Serializable {
    @Column(name = "x")
    private Double x;
    @Column(name = "y")
    private Double y;
    @Column(name = "r")
    private int r;
    @Column(name = "verdict")
    private boolean verdict;

    @Id
    @GeneratedValue(generator = "increment")
    @Column(name = "id")
    private Long id;

    public ResultBean() {
    }

    @Column(name = "x")
    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    @Column(name = "y")
    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    @Column(name = "r")
    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    @Column(name = "verdict")
    public boolean isVerdict() {
        return verdict;
    }

    public void setVerdict(boolean verdict) {
        this.verdict = false;
        if (y==null || x==null || r==-1) return;
        if (Double.compare(x,0)>=0) {
            if (Double.compare(y,0)>=0) {
                if ((Double.compare(x,r)<=0)&&(Double.compare(2*y,r)<=0)) this.verdict = true;
            } else {
                if (Double.compare(Math.pow(x,2)+Math.pow(y,2),r*r)<=0) this.verdict = true;
            }
        } else if (Double.compare(y,0)>=0) {
            if (Double.compare(y - x, r) <= 0) this.verdict = true;
        }
    }

    public String toJSON() {
        return "{\"x\":" +x+ ", \"y\":" +y+ ",\"r\":" +r+ ",\"v\":" +verdict+'}';
    }

    @Override
    public String toString() {
        return "ResultBean{" + "x=" + x + ", y=" + y + ", r=" + r + ", verdict=" + verdict + '}';
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "id")
    public Long getId() {
        return id;
    }
}
