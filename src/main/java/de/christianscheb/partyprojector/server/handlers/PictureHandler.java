package de.christianscheb.partyprojector.server.handlers;

import de.christianscheb.partyprojector.model.PictureStorage;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PictureHandler extends RouterNanoHTTPD.DefaultHandler {

    PictureStorage pictureStorage;

    public PictureHandler(PictureStorage pictureStorage) {
        this.pictureStorage = pictureStorage;
    }

    @Override
    public NanoHTTPD.Response post(RouterNanoHTTPD.UriResource uriResource, Map<String, String> urlParams, NanoHTTPD.IHTTPSession session) {
        try {
            Map<String, String> files = new HashMap<>();
            session.parseBody(files);
            if (files.containsKey("picture")) {
                String location = files.get("picture");
                FileInputStream stream = new FileInputStream(location);
                pictureStorage.addImage(stream);
                stream.close();

                return NanoHTTPD.newFixedLengthResponse(NanoHTTPD.Response.Status.OK, getMimeType(), JsonResponse.SUCCESS_RESPONSE);
            }
        } catch (IOException | NanoHTTPD.ResponseException e) {
            e.printStackTrace();
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