package com.klb.service;

import com.klb.dao.BookDao;
import com.klb.entity.Book;
import com.klb.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fmkam on 08.07.2017.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public void delete(Long id) {
        bookDao.delete(id);
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findOne(id);
    }

}
