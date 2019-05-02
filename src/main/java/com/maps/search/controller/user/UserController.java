package com.maps.search.controller.user;

import com.maps.search.model.user.User;
import com.maps.search.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/login/auth")
    public ResponseEntity<?> login(@RequestParam("id") String id, @RequestParam("pw") String pw) {
        boolean isAuthorized = false;
        User user = userService.getUser(id);
        if(user == null) {
            return new ResponseEntity<>(isAuthorized, HttpStatus.OK);
        } else {
            if(!user.getPw().equals(pw)) {
                return new ResponseEntity<>(isAuthorized, HttpStatus.OK);
            }
        }
        isAuthorized = true;
        return new ResponseEntity<>(isAuthorized, HttpStatus.OK);
    }
}
