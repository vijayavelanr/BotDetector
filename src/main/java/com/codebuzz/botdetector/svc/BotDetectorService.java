package com.codebuzz.botdetector.svc;

import com.codebuzz.botdetector.dto.RealPerson;
import com.codebuzz.botdetector.dto.RealPersonResponse;
import com.codebuzz.botdetector.exception.NotAHumanException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class BotDetectorService {

    private Map<String, RealPerson> clientIdentityRepo = new HashMap<>();

    public Map<String, RealPerson> getClientIdentityRepo() {
        return clientIdentityRepo;
    }

    public String isHuman(RealPersonResponse realPersonResponse) throws NotAHumanException {

        Objects.nonNull(realPersonResponse);
        if (null == getClientIdentityRepo().get(realPersonResponse.getId())) {
            throw new NotAHumanException("Bad Inputs");
        }
        boolean samePerson = getClientIdentityRepo().get(realPersonResponse.getId()).equals(new RealPerson(realPersonResponse.getId(), realPersonResponse.getQuestion()));

        if (!samePerson) {
            throw new NotAHumanException("Bad Inputs");
        }

        RealPerson fromCache = clientIdentityRepo.get(realPersonResponse.getId());
        String[] qpart = fromCache.question.split(" ")[4].split(",");

        Integer goodAnswer = Arrays.stream(qpart).mapToInt(value -> Integer.parseInt(value)).reduce(Integer::sum).getAsInt();

        if (goodAnswer == realPersonResponse.getAnswer()) {
            return "Great Works";
        } else {
           throw new NotAHumanException("Bad Inputs");
        }
    }

    public void setClientIdentityRepo(Map<String, RealPerson> clientIdentityRepo) {
        this.clientIdentityRepo = clientIdentityRepo;
    }
}
