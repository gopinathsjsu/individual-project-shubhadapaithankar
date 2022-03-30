package com.cmpe202;

public interface Handler {
    public void handleRequest(String order);
    public void setNext(Handler handler, Handler nextHandler);
}
