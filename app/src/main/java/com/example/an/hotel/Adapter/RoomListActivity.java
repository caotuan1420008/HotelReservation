package com.example.an.hotel.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.an.hotel.R;
import com.example.an.hotel.RoomDetailActivity;
import com.example.an.hotel.models.roomObj;

import io.realm.Realm;
import io.realm.RealmResults;

public class RoomListActivity extends AppCompatActivity {
    private Realm realm;
    public static String id;
    public static String Name;
    private ListView listView;
    public static roomObj room;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_list);
        realm = Realm.getDefaultInstance();
        RealmResults<roomObj> rooms = realm.where(roomObj.class).findAll();
        rooms = rooms.sort("name");
        final RoomAdapter adapter = new RoomAdapter(this , rooms);
        listView = (ListView) findViewById(R.id.room_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                 room = (roomObj) adapterView.getAdapter().getItem(i);
                // click on list
                Name = room.getName();
                id = room.getId();
                changeTaskDone(room.getId());
                Intent roomDetailIntent = new Intent(RoomListActivity.this,RoomDetailActivity.class);
                RoomListActivity.this.startActivity(roomDetailIntent);
            }
        });

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    public void setName(final String roomId) {

        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                roomObj room = realm.where(roomObj.class).equalTo("id", roomId).findFirst();
                Name = room.getName();
            }
        });
    }
    public void changeTaskDone(final String roomId) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                roomObj room = realm.where(roomObj.class).equalTo("id", roomId).findFirst();
                room.setCap(true);
            }
        });
    }
    public String getName() {
        return Name;
    }
}
