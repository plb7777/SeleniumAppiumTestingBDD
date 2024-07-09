package steps;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import utils.Utility;

public class AfterSteps {

	@AfterStep
	public void addImage(Scenario scenario) {
		Utility util = new Utility();
		if (scenario.isFailed()) {
			byte[] b = util.getScreenshot();
			if (b != null)
				scenario.attach(b, "image/png", "error");
		}
	}
	
}
