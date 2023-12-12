package kq.practice.workshop18.model;

public class Request {

    private String currTo;
    private String currFrom;
    private Double amount;

    public String getCurrTo() {
        return currTo;
    }

    public void setCurrTo(String currTo) {
        this.currTo = currTo;
    }

    public String getCurrFrom() {
        return currFrom;
    }

    public void setCurrFrom(String currFrom) {
        this.currFrom = currFrom;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Request() {
    }

    public Request(String currTo, String currFrom, Double amount) {
        this.currTo = currTo;
        this.currFrom = currFrom;
        this.amount = amount;
    }
}
