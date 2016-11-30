package com.teamnemitoff.phonedialer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.telephony.TelephonyManager;

public class PhoneDialer extends CordovaPlugin {
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	    try {
			Context context = this.cordova.getActivity().getApplicationContext(); 
			TelephonyManager telMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
			if(telMgr.getSimState() != TelephonyManager.SIM_STATE_ABSENT) {
				String phoneNumber = args.getString(0);
				Uri uri = Uri.parse("tel:"+phoneNumber);
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(uri);
				this.cordova.getActivity().startActivity(callIntent);
				callbackContext.success();
				return true;
			}
        } catch (Exception e) {
        	String msg = "Exception Dialing Phone Number: " + e.getMessage();
        	System.err.println(msg);
	        callbackContext.error(msg);
        }
		return false;
	}

}
