package de.christianscheb.partyprojector.model;

public class StreamModel {

    private String streamIp;

    public boolean canStream() {
        return streamIp == null;
    }

    public String getStreamIp() {
        return streamIp;
    }

    public void startStream(String ip) throws Exception {
        if (streamIp != null) {
            throw new Exception("Cannot stream while other stream is running.");
        }

        streamIp = ip;
    }

    public void releaseStream() {

    }
}
