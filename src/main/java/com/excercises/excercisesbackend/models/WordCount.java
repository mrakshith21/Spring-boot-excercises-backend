package com.excercises.excercisesbackend.models;

import java.util.HashMap;
import java.util.Map;

public class WordCount {

    Map<String, Integer> freq = new HashMap<>();

    public WordCount() {
    }

    public WordCount(Map<String, Integer> freq) {
        this.freq = freq;
    }

    public Map<String, Integer> getFreq() {
        return freq;
    }

    public void setFreq(Map<String, Integer> freq) {
        this.freq = freq;
    }
}
