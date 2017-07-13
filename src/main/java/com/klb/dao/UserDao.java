package com.klb.dao;

import com.klb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by fmkam on 08.07.2017.
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
    //zwraca dane uzytkownika na podstawie loginu(maila)
    User findByEmail (String email);
}
