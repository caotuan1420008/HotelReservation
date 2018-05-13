package com.example.an.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.an.hotel.Adapter.MainActivity;
import com.example.an.hotel.Adapter.RoomListActivity;

import io.realm.Realm;

public class RoomDetailActivity extends AppCompatActivity {
    private Realm realm;
    public static String idDetail;
    public static String nameDetail;
    public static  RoomListActivity ttt;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
//        realm = Realm.getDefaultInstance();
        final EditText lblName = (EditText) findViewById(R.id.lblName1);
        final EditText lblBuilding = (EditText) findViewById(R.id.lblBuilding1);

        ttt = new RoomListActivity();
        lblName.setText(ttt.Name);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ttt.room.setCap(true);
                Intent mainIntent = new Intent(RoomDetailActivity.this,MainActivity.class);
                RoomDetailActivity.this.startActivity(mainIntent);
            }
        });
    }
}
