package ru.jefremov.prog;


import jakarta.faces.application.FacesMessage;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.ConverterException;

@FacesConverter("ru.jefremov.prog.DoubleConverter")
public class DoubleConverter implements Converter<Double> {

    @Override
    public Double getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null) return null;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            String message_text = "Неправильный формат.";
            FacesMessage message = new FacesMessage(message_text);
            throw new ConverterException(message);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Double value) {
        if (value == null) return "";
        return value.toString();
    }
}
