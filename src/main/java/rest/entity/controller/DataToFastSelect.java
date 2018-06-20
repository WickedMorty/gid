package rest.entity.controller;

public class DataToFastSelect {
    private String text;
    private String value;

    public DataToFastSelect() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public DataToFastSelect(String text, String value) {
        this.text = text;
        this.value = value;
    }
}
