package com.jasonzhong.addressautocomplete;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.jasonzhong.addressautocomplete.addressAutocomplete.AddressAutocompleteFragment;
import com.jasonzhong.addressautocomplete.insuranceCarriers.InsuranceCarriersFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.placeholder, AddressAutocompleteFragment.newInstance());
        ft.commit();
    }

    public void goNext(){
        Fragment fragment = new InsuranceCarriersFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.placeholder, fragment);
        transaction.commit();
    }

}
