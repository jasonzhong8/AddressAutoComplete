package com.jasonzhong.addressautocomplete.insuranceCarriers;

import android.content.Context;
import com.jasonzhong.addressautocomplete.Util;

import java.util.ArrayList;

public class InsuranceCarrierIntractorImpl implements InsuranceCarrierContract.InsuranceCarrierIntractor {

    @Override
    public void getInsuranceCarrierArrayList(Context context, OnFinishedListener onFinishedListener) {

        String jsonSt = Util.loadJSONFromAsset(context, "carriers.json");
        if (jsonSt == null) {
            onFinishedListener.onFailure("error");
        } else {
            ArrayList<String> carriers = Util.parseJSon(jsonSt, "insurance_carriers");
            if (carriers != null && carriers.size() > 0) {
                onFinishedListener.onFinished(carriers);
            } else {
                onFinishedListener.onFailure("error");
            }
        }
    }

}
