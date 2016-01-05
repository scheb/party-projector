package de.christianscheb.partyprojector.server;

import java.io.IOException;

import de.christianscheb.partyprojector.model.MessageStorage;
import de.christianscheb.partyprojector.server.handlers.HomepageHandler;
import de.christianscheb.partyprojector.server.handlers.MessageHandler;
import de.christianscheb.partyprojector.server.handlers.PictureHandler;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

public class WebServer extends RouterNanoHTTPD {

    private static final int PORT = 8080;

    public WebServer(MessageStorage messageStorage) throws IOException {
        super(PORT);
        addRoute("/", new HomepageHandler());
        addRoute("/message", new MessageHandler(messageStorage));
        addRoute("/picture", new PictureHandler());
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, true);
    }
}
