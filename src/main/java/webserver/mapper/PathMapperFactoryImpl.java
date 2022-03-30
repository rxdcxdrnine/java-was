package webserver.mapper;

import java.util.HashMap;
import java.util.Map;

import webserver.handler.*;

public class PathMapperFactoryImpl implements PathMapperFactory {

    @Override
    public PathMapper create() {
        Map<Pair, PathHandler> handlerMap = new HashMap<>() {{
            put(new Pair("POST", "/user/create"), new UserCreateHandler());
            put(new Pair("POST", "/user/login"), new UserLoginHandler());
            put(new Pair("GET", "/user/logout"), new UserLogoutHandler());
            put(new Pair("GET", "/user/list"), new UserListHandler());
        }};
        return new PathMapper(handlerMap, new DefaultFileHandler());
    }
}
