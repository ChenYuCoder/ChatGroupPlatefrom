package com.group;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 聊天群路由注册
 */
@Configuration public class ChatGroupRouter {
  private static final String baseUrl = "/chat/group";

  @Bean public RouterFunction<ServerResponse> chatGroupRoutes(ChatGroupHandle chatGroupHandle) {
    return RouterFunctions.route(
      RequestPredicates.GET(baseUrl).and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
      chatGroupHandle::get).andRoute(RequestPredicates.POST(baseUrl)
      .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), chatGroupHandle::create)
      .andRoute(RequestPredicates.PUT(baseUrl)
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), chatGroupHandle::update)
      .andRoute(RequestPredicates.PATCH(baseUrl + "/{operate}")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), chatGroupHandle::patch)
      .andRoute(RequestPredicates.DELETE(baseUrl + "/{id}")
        .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)), chatGroupHandle::delete);
  }
}
