package edu.eci.arsw.controller;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import edu.eci.arsw.model.*;

@RestController
public class EntryController {

    private final AtomicLong counter = new AtomicLong();
    private List<Entry> entries = new ArrayList<>();
    {
        entries.add(new Entry("Title0","Content0"));
        entries.add(new Entry("Title1","Content1"));
    }

    @RequestMapping(method = RequestMethod.GET,value = "/blogs")
    public List<Entry> getEntries() {
        return entries;
    }

    @RequestMapping(method = RequestMethod.POST,value = "/blog")
    public  ResponseEntity<?>  postEntry(@RequestBody Entry p) {
        entries.add(p);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(method = RequestMethod.POST, value="/del")
    public  ResponseEntity<?>  postDeletion(@RequestBody int a) {
        entries.remove(a);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    
}