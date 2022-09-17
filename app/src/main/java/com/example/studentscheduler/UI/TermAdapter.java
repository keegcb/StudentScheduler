package com.example.studentscheduler.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.util.ArrayList;
import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    private List<Term> terms = new ArrayList<>();
    private View.OnClickListener listener;

    @NonNull
    @Override
    public TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_term_view, parent, false);
        return new TermViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        Term currentTerm = terms.get(position);
        holder.termTitle.setText(currentTerm.getTermTitle());
    }

    @Override
    public int getItemCount() {
        return terms.size();
    }

    public void setTermList(List<Term> terms){
        this.terms = terms;
        notifyDataSetChanged();
    }

    public Term getTerm(int position){
        return terms.get(position);
    }


    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termTitle;

        public TermViewHolder(@NonNull View view){
            super(view);
            termTitle = view.findViewById(R.id.txt_term_name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Term term);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = (View.OnClickListener) listener;
    }








    @Override
    public int getItemViewType(final int position){
        return R.layout.item_term_view;
    }
}
