package com.group;

import com.common.base.domain.response.ResultCodeEnum;
import com.common.base.domain.response.ResultDO;
import com.common.enums.PatchOperateEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

@Service @Transactional public class ChatGroupServiceImpl implements IChatGroupService {

  private final ChatGroupRepository chatGroupRepository;

  public ChatGroupServiceImpl(ChatGroupRepository chatGroupRepository) {
    this.chatGroupRepository = chatGroupRepository;
  }


  @Override public ResultDO<Page<ChatGroup>> get(ChatGroupParams chatGroupParams) {
    if (StringUtils.isEmpty(chatGroupParams.getSortType()) || StringUtils
      .isEmpty(chatGroupParams.getSortKey()) || 0 == chatGroupParams.getCount()) {
      return new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ERROR, chatGroupParams.toString());
    }
    Sort.Direction direction;
    try {
      direction = Sort.Direction.fromString(chatGroupParams.getSortType());
    } catch (IllegalArgumentException e) {
      return new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ERROR, chatGroupParams.toString());
    }


    Sort sort = Sort.by(direction, chatGroupParams.getSortKey());
    Pageable pageable = PageRequest.of(chatGroupParams.getPage(), chatGroupParams.getCount(), sort);
    Page<ChatGroup> pageData =
      chatGroupRepository.findAll(getSpecification(chatGroupParams), pageable);
    return new ResultDO<>(pageData);
  }

  @Override public ResultDO<Integer> create(ChatGroup chatGroup) {
    if (null == chatGroup || StringUtils.isEmpty(chatGroup.getName()) || StringUtils
      .isEmpty(chatGroup.getTwoDimensionCode()) || StringUtils.isEmpty(chatGroup.getDescription())
      || StringUtils.isEmpty(chatGroup.getTags()) || StringUtils
      .isEmpty(chatGroup.getCreateUserAccountNumber()) || StringUtils
      .isEmpty(chatGroup.getCreateUserId()) || StringUtils.isEmpty(chatGroup.getCreateUserName())) {
      return new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ERROR);
    }

    if (0 != chatGroup.getId()) {
      return new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ILLEGAL, chatGroup.getId());
    }

    chatGroup.setStatus(ChatGroupStatusEnum.NOT_VALIDATE.getCode());

    chatGroup.setCreateTime(System.currentTimeMillis());
    chatGroup.setUpdateTime(System.currentTimeMillis());

    chatGroupRepository.save(chatGroup);

    return new ResultDO<>(chatGroup.getId());
  }

  @Override public ResultDO update(ChatGroup chatGroup) {
    if (null == chatGroup || 0 == chatGroup.getId() || StringUtils.isEmpty(chatGroup.getName())
      || StringUtils.isEmpty(chatGroup.getTwoDimensionCode()) || StringUtils
      .isEmpty(chatGroup.getDescription()) || StringUtils.isEmpty(chatGroup.getTags())
      || StringUtils.isEmpty(chatGroup.getCreateUserAccountNumber()) || StringUtils
      .isEmpty(chatGroup.getCreateUserId()) || StringUtils.isEmpty(chatGroup.getCreateUserName())) {
      return new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ERROR,
        null != chatGroup ? chatGroup.toString() : "data is null");
    }

    if (!chatGroupRepository.existsById(chatGroup.getId())) {
      return new ResultDO<>(null, ResultCodeEnum.OBJECT_NOT_EXISTS, chatGroup.getId());
    }

    chatGroup.setUpdateTime(System.currentTimeMillis());

    chatGroupRepository.save(chatGroup);

    return ResultDO.SUCCESS_NO_DATA;
  }

  @Override public ResultDO patch(String operate, ChatGroup chatGroup) {
    PatchOperateEnum patchOperateEnum = PatchOperateEnum.getEnum(operate);

    if (null == patchOperateEnum) {
      return new ResultDO<>(null, ResultCodeEnum.FAILED, operate);
    }
    if (null == chatGroup || 0 == chatGroup.getId()) {
      return new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ERROR);
    }

    int id = chatGroup.getId();
    ChatGroup oldChatGroup = chatGroupRepository.findById(id).orElse(null);
    if (null == oldChatGroup) {
      return new ResultDO<>(null, ResultCodeEnum.OBJECT_NOT_EXISTS, id);
    }

    switch (patchOperateEnum) {
      case EFFECTIVE:
        if (ChatGroupStatusEnum.VALIDATE.getCode() != oldChatGroup.getStatus()
          && 1 != chatGroupRepository.updateByStatus(id, ChatGroupStatusEnum.VALIDATE.getCode())) {
          return new ResultDO<>(null, ResultCodeEnum.FAILED, id + ":" + operate);
        }
        break;

      case INEFFECTIVE:
        if (ChatGroupStatusEnum.NOT_VALIDATE.getCode() != oldChatGroup.getStatus()
          && 1 != chatGroupRepository
          .updateByStatus(id, ChatGroupStatusEnum.NOT_VALIDATE.getCode())) {
          return new ResultDO<>(null, ResultCodeEnum.FAILED, id + ":" + operate);
        }
        break;

      default:
        break;
    }

    return ResultDO.SUCCESS_NO_DATA;
  }

  @Override public ResultDO delete(String id) {
    int iid;
    try {
      iid = Integer.parseInt(id);
    } catch (NumberFormatException e) {
      return new ResultDO<>(null, ResultCodeEnum.INPUT_VALUE_ILLEGAL, id);
    }

    if (!chatGroupRepository.existsById(iid)) {
      return new ResultDO<>(null, ResultCodeEnum.OBJECT_NOT_EXISTS, id);
    }

    chatGroupRepository.deleteById(iid);

    return ResultDO.SUCCESS_NO_DATA;
  }

  private Specification<ChatGroup> getSpecification(ChatGroupParams chatGroupParams) {
    return (Specification<ChatGroup>) (root, query, criteriaBuilder) -> {
      Predicate predicate = null, temp;
      if (!StringUtils.isEmpty(chatGroupParams.getCreateUserId()))
        predicate =
          criteriaBuilder.equal(root.get("createUserId"), chatGroupParams.getCreateUserId());

      if (0 != chatGroupParams.getId()) {
        temp = criteriaBuilder.equal(root.get("id"), chatGroupParams.getId());
        predicate = null != predicate ? criteriaBuilder.and(predicate, temp) : temp;
      }

      if (!StringUtils.isEmpty(chatGroupParams.getName())) {
        temp = criteriaBuilder.like(root.get("name"), "%" + chatGroupParams.getName() + "%");
        predicate = null != predicate ? criteriaBuilder.and(predicate, temp) : temp;
      }

      if (!StringUtils.isEmpty(chatGroupParams.getTags())) {
        temp = criteriaBuilder.like(root.get("tags"), "%" + chatGroupParams.getTags() + "%");
        predicate = null != predicate ? criteriaBuilder.and(predicate, temp) : temp;
      }

      if (!StringUtils.isEmpty(chatGroupParams.getPosition())) {
        temp =
          criteriaBuilder.like(root.get("position"), "%" + chatGroupParams.getPosition() + "%");
        predicate = null != predicate ? criteriaBuilder.and(predicate, temp) : temp;
      }

      if (!StringUtils.isEmpty(chatGroupParams.getDescription())) {
        temp = criteriaBuilder
          .like(root.get("description"), "%" + chatGroupParams.getDescription() + "%");
        predicate = null != predicate ? criteriaBuilder.and(predicate, temp) : temp;
      }

      if (0 != chatGroupParams.getStatus()) {
        temp = criteriaBuilder.equal(root.get("status"), chatGroupParams.getStatus());
        predicate = null != predicate ? criteriaBuilder.and(predicate, temp) : temp;
      }

      if (0 < chatGroupParams.getStartTimestamp()
        && chatGroupParams.getEndTimestamp() > chatGroupParams.getStartTimestamp()) {
        temp = criteriaBuilder.between(root.get("createTime"), chatGroupParams.getStartTimestamp(),
          chatGroupParams.getEndTimestamp());
        predicate = null != predicate ? criteriaBuilder.and(predicate, temp) : temp;
      }

      return predicate;
    };
  }
}
