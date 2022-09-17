package com.example.studentscheduler.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.studentscheduler.Database.Repository;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.util.List;

public class TermViewModel extends AndroidViewModel {
    private Repository repo;
    private LiveData<List<Term>> allTerms;

    public TermViewModel(@NonNull Application application){
        super(application);
        repo = new Repository(application);
        allTerms = (LiveData<List<Term>>) repo.getAllTerms();
    }


    public void insert(Term term){
        repo.insert(term);
    }
    public void update(Term term){
        repo.update(term);
    }
    public void delete(Term term){
        repo.delete(term);
    }
    public LiveData<List<Term>> getAllTerms(){
        return allTerms;
    }
}