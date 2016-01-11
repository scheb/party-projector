package de.christianscheb.partyprojector.server.handlers;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

public class HomepageHandler extends RouterNanoHTTPD.DefaultHandler {

    @Override
    public String getText() {
        return "{\"success\":true,\"server\":\"Party Projector\"}";
    }

    @Override
    public String getMimeType() {
        return JsonResponse.JSON_MIME_TYPE;
    }

    @Override
    public NanoHTTPD.Response.IStatus getStatus() {
        return NanoHTTPD.Response.Status.OK;
    }
}