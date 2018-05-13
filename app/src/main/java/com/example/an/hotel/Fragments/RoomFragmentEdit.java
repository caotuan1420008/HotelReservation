package com.example.an.hotel.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.an.hotel.R;
import com.example.an.hotel.models.roomObj;

import io.realm.Realm;

public class RoomFragmentEdit extends DialogFragment {
    public static EditText edtTask;
    AlertDialog.Builder dialog;
    private Realm realm;
    String ID;
    public void setValue(String IDs) {
        this.ID = IDs;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = new AlertDialog.Builder(getActivity());
        realm = Realm.getDefaultInstance();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View mview = inflater.inflate(R.layout.room_add_fragment,null);
        dialog.setView(mview);
        edtTask = (EditText) mview.findViewById(R.id.etInputRoom);

        dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        roomObj room = realm.where(roomObj.class).equalTo("id",ID).findFirst();
                        room.setName(String.valueOf(String.valueOf(edtTask.getText())));

                    }
                });

            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return dialog.create();
    }
}
