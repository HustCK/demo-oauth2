package com.example.resourceserver.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRest {

    private String userFirstName;
    private String userLastName;
    private String userId;

}
