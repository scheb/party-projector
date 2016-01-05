package de.christianscheb.partyprojector.server.handlers;

import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

public class PictureHandler extends RouterNanoHTTPD.DefaultHandler {

    @Override
    public String getText() {
        return JsonResponse.FAILURE_RESPONSE;
    }

    @Override
    public String getMimeType() {
        return JsonResponse.JSON_MIME_TYPE;
    }

    @Override
    public NanoHTTPD.Response.IStatus getStatus() {
        return NanoHTTPD.Response.Status.NOT_ACCEPTABLE;
    }
}