package com.example.an.hotel.Adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.an.hotel.FinderActivity;
import com.example.an.hotel.R;
import com.example.an.hotel.RoomDetailActivity;
import com.example.an.hotel.models.roomObj;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private Realm realm;
    public static String id;
    public static String Name;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RoomListActivity ttt = new RoomListActivity();
        realm = Realm.getDefaultInstance();
        RealmQuery<roomObj> query  = realm.where(roomObj.class);
        query.equalTo("Cap", true);
        RealmResults<roomObj> rooms = query.findAll();
//        RealmResults<roomObj> rooms = realm.where(roomObj.class).findAll();
        rooms = rooms.sort("name");
        final MainUserAdapter adapter = new MainUserAdapter(this , rooms);
        listView = (ListView) findViewById(R.id.room_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final roomObj room = (roomObj) adapterView.getAdapter().getItem(i);
                // click on list
                Name = room.getName();
                id = room.getId();
                Intent roomDetailIntent = new Intent(MainActivity.this,RoomDetailActivity.class);
                MainActivity.this.startActivity(roomDetailIntent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View arg1,
                                           int pos, long id) {
                final roomObj room = (roomObj) adapter.getAdapter().getItem(pos);
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Delete room?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteUserMainList(room.getId());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        })
                        .create();
                dialog.show();
//                finish();
//                startActivity(getIntent());
                return true;
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent finderIntent = new Intent(MainActivity.this,FinderActivity.class);
                MainActivity.this.startActivity(finderIntent);
            }
        });
    }
    private void deleteUserMainList(final String roomID) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(roomObj.class).equalTo("id", roomID)
                        .findFirst().setCap(false);

            }
        });
    }
}
