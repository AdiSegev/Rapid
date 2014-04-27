package com.example.rapid;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

@SuppressLint("JavascriptInterface")
public class MainActivity extends Activity {

	WebView myWebView;

	private Handler mHandler = new Handler();

	public DemoJavaScriptInterface jsInterface = new DemoJavaScriptInterface();
	private int[] driversIds = { 12, 23, 65, 76 };

	/**
	 * Used to show custom circle progress bar dialog
	 */
	ProgressDialogActivity progressDialog;

	public String currentCourse = "";

	
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
	@JavascriptInterface
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.activity_main);
		myWebView = (WebView) findViewById(R.id.webview);

		// init progressDialog, the second parameter used to define dialog's
		// type
		progressDialog = new ProgressDialogActivity(MainActivity.this, 1);

		WebSettings webSettings = myWebView.getSettings();

		webSettings.setJavaScriptEnabled(true);
		webSettings.setDomStorageEnabled(true);

		myWebView.addJavascriptInterface(new DemoJavaScriptInterface(),
				"jsBridge");
		myWebView.setWebChromeClient(new MyWebChromeClient());
		
		// needed to enable access to local files by ajax
		if (Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) 
			myWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
		
		myWebView.loadUrl("file:///android_asset/index.html");

	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
			myWebView.goBack();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	final class DemoJavaScriptInterface {

		DemoJavaScriptInterface() {
		}

		/**
		 * This method calls JavaScript methods This is not called on the UI
		 * thread. Post a runnable to invoke loadUrl on the UI thread.
		 */
		@JavascriptInterface
		public void androidToJs(final String url) {
			mHandler.post(new Runnable() {
				public void run() {
					System.out.println("sending message ");

					if (myWebView.isShown()) {
						progressDialog.dismiss();
						System.out.println("webview shown");
						myWebView.loadUrl(url);
					}

				}
			});

		}

		/**
		 * This method uses to handle calles from JavaScript
		 * @throws JSONException 
		 */

		@JavascriptInterface
		public void handleJs(String action, String message) throws JSONException {

			System.out.println("got js!!!!!!!!!!!  " + message + " action = "
					+ action);

			if ("verifyDriverId".equalsIgnoreCase(action)) {
				System.out.println("message = " + message);

				// Progress Dialog until getting current position
				progressDialog.show();

				verifyDriverId(Integer.valueOf(message));

			} else {
				if ("changeTemp".equalsIgnoreCase(action)) {
					System.out.println("message = " + message);
					getTemp();
				} else {
					if ("getClientsList".equalsIgnoreCase(action)) {
						System.out.println("message = " + message);
						
						if (!currentCourse.equalsIgnoreCase(message)){
							currentCourse = message;
							clients.clear();
							getClientsList(message);
						}
						else {
							getClientsList(message);
						}
						
					}
					
					else {
						if ("getCoursesList".equalsIgnoreCase(action)){
							System.out.println("message = " + message);
							getCoursesList();
							
						}
						else {
							if ("getNewCourse".equalsIgnoreCase(action)){
								checkNewCourse(message);
							}
						}
					}

				}
			}

		}
		
	}

	/**
	 * This method uses to verify driver id if the id is valid, it would send
	 * the driver name as the second parameter if the id isn't valid, send false
	 * as second parameter
	 * 
	 * @param id
	 *            - the driver id to verify
	 */
	public void verifyDriverId(int id) {

		for (int i = 0; i < driversIds.length; i++) {

			if (id == driversIds[i]) {

				loadUrl("driverAuthentication", "true");
				return;
			}

		}
		loadUrl("driverAuthentication", "false");
	}

	/**
	 * @param message
	 * @throws JSONException 
	 */
	public void checkNewCourse(String message) throws JSONException {
		
		// for test -- means user entered valid course, send course's clients list back
		if ("10".equalsIgnoreCase(message)){
			getClientsList(message);
		}
		else{
			loadUrl("courseDenied", null);
		}
		
	}

	/**
	 * 
	 */
	public void getTemp() {
		int temp = (int) (Math.random() * 80) - 30;
		loadUrl("tempChanged", String.valueOf(temp));
	}

	public void loadUrl(String action, String message) {
		String url;
		url = "javascript:androidCallBack('" + action + "','" + message + "')";

		jsInterface.androidToJs(url);
	}

	private class MyWebChromeClient extends WebChromeClient {

		@Override
		public boolean onJsAlert(WebView view, String url, String message,
				final JsResult result) {
//			
//			if ("זיהוי משתמש".equalsIgnoreCase(message)){
//			new AlertDialog.Builder(MainActivity.this)
//					.setTitle("תנובה")
//					.setMessage(message)
//					.setPositiveButton("אישור",
//							new AlertDialog.OnClickListener() {
//
//								public void onClick(DialogInterface dialog,
//										int which) {
//									// do your stuff here
//									result.confirm();
//								}
//							}).setCancelable(false).create().show();
//			return true;
//			}
//			
//			else{
//				if ("updateClientLocation".equalsIgnoreCase(message)){
//					Intent intent = new Intent (getApplicationContext(), DialogActivity.class);
//					intent.putExtra("type", DialogActivity.UPDATECLIENTLOCATION);
//					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//					startActivity(intent);
//				}
//				return true;
//			}
			return true;
		}

	}

	
	/**
	 * contains list of the different courses
	 * 
	 * Integer course number, String name
	 */
	private HashMap<Integer, String> coursesList = new HashMap<Integer, String>();
	
	
	/**
	 * get courses list and populate courseList list
	 * if there's no course, return one member with values (0,0).
	 * @throws JSONException 
	 */
	
	
	private void getCoursesList() throws JSONException {
		// TODO Auto-generated method stub
		coursesList.put(10,"משה");
		coursesList.put(20,"שלום");
		
		prepareCoursesJsonList(coursesList);
	}
	
	/**
	 * this method puts the courses list into one Json object
	 * @param courses - list of courses 
	 * @throws JSONException
	 */
	public void prepareCoursesJsonList(HashMap<Integer, String> courses)
			throws JSONException {

		JSONArray jsonCoursesList = new JSONArray();
		
		
		for (Entry<Integer, String> entry : courses.entrySet()) {
			JSONObject jsonCourse = new JSONObject();
			
			jsonCourse.put("courseNumber", entry.getValue());
			jsonCourse.put("courseName", entry.getKey());
			
			jsonCoursesList.put(jsonCourse);
			
		}
		
		sendCoursesList(jsonCoursesList);
	}
	
	/**
	 * this method prepare the url to send back to javascript with the courses Json object
	 * @param jsonCoursesList - Json object with courses
	 */
	public void sendCoursesList(JSONArray jsonCoursesList) {
		loadUrl("coursesList", jsonCoursesList.toString());
	}
	
	
	/**
	 * Contains list of the clients. 
	 * 
	 * String name, boolean hasLocation.
	 */
	private HashMap<String, Boolean> clients = new HashMap<String, Boolean>();

	/**
	 * This method populate the client's map object with the list of clients.
	 * @throws JSONException
	 * 
	 */
	public void getClientsList(String message) throws JSONException {
		
		switch (Integer.valueOf(message)){
		
		case 10:
		clients.put("מכולת דני 10", true);
		clients.put("מכולת יוסי 10", false);
		break;
		
		case 20:
			clients.put("מכולת דני 20", true);
			clients.put("מכולת יוסי 20", false);
			break;
		}
		prepareJsonList(clients);
	}

	/**
	 * This method gets clients map contains list of clients. 
	 * Each client has it's name as key and boolean value meaning if there's knwon address of
	 * client. make one json array object that contains list of json objects. Each one
	 * of the inner json objects represent one client and has 2 fields: String
	 * name, Boolean hasLocation.
	 * @return 
	 * 
	 * @return json object contains list of json objects.
	 * 
	 * @throws JSONException
	 */
	public void prepareJsonList(HashMap<String, Boolean> clients)
			throws JSONException {

		JSONArray jsonClientsList = new JSONArray();
		
		
		for (Entry<String, Boolean> entry : clients.entrySet()) {
			JSONObject jsonClient = new JSONObject();
			
			jsonClient.put("hasLocation", entry.getValue());
			jsonClient.put("name", entry.getKey());
			
			jsonClientsList.put(jsonClient);
			
		}
		
		sendClientsList(jsonClientsList);
	}

	/**
	 * This method convert the clientsList to String
	 * @param jsonClientsList
	 */
	public void sendClientsList(JSONArray jsonClientsList) {
		loadUrl("clientsList", jsonClientsList.toString());
	}
}
