/*
 *
 * Copyright 2014 SLOOKY LLC
 * http://slooky.co
 *
*/

package co.slooky.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;


import android.os.Build;

public class CDVSLUtility extends CordovaPlugin {

	public static final String OEPN_EXTERNAL_URL = "openExternalURL";
	
	private CallbackContext callbackContext;
	
	private abstract class CDVSLUtilityRunnable implements Runnable {
	    public CallbackContext callbackContext;
	    CDVSLUtilityRunnable(CallbackContext cb) {
	      this.callbackContext = cb;
	    }
	  }
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext pCallbackContext) throws JSONException {
		this.callbackContext = pCallbackContext;
		try {
			if (OEPN_EXTERNAL_URL.equals(action)) { 
				return this.openExternalURL(args.getString(0), args.getString(1), args.getString(2), args.getString(3), args.getString(4));
			}
			callbackContext.error("Invalid action");
			return false;
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		} 
	}
	
	private boolean openExternalURL (String urlString, String title, String message, String positiveButtonTitle, String negativeButtonTitle) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this.cordova.getActivity());
		builder.setTitle(title);
		builder.setMessage(message);
		final CallbackContext callbackContextFin = this.callbackContext;
		builder.setPositiveButton((positiveButtonTitle != null) ? negativeButtonTitle : "Yes" , new DialogInterface.OnClickListener() {

	        public void onClick(DialogInterface dialog, int which) {
	            // Do nothing but close the dialog
	        	callbackContextFin.success(1);
	            dialog.dismiss();
	        }

	    });
		builder.setNegativeButton((negativeButtonTitle != null) ? negativeButtonTitle : "No", new DialogInterface.OnClickListener() {

	        @Override
	        public void onClick(DialogInterface dialog, int which) {
	            // Do nothing
	        	callbackContextFin.error(0);
	            dialog.dismiss();
	        }
	    });
		AlertDialog alert = builder.create();
	    alert.show();
		return true;
	}
	
	private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
	

    /*
	Intent calIntent = new Intent(Intent.ACTION_EDIT)
	.setType("vnd.android.cursor.item/event")
	.putExtra("beginTime", arg_object.getLong("startTimeMillis"))
	.putExtra("endTime", arg_object.getLong("endTimeMillis"))
	.putExtra("title", arg_object.getString("title"))
	.putExtra("description", arg_object.getString("description"))
	.putExtra("eventLocation", arg_object.getString("eventLocation"));

	this.cordova.getActivity().startActivity(calIntent);
	*/
	
}
