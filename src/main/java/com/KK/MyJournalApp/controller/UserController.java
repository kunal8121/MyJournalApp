package com.KK.MyJournalApp.controller;

import com.KK.MyJournalApp.ApiResponse.WeatherResponse;
import com.KK.MyJournalApp.Entity.User;
import com.KK.MyJournalApp.repository.UserRepository;
import com.KK.MyJournalApp.service.UserService;
import com.KK.MyJournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;


    @PutMapping
    public ResponseEntity<?> deleteByUserName(@RequestBody User user)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        User userInDb=userService.findByUserName(username) ;

            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> updateById(@RequestBody User user)
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        userRepository.deleteByUserName(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping
    public ResponseEntity<?> greetings()
    {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        WeatherResponse weatherResponse=weatherService.getWeather("Mumbai");
        String greeting="";
        if(weatherResponse!=null) {
            greeting = "Weather feels Like " + weatherResponse.getCurrent().getFeelslike();
        }
            return new ResponseEntity<>("Hii" + username +greeting , HttpStatus.OK);

    }


}
