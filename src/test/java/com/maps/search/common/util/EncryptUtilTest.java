package com.maps.search.common.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EncryptUtilTest {
    @Test
    public void encryptSHA256Test() {
        String test1_pw = "1b4f0e9851971998e732078544c96b36c3d01cedf7caa332359d6f1d83567014";
        String pw = EncryptUtil.encryptSHA256("test1");
        assertThat(test1_pw).isEqualTo(pw);
    }
}
