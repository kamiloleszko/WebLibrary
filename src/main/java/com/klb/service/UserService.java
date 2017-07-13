package com.klb.service;

import com.klb.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by fmkam on 08.07.2017.
 */
//rozszerzam interfejs poniewaz chodzi tu o zaezpieczenia springsecurity i to rozroznienie user/admin
public interface UserService extends UserDetailsService{

    void save (User user);
    User findByEmail (String email);
    List<User> findAll ();
    void deleteUser (Long id);
    User findById (Long id);

}
