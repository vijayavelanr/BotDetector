package com.codebuzz.botdetector.dto;

public class RealPersonResponse {
    String id;
    public String question;
    Integer answer;

    public RealPersonResponse() {

    }
    public RealPersonResponse(String id, String question, Integer answer) {
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
