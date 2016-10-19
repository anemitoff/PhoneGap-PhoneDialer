package com.teamnemitoff.phonedialer;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class PhoneDialer extends CordovaPlugin {
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
	    try {
	TelephonyManager telMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    	if(tm.getSimState() != TelephonyManager.SIM_STATE_ABSENT)
		    {
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
	        return false;
        }
	}
}
