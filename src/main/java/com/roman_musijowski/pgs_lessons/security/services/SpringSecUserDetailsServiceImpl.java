package com.roman_musijowski.pgs_lessons.security.services;

import com.roman_musijowski.pgs_lessons.converters.UserToUserDetails;
import com.roman_musijowski.pgs_lessons.models.User;
import com.roman_musijowski.pgs_lessons.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class SpringSecUserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;
    private UserToUserDetails userToUserDetails;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "userToUserDetails")
    public void setUserUserDetailsConverter(UserToUserDetails userToUserDetails) {
        this.userToUserDetails = userToUserDetails;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findByUserName(username);
        if (user == null){
            throw new UsernameNotFoundException("User Name "+ username +" Not Found");
        }

        return userToUserDetails.convert(userService.findByUserName(username));
    }


}
