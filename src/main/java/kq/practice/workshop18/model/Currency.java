package kq.practice.workshop18.model;

public class Currency {

    private String id;
    private String name;
    private String symbol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Currency() {
    }

    public Currency(String id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return ">> Currency: %s - %s (%s)".formatted(id, name, symbol);
    }
}
