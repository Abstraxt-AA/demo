package com.example.demo.controller;

import com.example.demo.client.YoutubeApiClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/youtube")
public class YoutubeController {

    private final YoutubeApiClient youtubeApiClient;

    @Autowired
    public YoutubeController(YoutubeApiClient youtubeApiClient) {
        this.youtubeApiClient = youtubeApiClient;
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<JsonNode> getYoutubePlaylist(@PathVariable String playlistId) {
        return ResponseEntity.ok(youtubeApiClient.listPlaylistItems(playlistId));
    }
}
