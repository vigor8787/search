package com.maps.search.user;

import com.maps.search.common.util.EncryptUtil;
import com.maps.search.model.user.User;
import com.maps.search.repository.user.UserRepository;
import com.maps.search.service.user.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void userValidationInRedisTest() {
        User user = new User();
        user.set_id("test1");
        user.setAge(15);
        user.setPw(EncryptUtil.encryptSHA256("test1"));

        //when
        userRepository.save(user);

        //then
        assertThat(userRepository.findBy_id("test1").get_id()).isEqualTo("test1");
    }


    @Test
    public void loginAuthTest() throws Exception {
        User user = new User();
        user.set_id("test1");
        user.setAge(15);
        user.setPw(EncryptUtil.encryptSHA256("test1"));

        userService.setUser(user);

        MvcResult mvcResult = mockMvc.perform(get("/login/auth?id=test1&pw=1b4f0e9851971998e732078544c96b36c3d01cedf7caa332359d6f1d83567014"))
        		.andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        String result = mvcResult.getResponse().getContentAsString();

        assertThat("true").isEqualTo(result);
    }
}
