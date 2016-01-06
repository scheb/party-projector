package de.christianscheb.partyprojector.server;

import fi.iki.elonen.NanoHTTPD;

public class LocalTempFileManagerFactory implements NanoHTTPD.TempFileManagerFactory {

    @Override
    public NanoHTTPD.TempFileManager create() {
        return new LocalTempFileManager();
    }
}
