package com.example.studentscheduler.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

public class TermList extends AppCompatActivity {
    private TermViewModel termViewModel;
    public static final int ADD_TERM = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView mRecyclerView = findViewById(R.id.recyclerview_term_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);

        final TermAdapter adapter = new TermAdapter();
        mRecyclerView.setAdapter(adapter);

        termViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termViewModel.getAllTerms().observe(this, allTerms -> adapter.setTermList(allTerms));

        /*
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                Term deleteTerm = adapter.getTerm(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(mRecyclerView);

         */
    }
/*
    protected void onActivityResult(int add, int complete, @NonNull Intent info){
        super.onActivityResult(add, complete, info);
        if((add == ADD_TERM) && (complete == RESULT_OK)){
            String title = info.getStringExtra(TermUpdate.EDIT_TERM_TITLE);

            Term term = new Term(title);
            termViewModel.insert(term);

            Toast.makeText(this, "A new Term has been add", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Term failed to add", Toast.LENGTH_LONG).show();
        }
    }
 */

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
            }
            return super.onOptionsItemSelected(item);
    }

    public void BeginTermActivity(View view) {
    }

    public void goToCourseList(View view) {
        Intent intent = new Intent(TermList.this, CourseList.class);
        startActivity(intent);
    }

    /*
    public boolean onCreateOptionMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_termlist, menu);
            return true;
    }
     */
}