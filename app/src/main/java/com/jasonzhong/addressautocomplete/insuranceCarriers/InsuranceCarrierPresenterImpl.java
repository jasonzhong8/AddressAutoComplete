package com.jasonzhong.addressautocomplete.insuranceCarriers;

import android.content.Context;

import java.util.ArrayList;

public class InsuranceCarrierPresenterImpl implements InsuranceCarrierContract.InsuranceCarrierPresenter, InsuranceCarrierContract.InsuranceCarrierIntractor.OnFinishedListener {

    private Context mContext;
    private InsuranceCarrierContract.InsuranceCarrierView view;
    private InsuranceCarrierContract.InsuranceCarrierIntractor intractor;

    public InsuranceCarrierPresenterImpl(Context context, InsuranceCarrierContract.InsuranceCarrierView view, InsuranceCarrierContract.InsuranceCarrierIntractor intractor) {
        this.mContext = context;
        this.view = view;
        this.intractor = intractor;
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void requestDataFromJsonFile() {
        intractor.getInsuranceCarrierArrayList(mContext, this);
    }

    @Override
    public void onFinished(ArrayList<String> insuranceCarrierArrayList) {
        if (view != null) {
            view.setDataToAutocomplete(insuranceCarrierArrayList);
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        if (view != null) {
            view.onResponseFailure(errorMessage);
        }
    }
}
