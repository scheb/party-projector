package de.christianscheb.partyprojector.server;

import java.io.IOException;

import de.christianscheb.partyprojector.model.MessageStorage;
import de.christianscheb.partyprojector.model.PictureStorage;
import de.christianscheb.partyprojector.server.handlers.HomepageHandler;
import de.christianscheb.partyprojector.server.handlers.MessageHandler;
import de.christianscheb.partyprojector.server.handlers.PictureHandler;
import fi.iki.elonen.NanoHTTPD;
import fi.iki.elonen.router.RouterNanoHTTPD;

public class WebServer extends RouterNanoHTTPD {

    public WebServer(int port, MessageStorage messageStorage, PictureStorage pictureStorage) throws IOException {
        super(port);
        setTempFileManagerFactory(new LocalTempFileManagerFactory());
        addRoute("/", new HomepageHandler());
        addRoute("/message", new MessageHandler(messageStorage));
        addRoute("/picture", new PictureHandler(pictureStorage));
        start(NanoHTTPD.SOCKET_READ_TIMEOUT, true);
    }
}
