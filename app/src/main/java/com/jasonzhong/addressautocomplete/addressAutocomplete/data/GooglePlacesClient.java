package com.jasonzhong.addressautocomplete.addressAutocomplete.data;

import com.jasonzhong.addressautocomplete.addressAutocomplete.model.PlaceAutocompleteResult;
import com.jasonzhong.addressautocomplete.addressAutocomplete.model.PlaceDetailsResult;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GooglePlacesClient {
    @GET("autocomplete/json?key=AIzaSyDCd_JYnWdyEUQjfrl5VRys56sxAsrr-xE&types=address")
    Observable<PlaceAutocompleteResult> autocomplete(@Query("input") String str);

    /*@GET("details/json")
    Observable<PlaceDetailsResult> details(@Query("placeid") String placeId);*/
}
