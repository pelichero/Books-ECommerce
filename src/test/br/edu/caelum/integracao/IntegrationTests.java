package br.edu.caelum.integracao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("integration-test")
public class IntegrationTests {

    private static final String HTTP_LOCALHOST_8888 = "http://localhost:9090/casadocodigo";
    private WebDriver driver;

    @Before
    public void iniciaNavegador() {
	if (System.getProperty("phantomjs.binary.path") != null) {
	    iniciaPhantomJs();
	} else if (System.getProperty("webdriver.chrome.driver") != null) {
	    iniciaChrome();
	} else {
	    throw new RuntimeException("Nao eh possivel determinar o navegador para execucao dos testes.");
	}
    }

    @After
    public void desligaNavegador() {
	driver.quit();
    }

    @Test
    public void verificaResultadosFiltradosNaTabela()
		    throws InterruptedException {
	driver.navigate().to(HTTP_LOCALHOST_8888 + "/login");

    }

    private void iniciaChrome() {
	driver = new ChromeDriver();
    }

    private void iniciaPhantomJs() {
	DesiredCapabilities dc = new DesiredCapabilities();
	dc.setJavascriptEnabled(true);
	driver = new PhantomJSDriver(dc);
    }
}
