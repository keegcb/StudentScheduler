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
    private final Repository repo;
    private final LiveData<List<Term>> allTerms;

    public TermViewModel(@NonNull Application application){
        super(application);
        repo = new Repository(application);
        allTerms = (LiveData<List<Term>>) repo.getAllTerms();
    }


    public void insert(Term term){
        repo.insertTerm(term);
    }
    public void update(Term term){
        repo.updateTerm(term);
    }
    public void delete(Term term){
        repo.deleteTerm(term);
    }
    public LiveData<List<Term>> getAllTerms(){
        return allTerms;
    }
}