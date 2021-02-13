# URL Shortener Service


Build a very minimalistic URL shortener service. This is a recruitment take-home assignment.

Sample redirect rules:

* /gg should redirect to https://google.com
* /fb should redirect to https://facebook.com
* /tw should redirect to https://twitter.com

## Code Organization

* `UrlShortenerController.java` is the entry-point API
* `ShortCodeMappingService.java` maintains the mapping from a short code such as `gg` to the destination URL such as `https://google.com`
* The mapping is externalized to a file `mapping.properties` which is available in the classpath


## Code Walkthrough

For a code walk-through, you can refer the following pull requests

* **Basic Implementation** - https://github.com/visiblemogambo/bitly/pull/1/files
* **Unit Tests** - https://github.com/visiblemogambo/bitly/pull/2/files  


## Running the code

This is a standard Spring Boot project. The initial scaffold was downloaded from https://start.spring.io/. It uses Maven and Java 8.

To run the project: `./mvnw springb-boot:run`

