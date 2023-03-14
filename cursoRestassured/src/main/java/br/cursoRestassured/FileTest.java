package br.cursoRestassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.junit.Test;



public class FileTest {
    @Test
    public void deveObrigarEnvioArquivo() {
    	given()
    	   .log().all()
    	.when()
    	   .post("http://restapi.wcaquino.me/upload")
    	.then()
    	   .log().all()
    	   .statusCode(404)  //deveria ser 400
    	   .body("error", is("Arquivo não enviado"))
    	;
    }
    @Test
    public void deveFazerUploadDoArquivo() {
    	given()
    	   .log().all()
    	   .multiPart("arquivo", new File("src/main/resources/users.pdf"))
    	.when()
    	   .post("http://restapi.wcaquino.me/upload")
    	.then()
    	   .log().all()
    	   .statusCode(200)  
    	   .body("name", is("users.pdf"))
    	   
    	;
    }
    @Test
    public void naoDeveFazerUploadDoArquivo() {
    	given()
    	   .log().all()
    	   .multiPart("arquivo", new File("src/main/resources/NF.pdf"))
    	.when()
    	   .post("http://restapi.wcaquino.me/upload")
    	.then()
    	   .log().all()
    	   .time(lessThan(3000L))
    	   .statusCode(413)  
    	 
    	   
    	;
    }
}
