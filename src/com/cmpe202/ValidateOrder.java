package com.cmpe202;

public class ValidateOrder implements Handler{
    private Handler next;
    @Override
    public void handleRequest(String order) {
        if (order.contains("validateorder")) {
            System.out.println("Order is validated");
        }
            else {
                next.handleRequest(order);
            }
    }


    @Override
    public void setNext(Handler handler, Handler nextHandler) {
        this.next=nextHandler;

    }
}
