package beta;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.mozilla.universalchardet.UniversalDetector;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlParagraph;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.html.HtmlUnorderedList;

public class HtmlUnitTut {

	public void homePage(String blogURL) throws Exception {

		/* turn off annoying htmlunit warnings */
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(
				java.util.logging.Level.OFF);

		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getCookieManager().setCookiesEnabled(true);

		final HtmlPage page = webClient.getPage(blogURL);

		final String pageTitle = page.getTitleText();
		System.out.println(pageTitle);

		getAllArticles(page);
		webClient.closeAllWindows();
	}

	/**
	 * start from the global URL of blog provider
	 * */
	public void globalHomePage(String blogURL, String username, String password)
			throws Exception {

		/* turn off annoying htmlunit warnings */
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(
				java.util.logging.Level.OFF);

		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getCookieManager().setCookiesEnabled(true);
		
		// allow pop-up window
		webClient.getOptions().setPopupBlockerEnabled(false);
		// Ajax used?
//		webClient.setAjaxController(new NicelyResynchronizingAjaxController());

//		System.out.println("GETTING HOME PAGE...");
		HtmlPage page = webClient.getPage(blogURL);

		// final String pageTitle = page.getTitleText();
		// System.out.println(pageTitle);
		System.out.println("GETTING LOGIN FORM...");
		
//		HtmlDivision loginDivision = (HtmlDivision) page
//				.getFirstByXPath("//div[@class='login-form-top']");
//		System.out.println(loginDivision);
		
		final HtmlForm loginForm = (HtmlForm) page.getFirstByXPath("//form[@id='notLogin']");
//		System.out.println(loginForm);
		
//		final HtmlSubmitInput loginButton =  loginForm.getInputByName("loginButton");
		
//		final HtmlTextInput usrnHtmlTextInput = loginForm.getInputByName("loginName");
//		usrnHtmlTextInput.setText(username);
//		usrnHtmlTextInput.setValueAttribute(username);
//		System.out.println(usrnHtmlTextInput);
		
		final HtmlInput loginInput = page.getHtmlElementById("loginName");
		loginInput.setValueAttribute(username);
		System.out.println(loginInput);
		
		
		final HtmlInput passwordInput = page.getHtmlElementById("loginPass");
//		final HtmlPasswordInput pwdInput = loginForm.getInputByName("password");
//		pwdInput.setText(password);
		passwordInput.setValueAttribute(password);
		System.out.println(passwordInput);
		
		final HtmlPage page2 =  (HtmlPage) loginForm.getInputByValue("登录").click();
		System.out.println(page2);
		
//		HtmlAnchor loginButton = loginForm.getElementById("loginButton");
		
//		List<HtmlDivision> loginDivision1 = (List<HtmlDivision>) loginDivision
//				.getByXPath("//div[@class='ntopbar_login']");
//
//		System.out.println(loginDivision1);
//		
//		HtmlAnchor loginAnchor = (HtmlAnchor) page.getAnchorByHref("###");
//		System.out.println(loginAnchor);

		// List<HtmlDivision> loginDivisions = (List<HtmlDivision>) page
		// .getByXPath("//div[@class='ntopbar_main']");
		// System.out.println(loginDivisions);

//		List<HtmlAnchor> loginAnchor = (List<HtmlAnchor>) page
//				.getByXPath("//a[@action-type='tray-login-btn']");
//		System.out.println(loginAnchor);

		// getAllArticles(page);

		webClient.closeAllWindows();
	}
	
