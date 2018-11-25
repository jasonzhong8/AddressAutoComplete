package com.jasonzhong.addressautocomplete.insuranceCarriers;

import android.content.Context;

import java.util.ArrayList;

public interface InsuranceCarrierContract {

    /**
     * Call when user interact with the view and other when view OnDestroy()
     */
    interface InsuranceCarrierPresenter {

        void onDestroy();

        void requestDataFromJsonFile();

    }

    interface InsuranceCarrierView {

        void setDataToAutocomplete(ArrayList<String> insuranceCarrierArrayList);

        void onResponseFailure(String errorMessage);

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface InsuranceCarrierIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<String> insuranceCarrierArrayList);

            void onFailure(String errorMessage);
        }

        void getInsuranceCarrierArrayList(Context context, OnFinishedListener onFinishedListener);
    }

}
