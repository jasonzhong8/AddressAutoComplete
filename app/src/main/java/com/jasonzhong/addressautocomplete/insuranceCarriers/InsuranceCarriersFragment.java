package com.jasonzhong.addressautocomplete.insuranceCarriers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.jasonzhong.addressautocomplete.R;
import com.jasonzhong.addressautocomplete.Util;

import java.util.ArrayList;
import java.util.List;

public class InsuranceCarriersFragment extends Fragment implements InsuranceCarrierContract.InsuranceCarrierView {

    private TextView next_textView;
    private AutoCompleteTextView autoCompleteTextView;
    private boolean isOptionSelected;
    private InsuranceCarrierContract.InsuranceCarrierPresenter presenter;

    public InsuranceCarriersFragment() {
    }

    public static InsuranceCarriersFragment newInstance() {
        InsuranceCarriersFragment fragment = new InsuranceCarriersFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insurance_carriers, container, false);
        autoCompleteTextView = view.findViewById(R.id.autocomplete_text);
        next_textView = view.findViewById(R.id.next_textView);
        next_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextPage();
            }
        });
        presenter = new InsuranceCarrierPresenterImpl(this.getContext(), this, new InsuranceCarrierIntractorImpl());
        presenter.requestDataFromJsonFile();

        return view;
    }

    private void goNextPage() {
        if (isOptionSelected) {
            isOptionSelected = false;
            Toast.makeText(this.getContext(),
                    "Done! ",
                    Toast.LENGTH_SHORT).show();
        } else {
            Util.alertDialogShow(this.getContext());
        }
    }

    private void setupAutoComplete(AutoCompleteTextView autoCompleteTextView, List<String> carriers) {
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this.getActivity(),
                android.R.layout.simple_list_item_1, carriers);
        autoCompleteTextView.setAdapter(itemsAdapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                isOptionSelected = true;
            }
        });
    }

    @Override
    public void setDataToAutocomplete(ArrayList<String> insuranceCarrierArrayList) {
        setupAutoComplete(autoCompleteTextView, insuranceCarrierArrayList);
    }

    @Override
    public void onResponseFailure(String errorMessage) {
        Toast.makeText(this.getContext(),
                "No Data Available !",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
