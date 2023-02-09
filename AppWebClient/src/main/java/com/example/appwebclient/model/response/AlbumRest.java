package com.example.appwebclient.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlbumRest {

    private String userId;
    private String albumId;
    private String albumTitle;
    private String albumDescription;
    private String albumUrl;

}
