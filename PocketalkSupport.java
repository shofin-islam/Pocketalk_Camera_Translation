import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.Assert;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;



public class PocketalkSupport {
	AndroidDriver<MobileElement> driver;
	public String packageName;
	private static File classpathRoot = new File(System.getProperty("user.dir"));
	private static String ANDROID_PHOTO_PATH = "/mnt/sdcard/Android/data/com.sourcenext.pocketalksp/files";
	private static String errorImagePath = System.getProperty("user.dir")+"\\assets\\errorImages\\";
	private  ArrayList<String> backButtons = new ArrayList<String>();
	ModelMainActivity objMainActivity;
	Model_MainMenuScreen objMainMenuScreen;
	ModelCameraActivityMain objCameraMain;
	Model_CapturedImagePreview objCameraCapturedImagePreview;
	Model_FromLanguageSelectionScreen objFromLanguage;
	Model_TranslationResultBlock objTrnaslationResult;
	Model_TTS_Screen ttsScreen;
	

	public PocketalkSupport (AndroidDriver<MobileElement> driver, String packageName){
		this.driver = driver;
		this.packageName=packageName;
		this.objMainActivity = new ModelMainActivity(driver);
		this.objMainMenuScreen = new Model_MainMenuScreen(driver);
		this.objCameraMain = new ModelCameraActivityMain(driver);
		this.objCameraCapturedImagePreview = new Model_CapturedImagePreview(driver);
		this.objFromLanguage = new Model_FromLanguageSelectionScreen(driver);
		this.objTrnaslationResult = new Model_TranslationResultBlock(driver);
		this.ttsScreen = new Model_TTS_Screen(driver);
		this.backButtons.add("btn_navigation_menu_left");
		this.backButtons.add("ib_back");
		this.backButtons.add("iv_top_back");
		this.backButtons.add("ib_top_back");
		this.backButtons.add("discard");
		this.backButtons.add("close_dialog");
		this.backButtons.add("imagebutton_top_back");
		this.backButtons.add("btn_ok");
		this.backButtons.add("//android.widget.LinearLayout/android.widget.Button");
		this.backButtons.add("android:id/button1");

	}

	public void pullCroppedImage() throws IOException {
		String filePath = System.getProperty("user.dir");
		byte[] returnData = driver.pullFile("/sdcard/Android/data/com.sourcenext.pocketalksp/files/Pictures/cropped.jpg");
		BufferedImage image=ImageIO.read(new ByteArrayInputStream(returnData));
		ImageIO.write(image, "jpg", new File(filePath+"\\assets\\temp\\","cropped.jpg"));
	}

	public void pullCapturedImage() throws IOException {
		String filePath = System.getProperty("user.dir");
		byte[] returnData = driver.pullFile("/sdcard/Android/data/com.sourcenext.pocketalksp/files/temp.jpg");
		BufferedImage image=ImageIO.read(new ByteArrayInputStream(returnData));
		ImageIO.write(image, "jpg", new File(filePath+"\\assets\\temp\\","temp.jpg"));
	}

	public void imagePush(String imageName) throws InterruptedException {
		try {
			File assetDir = new File(classpathRoot, "/assets/images/translation");
			File img = new File(assetDir.getCanonicalPath(), imageName);
			driver.pushFile(ANDROID_PHOTO_PATH + "/temp.jpg", img);
			Thread.sleep(1000);
		} catch (Exception e) {
			System.out.println("Push Failed!!");
		}
	}

