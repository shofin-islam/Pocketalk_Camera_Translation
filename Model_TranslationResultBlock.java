import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Model_TranslationResultBlock {
	AndroidDriver<MobileElement> driver;
	String activityName;
	By resultsBlock = By.xpath("//android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView");
	By backButton = By.id("iv_top_back");

	public Model_TranslationResultBlock (AndroidDriver<MobileElement> driver){
		this.driver = driver;
		this.activityName=".camera.activity.DetectedBlockActivity";
	}
	
	public String getActivityName() {
		return activityName;
	}
	
	public int getTranslationResultBlock() throws InterruptedException {
		Thread.sleep(1000);
		java.util.List<MobileElement> elements = driver.findElements(resultsBlock);
		System.out.println("Inside model ---- getTranslationResultBlock------"+elements.size());
		if (elements.size()>0) {
			return elements.size();
		}else {
			return 0;
		}
	}
	
	public boolean isBackButtonAvailable() {
		if (driver.findElement(backButton).isDisplayed()) {
			return true;
		}else {
			return false;
		}
	}
	public boolean tapOnBackButton() {
		try {
			driver.findElement(backButton).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean tapOnResultBlock(int index) {
		java.util.List<MobileElement> elements = driver.findElements(resultsBlock);
		System.out.println(elements.size());
		 if (elements.size()>0) {
			 try {
				 elements.get(index).click();
				 return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
		}else {
			return false;
		}
	}
}
