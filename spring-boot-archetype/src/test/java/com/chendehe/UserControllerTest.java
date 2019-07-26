package com.chendehe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试 controller 的 Rest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void exampleTest() {
    String body = this.restTemplate.getForObject("/users/hello", String.class);
    assertThat(body).isEqualTo("Hello World");
  }

}