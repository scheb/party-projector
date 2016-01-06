package de.christianscheb.partyprojector.server;

import fi.iki.elonen.NanoHTTPD;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LocalTempFileManager implements NanoHTTPD.TempFileManager {

    private final File tmpdir;

    private final List<NanoHTTPD.TempFile> tempFiles;

    public LocalTempFileManager() {
        this.tmpdir = new File(System.getProperty("user.dir") + "/pictures");
        if (!tmpdir.exists()) {
            tmpdir.mkdirs();
        }
        this.tempFiles = new ArrayList<>();
    }

    @Override
    public void clear() {
        for (NanoHTTPD.TempFile file : this.tempFiles) {
            try {
                file.delete();
            } catch (Exception ignored) {
                System.err.println("Could not delete file.");
            }
        }
        this.tempFiles.clear();
    }

    @Override
    public NanoHTTPD.TempFile createTempFile(String filenameHint) throws Exception {
        NanoHTTPD.DefaultTempFile tempFile = new NanoHTTPD.DefaultTempFile(this.tmpdir);
        if (filenameHint == null) {
            this.tempFiles.add(tempFile);
        }
        return tempFile;
    }
}