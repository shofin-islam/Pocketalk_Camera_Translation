import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ModelCameraActivityMain {
	AndroidDriver<MobileElement> driver;
	String activityName;

    By backButton = By.id("iv_top_back");
    By flash = By.id("flashOnOffIV");
    By languageSelectionButton_From =By.id("tvLanguageShooting");
    By languageSelectionButton_To = By.id("tvLanguageTranslate");
    By cameraPreviewBottomSection = By.id("cameraPreviewBottomLayout");
    By translationHistory = By.id("imageview_image_history");
    ModelMainActivity objMainActivity;
    Model_MainMenuScreen objMainMenuScreen;
    

    public ModelCameraActivityMain (AndroidDriver<MobileElement> driver){

        this.driver = driver;
        this.activityName=".camera.activity.CustomCameraActivity";
        this.objMainActivity = new ModelMainActivity(driver);
        this.objMainMenuScreen= new Model_MainMenuScreen(driver);

    }
    
    public boolean captureImage(){
    	try {
			List<String> captureImage = Arrays.asList("keyevent","285");
			Map<String, Object> captureImageCmd = ImmutableMap.of("command", "input","args", captureImage);
			driver.executeScript("mobile: shell", captureImageCmd);
			Thread.sleep(2000);
			return true;
		} catch (Exception e) {
			System.out.println("Key Code Execution Failed : "+e);
			return false;
		}
    }
    
    public String getFromLanguageName(){
    	try {
    		return driver.findElement(languageSelectionButton_From).getText();
		} catch (Exception e) {
			System.out.print(e);
			return "Empty Value - From Language";
		}
    }
    
    public String getToLanguageName(){
    	try {
    		return driver.findElement(languageSelectionButton_To).getText();
		} catch (Exception e) {
//			System.out.print(e);
			return "Empty Value - To Language";
		}
    }

    public boolean backButtonAvailability(){
    	try {
    		if (driver.findElement(backButton).isDisplayed()) {
        		return true;
			}else {
				return false;
			}
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
    }
    
    public boolean backButtonFunctionality(){
    	try {
    		driver.findElement(backButton).click();
    		Thread.sleep(1000);
    		if (objMainActivity.getActivityName().contains(".activity.MainActivity")) {
        		return true;
			}else {
				return false;
			}
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
    }
    
    public boolean flashButtonAvailability(){
    	try {
    		if (driver.findElement(flash).isDisplayed()) {
        		return true;
			}else {
				return false;
			}
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
       
    }
    
    public boolean FromlanguageSelectionButtonAvailability(){

    	try {
    		if (driver.findElement(languageSelectionButton_From).isDisplayed()) {
        		return true;
			}else {
				return false;
			}
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
       
    }
    
    public boolean TapOnFromlanguageSelectionButton(){
    	try {
    		driver.findElement(languageSelectionButton_From).click();
    		Thread.sleep(1000);
    		return true;
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
    }
    
    
    public boolean TolanguageSelectionButtonAvailability(){
    	try {
    		if (driver.findElement(languageSelectionButton_To).isDisplayed()) {
        		return true;
			}else {
				return false;
			}
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
       
    }
    
    public boolean TapOnTOlanguageSelectionButton(){
    	try {
    		driver.findElement(languageSelectionButton_To).click();
    		Thread.sleep(1000);
    		return true;
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
    }
    public boolean translationHistoryButtonAvailability(){
    	try {
    		if (driver.findElement(translationHistory).isDisplayed()) {
        		return true;
			}else {
				return false;
			}
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
       
    }
    
    public boolean TapOnTranslationHistoryButton(){
    	try {
    		driver.findElement(translationHistory).click();
    		Thread.sleep(1000);
    		return true;
		} catch (Exception e) {
//			System.out.print(e);
			return false;
		}
       
    }
    
    public String getActivityName(){
       return activityName;
   }
}
