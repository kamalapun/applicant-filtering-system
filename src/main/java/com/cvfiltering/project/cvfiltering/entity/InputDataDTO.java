package com.cvfiltering.project.cvfiltering.entity;


import lombok.Data;

import java.util.List;

@Data
public class InputDataDTO {
   String  firstName;
   String lastName;
   String gender;
   String email;
   String address;
   Integer phone;
   List<String> jobPost;
   List<String> qualification;
   List<Integer> experience;
   List<String> skill;
   List<String> interactivity;
}
