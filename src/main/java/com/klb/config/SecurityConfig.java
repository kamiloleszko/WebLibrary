package com.klb.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.klb.service.UserService;

//konfiguracja pod katem SpringSecurity
@Configuration
@EnableWebSecurity //domysla konfguracja zwiazana z bezpieczestwem dla aplikacji biznesowej
//musi byc to dziedziczenie tutuaj
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //tutaj jest okreslona sila kodowania hasla
    public static final int PASSWORD_STRENGHT = 10;

    //klasa zwiazana z uzytkownikiem
    @Autowired
    private UserService userService;

    //tutaj jest ze podczas calej autoryzacji bedizie wykorzystywane szyfrowanie hasla
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder(PASSWORD_STRENGHT)); //tutaj robie anonimowy obiekt do szyfrowania

    }

    //!!!! dodac z ponizszego przykladu braki
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()        // konfiguracja tego, ktore requesty maja byc autoryzowane a ktore nie.
                // To zwraca obiekt dla ktorego uruchamia sie metode antMatchers ktora okresa co jest a co niejest dozwolone dla okreslonego obiektu
                .antMatchers("/login").permitAll() // logowanie dostepne dla kazdego
                .antMatchers("/register").permitAll() // rejestracja dostepna dla kazdego
                .antMatchers("/resources/**").permitAll() // jak sie juz zarejestruje to do zasobow maja wszyscy dostep
                .antMatchers("/users/**", "/create-user").hasRole("ADMIN") // nowego uzytkownika moze zrobic tylko admin
                .antMatchers("/admin/**").hasRole("ADMIN") // jakies operacje dla admina
                .antMatchers("/api/**").permitAll() // wyswietlanie stron
                .antMatchers("/**").authenticated() //wymagane jest zalogowanie dla calej reszty
                .and()
                .formLogin()                // konfigaracja formularza logowania
                .usernameParameter("email") //pod jaka nazwa bedize sie kryl email
                .passwordParameter("password")
                .loginPage("/login")
                //!!!!!!!!!!!!!!!!!!!!!!!!
                .loginProcessingUrl("/login")        // adres na ktory wysyalmy post z formularza logowania
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")  //?logout to info (parametr) ze sie wylogowano
                .and()
                .csrf().disable();             // cross site request forgery - nie zapobiegamy


        //csrf- metoda ataku korzystajaca ze uzytkownik jest zalogowany
        //atakujacy podsyla mu link
    }
}