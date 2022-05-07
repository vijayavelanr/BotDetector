package com.codebuzz.botdetector.svc;

import com.codebuzz.botdetector.dto.Challenge;
import com.codebuzz.botdetector.dto.ChallengeResponse;
import com.codebuzz.botdetector.exception.NotAHumanException;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

class BotDetectorServiceTest {

    @Test
    void getClientIdentityRepo() {
        BotDetectorService svc = new BotDetectorService();
        Assert.notNull(svc.getClientIdentityRepo(), "repo can't be null");
    }

    @Test
    void isHuman() throws NotAHumanException {
        String id = "9c59570b-8b8b-46ce-8825-77289a378ba3";
        Challenge realPerson = new Challenge(id, "Please sum the numbers 3,6,4");
        BotDetectorService svc = new BotDetectorService();
        svc.getClientIdentityRepo().put(id, realPerson);

        ChallengeResponse realPersonResponse = new ChallengeResponse(id, realPerson.question, 13);
        svc.isHuman(realPersonResponse);

    }

    @Test
    void isHumanBadAnswer()  {
        String id = "9c59570b-8b8b-46ce-8825-77289a378ba3";
        Challenge realPerson = new Challenge(id, "Please sum the numbers 3,6,4");
        BotDetectorService svc = new BotDetectorService();
        svc.getClientIdentityRepo().put(id, realPerson);

        ChallengeResponse realPersonResponse = new ChallengeResponse(id, realPerson.question, 153);
        Exception exception = assertThrows(NotAHumanException.class, () -> {
            svc.isHuman(realPersonResponse);
        });

        String expectedMessage = "Bad Inputs";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }
}