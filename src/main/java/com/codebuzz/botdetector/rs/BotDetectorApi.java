package com.codebuzz.botdetector.rs;

import com.codebuzz.botdetector.dto.RealPerson;
import com.codebuzz.botdetector.dto.RealPersonResponse;
import com.codebuzz.botdetector.exception.NotAHumanException;
import com.codebuzz.botdetector.svc.BotDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toMap;

@RestController
@RequestMapping("/bot")
public class BotDetectorApi {

    BotDetectorService botDetectorService;

    @Autowired
    public BotDetectorApi(BotDetectorService botDetectorService) {
        this.botDetectorService = botDetectorService;
    }

    @GetMapping(value = "/identity", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    RealPerson verifyRealPerson() {
        Random random = new Random();
        UUID id = UUID.randomUUID();
        IntStream limitedIntStreamWithinARange = random.ints(3, 1, 9);
        List<Integer> noToSum = limitedIntStreamWithinARange.boxed().collect(toCollection(ArrayList::new));
        RealPerson realPerson = new RealPerson(id.toString(), noToSum);
        botDetectorService.getClientIdentityRepo().put(realPerson.id, realPerson);
        return realPerson;
    }

    @PostMapping(value = "/prove", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity verifyRealPerson(@RequestBody RealPersonResponse realPersonResponse) {


        String authecticatedPerson = null;
        try {
            authecticatedPerson = botDetectorService.isHuman(realPersonResponse);
            return ResponseEntity.accepted().body(authecticatedPerson);
        } catch (NotAHumanException e) {


            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
