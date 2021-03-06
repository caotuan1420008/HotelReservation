package com.example.an.hotel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.an.hotel.models.roomObj;

import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

public class RoomDetailAdapter extends RealmBaseAdapter<roomObj> implements ListAdapter {
    private RoomDetailActivity activity;
    private static class ViewHolder {
        TextView roomName;
    }

    RoomDetailAdapter(RoomDetailActivity activity, OrderedRealmCollection<roomObj> data) {
        super(data);
        this.activity = activity;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.room_list_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.roomName = (TextView) convertView.findViewById(R.id.room_item_name);
//            viewHolder.roomName.setOnClickListener(listener);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (adapterData != null) {
            roomObj room = adapterData.get(position);
            viewHolder.roomName.setText(room.getName());
        }

        return convertView;
    }
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
                // open room detail

        }
    };
}
