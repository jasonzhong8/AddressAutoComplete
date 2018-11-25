package com.jasonzhong.addressautocomplete;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Util {

    public static String loadJSONFromAsset(Context context, String fileName) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        return json;
    }

    public static ArrayList<String> parseJSon(String data, String tag) {
        ArrayList<String> allNames = new ArrayList<String>();
        try {
            JSONObject mainObject = new JSONObject(data);
            JSONArray uniJSONArray = mainObject.getJSONArray(tag);
            for (int i = 0; i < uniJSONArray.length(); i++) {
                String carrier = (String) uniJSONArray.get(i);
                allNames.add(carrier);
            }
            Log.d("", "" + allNames.size());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return allNames;
    }

    public static void alertDialogShow(Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.error_dialog, null);
        alertDialogBuilder.setView(dialogView);
        AlertDialog alertDialog = alertDialogBuilder.create();
        TextView textView = dialogView.findViewById(R.id.ok_textView);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
