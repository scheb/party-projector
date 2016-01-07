package de.christianscheb.partyprojector.server.handlers;

import de.christianscheb.partyprojector.model.MessageStorage;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MessageHandler extends RouterNanoHTTPD.DefaultHandler {

    private MessageStorage messageStorage;

    public MessageHandler(MessageStorage messageStorage) {
        this.messageStorage = messageStorage;
    }

    @Override
    public NanoHTTPD.Response get(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        try {
            session.parseBody(new HashMap<>());
            Map<String, String> queryParams = session.getParms();
            if (queryParams.containsKey("message")) {
                String message = queryParams.get("message");
                if (message.length() > 0) {
                    messageStorage.addMessage(message);
                    return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, getMimeType(), JsonResponse.SUCCESS_RESPONSE);
                }
            }
        } catch (IOException ioe) {
            return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.INTERNAL_ERROR, "text/plain", "SERVER INTERNAL ERROR: IOException: " + ioe.getMessage());
        } catch (NanoHTTPD.ResponseException re) {
            return NanoHTTPD.newFixedLengthResponse(re.getStatus(), "text/plain", re.getMessage());
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