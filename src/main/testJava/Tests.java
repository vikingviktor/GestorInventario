
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.openqa.selenium.WebDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.TextFieldElement;

@RunWith(BlockJUnit4ClassRunner.class)
public class Tests extends MyUI{
	
	private WebDriver driver;

	@Before
	public void setup() {
	}

	@Test
	public void addNewCustomer_formShouldBeVisible() {
	}

	@After
	public void teardown() {
	}

}




