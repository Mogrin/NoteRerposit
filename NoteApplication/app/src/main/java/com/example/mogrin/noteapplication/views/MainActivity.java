package com.example.mogrin.noteapplication.views;

import java.util.ArrayList;

import android.app.DialogFragment;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ListView;

import com.example.mogrin.noteapplication.R;
import com.example.mogrin.noteapplication.controllers.CreateItemDialog;
import com.example.mogrin.noteapplication.controllers.NoteAdapter;
import com.example.mogrin.noteapplication.modules.NoteItem;

public class MainActivity extends Activity {

    private static final int CM_DELETE_ID = 1;

    DialogFragment crtDlg;
    ListView lvNote;
    NoteAdapter noteAdapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        crtDlg = new CreateItemDialog();

        noteAdapter = new NoteAdapter(this, new ArrayList<NoteItem>());
        lvNote = (ListView) findViewById(R.id.lvSimple);
        lvNote.setAdapter(noteAdapter);
    }

    public void onButtonClick(View v) {
        //crtDlg.show(getFragmentManager(), "crtDlg");
        noteAdapter.objects.add(new NoteItem("name"));
        noteAdapter.notifyDataSetChanged();
    }

    public void addItem(String s){
        noteAdapter.objects.add(new NoteItem(s));
        noteAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if (item.getItemId() == CM_DELETE_ID) {
            AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
            noteAdapter.objects.remove(acmi.position);
            noteAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
