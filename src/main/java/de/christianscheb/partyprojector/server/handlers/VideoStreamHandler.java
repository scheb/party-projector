package de.christianscheb.partyprojector.server.handlers;

import de.christianscheb.partyprojector.model.StreamModel;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

import java.util.Map;

public class VideoStreamHandler extends RouterNanoHTTPD.DefaultHandler {

    private StreamModel streamModel;

    public VideoStreamHandler(StreamModel streamModel) {
        this.streamModel = streamModel;
    }

    @Override
    public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        String clientIp = session.getHeaders().get("remote-addr");
        if (streamModel.canStream()) {
            try {
                streamModel.startStream(clientIp);
                return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, getMimeType(), JsonResponse.SUCCESS_RESPONSE);
            } catch (Exception ignored) {
            }
        }

        return NanoHTTPD.newFixedLengthResponse(getStatus(), getMimeType(), getText());
    }

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