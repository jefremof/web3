package ru.jefremov.prog;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.ValidatorException;

import java.io.Serializable;


public class PointBean implements Serializable {
    public PointBean() {
    }
    private Double x = -1.0;
    private Double y;

    public Double getGraphX() {
        return graphX;
    }

    public void setGraphX(Double graphX) {
        if ((Double.compare(graphX,-5.0)>=0)&&(Double.compare(graphX,3.0)<=0))  this.graphX = graphX;
    }

    public Double getGraphY() {
        return graphY;
    }

    public void setGraphY(Double graphY) {
        if ((Double.compare(graphY,-5.0)>=0)&&(Double.compare(graphY,5.0)<=0))  this.graphY = graphY;
    }

    private Double graphX = null;
    private Double graphY = null;

    public boolean isGraph() {
        return graph;
    }

    public void setGraph(boolean graph) {
        this.graph = graph;
    }

    private boolean graph = false;
    private int selectedR = 1;
    private final static int firstR = 1;
    private final static int lastR = 5;

    public Double getX() {
        return x;
    }
    public void setX(Double x) {
        if ((Double.compare(x,-5.0)>=0)&&(Double.compare(x,3.0)<=0))  this.x = x;
    }
    public void incX() {
        if (Double.compare(x,2.0)<=0) x++;
    }
    public void decX() {
        if (Double.compare(x,-4.0)>=0) x--;
    }

    public void validateRSelect(FacesContext context, UIComponent component, Object value) {
        if (selectedR == -1) {
            FacesMessage message = new FacesMessage("Не выбрано");
            throw new ValidatorException(message);
        }
    }

    public void setSelectedR(int option) {
        selectedR = (selectedR==option? -1: option);
    }
    public int getSelectedR() {
        return selectedR;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        if ((Double.compare(y,-5.0)>=0)&&(Double.compare(y,5.0)<=0))  this.y = y;
    }

    public int getFirstR() {
        return firstR;
    }
    public int getLastR() {
        return lastR;
    }
    public void submit() {
        this.x = -3.0;
    }
}

