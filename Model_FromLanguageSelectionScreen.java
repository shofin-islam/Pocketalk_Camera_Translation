import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Model_FromLanguageSelectionScreen {
	AndroidDriver<MobileElement> driver;
	String activityName;
    By backButton = By.id("ib_top_back");
    
    public Model_FromLanguageSelectionScreen (AndroidDriver<MobileElement> driver){

        this.driver = driver;
        this.activityName = ".camera.activity.CameraLanguageSelectionActivity";

    }
    public String getActivityName() {
    	return activityName;
    }
    
    public boolean TapOnBackButton(){
    	try {
    		driver.findElement(backButton).click();
    		Thread.sleep(1000);
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
}
