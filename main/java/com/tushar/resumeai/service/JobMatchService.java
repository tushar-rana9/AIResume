package com.tushar.resumeai.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobMatchService {


    public int calculateJobMatch(List<String> resumeSkills, List<String> jobSkills){

        int matched = 0;

        for(String skill : resumeSkills){
            if(jobSkills.contains(skill)){
                matched++;
            }
        }

        if(jobSkills.isEmpty()) return 0;

        return (matched* 100)/jobSkills.size();
    }
}
