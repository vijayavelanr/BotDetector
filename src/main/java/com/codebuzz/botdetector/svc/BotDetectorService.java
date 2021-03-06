package com.codebuzz.botdetector.svc;

import com.codebuzz.botdetector.dto.Challenge;
import com.codebuzz.botdetector.dto.ChallengeResponse;
import com.codebuzz.botdetector.exception.NotAHumanException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class BotDetectorService {

    private Map<String, Challenge> clientIdentityRepo = new HashMap<>();

    public Map<String, Challenge> getClientIdentityRepo() {
        return clientIdentityRepo;
    }

    public String validateHuman(ChallengeResponse realPersonResponse) throws NotAHumanException {

        Objects.nonNull(realPersonResponse);
        if (null == getClientIdentityRepo().get(realPersonResponse.getId())) {
            throw new NotAHumanException("Bad Inputs");
        }
        boolean samePerson = getClientIdentityRepo().get(realPersonResponse.getId()).equals(new Challenge(realPersonResponse.getId(), realPersonResponse.getQuestion()));

        if (!samePerson) {
            throw new NotAHumanException("Bad Inputs");
        }

        Challenge fromCache = clientIdentityRepo.get(realPersonResponse.getId());
        String[] qpart = fromCache.question.split(" ")[4].split(",");

        Integer goodAnswer = Arrays.stream(qpart).mapToInt(value -> Integer.parseInt(value)).reduce(Integer::sum).getAsInt();

        if (goodAnswer == realPersonResponse.getAnswer()) {
            return "Great Work";
        } else {
           throw new NotAHumanException("Bad Inputs");
        }
    }

    public void setClientIdentityRepo(Map<String, Challenge> clientIdentityRepo) {
        this.clientIdentityRepo = clientIdentityRepo;
    }
}
