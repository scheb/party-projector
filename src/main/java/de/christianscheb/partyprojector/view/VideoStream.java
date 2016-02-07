package de.christianscheb.partyprojector.view;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.ds.ipcam.IpCamDeviceRegistry;
import com.github.sarxos.webcam.ds.ipcam.IpCamDriver;
import com.github.sarxos.webcam.ds.ipcam.IpCamMode;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;

public class VideoStream extends Pane {

    private static final int MAX_DISCONNECT_TIME = 10;

    static {
        Webcam.setDriver(new IpCamDriver());
    }

    private ImageView cameraImageView;
    private Webcam webCam;
    private Thread updateImageThread;

    private boolean isStopped = true;
    private int disconnectedTime = 0;
    private BufferedImage grabbedImage;
    private BufferedImage bufferedImagePrevious;

    private void createImageView() {
        cameraImageView = new ImageView();
        cameraImageView.setPreserveRatio(false);
        cameraImageView.fitWidthProperty().bind(widthProperty());
        cameraImageView.fitHeightProperty().bind(heightProperty());
        getChildren().add(cameraImageView);
    }

    public void startVideoStream(String ip) {
        if(connectWebCam(ip)) {
            createImageView();
            isStopped = false;
            startGrabImage();
        }
    }

    private void startGrabImage() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                while (!isStopped) {
                    try {
                        Thread.sleep(100);
                        if (isStopped) {
                            return;
                        }

                        grabbedImage = webCam.getImage();
                        if (bufferedImagePrevious != null) {
                            if (bufferedImagePrevious == grabbedImage) {
                                disconnectedTime++;
                            } else {
                                disconnectedTime = 0;
                            }
                        }
                        if (disconnectedTime > MAX_DISCONNECT_TIME) {
                            endWebCamStream();
                            return;
                        }
                        bufferedImagePrevious = grabbedImage;

                        if (grabbedImage != null) {
                            Platform.runLater(() -> {
                                try {
                                    Image mainImage = SwingFXUtils.toFXImage(grabbedImage, null);
                                    cameraImageView.imageProperty().set(mainImage);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            });

                            grabbedImage.flush();
                        }
                    } catch (InterruptedException ignored) {
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        updateImageThread = new Thread(task);
        updateImageThread.setDaemon(true);
        updateImageThread.start();
    }

    private boolean connectWebCam(String ip) {
        try {
            IpCamDeviceRegistry.unregisterAll();
            IpCamDeviceRegistry.register(ip, "http://" + ip + ":8089", IpCamMode.PUSH);
            webCam = Webcam.getWebcams().get(0);
            webCam.open();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private void endWebCamStream() {
        isStopped = true;

        // Interrupt the thread
        if (updateImageThread != null) {
            updateImageThread.interrupt();
            updateImageThread = null;
        }

        // Stop WebCam and thread
        if (webCam != null) {
            webCam.close();
            webCam = null;
        }

        // Remove image from the view
        Platform.runLater(() -> {
            if (getChildren().size() > 0) {
                getChildren().remove(cameraImageView);
            }
        });

        // Reset status variables
        disconnectedTime = 0;
        grabbedImage = null;
        bufferedImagePrevious = null;

        System.gc();
    }

    public void stop() {
        endWebCamStream();
    }
}
