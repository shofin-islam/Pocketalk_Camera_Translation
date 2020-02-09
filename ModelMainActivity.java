import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ModelMainActivity {

	AndroidDriver<MobileElement> driver;
	String activityName;

    By menu = By.id("btn_navigation_menu_left");
    By shortcut = By.id("iv_shortcut");
    By historyButton =By.id("top_sheet");
    By languageSelectionTop = By.id("layout_gradient_top");
    By translate_languageName_Local = By.id("tv_translate_view");
    By translate_languageName_System = By.id("tv_translate_system");
    By languageSwitch_TotalArea = By.id("button_switching_language");
    By languageSwitch_Button = By.id("arrow_iv");
    By languageSelectionBottom = By.id("layout_gradient_bottom");
    By native_languageName_Local = By.id("tv_native_view");
    By native_languageName_System = By.id("tv_native_system");
    

    public ModelMainActivity (AndroidDriver<MobileElement> driver){

        this.driver = driver;
        this.activityName=".activity.MainActivity";

    }
    
    public boolean menuButtonAvailability(){
    	try {
    		if (driver.findElement(menu).isDisplayed()) {
        		return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
       
    }

    public void tapOnMenu(){
    	try {
    		driver.findElement(menu).click();
    		Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
    
    public String getActivityName(){
       return activityName;
   }
    
    
}
