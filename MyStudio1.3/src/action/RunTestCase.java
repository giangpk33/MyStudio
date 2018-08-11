package action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import connectmysql.MySQLConnect;
import main.frmMain;
import model.TestScript;
import util.WebDriverClass;

public class RunTestCase {

	// List<WebElement> listOfElements = new ArrayList<WebElement>();
	// WebElement element;
	// ReadElementLocators read = new ReadElementLocators();

	WebDriver driver;
	String LOG = null;

	// magic number=
	// millisec * sec * min * hours
	// 1000 * 60 * 60 * 24 = 86400000
	// 1000 * 60 = 60000
	public static final long MAGIC = 60000L;

	public void InitDriver() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void executeTestCase(ArrayList<TestScript> testscripList, int id_testsuite) {
		InitDriver();

		// Đổi ngày hiện tại ra string
		// Get the date today using Calendar object.
		Date today = Calendar.getInstance().getTime();
		String time = DateToString(today);

		// Khi Run, gán lại trạng thái true, false của toàn bộ testscript thành null
		String query = "UPDATE `mystudio`.`testscript` SET `RESULT` =" + '"' + '"' + " WHERE `ID_TESTCASE` = "
				+ testscripList.get(0).getId_testcase();
		MySQLConnect.executeSQLQuery(query);

		// Lần lượt chạy từng testscript
		for (int i = 0; i < testscripList.size(); i++) {
			TestScript scripts = testscripList.get(i);
			boolean result = true;
			switch (scripts.getItem()) {

			case ("CLICK"):
				result = click(scripts);
				break;
			case ("SUBMIT"):
				result = submit(scripts);
				break;
			case ("SENDKEY"):
				result = sendkey(scripts);
				break;
			case ("FIND"):
				WebElement element = findElement(scripts);

				// Do hàm findElement trả về 1 element hoặc null chứ không trả về true/false
				if (element == null) {
					result = false;
				}
				break;
			case ("OPEN_BROWSER"):
				result = open_browser(scripts);
				break;
			case ("VERIFY_EQUAL"):
				result = verify_equal(scripts);
				break;
			case ("PAGE_LOAD_TIMEOUT"):
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				result = true;
				break;
			case ("CHECK_VISIBLE"):
				result = check_visible(scripts);
				break;
			case ("CLOSE_BROWSER"):
				result = close_browser(scripts);
				break;
			case ("WAIT"):
				result = wait(scripts);
				break;
			case ("MAXIMIZE_WINDOW"):
				result = maximize(scripts);
				break;

			}

			// testscripList.get(i).setResult(String.valueOf(result));
			query = "UPDATE `mystudio`.`testscript` SET `RESULT` =" + '"' + String.valueOf(result) + '"'
					+ " WHERE `ID` = " + testscripList.get(i).getId();
			MySQLConnect.executeSQLQuery(query);

			if (result == true && (i == testscripList.size() - 1)) {
				query = "INSERT INTO `mystudio`.`result`(`ID_TESTCASE`,`TIME`,`RESULT`,`LOG`)VALUES(" + '"'
						+ testscripList.get(i).getId_testcase() + '"' + "," + '"' + time + '"' + "," + '"' + "Pass"
						+ '"' + "," + '"' + LOG + '"' + ")";
				MySQLConnect.executeSQLQuery(query);
				query = "INSERT INTO `mystudio`.`suite_case`(`ID_TESTCASE`,`ID_TESTSUITE`,`ID_RESULT`)VALUES ("
						+ testscripList.get(i).getId_testcase() + "," + id_testsuite
						+ ", (SELECT result.ID FROM mystudio.result where result.TIME = '" + time + "'))";
				MySQLConnect.executeSQLQuery(query);
			} else if (result == false) {
				// Xóa hết ký tự '"' vì insert vào SQL bị lỗi
				// System.out.println("/=================SQL================\n "+ LOG);
				LOG = LOG.replace('"', ' ');
				// System.out.println("/=================SQL================\n "+ LOG);
				query = "INSERT INTO `mystudio`.`result`(`ID_TESTCASE`,`TIME`,`RESULT`,`LOG`)VALUES(" + '"'
						+ testscripList.get(i).getId_testcase() + '"' + "," + '"' + time + '"' + "," + '"' + "Fail"
						+ '"' + "," + '"' + LOG + '"' + ")";
				// System.out.println(query);
				MySQLConnect.executeSQLQuery(query);
				query = "INSERT INTO `mystudio`.`suite_case`(`ID_TESTCASE`,`ID_TESTSUITE`,`ID_RESULT`)VALUES ("
						+ testscripList.get(i).getId_testcase() + "," + id_testsuite
						+ ", (SELECT result.ID FROM mystudio.result where result.TIME = '" + time + "'))";
				MySQLConnect.executeSQLQuery(query);
				driver.close();
				break;
			}

		}

	}

