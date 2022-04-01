package webserver.mapper;

import java.util.Map;
import java.util.Optional;
import webserver.Request;
import webserver.Response;
import webserver.handler.PathHandler;

public class PathMapper {

    private final Map<String, PathHandler> handlerMap;
    private final PathHandler defaultFileHandler;

    public PathMapper(Map<String, PathHandler> handlerMap, PathHandler defaultFileHandler) {
        this.handlerMap = handlerMap;
        this.defaultFileHandler = defaultFileHandler;
    }

    public Response callHandler(Request request) {
        PathHandler pathHandler = Optional.ofNullable(handlerMap.get(request.getPath()))
            .orElse(defaultFileHandler);

        return pathHandler.handle(request);
    }

}
