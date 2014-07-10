package co.slooky.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Intent;

public class CDSLUtility extends CordovaPlugin {

	public static final String OEPN_EXTERNAL_URL = "openExternalURL";
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		try {
			if (OEPN_EXTERNAL_URL.equals(action)) { 
				JSONObject arg_object = args.getJSONObject(0);
				Intent calIntent = new Intent(Intent.ACTION_EDIT)
				.setType("vnd.android.cursor.item/event")
				.putExtra("beginTime", arg_object.getLong("startTimeMillis"))
				.putExtra("endTime", arg_object.getLong("endTimeMillis"))
				.putExtra("title", arg_object.getString("title"))
				.putExtra("description", arg_object.getString("description"))
				.putExtra("eventLocation", arg_object.getString("eventLocation"));
			
				this.cordova.getActivity().startActivity(calIntent);
				callbackContext.success();
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