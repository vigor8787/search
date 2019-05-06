package com.maps.search.service.user;

import com.maps.search.common.util.EncryptUtil;
import com.maps.search.model.user.User;
import com.maps.search.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void setUser(User user) {
        userRepository.save(user);
    }

    public User getUser(String id) {
        return userRepository.findBy_id(id);
    }

    /**
     * 테스트 유저 계정 생성
     * (ID:test1)(PW:SHA256(test1)) ~ (ID:test10)(PW:SHA256(test10))
     * */
    @PostConstruct
    private void dummyData () {
        for(int i = 1 ; i <= 10 ; i++) {
            String id = "test" + i;
            String pw = EncryptUtil.encryptSHA256(id);
            User user = new User();
            user.set_id(id);
            user.setPw(pw);
            user.setAge(i);
            userRepository.save(user);
        }
    }
}
