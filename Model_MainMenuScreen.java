import java.util.ArrayList;

import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Model_MainMenuScreen {
	AndroidDriver<MobileElement> driver;
	String activityName;
	ArrayList<String> mainMenuList = new ArrayList<String>();
    By backButton = By.id("ib_back");
    By mobileData = By.id("iv_mobile_data");
    By wifi =By.id("iv_wifi");
    By battery_icon = By.id("battery_icon");
    By battery_level = By.id("txt_battery_level");
    ModelMainActivity objMainActivity;
    

    public Model_MainMenuScreen (AndroidDriver<MobileElement> driver){

        this.driver = driver;
        this.objMainActivity = new ModelMainActivity(driver);
        this.activityName=".translation.activity.ContentActivity";

    }

    public String getActivityName() {
    	return activityName;
    }
    public boolean backButtonAvailability(){

    	try {
    		if (driver.findElement(backButton).isDisplayed()) {
        		return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
       
    }
    
    public boolean tapOnBackButton(){
    	try {
    		driver.findElement(backButton).click();
    		
    		if(objMainActivity.getActivityName().toLowerCase().contains(".activity.MainActivity".toLowerCase())) {
    			return true;
    		}else {
				return false;
			}
    		
		} catch (Exception e) {
			System.out.print(e);
			return false;
		}
       
    }
    
    public ArrayList<String> getMainMenuList() throws InterruptedException{
    	try {
    	objMainActivity.tapOnMenu();
    	int menuCount = driver.findElementsById("tv_phrase_name").size();
		String menu ="";
		for (int i = 0; i < menuCount; i++) {
					menu = driver.findElementsById("tv_phrase_name").get(i).getText();
					mainMenuList.add(i,menu);
					Thread.sleep(1000);
				}
		}catch (Exception e) {
			// TODO: handle exception
		}
    	return mainMenuList;
    	
    }
    
    public boolean tapOnTargetMenu(String menuName) throws InterruptedException{
    	try {
    	int menuCount = driver.findElementsById("tv_phrase_name").size();
		for (int j = 0; j < menuCount; j++) {
			String menu = driver.findElementsById("tv_phrase_name").get(j).getText();
					if (menu.toLowerCase().contains(menuName.toLowerCase())) {
						driver.findElementsById("tv_phrase_name").get(j).click();
						j=menuCount;
						Thread.sleep(1000);
					}else {
//						System.out.println("Menu Name Not Found!!");
					}
				}
		}catch (Exception e) {
			System.out.println(e);
			return false;
		}
    	return true;
    	
    }
}
