package lco.scraper.api.services;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lco.scraper.api.entity.MetaResponse;
import lco.scraper.api.entity.MetasResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class Scraper {

    Logger logger = LoggerFactory.getLogger(Scraper.class);

    public static final String CONTENT = "content";

    private HtmlPage initPage(String pageUrl) throws IOException {
        //initialize a headless browser
        try(WebClient webClient = new WebClient(BrowserVersion.CHROME)) {

            //configuring options
            webClient.getOptions().setUseInsecureSSL(true);
            webClient.getOptions().setCssEnabled(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setThrowExceptionOnScriptError(false);

            //fetching the web page
            return webClient.getPage(pageUrl);
        }
    }

    public MetaResponse scrapPage(String pageUrl) throws IOException {

        HtmlPage page = initPage(pageUrl);
        logger.info("Processing page : {}", pageUrl);
        String title = scrapOgTitle(page);
        String description = scrapOgDescription(page);
        String image = scrapOgImage(page);
        String url = scrapOgUrl(page);

        return new MetaResponse(title, description, url, image);
    }

    public MetasResponse scrapManyPages(List<String> urls) throws IOException {
        MetasResponse metasResponse = new MetasResponse();

        for (String url : urls) {
            MetaResponse metaResponse = scrapPage(url);
            metasResponse.getMetaList().add(metaResponse);
        }
        return metasResponse;
    }

    private String scrapOgTitle(HtmlPage page) {
        DomNode titleNode = page.querySelector("meta[property='og:title']");
        if (titleNode != null) {
            String title = titleNode.getAttributes().getNamedItem(CONTENT).getTextContent().replace("\u00a0", "");
            logger.info("og:title : {}", title);
            return title;
        } else {
            return "";
        }
    }

    private String scrapOgDescription(HtmlPage page) {
        DomNode descNode = page.querySelector("meta[property='og:description']");
        if (descNode != null) {
            String description = descNode.getAttributes().getNamedItem(CONTENT).getTextContent().replace("\u00a0", "");
            logger.info("og:description : {}", description);
            return description;
        } else {
            return "";
        }
    }

    private String scrapOgImage(HtmlPage page) {
        DomNode imgNode = page.querySelector("meta[property='og:image']");
        if (imgNode != null) {
            String image = imgNode.getAttributes().getNamedItem(CONTENT).getTextContent();
            logger.info("og:image : {}", image);
            return image;
        } else {
            return "";
        }
    }

    private String scrapOgUrl(HtmlPage page) {
        DomNode urlNode = page.querySelector("meta[property='og:url']");
        if (urlNode != null) {
            String url = urlNode.getAttributes().getNamedItem(CONTENT).getTextContent();
            logger.info("og:url : {}", url);
            return url;
        } else {
            return "";
        }
    }
}
