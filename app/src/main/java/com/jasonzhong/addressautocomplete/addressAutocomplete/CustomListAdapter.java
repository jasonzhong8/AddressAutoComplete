package com.jasonzhong.addressautocomplete.addressAutocomplete;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.jasonzhong.addressautocomplete.MainActivity;
import com.jasonzhong.addressautocomplete.R;

import java.util.List;

public class CustomListAdapter extends ArrayAdapter {

    private List<AddressAutocompleteFragment.NameAndPlaceId> dataList;
    private Context mContext;
    private int itemLayout;

    public CustomListAdapter(Context context, int resource, List<AddressAutocompleteFragment.NameAndPlaceId> storeDataLst) {
        super(context, resource, storeDataLst);
        dataList = storeDataLst;
        mContext = context;
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public AddressAutocompleteFragment.NameAndPlaceId getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        String[] des = getItem(position).name.split(",");
        final int length = des.length;
        StringBuilder sb = new StringBuilder();
        if (length > 0) {
            sb.append("<b>").append(des[0]).append("</b>");
        }
        if (length >= 1) {
            sb.append("<br/><font color=#A2AA86><small>");
            for (int i = 1; i < length; i++) {
                if (i != length - 1) {
                    sb.append(des[i]).append(",");
                } else {
                    sb.append(des[i]);
                }
            }
            sb.append("</small></font>");
        }

        TextView text1 = (TextView) view.findViewById(R.id.textView);
        text1.setText(Html.fromHtml(sb.toString()));

        return view;
    }
}