	/*
	 * starting from a specific blog URL
	 * */
	public void homePage(String blogURL, String username, String password)
			throws Exception {

		/* turn off annoying htmlunit warnings */
		java.util.logging.Logger.getLogger("com.gargoylesoftware").setLevel(
				java.util.logging.Level.OFF);

//		System.out.println("PRELIMINARY CONFIG...");
		final WebClient webClient = new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
		webClient.getOptions().setThrowExceptionOnScriptError(false);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setUseInsecureSSL(true);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getCookieManager().setCookiesEnabled(true);
		
		// allow pop-up window
		webClient.getOptions().setPopupBlockerEnabled(false);

		HtmlPage page = webClient.getPage(blogURL);

		// final String pageTitle = page.getTitleText();
		// System.out.println(pageTitle);
		System.out.println("GETTING LOGIN FORM...");
		
//		HtmlDivision loginDivision = (HtmlDivision) page
//				.getFirstByXPath("//div[@class='login-form-top']");
//		System.out.println(loginDivision);
		
//		HtmlForm loginForm = (HtmlForm) page.getFirstByXPath("//a[@class='login']");
//		System.out.println(loginForm);
		
		final HtmlInput loginInput = page.getHtmlElementById("login_name_d");
		System.out.println(loginInput);
		loginInput.setValueAttribute(username);
		
		
		final HtmlInput passwordInput = page.getHtmlElementById("login_pass_d");
//		final HtmlPasswordInput pwdInput = loginForm.getInputByName("password");
//		pwdInput.setText(password);
		passwordInput.setValueAttribute(password);
		System.out.println(passwordInput);
		
		final HtmlInput loginButton = page.getHtmlElementById("login_button");
		final HtmlPage page3 = (HtmlPage)loginButton.click();
		System.out.println(page3);
		
//		final HtmlPage page2 =  (HtmlPage) loginForm.getInputByValue("登录").click();
//		System.out.println(page2);
		
//		HtmlAnchor loginButton = loginForm.getElementById("loginButton");
		
//		List<HtmlDivision> loginDivision1 = (List<HtmlDivision>) loginDivision
//				.getByXPath("//div[@class='ntopbar_login']");
//
//		System.out.println(loginDivision1);
//		
//		HtmlAnchor loginAnchor = (HtmlAnchor) page.getAnchorByHref("###");
//		System.out.println(loginAnchor);

		// List<HtmlDivision> loginDivisions = (List<HtmlDivision>) page
		// .getByXPath("//div[@class='ntopbar_main']");
		// System.out.println(loginDivisions);

//		List<HtmlAnchor> loginAnchor = (List<HtmlAnchor>) page
//				.getByXPath("//a[@action-type='tray-login-btn']");
//		System.out.println(loginAnchor);

		// getAllArticles(page);

		webClient.closeAllWindows();
	}

