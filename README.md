# og-meta-scraper-api

This api has for objective to scrap some urls in order to get their metadatas infos.

It will get the following properties and generate a json response :
* "metaResponse[property='og:title']"
* "metaResponse[property='og:description']"
* "metaResponse[property='og:image']"
* "metaResponse[property='og:url']"

I wanted to create some preview links as you can see in social media application (Twitter, facebook etc...)

You can use it just for one url or for several urls as well

## Scrap one url

Api : POST /api/scraper/scrapOne

Body : JSON
```
{
  "url": "https://www.huffingtonpost.fr/"
}
```

Response : JSON
```
{
  "title": "Le HuffPost : actualités et infos décalées en continu, en France et dans le monde",
  "description": "Ne ratez rien de l'actu grâce à nos articles et vidéos en accès libre : Politique, France, International, Environnement, Sciences, Life, Culture et Divertissement... A décrouvrir à travers nos nombreux contenus, mais aussi des témoignages et des tribunes.",
  "url": "",
  "image": "https://www.huffingtonpost.fr/dist/assets/img/socials/default-entry.jpg"
}
```


## Scrap several urls

Api : POST /api/scraper/scrapMany

Body : JSON
```
{
  "url": [
    "https://www.huffingtonpost.fr/",
    "https://www.lemonde.fr/"
  ]
}
```

Response : JSON
```
{
  "metaList":[
    {
      "title": "Le HuffPost : actualités et infos décalées en continu, en France et dans le monde",
      "description": "Ne ratez rien de l'actu grâce à nos articles et vidéos en accès libre : Politique, France, International, Environnement, Sciences, Life, Culture et Divertissement... A décrouvrir à travers nos nombreux contenus, mais aussi des témoignages et des tribunes.",
      "url": "",
      "image": "https://www.huffingtonpost.fr/dist/assets/img/socials/default-entry.jpg"
    },
    {
      "title": "Le Monde - Toute l’actualité en continu",
      "description": "International, Economie, Environnement … La référence, partout, tout le temps.",
      "url": "https://www.lemonde.fr",
      "image": "https://asset.lemde.fr/medias/img/social-network/default.png"
    }
  ]
}}
```
