package com.chendehe;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.chendehe.service.UserService;
import com.chendehe.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Bean mockito
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RemoteServiceTest {

  @MockBean
  private UserService remoteService;

  @Autowired
  private UserService reverser;

  @Test
  public void exampleTest() {
    UserVO user = new UserVO();
    user.setId("123");
    given(this.remoteService.findOne("123")).willReturn(user);
    UserVO reverse = reverser.findOne("123");
    assertThat(reverse.getId()).isEqualTo("123");
  }

}