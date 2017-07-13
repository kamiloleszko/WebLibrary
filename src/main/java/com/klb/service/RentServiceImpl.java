package com.klb.service;

import com.klb.dao.BookDao;
import com.klb.dao.RentDao;
import com.klb.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fmkam on 08.07.2017.
 */
@Service
public class RentServiceImpl implements RentService {

    @Autowired
    private RentDao rentDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public void createRent(User user, Book book) {
        Rent rent = new Rent(user, book);
        rentDao.save(rent);
        book.decrementAvailability();
        bookDao.save(book);
    }

    @Override
    public List<Rent> findAll() {
        return rentDao.findAll();
    }

    @Override
    public List<Rent> findByUserOrderByCreatedDateDesc(User user) {
        return rentDao.findByUserOrderByCreatedDateDesc(user);
    }

    @Override
    public void returnBook(Rent rent) {

        if (rent.getStatus() == Status.IN_PROGRESS) {
            rent.setStatus(Status.COMPLETED);
            Book book = rent.getBook();
            book.incrementAvailability();
            bookDao.save(book);
            rentDao.save(rent);
        }

    }

    @Override
    public Rent findById(Long id) {
        return rentDao.findOne(id);
    }
}
