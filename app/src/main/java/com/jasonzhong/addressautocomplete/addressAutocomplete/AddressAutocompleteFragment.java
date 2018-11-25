package com.jasonzhong.addressautocomplete.addressAutocomplete;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jasonzhong.addressautocomplete.MainActivity;
import com.jasonzhong.addressautocomplete.R;
import com.jasonzhong.addressautocomplete.Util;
import com.jasonzhong.addressautocomplete.addressAutocomplete.data.RestClient;
import com.jasonzhong.addressautocomplete.addressAutocomplete.model.PlaceAutocompleteResult;
import com.jasonzhong.addressautocomplete.addressAutocomplete.model.Prediction;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddressAutocompleteFragment extends Fragment {

    private static final String TAG = "MainActivity";
    private static final long DELAY_IN_MILLIS = 600;
    public static final int MIN_LENGTH_TO_START = 2;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TextView next_textView;
    private boolean isOptionSelected;
    private MainActivity mCallback;

    public AddressAutocompleteFragment() {
    }

    public static AddressAutocompleteFragment newInstance() {
        AddressAutocompleteFragment fragment = new AddressAutocompleteFragment();
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
        View view = inflater.inflate(R.layout.fragment_address_autocomplete, container, false);
        next_textView = view.findViewById(R.id.next_textView);
        next_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goNextPage();
            }
        });
        final AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autocomplete_text);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                isOptionSelected = true;
                Log.d("", "");
            }
        });

        addOnAutoCompleteTextViewTextChangedObserver(autoCompleteTextView);

        return view;
    }

    private void addOnAutoCompleteTextViewTextChangedObserver(final AutoCompleteTextView autoCompleteTextView) {

        Observable<PlaceAutocompleteResult> autocompleteResponseObservable =
                RxTextView.textChangeEvents(autoCompleteTextView)
                        .debounce(DELAY_IN_MILLIS, TimeUnit.MILLISECONDS)
                        .map(textViewTextChangeEvent -> textViewTextChangeEvent.text().toString())
                        .filter(s -> s.length() >= MIN_LENGTH_TO_START)
                        .observeOn(Schedulers.io())
                        .switchMap(s -> RestClient.INSTANCE.getGooglePlacesClient().autocomplete(s))
                        .observeOn(AndroidSchedulers.mainThread())
                        .retry();

        compositeDisposable.add(
                autocompleteResponseObservable.subscribe(
                        placeAutocompleteResult -> {
                            List<NameAndPlaceId> list = new ArrayList<>();

                            for (Prediction prediction : placeAutocompleteResult.predictions) {
                                list.add(new NameAndPlaceId(prediction.description, prediction.id));
                            }

                            CustomListAdapter itemsAdapter = new CustomListAdapter(this.getContext(),
                                    R.layout.list_item, list);
                            autoCompleteTextView.setAdapter(itemsAdapter);
                            String enteredText = autoCompleteTextView.getText().toString();

                            if (list.size() >= 1 && enteredText.equals(list.get(0).name)) {
                                autoCompleteTextView.dismissDropDown();
                            } else {
                                autoCompleteTextView.showDropDown();
                            }
                        },
                        e -> Log.e(TAG, "onError", e),
                        () -> Log.i(TAG, "onCompleted")));
    }

    private void goNextPage() {
        if (isOptionSelected) {
            mCallback.goNext();
            isOptionSelected = false;
        } else {
            Util.alertDialogShow(this.getContext());
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
    }

    public static class NameAndPlaceId {
        final String name;
        final String placeId;

        NameAndPlaceId(String name, String placeId) {
            this.name = name;
            this.placeId = placeId;
        }

        @Override
        public String toString() {
            return name;
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (MainActivity) context;
        } catch (ClassCastException e) {
            Log.e(TAG, "ClassCastException", e);
        }
    }

}
