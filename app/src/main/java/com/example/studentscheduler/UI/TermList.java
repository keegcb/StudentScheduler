package com.example.studentscheduler.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.studentscheduler.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
        public boolean onCreateOptionMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_termlist, menu);
            return true;
        }

        public boolean onOptionsItemSelected(MenuItem item){
            switch (item.getItemId()){
                case android.R.id.home:
                    this.finish();
                    return true;
            }
                return super.onOptionsItemSelected(item);
        }
}