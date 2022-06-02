package com.excercises.excercisesbackend.models;

import java.util.List;

public class Skills {

    List<String> skills;

    public Skills() {
    }

    public Skills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
