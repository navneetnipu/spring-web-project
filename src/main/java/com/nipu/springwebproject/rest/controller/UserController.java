package com.nipu.springwebproject.rest.controller;

import com.nipu.springwebproject.model.User;
import com.nipu.springwebproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/")
    public ResponseEntity<List<User>> index(){
        return new ResponseEntity<List<User>>(userService.findAll(), HttpStatus.OK);
    }
    @RequestMapping("/user/{age}")
    public ResponseEntity<List<User>> findUserByAge(@PathVariable("age") Long age){
        return new ResponseEntity<List<User>>(userService.findByAge(age), HttpStatus.OK);
    }
    @RequestMapping(value = "/user/add/{name}/{age}",method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@PathVariable("name") String name,@PathVariable("age") Long age){
        User user=new User();
        user.setName(name);
        user.setAge(age);
        user=userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
    @RequestMapping(value = "/user/add",method = RequestMethod.POST)
    public ResponseEntity<User> addUser(@RequestBody User user){
        user=userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/update/{id}/{name}/{age}",method = RequestMethod.POST)
    public ResponseEntity<User> updateUser(@PathVariable("id") Long id,@PathVariable("name") String name,@PathVariable("age") Long age){
        Optional<User> optionalUser=userService.findById(id);
        if(optionalUser.isPresent()){
            User user=optionalUser.get();
            user.setName(name);
            user.setAge(age);
            user=userService.save(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }else{
            return new ResponseEntity<User>(new User(), HttpStatus.NOT_FOUND);
        }
    }
    @RequestMapping(value = "/user/delete",method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestParam("id") Long id){
        Optional<User> optionalUser=userService.findById(id);
        if(optionalUser.isPresent()) {
            User user=optionalUser.get();
            userService.delete(user);
            return new ResponseEntity<String>("user with id:"+user.getId()+" deleted", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<String>("", HttpStatus.NOT_FOUND);
    }
}
