package com.codebuzz.botdetector.dto;


import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RealPerson {
    public String question;
    public String id;

    public RealPerson(String id, List<Integer> noToSum) {
        this.id = id;
        this.question = "Please sum the numbers "+noToSum.stream().map(Objects::toString).collect(Collectors.joining(","));
    }

    public RealPerson(String id, String question) {
        this.id = id;
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RealPerson that = (RealPerson) o;
        return Objects.equals(question, that.question) && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(question, id);
    }
}
