import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Model_TTS_Screen {
	AndroidDriver<MobileElement> driver;
	By close = By.id("close_dialog");
	By play = By.id("btn_play");
	By languageName = By.id("textview_source_language");
	By translatedText_Top = By.id("tv_horizontal_text_translated");
	By originalText_Bottom = By.id("tv_horizontal_text_original");
	public Model_TTS_Screen (AndroidDriver<MobileElement> driver){
		this.driver = driver;
	}
	
	public boolean isCloseButtonAvailable() throws InterruptedException {
		try {
			return driver.findElement(close).isDisplayed();
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public void tapOnCloseButton() throws InterruptedException {
		try {
			driver.findElement(close).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
		
	public boolean isPlayButtonAvailable() throws InterruptedException {
		try {
			return driver.findElement(play).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public void tapOnPlayButton() throws InterruptedException {
		try {
			driver.findElement(play).click();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public boolean isLanguageNameAvailable() throws InterruptedException {
		try {
			return driver.findElement(languageName).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getSourceLanguageName() throws InterruptedException {
		try {
			return driver.findElement(languageName).getText();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public boolean isTranslatedText_Top_Available() throws InterruptedException {
		try {
			return driver.findElement(translatedText_Top).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getTranslatedText_Top() throws InterruptedException {
		try {
			return driver.findElement(translatedText_Top).getText();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	public boolean isOriginalText_Bottom_Available() throws InterruptedException {
		try {
			return driver.findElement(originalText_Bottom).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getOriginalText_Bottom() throws InterruptedException {
		try {
			return driver.findElement(originalText_Bottom).getText();
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
