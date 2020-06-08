import org.openqa.selenium.Point;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;

public class BatCameraExecute {

	ModelMainActivity objMainActivity;
	ModelCameraActivityMain objCameraActivityMain;
	Model_MainMenuScreen objMainMenu;
	Model_FromLanguageSelectionScreen objFromLanguageSelectionScreen;
	Model_CapturedImagePreview objCapturedImagePreview;
	PocketalkSupport objPTSupport;
	Model_TranslationResultBlock objTranslationResultsScreen;
	Model_TTS_Screen ttsScreen;


	static AndroidDriver<MobileElement> driver;
	public static String packageName = "packageName";
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	private static String ANDROID_PHOTO_PATH = "/mnt/sdcard/Android/data/**********/files";
	private static String assetsPath = System.getProperty("user.dir")+"\\assets\\";
	private  ArrayList<String> backButtons = new ArrayList<String>();
	private boolean isFail=false;

	@BeforeTest

	public void setup() throws InterruptedException, IOException{
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); 
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,"8.1.0");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"UCPVFIM7NVLV497D");
		capabilities.setCapability("noReset","true"); 
		capabilities.setCapability("fullReset","false");
		capabilities.setCapability("appPackage", packageName);
		capabilities.setCapability("appActivity",packageName+".activity.MainActivity"); 

		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		Thread.sleep(5000);
		objMainActivity = new ModelMainActivity(driver);
		objCameraActivityMain = new ModelCameraActivityMain(driver);
		objMainMenu = new Model_MainMenuScreen(driver);
		objFromLanguageSelectionScreen = new Model_FromLanguageSelectionScreen(driver);
		objCapturedImagePreview = new Model_CapturedImagePreview(driver);
		objPTSupport = new PocketalkSupport(driver,packageName);
		objTranslationResultsScreen = new Model_TranslationResultBlock(driver);
		ttsScreen = new Model_TTS_Screen(driver);
	}

	@Test(priority = 1)
	public void verifyMainActivityName() {
		System.out.println("Case 1");
		try {
			Assert.assertEquals(objMainActivity.getActivityName(), driver.currentActivity());
		} catch (Exception e) {
			Assert.fail();
			isFail=true;
		}

	}
	@Test(priority = 2)
	public void verifyThatShootingScreenAppear_TapOnCameraByMenu() throws InterruptedException {
		System.out.println("Case 2");
		try {
			objPTSupport.homeToMainMenuActivity();
			objMainMenu.tapOnTargetMenu("Camera");
			Assert.assertEquals(objCameraActivityMain.getActivityName(), driver.currentActivity());
		} catch (Exception e) {
			isFail=true;
			Assert.fail();
			objPTSupport.backToHomeScreen();
		}
	}

	@Test(priority = 3)
	public void verifyTheAvailabilityOf_FromLanguageButton() throws InterruptedException {
		System.out.println("Case 3");
		if (isFail) {
			objPTSupport.homeToCameraActivity();
			Assert.assertTrue(objCameraActivityMain.FromlanguageSelectionButtonAvailability());
		}else {
			Assert.assertTrue(objCameraActivityMain.FromlanguageSelectionButtonAvailability());
		}
	}

	@Test(priority = 4)
	public void verifyTheAvailabilityof_BackButton() throws InterruptedException {
		System.out.println("case 4");
		Assert.assertTrue(objCameraActivityMain.backButtonAvailability());
	}

	@Test(priority = 5)
	public void verifyTheAvailabilityOf_FlashIcon() throws InterruptedException {
		System.out.println("case 5");
		Assert.assertTrue(objCameraActivityMain.flashButtonAvailability());
	}
	@Test(priority = 6)
	public void verifyTheTranslationHistoryIconStatus_forFirstTimeLaunch() throws InterruptedException {
		System.out.println("case 6");
		if (driver.currentActivity().contains(objCameraActivityMain.getActivityName())) {
			Assert.assertFalse(objCameraActivityMain.translationHistoryButtonAvailability());
		}else {
			Assert.fail();
		}
	}
	@Test(priority = 7)
	public void verifyTheAvailabilityOf_TOLanguageButton() throws InterruptedException {
		System.out.println("case 7");
		Assert.assertTrue(objCameraActivityMain.TolanguageSelectionButtonAvailability());
	}
	@Test(priority = 8)
	public void verifyTheFunctionalityof_BackButton() throws InterruptedException {
		System.out.println("case 8");
		if (!objCameraActivityMain.backButtonFunctionality()) {
			isFail=true;
		}else {
			isFail=false;
			Assert.assertTrue(0<1);
		}
	}
	@Test(priority = 9)
	public void verifyDefaultFromLanguageName() throws InterruptedException {
		System.out.println("case 9");
		if (isFail) {
			Assert.assertTrue(objCameraActivityMain.getFromLanguageName().contains("Automatic Recognition"));
		}else {
			objPTSupport.homeToCameraActivity();
			isFail=false;
			Assert.assertTrue(objCameraActivityMain.getFromLanguageName().contains("Automatic Recognition"));
		}
	}
	@Test(priority = 10)
	public void verifyDefaultToLanguageName_ForSystemLanguage_English() throws InterruptedException {
		System.out.println("case 10");
		Assert.assertTrue(objCameraActivityMain.getToLanguageName().contains("English"));
	}
	@Test(priority = 11)
	public void verifyFromLanguageSelectionButtonFunctionality() throws InterruptedException {
		System.out.println("case 11");
		try {
			objCameraActivityMain.TapOnFromlanguageSelectionButton();
			Assert.assertTrue(driver.currentActivity().contains(objFromLanguageSelectionScreen.getActivityName()));
			driver.pressKey(new KeyEvent(AndroidKey.BACK));
			Thread.sleep(1000);
		} catch (Exception e) {
			objPTSupport.backToHomeScreen();
			isFail=true;
			Assert.fail();
		}
	}
	@Test(priority = 12)
	public void verifyTOLanguageSelectionButtonFunctionality() throws InterruptedException {
		System.out.println("case 12");
		if (isFail) {
			try {
				objPTSupport.homeToCameraActivity();
				objCameraActivityMain.TapOnTOlanguageSelectionButton();
				Assert.assertTrue(driver.currentActivity().contains(objFromLanguageSelectionScreen.getActivityName()));
				driver.pressKey(new KeyEvent(AndroidKey.BACK));
				Thread.sleep(1000);
			} catch (Exception e) {
				isFail=true;
				Assert.fail();
			}
		}else {
			try {
				objCameraActivityMain.TapOnTOlanguageSelectionButton();
				Assert.assertTrue(driver.currentActivity().contains(objFromLanguageSelectionScreen.getActivityName()));
				driver.pressKey(new KeyEvent(AndroidKey.BACK));
				Thread.sleep(1000);
			} catch (Exception e) {
				isFail=true;
				Assert.fail();
			}
		}
	}
	@Test(priority = 13)
	public void imageCapture_Functionality() throws InterruptedException {
		System.out.println("case 13");
		if (driver.currentActivity().contains(objCameraActivityMain.getActivityName())) {
			Assert.assertTrue(objCameraActivityMain.captureImage());
		}else {
			objPTSupport.homeToCameraActivity();
			try {
				Assert.assertTrue(objCameraActivityMain.captureImage());
			} catch (Exception e) {
				Assert.fail();
			}
		}
	}

	@Test(priority = 14)
	public void verifyDiscardButtonAvailabilityInPreviewScreen() throws InterruptedException {
		System.out.println("case 14");
		if (isFail) {
			objPTSupport.backToHomeScreen();
			objPTSupport.homeToPreviewScreen();
			Assert.assertTrue(objCapturedImagePreview.isDiscardButtonAvailable());
		}else {
			Assert.assertTrue(objCapturedImagePreview.isDiscardButtonAvailable());
			isFail=false;
		}
	}

	@Test(priority = 15)
	public void verifySaveButtonAvailabilityInPreviewScreen() throws InterruptedException {
		System.out.println("case 15");
		Assert.assertTrue(objCapturedImagePreview.isTickButtonAvailable());
	}

	@Test(priority = 16)
	public void verifyDiscardButtonFunctionality() throws InterruptedException {
		System.out.println("case 16");
		try {
			objCapturedImagePreview.tapOnDiscardButton();
			Assert.assertTrue(driver.currentActivity().contains(".camera.activity.CustomCameraActivity"));
		} catch (Exception e) {
			objPTSupport.backToHomeScreen();
			isFail=true;
			Assert.fail();
		}
	}
	@Test(priority = 17)
	public void verifyNoTextAlert() throws InterruptedException {
		System.out.println("case 17");
		objPTSupport.homeToPreviewScreen();
		objPTSupport.imagePush("progressBarTest.png");
		objCapturedImagePreview.tapOnTickButton();
		Thread.sleep(6000);
		Assert.assertTrue(objCapturedImagePreview.noTextAlertDisplayed());
	}
	@Test(priority = 18)
	public void verify_No_Text_Alert_OK_Button_Functionality() throws InterruptedException {
		System.out.println("Case 18");
		try {
			objCapturedImagePreview.tapOKOnNoTextAlert();
			Thread.sleep(1000);
			if (driver.currentActivity().contains(objCapturedImagePreview.getActivityName())) {
				isFail=false;
				Assert.assertTrue(driver.currentActivity().contains(objCapturedImagePreview.getActivityName()));
			}else {
				objPTSupport.backToHomeScreen();
				isFail=true;
				Assert.fail();
			}
		} catch (Exception e) {
			Assert.fail();
		}	
	}
	@Test(priority = 19)
	public void imageCropSelection_Functionality() throws InterruptedException {
		System.out.println("Case 19");
		if (!isFail) {
			Point source = new Point(1000, 450);
			Point target = new Point(808, 803);
			try {
				Assert.assertTrue(objCapturedImagePreview.imageCropAreaSelection(source, target));
			} catch (Exception e) {
				objPTSupport.backToHomeScreen();
			}
		}else {
			try {
				objPTSupport.homeToCameraActivity();
				objCameraActivityMain.captureImage();
				objPTSupport.imagePush("progressBarTest.png");
				objCapturedImagePreview.tapOnTickButton();
				Thread.sleep(5000);
				objCapturedImagePreview.tapOKOnNoTextAlert();
				Point source = new Point(1000, 450);
				Point target = new Point(808, 803);
				Assert.assertTrue(objCapturedImagePreview.imageCropAreaSelection(source, target));
			} catch (Exception e) {
				objPTSupport.backToHomeScreen();
				Assert.fail();
			}
		}    
	}
	@Test(priority = 20)
	public void verifyProgressBarAvailability() throws InterruptedException, IOException {
		System.out.println("Case 20");
		objCapturedImagePreview.tapOnTickButton();
		Thread.sleep(500);
		if (objCapturedImagePreview.isProgressBarAvailable()) {
			Thread.sleep(8000);
			isFail=false;
			Assert.assertTrue(0<1);
		}else {
			isFail=true;
			objPTSupport.errorScreenshot("progressBar");
			objPTSupport.backToHomeScreen();
			Assert.fail();
		}
	}
	@Test(priority = 21)
	public void Verify_Translation_Results_Block() throws InterruptedException, IOException {
		System.out.println("Case 21");
		try {
			System.out.println("Verify_Translation_Results_Block --- Main Class "+objTranslationResultsScreen.getTranslationResultBlock());
			if (isFail) {
				if(objPTSupport.homeToTranslationResultsBlock("japanese.png", "Automatic Recognition")) {
					Assert.assertTrue(objTranslationResultsScreen.getTranslationResultBlock()>0);
				}else {
					System.out.println("homeToTranslationResultsBlock ----- fail"); 
				}
			}else {
				Assert.assertTrue(objTranslationResultsScreen.getTranslationResultBlock()>0);
			}
		} catch (Exception e) {
			objPTSupport.backToHomeScreen();
			Assert.fail();
		}
	}

	@Test(priority = 22)
	public void Verify_Back_Button_In_Translation_Result_Screen() throws InterruptedException, IOException {
		System.out.println("case 22");
		if (!isFail) {
			Assert.assertTrue(objTranslationResultsScreen.isBackButtonAvailable());
		}else {
			objPTSupport.homeToTranslationResultsBlock("japanese.png", "Automatic Recognition");
			Thread.sleep(1000);
			Assert.assertTrue(objTranslationResultsScreen.isBackButtonAvailable());
		}
	}
	@Test(priority = 23)
	public void Verify_Back_Button_Functionality_In_Translation_Result() throws InterruptedException {
		System.out.println("case 23");
		objTranslationResultsScreen.tapOnBackButton();
		Thread.sleep(1000);
		Assert.assertTrue(objCameraActivityMain.getActivityName().contains(driver.currentActivity()));
	}
	
	@Test(priority = 24)
	public void Verify_Tap_On_Block_Open_TTS_Screen() throws InterruptedException, IOException {
		System.out.println("case 24");
		objPTSupport.cameraToTranslationResultsBlock("japanese.png", "Automatic Recognition");
		Thread.sleep(1000);
		int count = objTranslationResultsScreen.getTranslationResultBlock();
		if (count>0) {
			if (objTranslationResultsScreen.tapOnResultBlock(0)) {
				Thread.sleep(1000);
				isFail=false;
				Assert.assertTrue(ttsScreen.isCloseButtonAvailable());
			}else {
				System.out.println("Failed to tap on block...");
			}
		}else {
			System.out.println("No block found!!");
			isFail=true;
			Assert.fail();
		}
	}
	@Test(priority = 25)
	public void Verify_Close_Button_Is_Available_In_TTS_Screen() throws InterruptedException, IOException {
		System.out.println("case 25");
		if (isFail) {
			if (objPTSupport.homeToTTSScreen(0, "japanese.png", "Automatic Recognition")) {
				Assert.assertTrue(ttsScreen.isCloseButtonAvailable());
			}else {
				Assert.fail();
			}
		}else {
			Assert.assertTrue(ttsScreen.isCloseButtonAvailable());
		}
	}
	@Test(priority = 26)
	public void Verify_Play_Button_Is_Available_In_TTS_Screen_For_SupportedLanguage() throws InterruptedException, IOException {
		System.out.println("case 26");
		Assert.assertTrue(ttsScreen.isPlayButtonAvailable());
	}
	@Test(priority = 27)
	public void Verify_Translated_Text_Available() throws InterruptedException, IOException {
		System.out.println("case 27");
		Assert.assertTrue(ttsScreen.isTranslatedText_Top_Available());
	}
	@Test(priority = 28)
	public void Verify_Original_Text_Available() throws InterruptedException, IOException {
		System.out.println("case 28");
		Assert.assertTrue(ttsScreen.isOriginalText_Bottom_Available());
	}
	@Test(priority = 29)
	public void Verify_From_Language_Name_Available_In_TTS_Screen() throws InterruptedException, IOException {
		System.out.println("case 29");
		Assert.assertTrue(ttsScreen.isLanguageNameAvailable());
	}
	
	@Test(priority = 30)
	public void Verify_From_Language_Name_Is_Japanese_In_TTS_Screen() throws InterruptedException, IOException {
		System.out.println("case 29");
		Assert.assertTrue(ttsScreen.getSourceLanguageName().contains("Japanese"));
	}
	//	@Test(priority = 21)
	//	public void imageCrop_Functionality() throws InterruptedException, URISyntaxException, IOException {
	//		Point source = new Point(1000, 450);
	//		Point target = new Point(808, 803);
	//		objCapturedImagePreview.imageCropAreaSelection(source, target);
	//		objCapturedImagePreview.tapOnTickButton();
	//		Thread.sleep(10000);
	//		objPTSupport.pullCapturedImage();
	//		Thread.sleep(1000);
	//		objPTSupport.pullCroppedImage();
	//		Thread.sleep(1000);
	//		Assert.assertTrue(objPTSupport.imageCompare());
	//	}	
}