	public boolean imageCompare() throws URISyntaxException, IOException {
		try {
			if (this.getImageSize("temp.jpg").get(0)!=this.getImageSize("cropped.jpg").get(0)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public byte[] getReferenceImageB64(String ImageName) throws URISyntaxException, IOException {
		BufferedImage bImage = ImageIO.read(new File(classpathRoot, "/assets/temp/"+ImageName));
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bImage, "jpg", bos );
		byte [] data = bos.toByteArray();
		return data;
	}

	public ArrayList<Integer> getImageSize(String imageName) throws IOException {
		ArrayList<Integer> size=new ArrayList<Integer>();
		File assetDir = new File(classpathRoot, "/assets/temp");
		BufferedImage bimg = ImageIO.read(new File(assetDir.getCanonicalPath(), imageName));
		size.add(bimg.getWidth());
		size.add(bimg.getHeight());
		return size;
	}

	public void backToHomeScreen() throws InterruptedException {
		String menu="no";
		while (menu.contains("no")) {
			for (int i = 0; i < backButtons.size(); i++) {
				try {
					if (backButtons.get(i).contains("//android.widget.LinearLayout/android.widget.Button")) {
						try {
							if (!driver.findElementByXPath(backButtons.get(i)).getId().isEmpty()) {
								driver.findElementByXPath(backButtons.get(i)).click();
								i=backButtons.size();
							}else {
								System.out.print("Ok Button Not Working ");
							}
						} catch (Exception e) {
							//							System.out.print("Ok Button Not found !!! ");
						}
					}else {
						if(!driver.findElementById(backButtons.get(i)).getId().isEmpty()) {
							if (backButtons.get(i).contains("btn_navigation_menu_left")) {
								menu="YES";
								i= backButtons.size();
								System.out.println("Home Activity Found !!");
							}else {
								driver.findElementById(backButtons.get(i)).click();
							}
						}
					}
				} catch (Exception e) {
					//					System.out.println("Back To Home Not working....!!!");
				}
			}			
		}				
		Thread.sleep(1000);
	}

	public void backToTargetScreenByCount(int count) throws InterruptedException {

		for (int j = 0; j < count; j++) {
			Thread.sleep(500);
			for (int i = 0; i <this.backButtons.size(); i++) {
				try {
					if (this.backButtons.get(i).contains("//android.widget.LinearLayout/android.widget.Button")) {
						try {
							if (!driver.findElementByXPath(this.backButtons.get(i)).getId().isEmpty()) {
								driver.findElementByXPath(this.backButtons.get(i)).click();
								i=this.backButtons.size();
							}else {
								//								System.out.print("Ok Button Not Working ");
							}
						} catch (Exception e) {
							//											System.out.print(" Ok Button Not found !!! ");
						}
					}else {
						if(!driver.findElementById(this.backButtons.get(i)).getId().isEmpty()) {
							driver.findElementById(this.backButtons.get(i)).click();
							i=this.backButtons.size();
						}else {
							//							System.out.print(" back Button Not found !!! ");
						}
					}
				}catch (Exception e) {
					//									System.out.println(e);
				}
			}
		}
		Thread.sleep(1000);
	}

	public void backToMyActivity(String myActivityName) throws InterruptedException {

		boolean match=false;
		while (!match) {
			for (int i = 0; i < backButtons.size(); i++) {
				try {
					if (backButtons.get(i).contains("//android.widget.LinearLayout/android.widget.Button")) {
						try {
							if (!driver.findElementByXPath(backButtons.get(i)).getId().isEmpty()) {
								driver.findElementByXPath(backButtons.get(i)).click();
								i=backButtons.size();
							}else {
								System.out.print("Ok Button Not Working ");
							}
						} catch (Exception e) {
							//							System.out.print("Ok Button Not found !!! ");
						}
					}else {
						if(!driver.findElementById(backButtons.get(i)).getId().isEmpty()) {
							if (driver.currentActivity().contains(myActivityName)) {
								match=true;
								i= backButtons.size();
								System.out.println("Home Activity Found !!");
							}else {
								driver.findElementById(backButtons.get(i)).click();
							}
						}
					}
				} catch (Exception e) {
					//					System.out.println("Back To Home Not working....!!!");
				}
			}			
		}				
		Thread.sleep(1000);
	}

	public boolean homeToCameraActivity() throws InterruptedException {
		objMainActivity.tapOnMenu();
		objMainMenuScreen.tapOnTargetMenu("Camera");
		if (driver.currentActivity().contains(objCameraMain.getActivityName())) {
			return true;
		}else {
			return false;
		}
	}

	public boolean homeToMainMenuActivity() throws InterruptedException {
		objMainActivity.tapOnMenu();
		if (driver.currentActivity().contains(objMainMenuScreen.getActivityName())) {
			return true;
		}else {
			return false;
		}
	}
	public boolean homeToPreviewScreen() throws InterruptedException {
		objMainActivity.tapOnMenu();
		objMainMenuScreen.tapOnTargetMenu("Camera");
		objCameraMain.captureImage();
		if (driver.currentActivity().contains(objCameraCapturedImagePreview.getActivityName())) {
			return true;
		}else {
			return false;
		}
	}

	public boolean homeToFromLanguageSelection() throws InterruptedException {
		objMainActivity.tapOnMenu();
		objMainMenuScreen.tapOnTargetMenu("Camera");
		objCameraMain.TapOnFromlanguageSelectionButton();
		if (driver.currentActivity().contains(objFromLanguage.getActivityName())) {
			return true;
		}else {
			return false;
		}
	}
	public boolean homeToTranslationResultsBlock(String imageName,String LanguageName) throws InterruptedException, IOException {
		objMainActivity.tapOnMenu();
		objMainMenuScreen.tapOnTargetMenu("Camera");
		if (this.selectCameraLanguage(packageName, "recycler_view", "Left", LanguageName)) {
			objCameraMain.captureImage();
			this.imagePush(imageName);
			objCameraCapturedImagePreview.tapOnTickButton();
			Thread.sleep(6000);
			objCameraCapturedImagePreview.tapOKOnNoTextAlert();
			Thread.sleep(1000);
			objCameraCapturedImagePreview.tapOnTickButton();
			Thread.sleep(10000);
			if (driver.currentActivity().contains(objTrnaslationResult.getActivityName())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean homeToTTSScreen(int index,String imageName, String languageName) throws InterruptedException, IOException {
		
		if (homeToTranslationResultsBlock(imageName, languageName)) {
			Thread.sleep(1000);
			int count = objTrnaslationResult.getTranslationResultBlock();
			if (count>0) {
				if (objTrnaslationResult.tapOnResultBlock(0)) {
					Thread.sleep(1000);
					return ttsScreen.isCloseButtonAvailable();
				}else {
					System.out.println("Failed to tap on block...");
					return false;
				}
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	
	public boolean cameraToTranslationResultsBlock(String imageName,String LanguageName) throws InterruptedException, IOException {
		if (this.selectCameraLanguage(packageName, "recycler_view", "Left", LanguageName)) {
			objCameraMain.captureImage();
			this.imagePush(imageName);
			objCameraCapturedImagePreview.tapOnTickButton();
			Thread.sleep(6000);
			objCameraCapturedImagePreview.tapOKOnNoTextAlert();
			Thread.sleep(1000);
			objCameraCapturedImagePreview.tapOnTickButton();
			Thread.sleep(10000);
			if (driver.currentActivity().contains(objTrnaslationResult.getActivityName())) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public boolean selectCameraLanguage(String packageName,String viewId,String Left_Or_Right, String languageName) throws InterruptedException, IOException {
		try {
			if (Left_Or_Right.contains("Left")) {
				driver.findElementById("tvLanguageShooting").click();
			}else {
				driver.findElementById("tvLanguageTranslate").click();
			}
			Thread.sleep(1000);
			MobileElement setLanguage = (MobileElement) driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().resourceId(\""+packageName+":id/"+viewId+"\").scrollable(true)).scrollIntoView(new UiSelector().textContains(\""+languageName+"\"));"));
			setLanguage.click();
			driver.findElementById("ib_top_back").click();
			Thread.sleep(1000);
			if (driver.currentActivity().contains(objCameraMain.getActivityName())) {
				return true;
			}else {
				return false;
			}
		} catch (Exception e) {
			this.errorScreenshot(languageName);
			System.out.println("Select Language Failed...!!!"+e);
			return false;
		}
	}
	
	public void errorScreenshot(String imageName) throws IOException, InterruptedException{
		File srcFile=driver.getScreenshotAs(OutputType.FILE);
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String datetime = formatter.format(calendar.getTime());
		datetime = datetime.replaceAll("[:-]", "").replaceAll("[ ]", "_");
		File targetFile=new File(errorImagePath + datetime+"_"+imageName+".jpg");
		FileUtils.copyFile(srcFile,targetFile);
		Thread.sleep(1000);
	}

}
