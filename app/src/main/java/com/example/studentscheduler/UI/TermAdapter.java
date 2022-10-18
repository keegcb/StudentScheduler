package com.example.studentscheduler.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studentscheduler.Database.DateConverter;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {
    private final Context mContext;
    private List<Term> mTerms;
    private final LayoutInflater mInflator;

    class TermViewHolder extends RecyclerView.ViewHolder{
        private final TextView termTitle;
        private final CardView termCard;

        public TermViewHolder(View view){
            super(view);
            termTitle = view.findViewById(R.id.txt_term_name);
            termCard = view.findViewById(R.id.card_term_item);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Term currentTerm = mTerms.get(position);
                    Intent intent = new Intent(mContext, TermDetails.class);
                    intent.putExtra("id", Integer.toString(currentTerm.getTermId()));
                    intent.putExtra("title", currentTerm.getTermTitle());
                    intent.putExtra("start", DateConverter.toTimestamp(currentTerm.getStartDate()));
                    intent.putExtra("end", DateConverter.toTimestamp(currentTerm.getEndDate()));
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public TermAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View itemView = mInflator.inflate(R.layout.term_list_item, parent, false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermViewHolder holder, int position) {
        if(mTerms != null){
            Term currentTerm = mTerms.get(position);
            String title = currentTerm.getTermId() + " " + currentTerm.getTermTitle();
            holder.termTitle.setText(title);
        } else {
            holder.termTitle.setText("No Title");
        }
    }

    @Override
    public int getItemCount() {
        if(mTerms != null){
            return mTerms.size();
        } else {
            return 0;
        }
    }

    public void setTermList(List<Term> terms){
        mTerms = terms;
        notifyDataSetChanged();
    }
}
