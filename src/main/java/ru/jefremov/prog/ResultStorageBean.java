package ru.jefremov.prog;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Map;
import jakarta.faces.context.FacesContext;

public class ResultStorageBean implements Serializable{
    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    private String json = "";

    private Double last_graphx = -10d;
    private Double last_graphy = -10d;
    private int last_graphr = -1;

    public ResultStorageBean() {
        results = new LinkedList<>();
        try {
            DAOFactory.getInstance().getResultDAO().getAllResults();
        } catch (SQLException ignored) {}
    }

    public LinkedList<ResultBean> getResults() {
        return results;
    }

    public void setResults(LinkedList<ResultBean> results) {
        this.results = results;
    }
    private LinkedList<ResultBean> results;
    public void submitPoint() {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        PointBean point = (PointBean) viewMap.get("pointBean");
        if (point!=null) {
            ResultBean result = new ResultBean();
            Double x = Math.round(point.getX() * 100.0) / 100.0;
            Double y = Math.round(point.getY() * 100.0) / 100.0;
            int selectedR = point.getSelectedR();
            if (selectedR==-1) return;
            result.setX(x);
            result.setY(y);
            point.setGraph(false);
            result.setR(selectedR);
            result.setVerdict(true);
            addResult(result);
        }
    }
    public String submitGraph() {
        Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();
        PointBean point = (PointBean) viewMap.get("pointBean");
        if (point!=null) {
            ResultBean result = new ResultBean();
            Double graphX = point.getGraphX();
            Double graphY = point.getGraphY();
            boolean graph = point.isGraph();
            int selectedR = point.getSelectedR();
            if (graph && selectedR != -1 && graphX != null && graphY != null) {
                graphX = Math.round(graphX * 100.0) / 100.0;
                graphY = Math.round(graphY * 100.0) / 100.0;
                result.setX(graphX);
                result.setY(graphY);
                result.setR(selectedR);
                if (graphX.equals(last_graphx)
                        && graphY.equals(last_graphy) && selectedR==last_graphr) {
                    return "";
                }
                last_graphx = graphX;
                last_graphy = graphY;
                last_graphr = selectedR;
                result.setVerdict(false);
                addResult(result);
            } else {
                last_graphx = -10d;
                last_graphy = -10d;
                last_graphr = -1;
            }
        }
        return "";
    }

    public void insertResult(ResultBean result) {
        try {
            DAOFactory.getInstance().getResultDAO().addNewResult(result);
        } catch (SQLException ignored) {}
    }

    public void addResult(ResultBean result) {
        results.addFirst(result);
        insertResult(result);
        if (json.isEmpty()) {
            json = result.toJSON();
        } else {
            json = json.concat(",").concat(result.toJSON());
        }
    }

    public void clear() {
        try {
            DAOFactory.getInstance().getResultDAO().clearResults();
        } catch (SQLException ignored) {}
        results.clear();
        json = "";
    }
}
