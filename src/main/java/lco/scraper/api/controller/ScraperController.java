package lco.scraper.api.controller;

import lco.scraper.api.entity.MetaResponse;
import lco.scraper.api.entity.MetasResponse;
import lco.scraper.api.entity.MetaRequest;
import lco.scraper.api.entity.MetasRequest;
import lco.scraper.api.services.Scraper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/scraper")
public class ScraperController {

    @PostMapping(value = "/scrapOne", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MetaResponse scrapOne(@RequestBody MetaRequest metaRequest) throws IOException {
        Scraper scraper = new Scraper();
        return scraper.scrapPage(metaRequest.getUrl());
    }

    @PostMapping(value = "/scrapMany", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MetasResponse scrapMany(@RequestBody MetasRequest metasRequest) throws IOException {
        Scraper scraper = new Scraper();
        return scraper.scrapManyPages(metasRequest.getUrl());
    }

}
