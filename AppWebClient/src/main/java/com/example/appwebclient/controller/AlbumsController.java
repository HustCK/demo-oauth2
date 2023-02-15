package com.example.appwebclient.controller;

import com.example.appwebclient.model.response.AlbumRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Controller
public class AlbumsController {

    @Autowired
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WebClient webClient;

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {

//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
//        OAuth2AuthorizedClient oAuth2AuthorizedClient = oAuth2AuthorizedClientService.loadAuthorizedClient(oauthToken.getAuthorizedClientRegistrationId(), oauthToken.getName());
//
//        String jwt = oAuth2AuthorizedClient.getAccessToken().getTokenValue();
        String url = "http://localhost:8080/users/status/check";
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Authorization", "Bearer " + jwt);
//        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        AlbumRest album = new AlbumRest();
		album.setAlbumId("albumOne");
 		album.setAlbumUrl("http://localhost:8082/albums/1");
//
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<String>() {
//        });


//        AlbumRest album = new AlbumRest();
//		album.setAlbumId("albumOne");
//		album.setAlbumTitle("Album one title");
//		album.setAlbumUrl("http://localhost:8082/albums/1");
//
//		AlbumRest album2 = new AlbumRest();
//		album2.setAlbumId("albumTwo");
//		album2.setAlbumTitle("Album two title");
//		album2.setAlbumUrl("http://localhost:8082/albums/2");
//
//        List<AlbumRest> albums = List.of(album, album2);

        String response = webClient.get().uri(url).retrieve().bodyToMono(new ParameterizedTypeReference<String>() {
        }).block();

        album.setAlbumTitle(response);

        model.addAttribute("albums", List.of(album));
        return "albums";
    }
}
