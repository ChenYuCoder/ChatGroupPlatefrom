package com.group;

import com.common.base.IBaseHandler;
import com.common.base.domain.response.ResultCodeEnum;
import com.common.base.domain.response.ResultDO;
import com.common.util.ConverterUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component public class ChatGroupHandle implements IBaseHandler {

  private final IChatGroupService chatGroupService;

  public ChatGroupHandle(IChatGroupService chatGroupService) {
    this.chatGroupService = chatGroupService;
  }

  /**
   * 获取群组
   *
   * @param serverRequest 请求对象
   * @return 群组对象
   */
  Mono<ServerResponse> get(ServerRequest serverRequest) {
    ChatGroupParams chatGroupParams =
      ConverterUtil.mapToObject(serverRequest.queryParams(), ChatGroupParams.class);
    return ServerResponse.ok()
      .body(Mono.just(chatGroupService.get(chatGroupParams)), ResultDO.class);
  }

  /**
   * 创建聊天群组
   *
   * @param serverRequest 请求对象
   * @return 执行结果
   */
  Mono<ServerResponse> create(ServerRequest serverRequest) {
    Mono<ChatGroup> chatGroupMono = serverRequest.bodyToMono(ChatGroup.class);
    return chatGroupMono.flatMap(chatGroup -> Mono.just(chatGroupService.create(chatGroup)))
      .flatMap(resultDO -> ServerResponse.ok().body(Mono.just(resultDO), ResultDO.class))
      .switchIfEmpty(ServerResponse.ok()
        .body(Mono.just(new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ERROR)), ResultDO.class));
  }

  /**
   * 更新聊天群组
   *
   * @param serverRequest 请求对象
   * @return 执行结果
   */
  Mono<ServerResponse> update(ServerRequest serverRequest) {
    Mono<ChatGroup> chatGroupMono = serverRequest.bodyToMono(ChatGroup.class);
    return chatGroupMono.flatMap(chatGroup -> Mono.just(chatGroupService.update(chatGroup)))
      .flatMap(resultDO -> ServerResponse.ok().body(Mono.just(resultDO), ResultDO.class))
      .switchIfEmpty(ServerResponse.ok()
        .body(Mono.just(new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ERROR)), ResultDO.class));
  }

  /**
   * 局部更新聊天群组
   *
   * @param serverRequest 请求对象
   * @return 执行结果
   */
  Mono<ServerResponse> patch(ServerRequest serverRequest) {
    Mono<ChatGroup> chatGroupMono = serverRequest.bodyToMono(ChatGroup.class);
    return chatGroupMono.flatMap(chatGroup -> Mono
      .just(chatGroupService.patch(serverRequest.pathVariable("operate"), chatGroup))
      .flatMap(resultDo -> ServerResponse.ok().body(Mono.just(resultDo), ResultDO.class))
      .switchIfEmpty(ServerResponse.ok()
        .body(Mono.just(new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ERROR)), ResultDO.class)));
  }

  /**
   * 删除聊天群组
   *
   * @param serverRequest 请求对象
   * @return 执行结果
   */
  Mono<ServerResponse> delete(ServerRequest serverRequest) {
    return ServerResponse.ok()
      .body(Mono.just(chatGroupService.delete(serverRequest.pathVariable("id"))), ResultDO.class);
  }
}
