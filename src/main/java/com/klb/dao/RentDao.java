package com.klb.dao;

import com.klb.entity.Rent;
import com.klb.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by fmkam on 08.07.2017.
 */
@Repository
public interface RentDao extends JpaRepository<Rent, Long> {

    //potrzebuje tu niestandardowej metody tez.
    //zwraca liste wypozyczen danego uzytkownika posortowna (od daty najwczesniejszej) ze wzgleda na date dodania wypozyczenia
    List<Rent> findByUserOrderByCreatedDateDesc (User user);
}
