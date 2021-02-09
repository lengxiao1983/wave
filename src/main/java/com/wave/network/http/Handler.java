package com.wave.network.http;

public interface Handler {
    public void service(Request request, Response response);

    public void doGet(Request request, Response response);

    public void doPost(Request request, Response response);

}
