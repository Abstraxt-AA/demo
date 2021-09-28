package com.example.demo.client;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "demoFeignClient", url = "https://content-youtube.googleapis.com/",
        configuration = YoutubeApiClientConfiguration.class)
public interface YoutubeApiClient {

    String PART = "snippet";
    String API_KEY = "AIzaSyAa8yy0GdcGPHdtD083HiGGx_S0vMPScDM";
    String MAX_RESULTS = "50";
    String F = "pageInfo%2CnextPageToken%2CprevPageToken%2Citems%28snippet%2FresourceId%2FvideoId%2Csnippet%2Ftitle%29";

    @GetMapping("youtube/v3/playlistItems?part=" + PART + "&playlistId={playlistId}&key=" +API_KEY + "&maxResults=" +
            MAX_RESULTS + "&fields=" + F)
    JsonNode listPlaylistItems(@PathVariable String playlistId);
}
