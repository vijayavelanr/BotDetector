package com.codebuzz.botdetector.dto;

public class ChallengeResponse {
    String id;
    public String question;
    Integer answer;

    public ChallengeResponse() {

    }
    public ChallengeResponse(String id, String question, Integer answer) {
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public String getId() {
        return id;
    }
    public Integer getAnswer() {
        return answer;
    }

    public String getQuestion() {
        return question;
    }
}
