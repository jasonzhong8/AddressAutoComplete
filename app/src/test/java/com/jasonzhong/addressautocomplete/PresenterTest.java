package com.jasonzhong.addressautocomplete;

import android.content.Context;
import com.jasonzhong.addressautocomplete.insuranceCarriers.InsuranceCarrierContract;
import com.jasonzhong.addressautocomplete.insuranceCarriers.InsuranceCarrierIntractorImpl;
import com.jasonzhong.addressautocomplete.insuranceCarriers.InsuranceCarrierPresenterImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PresenterTest {

    private InsuranceCarrierIntractorImpl insuranceCarrierIntractor = new InsuranceCarrierIntractorImpl();
    private InsuranceCarrierContract.InsuranceCarrierView view = new InsuranceCarrierContract.InsuranceCarrierView() {
        @Override
        public void onResponseFailure(String message) {

        }

        @Override
        public void setDataToAutocomplete(ArrayList<String> insuranceCarrierArrayList) {

        }
    };
    private Context context = mock(Context.class);
    private InsuranceCarrierPresenterImpl presenter = new InsuranceCarrierPresenterImpl(context, view, insuranceCarrierIntractor);

    @Test
    public void onDestroyTest(){
        presenter.onDestroy();
        Assert.assertNull(view);
    }

   /* @Test
    public void requestDataFromJsonFileTest(){
        presenter.requestDataFromJsonFile();
    }*/

    @Test
    public void onFinishedTest(){
        ArrayList<String> caririers = new ArrayList<>();
        presenter.onFinished(caririers);
        verify(view, times(1)).setDataToAutocomplete(caririers);
    }

    @Test
    public void onFailureTest(){
        presenter.onFailure("error");
        verify(view, times(1)).onResponseFailure("error");
    }
}
