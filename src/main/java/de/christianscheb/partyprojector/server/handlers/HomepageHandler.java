package de.christianscheb.partyprojector.server.handlers;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

public class HomepageHandler extends RouterNanoHTTPD.DefaultHandler {

    @Override
    public String getText() {
        return "<h1>Party Projector Web Service</h1>";
    }

    @Override
    public String getMimeType() {
        return "text/html";
    }

    @Override
    public NanoHTTPD.Response.IStatus getStatus() {
        return NanoHTTPD.Response.Status.OK;
    }
}