	/**
	 * Get all articles from the article list
	 * */
	private void getAllArticles(HtmlPage page) {
		List<HtmlAnchor> articleList = (List<HtmlAnchor>) page
				.getByXPath("//div[@class='blognavInfo']/span/a");
		// System.out.println(articleList);
		for (HtmlAnchor htmlAnchor : articleList) {
			String articleAllStr = htmlAnchor.getTextContent();
			if (articleAllStr.equals("博文目录")) {
				// String articleAllLink = htmlAnchor.getHrefAttribute();
				try {
					HtmlPage firstArticlesPage = (HtmlPage) htmlAnchor
							.openLinkInNewWindow();

					// different pages
					// HtmlUnorderedList articlePageList = (HtmlUnorderedList)
					// allArticlesPage
					// .getFirstByXPath("//ul[@class='SG_pages']");
					// System.out.println(articlePageList);

					// System.out.println(pageItems);

					// Set<HtmlAnchor> uniqueArticlePages = new
					// LinkedHashSet<HtmlAnchor>(pageItems);
					// System.out.println(uniqueArticlePages.size());

					// the first page of articles
					fetchArticleInOnePage(firstArticlesPage);

					//
					List<HtmlAnchor> pageItems = (List<HtmlAnchor>) firstArticlesPage
							.getByXPath("//ul[@class='SG_pages']/li/a");
					// System.out.println(pageItems.size());

					for (int i = 0; i < pageItems.size() - 1; i++) {
						HtmlAnchor pageItem = pageItems.get(i);
						// System.out.println(pageItem.getHrefAttribute());

						HtmlPage articlesPage = (HtmlPage) pageItem
								.openLinkInNewWindow();

						fetchArticleInOnePage(articlesPage);
					}

				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			// break;
		}
	}

	/**
	 * @param allArticlesPage
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	private void fetchArticleInOnePage(HtmlPage allArticlesPage)
			throws MalformedURLException, IOException {
		List<HtmlDivision> artilesDivs = (List<HtmlDivision>) allArticlesPage
				.getByXPath("//div[@class='articleCell SG_j_linedot1']");

		// System.out.println(artilesDivs.size());
		// System.out.println(artilesDivs);

		for (int j = 0; j < artilesDivs.size(); j++) {

			HtmlDivision htmlDivision = artilesDivs.get(j);
			List<HtmlSpan> articleTimeStamps = (List<HtmlSpan>) htmlDivision
					.getByXPath("//p[@class='atc_info']//span[@class='atc_tm SG_txtc']");

			List<HtmlAnchor> articleURLs = (List<HtmlAnchor>) htmlDivision
					.getByXPath("//p[@class='atc_main SG_dot']//span[@class='atc_title']/a[@target='_blank']");
			if (articleTimeStamps.size() == articleURLs.size()) {

				for (int i = 0; i < articleTimeStamps.size(); i++) {
					// use timestamp as title of each file

					String timeStampStr = articleTimeStamps.get(i)
							.getTextContent();

					String articleTempName = parseTimeStamp(timeStampStr);
					System.out.println("Article temporary name: "
							+ articleTempName);

					// get artile title to add to the beginning of
					// each article
					String articleTitle = articleURLs.get(i).getTextContent();
					System.out.println("Article title: " + articleTitle);

					// get the content of each article

					HtmlAnchor artileURLAnchor = articleURLs.get(i);

					String articleURL = artileURLAnchor.getHrefAttribute();

					System.out.println("GETTING INTO: " + artileURLAnchor);

					HtmlPage articlePage = (HtmlPage) artileURLAnchor
							.openLinkInNewWindow();

					System.out.println("GETTING BACK WITH: " + articlePage);

					HtmlDivision articleContentDiv = (HtmlDivision) articlePage
							.getFirstByXPath("//div[@class='articalContent   ']");

					System.out.println(articleContentDiv);

					String articleContent = articleContentDiv.getTextContent();

					System.out.println(articleContent);

					String articleTitleAndContent = articleURL + '\n'
							+ articleTitle + articleContent;

					// store file in local hard disk
					File file = new File("atcls/" + articleTempName + ".txt");

					FileUtils.writeStringToFile(file, articleTitleAndContent);

				}
				break;
			} else {
				System.err
						.println("The number of time stamps does not match that of articles.");
			}

			// for (HtmlAnchor url : articleURLs) {
			//
			// }

			// System.out.println(articleURLs);
		}
	}

	private String parseTimeStamp(String timeStampStr) {
		// System.out.println(timeStampStr);

		return timeStampStr.replaceAll("-", "").replaceAll(":", "")
				.replaceAll(" ", "");
	}

	/**
	 * Get all the article snippets on the front page
	 * 
	 * @param page
	 */
	private void getHostPage(final HtmlPage page) {
		// List<HtmlAnchor> articles = (List<HtmlAnchor>)
		// page.getByXPath("//a[@class='lblentrylink']");
		List<HtmlAnchor> articleTitles = (List<HtmlAnchor>) page
				.getByXPath("//div[@class='bloglist']/div[@class='blog_title_h']/div[@class='blog_title']/a[@target='_blank']");
		// System.out.println(articleTitles);

		List<HtmlSpan> articleTimeStamp = (List<HtmlSpan>) page
				.getByXPath("//div[@class='bloglist']/div[@class='blog_title_h']/span[@class='time SG_txtc']");

		// System.out.println(articleTimeStamp);

		List<HtmlDivision> articleContents = (List<HtmlDivision>) page
				.getByXPath("//div[@class='content ']");

		// System.out.println(articleContents);

		// System.out.println(articleTitles.size());
		// System.out.println(articleTimeStamp.size());
		// System.out.println(articleContents.size());

		if ((articleTitles.size() == articleContents.size())
				&& (articleContents.size() == articleTimeStamp.size())) {
			for (int x = 0; x < articleContents.size(); x++) {
				// articles.get(x).getTextContent();
				// title
				System.out.println(articleTitles.get(x).getTextContent());
				// timestamp
				System.out.println(articleTimeStamp.get(x).getTextContent());

				System.out.println(x);
				// content
				HtmlDivision anchor = articleContents.get(x);
				if (anchor.hasChildNodes()) {

					System.out.println(anchor.getTextContent());

				} else {

					System.out.println("LEAVE: article content...");

					// System.out.println(anchor.getTextContent());
				}

			}
		} else {
			System.err
					.println("The number of article titles does not match the number of article content!");
		}
	}

	public void submittingForm() throws Exception {
		final WebClient webClient = new WebClient();

		// Get the first page
		final HtmlPage page1 = webClient
				.getPage("http://blog.sina.com.cn/tsuanwoo");

		// Get the form that we are dealing with and within that form,
		// find the submit button and the field that we want to change.
		final HtmlForm form = page1.getFormByName("myform");

		final HtmlSubmitInput button = form.getInputByName("submitbutton");
		final HtmlTextInput textField = form.getInputByName("userid");

		// Change the value of the text field
		textField.setValueAttribute("root");

		// Now submit the form by clicking the button and get back the second
		// page.
		final HtmlPage page2 = button.click();

		webClient.closeAllWindows();
	}

	public static void main(String[] args) {
		HtmlUnitTut tut = new HtmlUnitTut();
		String blogURL = "http://blog.sina.com.cn/tsuanwoo";
		String blogURLGlobal = "http://blog.sina.com.cn/";
		String username = "wuxichuan.go@gmail.com";
		String password = "1SONofLiGHT";
		try {
			// tut.homePage(blogURL);
			System.out.println("BLOGBROKER");
//			tut.globalHomePage(blogURLGlobal, username, password);
			tut.homePage(blogURL, username, password);
		} catch (Exception e) {
			// e.printStackTrace();
		}

	}

}
