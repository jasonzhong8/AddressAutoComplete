package com.jasonzhong.addressautocomplete;


import android.content.Context;
import com.jasonzhong.addressautocomplete.insuranceCarriers.InsuranceCarrierContract;
import com.jasonzhong.addressautocomplete.insuranceCarriers.InsuranceCarrierIntractorImpl;
import com.jasonzhong.addressautocomplete.insuranceCarriers.InsuranceCarrierPresenterImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class IntractorTest {

    private Context context = mock(Context.class);
    private InsuranceCarrierIntractorImpl insuranceCarrierIntractor = new InsuranceCarrierIntractorImpl();
    private InsuranceCarrierContract.InsuranceCarrierView view = new InsuranceCarrierContract.InsuranceCarrierView() {
        @Override
        public void onResponseFailure(String message) {

        }

        @Override
        public void setDataToAutocomplete(ArrayList<String> insuranceCarrierArrayList) {

        }
    };

    private InsuranceCarrierPresenterImpl presenter;


    @Before
    public void setup() {
        presenter = new InsuranceCarrierPresenterImpl(context, view, insuranceCarrierIntractor);
    }

    @Test
    public void getInsuranceCarrierArrayListTest() {
        insuranceCarrierIntractor.getInsuranceCarrierArrayList(context, presenter);
        verify(presenter, times(1)).onFinished(new ArrayList<>());
        verify(presenter, times(1)).onFailure("eror");
    }

}
