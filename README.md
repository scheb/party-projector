scheb/party-projector
=====================

Display messages and pictures from party guests on a projector screen.

Except for the message ticker the screen is transparent, so you can run videos/animations in the background.

Related Android app: [scheb/party-projector-app](https://github.com/scheb/party-projector-app)

## Web API

The applications runs a web server (default port 8080, can be configured in settings.ini), which provides a
simple API for sending content to the projector.

The following examples assume that the web server is running on localhost:8080

### Messages

Send a POST request to: http://localhost:8080/message

Post body must contain a field "message" with the message text to be displayed on the screen.

### Pictures

Send a POST request to: http://localhost:8080/picture

The multipart post body must contain file in the "picture" field, which is intended to be an image (JPG/PNG).
