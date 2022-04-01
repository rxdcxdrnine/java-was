package webserver.mapper;

import java.util.HashMap;
import java.util.Map;
import webserver.handler.ArticleCreateHandler;
import webserver.handler.ArticleListHandler;
import webserver.handler.DefaultFileHandler;
import webserver.handler.PathHandler;
import webserver.handler.UserCreateHandler;
import webserver.handler.UserListHandler;
import webserver.handler.UserLoginHandler;
import webserver.handler.UserLogoutHandler;

public class PathMapperFactoryImpl implements PathMapperFactory {

    @Override
    public PathMapper create() {
        Map<String, PathHandler> handlerMap = new HashMap<>() {{
            // TODO: method 제거하기
            put("/user/create", new UserCreateHandler());
            put("/user/login", new UserLoginHandler());
            put("/user/logout", new UserLogoutHandler());
            put("/user/list", new UserListHandler());
            put("/article", new ArticleCreateHandler());
            put("/", new ArticleListHandler());
        }};
        return new PathMapper(handlerMap, new DefaultFileHandler());
    }
}
