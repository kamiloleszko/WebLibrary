package com.klb.controller;

import com.klb.entity.Book;
import com.klb.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by fmkam on 08.07.2017.
 */
@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String getBooksPage (Model model){
        List<Book> books = bookService.findAll();
        model.addAttribute("bookList",books); //dodaje do modelu liste ksiazek

        return "books"; //bedzie to przekierowanie na strone "..books.jsp"
    }

    //wiaze model z kontrolkami ksiazki na danej stronie. Przeierwuje na strone do edycji nowej ksiazki
    @RequestMapping(value = "/book/create", method = RequestMethod.GET)
    public String getCreateBookForm (Model model){
        model.addAttribute("book",new Book());

        return "book-create";
    }

    @RequestMapping(value = "/book/save", method = RequestMethod.POST)
    //w tym br bedzie informacja jesli uzytkownik nie dostosowal sie do wymagan, np nazwa ksiazki jest za dluga.
    public String postCreateBook (@ModelAttribute @Valid Book book, BindingResult br){

        if (br.hasErrors()){
            return "book-create";
        }

        bookService.save(book);
        //jesli zmieniamy cos w bazie danych to robimy z redirect:/
        return "redirect:/books";
    }

    @RequestMapping(value = "/book/delete/{id}", method = RequestMethod.POST)
    public String deleteBook (@PathVariable Long id){
        bookService.delete(id);

        return "redirect:/books";
    }

    //metoda przekieruje na strone do edyji ksiazki
    @RequestMapping(value = "/book/edit/{id}", method = RequestMethod.GET)
    public String getEditBookForm (@PathVariable Long id, Model model ){
        Book book = bookService.findById(id);
        model.addAttribute("book", book);

        return "book-create";
    }

}
