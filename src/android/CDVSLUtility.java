package co.slooky.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

public class CDVSLUtility extends CordovaPlugin {

	public static final String OEPN_EXTERNAL_URL = "openExternalURL";
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		try {
			if (OEPN_EXTERNAL_URL.equals(action)) { 
				JSONObject arg_object = args.getJSONObject(0);
				
				final CallbackContext callbackContextFin = callbackContext;
				
				AlertDialog.Builder builder = new AlertDialog.Builder(this.cordova.getActivity());
				builder.setTitle(arg_object.getString("title"));
				builder.setMessage(arg_object.getString("message"));
				
				builder.setPositiveButton(arg_object.getString("positiveButtonTitle"), new DialogInterface.OnClickListener() {

			        public void onClick(DialogInterface dialog, int which) {
			            // Do nothing but close the dialog
			        	callbackContextFin.success(1);
			            dialog.dismiss();
			        }

			    });
				
				builder.setNegativeButton(arg_object.getString("negativeButtonTitle"), new DialogInterface.OnClickListener() {

			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			            // Do nothing
			        	callbackContextFin.error(0);
			            dialog.dismiss();
			        }
			    });
				
				AlertDialog alert = builder.create();
			    alert.show();
				
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
				return true;
			}
			callbackContext.error("Invalid action");
			return false;
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		} 
	}
	
	private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
	
}
