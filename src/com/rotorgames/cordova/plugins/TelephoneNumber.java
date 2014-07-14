package com.rotorgames.cordova.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import android.content.Context;
import android.telephony.TelephonyManager;

public class TelephoneNumber extends CordovaPlugin {

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("get")) {
            TelephonyManager telephonyManager =
                (TelephonyManager)this.cordova.getActivity().getSystemService(Context.TELEPHONY_SERVICE);
            String simSerialNumber = telephonyManager.getSimSerialNumber();
            String line1Number = telephonyManager.getLine1Number();

            JSONObject json = new JSONObject();
            
            try{
            	json.put("simSerialNumber", simSerialNumber);
            	json.put("line1Number", line1Number);
            }catch(JSONException exc){}
            
           // String result = json.toString();
            if (json != null) {
                callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, json));
               return true;
            }
        }
        callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.ERROR));
        return false;
    }
}
