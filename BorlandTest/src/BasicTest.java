import org.junit.Before;
import org.junit.Test;

import com.borland.silktest.jtf.BrowserBaseState;
import com.borland.silktest.jtf.Desktop;
import com.borland.silktest.jtf.common.BrowserType;
import com.borland.silktest.jtf.common.CommonOptions;
import com.borland.silktest.jtf.common.TruelogScreenshotMode;
import com.borland.silktest.jtf.xbrowser.DomElement;
import com.borland.silktest.jtf.common.types.MouseButton;
import com.borland.silktest.jtf.common.types.Point;
import com.borland.silktest.jtf.xbrowser.DomLink;

public class BasicTest {

	private Desktop desktop = new Desktop();

	@Before
	public void baseState() {
		// Go to web page 'http://www.borland.com'
		BrowserBaseState baseState = new BrowserBaseState();
		baseState.setOption(CommonOptions.OPT_APPREADY_TIMEOUT, 60000);
		if ("true".equals(System.getProperty("#sctm_tunnel_to_internal_proxy"))
				&& baseState.getBrowserType().equals(BrowserType.GoogleChrome)) {
			baseState.setCommandLineArguments("--proxy-server=localhost:8080");
			baseState.setCommandLinePattern("*"); 
		}
		baseState.execute(desktop);
	}

	@Test
	public void validateAppearance() {
		// Add a screenshot of the browser to the result
		desktop.logInfo("Screenshot", TruelogScreenshotMode.ActiveApplication);
	}

	@Test
	public void simpleTest() {
		desktop.<DomElement> find("borland_com.BrowserWindow.Trials »").click(
				MouseButton.LEFT, new Point(22, 9));
		desktop.<DomLink> find("borland_com.BrowserWindow.Home").click(
				MouseButton.LEFT, new Point(32, 10));
	}
}