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

import com.example.an.hotel.Fragments.RoomAddFragment;
import com.example.an.hotel.R;
import com.example.an.hotel.RoomDetailActivity;
import com.example.an.hotel.models.roomObj;

import io.realm.Realm;
import io.realm.RealmResults;

public class AdminRoomList extends AppCompatActivity {
    private Realm realm;
    public String id;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_room_list);
        realm = Realm.getDefaultInstance();
        RealmResults<roomObj> rooms = realm.where(roomObj.class).findAll();
        rooms = rooms.sort("name");
        final RoomAdapterAdmin adapter = new RoomAdapterAdmin(this , rooms);
        listView = (ListView) findViewById(R.id.room_list);
        listView.setAdapter(adapter);
        //detail look
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final roomObj room = (roomObj) adapterView.getAdapter().getItem(i);
                // click on list
                Intent roomDetailIntent = new Intent(AdminRoomList.this,RoomDetailActivity.class);
                AdminRoomList.this.startActivity(roomDetailIntent);
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoomAddFragment dialog1 = new RoomAddFragment();
                dialog1.show(getFragmentManager(),"aab");
            }
        });
        //xoa + edit
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapter, View arg1,
                                           int pos, long id) {
                final roomObj room = (roomObj) adapter.getAdapter().getItem(pos);
                AlertDialog dialog = new AlertDialog.Builder(AdminRoomList.this)
                        .setTitle("Delete Task?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteTask(room.getId());
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
//                                deleteTask(task.getId());
                            }
                        })
                        .create();
                dialog.show();

                return true;
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
    private void deleteTask(final String roomID) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(roomObj.class).equalTo("id", roomID)
                        .findFirst()
                        .deleteFromRealm();
            }
        });
    }
    public String getId() {
        return id;
    }
}
