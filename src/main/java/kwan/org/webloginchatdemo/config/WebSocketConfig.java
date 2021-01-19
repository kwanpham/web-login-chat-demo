package kwan.org.webloginchatdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

import java.util.Map;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry
                                                  webSocketHandlerRegistry) {
//        webSocketHandlerRegistry.addHandler(socketHandler,
//                "/server1").withSockJS();
        webSocketHandlerRegistry
                .addHandler(socketHandler, "/server1")
                .addInterceptors(new HttpSessionHandshakeInterceptor() {
                    @Override
                    public void afterHandshake(ServerHttpRequest request,
                                               ServerHttpResponse response, WebSocketHandler wsHandler,
                                               @Nullable Exception ex) {

                        super.afterHandshake(request, response, wsHandler, ex);

                    }

                    @Override
                    public boolean beforeHandshake(ServerHttpRequest request,
                                                   ServerHttpResponse response, WebSocketHandler wsHandler,
                                                   Map<String, Object> attributes) throws Exception {
                        boolean b;
                        try {
                            b = super.beforeHandshake(request, response,
                                    wsHandler, attributes) &&
                                    ((UsernamePasswordAuthenticationToken)
                                            request.getPrincipal()).isAuthenticated();
                        } catch (Exception e) {

                            b = false;
                        }
                        return b;
                    }
                })
                .setAllowedOrigins("*");

    }

    @Autowired
    SocketHandler socketHandler;

}