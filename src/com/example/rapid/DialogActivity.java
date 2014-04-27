package com.example.rapid;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This class handles different dialog being used by the application.
 * Each dialog type has it's own id.
 * @author isr
 *
 */
public class DialogActivity extends Activity {

	/**
	 * dialog shown when updating client's location. 
	 */
	public static final int UPDATECLIENTLOCATION = 1;
	
	/**
	 * Show current spm server ip 
	 */
	static final int SPMIPSERVERCONFIG = 2;
	
	/**
	 * Show dialog when driver chosed to tow bus
	 */
	static final int TOWTOGARAGE = 3;

	

	static String text = "";
	static String messageOnCancelDialog = "";

	
	/** Called when the activity is first created. */

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// need to enable communication with sever
		disableWiFi();
		
		setContentView(R.layout.activity_dialog);
		
		// gets the dialog's type to show
		if (getIntent().hasExtra("type"))
			showDialog(getIntent().getIntExtra("type", 1));
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		
		switch (id) {
		
		case UPDATECLIENTLOCATION:
			final AlertDialog alertDialog = new AlertDialog.Builder(this).create();  
			alertDialog.setTitle("Alert 3");  
			alertDialog.setMessage("00:10");
			alertDialog.show();   // 

			new CountDownTimer(10000, 1000) {
			    @Override
			    public void onTick(long millisUntilFinished) {
			       alertDialog.setMessage("00:"+ (millisUntilFinished/1000));
			    }

			    @Override
			    public void onFinish() {
			    	alertDialog.dismiss();
			    	DialogActivity.this.finish();
			    }
			}.start();
			// Create out AlterDialog
//			Builder builder = new AlertDialog.Builder(this);
//			
//			builder.setMessage(R.string.updateClientLocation);
//			builder.setCancelable(true);
//			builder.show(); 
//						new CountDownTimer(10000, 1000) {
//						    @Override
//						    public void onTick(long millisUntilFinished) {
//						       alertDialog.setMessage("00:"+ (millisUntilFinished/1000));
//						    }
			//
//						    @Override
//						    public void onFinish() {
//						    	alertDialog.dismiss();  
//						    }
//						}.start();
					
//			builder.setPositiveButton(R.string.openGpsSettings,
//					new OkOnClickListener());
//			
//			messageOnCancelDialog = "אין אפשרות לקבל מיקום!";
//			builder.setNegativeButton(R.string.cancelNewMission, new CancelOnClickListener());
//			
//			AlertDialog dialog = builder.create();
//			
//			dialog.show();
			
			
			break;
//		
//		case SPMIPSERVERCONFIG:
//			
//			Builder builder1 = new AlertDialog.Builder(this);
//			LayoutInflater factory = LayoutInflater.from(this);
//			
//			//
//			final View textEntryView = factory.inflate(	R.layout.spm_server_ip_config, null);
//			EditText et = (EditText) textEntryView.findViewById(R.id.spmServerIp_edit);
//			
//			et.setHint(InfoForUser.spmIpServerIp.toString().substring(1));
//			
//			InputFilter[] filters = new InputFilter[1];
//			
//			// handle new entered values and validate input
//			filters[0] = new InputFilter() {
//			
//				public CharSequence filter(CharSequence source, int start,
//						int end, Spanned dest, int dstart, int dend) {
//					if (end > start) {
//						String destTxt = dest.toString();
//						String resultingTxt = destTxt.substring(0, dstart)
//								+ source.subSequence(start, end)
//								+ destTxt.substring(dend);
//						if (!resultingTxt
//								.matches("^\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3}(\\.(\\d{1,3})?)?)?)?)?)?")) {
//							text = "illegal input";
//							return null;
//						} else {
//							String[] splits = resultingTxt.split("\\.");
//							for (int i = 0; i < splits.length; i++) {
//								if (Integer.valueOf(splits[i]) > 255) {
//									text = "illegal input";
//									return null;
//								} else {
//									text = resultingTxt;
//								}
//							}
//						}
//					}
//					return null;
//				}
//			};
//
//			et.setFilters(filters);
//
//			builder1.setView(textEntryView);
//			builder1.setMessage("SPM Server IP");
//			builder1.setCancelable(true);
//			builder1.setPositiveButton(R.string.confirmPartListButton, new OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					System.out.println("text = " + text);
//					if ("illegal input".equalsIgnoreCase(text)) {
//						Toast.makeText(getApplicationContext(),
//								"כתובת IP לא עודכנה, כתובת IP אינה חוקית. ",
//								Toast.LENGTH_SHORT).show();
//					} else {
//						if ("".equalsIgnoreCase(text)) {
//							return;
//						}
//						try {
//							InfoForUser.setSpmIP(InetAddress.getByName(text));
//							Toast.makeText(getApplicationContext(),
//									"New IP Saved: " + text, Toast.LENGTH_LONG)
//									.show();
//							DialogActivity.this.finish();
//						} catch (UnknownHostException e) {
//							e.printStackTrace();
//						}
//
//					}
//
//				}
//			});
//			messageOnCancelDialog = "כתובת IP לא עודכנה.";
//			
//			AlertDialog dialog1 = builder1.create();
//
//			dialog1.setOnDismissListener(new OnDismissListener() {
//
//				@Override
//				public void onDismiss(DialogInterface dialog) {
//					// TODO Auto-generated method stub
//					System.out.println("on dismiss");
//
//					DialogActivity.this.finish();
//				}
//			});
//			dialog1.show();
//			break;
//		
//		case TOWTOGARAGE:
//		
//			// Create out AlterDialog
//			Builder builder2 = new AlertDialog.Builder(this);
//			
//			builder2.setMessage(R.string.startNavigateToGarage);
//			builder2.setCancelable(true);
//
//			messageOnCancelDialog = "בחזרה לעמוד ראשי";
//			
//			builder2.setPositiveButton(R.string.startNavigation, new OnClickListener() {
//
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//
//					if (getIntent().hasExtra("latLonDetails")) {
//						System.out.println("got intent "
//								+ getIntent().getIntExtra("latLonDetails", 0));
//						Coordinates coordinates = TowingTicket.garageCoordinates[getIntent()
//								.getIntExtra("latLonDetails", 0)];
//						String lat = Double.toString(coordinates.lat);
//						String lon = Double.toString(coordinates.lon);
//
//						System.out.println("navigate to " + lon);
//						
//						// try navigating with waze
//						try {
//
//							String url = "waze://?ll=" + lat + "," + lon
//									+ "&navigate=yes";
//							System.out.println(url);// TEST
//							Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri
//									.parse(url));
//							startActivity(intent3);
//							DialogActivity.this.finish();
//
//						} 
//						// in case waze not installed, open Waze site at Google Play
//						catch (ActivityNotFoundException ex) {
//							
//							Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri
//									.parse("market://details?id=com.waze"));
//							startActivity(intent3);
//							DialogActivity.this.finish();
//							ex.printStackTrace();
//
//						}
//					}
//
//				}
//			});
//
//			builder2.setNegativeButton(R.string.backToMainScreen,new CancelOnClickListener());
//			
//			AlertDialog dialog2 = builder2.create();
//			dialog2.setOnDismissListener(new OnDismissListener() {
//
//				@Override
//				public void onDismiss(DialogInterface dialog) {
//					System.out.println("on dismiss");
//					DialogActivity.this.finish();
//				}
//			});
//			dialog2.show();
//			break;

		}
		return super.onCreateDialog(id);
	}

	private final class CancelOnClickListener implements
			DialogInterface.OnClickListener {

		public void onClick(DialogInterface dialog, int which) {
			
			if ( "אין אפשרות לקבל מיקום!".equalsIgnoreCase(messageOnCancelDialog)){
			Toast.makeText(getApplicationContext(), messageOnCancelDialog,
					Toast.LENGTH_LONG).show();}
			
			setResult(RESULT_CANCELED);
			DialogActivity.this.finish();

			if ("בחזרה לעמוד ראשי".equalsIgnoreCase(messageOnCancelDialog)) {
//				Intent intent = new Intent(getApplicationContext(),
//						MissionsTableActivity.class);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
			}

		}
	}

	private final class OkOnClickListener implements
			DialogInterface.OnClickListener {
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);
			Intent intent1 = new Intent();
			intent1.putExtra("GpsSettingsOpened", true);
			setResult(RESULT_OK, intent1);
			DialogActivity.this.finish();

		}

//		private final class SPMOnClickListener implements
//				DialogInterface.OnClickListener {
//
//			public void onClick(DialogInterface dialog, int which) {
//				Toast.makeText(getApplicationContext(),
//						"Apllication is closing", Toast.LENGTH_LONG).show();
//				setResult(RESULT_CANCELED);
//				DialogActivity.this.finish();
//
//			}
//		}
	}

	@Override
	protected void onResume() {
		disableWiFi();
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	// disable WiFi connection
	public void disableWiFi() {
		System.out.println("in disableWiFi");
		ConnectivityManager conMan = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = conMan.getActiveNetworkInfo();

		try {
			if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
//				MainActivity.previousWiFiState = true;
				Log.d("WifiReceiver", "Have Wifi Connection");
				WifiManager wifi = (WifiManager) getSystemService(MainActivity.WIFI_SERVICE);
				wifi.setWifiEnabled(false);
			}
		} catch (Exception ex) {

			System.out.println("no netInfo" + ex.getMessage());
		}
	}

}