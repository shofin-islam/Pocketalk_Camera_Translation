import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;

import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class Model_CapturedImagePreview {

	AndroidDriver<MobileElement> driver;
	private String activityName;
    By saveButton = By.id("save");
    By discardButton = By.id("discard");
    By cropEdge =By.id("CropOverlayView");
    By progressBar =By.id("progress_wheel");
    By noTextAlert_OK =By.xpath("//android.widget.LinearLayout/android.widget.Button");
    

    public Model_CapturedImagePreview (AndroidDriver<MobileElement> driver){

        this.driver = driver;
        this.activityName = ".camera.cropimage.CropImageActivity";

    }
    
    public String getActivityName() {
		return activityName;
	}
    
    public boolean isTickButtonAvailable(){
    	try {
    		return driver.findElement(saveButton).isDisplayed();
		} catch (Exception e) {
			return false;
		}
    }
    
    public boolean tapOnTickButton(){
    	try {
    		driver.findElement(saveButton).click();
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    public boolean isDiscardButtonAvailable(){
    	try {
    		return driver.findElement(discardButton).isDisplayed();
		} catch (Exception e) {
			return false;
		}
    }
    public boolean isProgressBarAvailable(){
    	try {
    		return driver.findElement(progressBar).isDisplayed();
		} catch (Exception e) {
			return false;
		}
    }
    public boolean tapOnDiscardButton(){
    	try {
    		driver.findElement(discardButton).click();
    		Thread.sleep(1000);
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    @SuppressWarnings("rawtypes")
	public boolean imageCropAreaSelection(Point source, Point target){
    	try {
    		Thread.sleep(4000);
    		TouchAction action = new TouchAction(driver);
    		action.longPress(LongPressOptions.longPressOptions().withPosition(PointOption.point(source)));
    		action.moveTo(PointOption.point(target)).release().perform();
    		Thread.sleep(1000);
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    
    public boolean noTextAlertDisplayed(){
    	try {
    		driver.findElement(noTextAlert_OK).isDisplayed();
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    
    public boolean tapOKOnNoTextAlert(){
    	try {
    		driver.findElement(noTextAlert_OK).click();
    		return true;
		} catch (Exception e) {
			return false;
		}
    }
    
}
