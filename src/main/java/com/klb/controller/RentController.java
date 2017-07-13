package com.klb.controller;

import com.klb.entity.Book;
import com.klb.entity.Rent;
import com.klb.entity.Role;
import com.klb.entity.User;
import com.klb.service.BookService;
import com.klb.service.RentService;
import com.klb.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;



/**
 * Created by fmkam on 08.07.2017.
 */
@Controller
public class RentController {

    @Autowired
    private RentService rentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

    //obiekt loggera
    private final static Logger logger = Logger.getLogger(RentController.class.getName()); //  tutaj zwracam nazwe klasy z ktorej nastepuje logowanie. Jest to jednakze stary sposob na logowanie


    @RequestMapping (value = "/rents", method = RequestMethod.GET)
    // bede chcial uzyskac jeszcze login zalogowanego uzytkownika - Principal klasa
    public String getRentsPage (Model model, Principal principal){
        String email = principal.getName();
        User user = userService.findByEmail(email);

        List<Rent> rents;
        if (user.getRole()== Role.CUSTOMER){
            rents = rentService.findByUserOrderByCreatedDateDesc(user);
        } else {
            rents = rentService.findAll();
        }
        model.addAttribute("rentsList", rents);

        return "rents";
    }

    //metoda dodaje wypozyczenie ksiazki o id = bookId, zalogowanego uzytkownika
    @RequestMapping (value = "/rent/book/{bookId}", method = RequestMethod.GET)
    public String createRent (@PathVariable Long bookId, Principal principal){

        logger.log(Level.INFO, String.format("Wykonywanie moteody createRent dla bookId=%d", bookId));
        //w zaleznosci od koniguracji log4j komunikat trafi w odpowiednie miejsce.
        System.out.println(String.format("Wykonywanie moteody createRent dla bookId=%d", bookId));

        String email = principal.getName();
        User user = userService.findByEmail(email);
        Book book = bookService.findById(bookId);
        rentService.createRent(user, book);

        return "redirect:/rents";
    }

    @RequestMapping (value = "/rent/return/{rentId}", method = RequestMethod.GET)
    public String returnBook (@PathVariable Long rentId, Model model){
        logger.log(Level.INFO, String.format("Wykonywanie metody returnBook dla rentID=%d",rentId));
        System.out.println(String.format("Wykonywanie metody returnBook dla rentID=%d",rentId));

        rentService.returnBook(rentService.findById(rentId));

        return "redirect:/rents";

    }

}
