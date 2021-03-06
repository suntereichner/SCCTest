package com.borland.insurance.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.borland.insurance.Automobile;
import com.borland.insurance.DrivingRecord;
import com.borland.insurance.FinacialInfo;
import com.borland.insurance.Gender;
import com.borland.insurance.Keywords;
import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.common.CommonOptions;
import com.borland.silktest.jtf.common.TruelogScreenshotMode;
import com.borland.silktest.jtf.xbrowser.BrowserWindow;
import com.borland.silktest.jtf.xbrowser.DomButton;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.borland.silktest.jtf.xbrowser.DomLink;
import com.borland.silktest.jtf.xbrowser.DomListBox;

public class InsuranceWeb {

	private static Keywords keywords = new Keywords();

	private static Desktop desktop = new Desktop();
	private static BrowserWindow browser;

	@Before
	public void baseState() {
		browser = keywords.startBrowser().find("//BrowserWindow");
	}

	@Test
	public void autoQuote() {
		browser.<DomListBox> find("//SELECT[@id='quick-link:jump-menu']").select("Auto Quote");

		keywords.fillOutCommunicationInfo("4040", "john.smith@gmail.com", Automobile.CAR);
		keywords.fillOutGeneralInfo(30, Gender.MALE, DrivingRecord.EXCELLENT);
		keywords.fillOutCarInfo(2012, "Buick", "Electra", FinacialInfo.OWN);

		desktop.logInfo("Quote", TruelogScreenshotMode.ActiveWindow);
		DomElement quoteResultFinalp = browser.<DomElement> find("//B[@textContents='USD*']");
		Assert.assertEquals("USD 1.190,00", quoteResultFinalp.getText());
		desktop.<DomLink> find("//A[@textContents='Home']").domClick();
	}

	@Test
	public void login() {
		desktop.setOption(CommonOptions.OPT_TRUELOG_SCREENSHOT_MODE, TruelogScreenshotMode.ActiveWindow);
		Assert.assertEquals("John Smith", keywords.login("john.smith@gmail.com", "john"));
		desktop.logInfo("Logged-In", TruelogScreenshotMode.ActiveWindow);
		browser.<DomButton> find("//INPUT[@id='logout-form:logout']").select();
		desktop.setOption(CommonOptions.OPT_TRUELOG_SCREENSHOT_MODE, TruelogScreenshotMode.None);
	}
}