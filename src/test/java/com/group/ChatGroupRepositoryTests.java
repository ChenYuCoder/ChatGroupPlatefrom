package com.group;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class ChatGroupRepositoryTests {
  @Autowired
  private TestEntityManager testEntityManager;

  @Autowired
  private ChatGroupRepository chatGroupRepository;

  @Test
  public void testFindById() {
    ChatGroup chatGroup = new ChatGroup();
    chatGroup.setId(1);
    chatGroup.setName("test");


//    testEntityManager.persist(chatGroup);
//
//    Optional<ChatGroup> mailAlarmOptional = chatGroupRepository.findById(chatGroup.getId());
//    assertThat(mailAlarmOptional.isPresent()).isTrue();
//    assertThat(mailAlarmOptional.get().getName()).isEqualTo("test");
  }
}