	private WebElement findElement(TestScript script) {
		try {
			WebElement element = null;
			WebDriverWait wait;
			switch (script.getObjects().getMethodType()) {
			case ("ID"):
				wait = new WebDriverWait(driver, 30);
				wait.until(
						ExpectedConditions.visibilityOfElementLocated(By.id(script.getObjects().getObjectLocators())));
				element = driver.findElement(By.id(script.getObjects().getObjectLocators()));
				break;
			case ("CSS"):
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.cssSelector(script.getObjects().getObjectLocators())));
				element = driver.findElement(By.cssSelector(script.getObjects().getObjectLocators()));
				break;
			case ("XPATH"):
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath(script.getObjects().getObjectLocators())));
				element = driver.findElement(By.xpath(script.getObjects().getObjectLocators()));
				break;
			case ("CLASS"):
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions
						.visibilityOfElementLocated(By.className(script.getObjects().getObjectLocators())));
				element = driver.findElement(By.className(script.getObjects().getObjectLocators()));
				break;

			}
			return element;
		} catch (Exception e) {
			// TODO: handle exception
			script.setResult("Fail");
			LOG = e.getMessage().substring(0, 200);
			return null;
		}
		// return null;
	}

	private boolean sendkey(TestScript scripts) {
		// TODO Auto-generated method stub
		// 6868682607535021
		try {
			WebElement element = findElement(scripts);
			if (element != null) {
				element.clear();
				element.sendKeys(scripts.getInput());
			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}
	}

	/**
	 * Click on Submit button
	 */
	private boolean submit(TestScript script) {
		try {
			WebElement element;
			WebDriverWait wait;
			switch (script.getObjects().getMethodType()) {
			case ("ID"):
				element = driver.findElement(By.id(script.getObjects().getObjectLocators()));
				wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
				wait.until(ExpectedConditions.elementToBeClickable(element)).submit();
				break;
			case ("CSS"):
				element = driver.findElement(By.cssSelector(script.getObjects().getObjectLocators()));
				wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
				wait.until(ExpectedConditions.elementToBeClickable(element)).submit();
				break;
			case ("XPATH"):
				element = driver.findElement(By.xpath(script.getObjects().getObjectLocators()));
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(element)).submit();
				break;
			case ("CLASS"):
				element = driver.findElement(By.className(script.getObjects().getObjectLocators()));
				wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
				wait.until(ExpectedConditions.elementToBeClickable(element)).submit();
				break;

			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}

	}

	// Lấy loại
	/**
	 * Click on button/checkbox/radio button
	 */
	public boolean click(TestScript script) {

		try {
			WebElement element;
			WebDriverWait wait;
			switch (script.getObjects().getMethodType()) {
			case ("ID"):
				element = driver.findElement(By.id(script.getObjects().getObjectLocators()));
				/*
				 * wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
				 * wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				 */
				element.click();
				break;
			case ("CSS"):
				element = driver.findElement(By.cssSelector(script.getObjects().getObjectLocators()));
				wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				break;
			case ("XPATH"):
				element = driver.findElement(By.xpath(script.getObjects().getObjectLocators()));
				wait = new WebDriverWait(driver, 30);
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				break;
			case ("CLASS"):
				element = driver.findElement(By.className(script.getObjects().getObjectLocators()));
				wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
				wait.until(ExpectedConditions.elementToBeClickable(element)).click();
				break;

			}
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}

	}

	private boolean verify_equal(TestScript script) {
		try {
			WebElement element = findElement(script);
			// Assert.assertEquals(script.getInput(), element.getText(), "expected ["
			// +script.getInput()+"]"+"but actual["+element.getText()+"]");
			Assert.assertEquals(element.getText(),script.getInput());
			return true;

		} catch (AssertionError e) {
			// TODO: handle exception
			LOG = e.getMessage();
			if (LOG.length() > 201) {
				LOG = LOG.substring(0, 200);
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}
	}

	// Hàm check_visible chỉ làm việc với XPath
	private boolean check_visible(TestScript script) {
		// TODO Auto-generated method stub
		try {
			switch (script.getObjects().getMethodType()) {
			case ("ID"):
				if (driver.findElement(By.id(script.getObjects().getObjectLocators())).isDisplayed())
					return true;
			case ("CSS"):
				if (driver.findElement(By.cssSelector(script.getObjects().getObjectLocators())).isDisplayed())
					return true;
				break;
			case ("XPATH"):
				if (driver.findElement(By.xpath(script.getObjects().getObjectLocators())).isDisplayed())
					return true;
				break;
			case ("CLASS"):
				if (driver.findElement(By.className(script.getObjects().getObjectLocators())).isDisplayed())
					return true;
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}
	}

	private boolean wait(TestScript scripts) {
		// TODO Auto-generated method stub
		try {
			int time = Integer.parseInt(scripts.getInput());
			Thread.sleep(time);
			return true;

		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}
	}

	private boolean open_browser(TestScript scripts) {
		// TODO Auto-generated method stub
		try {
			driver.get(scripts.getInput());
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}

	}

	private boolean close_browser(TestScript scripts) {
		// TODO Auto-generated method stub
		try {
			driver.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}

	}

	private boolean maximize(TestScript scripts) {
		// TODO Auto-generated method stub
		try {
			driver.manage().window().maximize();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOG = e.getMessage().substring(0, 200);
			return false;
		}
	}

	public int DateToMinutes(Date date) {
		// convert a date to an integer and back again
		long currentTime = date.getTime();
		currentTime = currentTime / MAGIC;
		System.out.println("current time = " + currentTime);
		return (int) currentTime;
	}

	public Date MinutesToDate(int minutes) {
		// convert integer back again to a date
		long currentTime = (long) minutes * MAGIC;
		return new Date(currentTime);
	}

	public String DateToString(Date date) {
		// the string representation of date (month/day/year)
		DateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");

		// Using DateFormat format method we can create a string
		// representation of a date with the defined format.
		String reportDate = df.format(date);

		return reportDate;
	}

}