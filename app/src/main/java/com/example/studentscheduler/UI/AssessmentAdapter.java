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
import com.example.studentscheduler.Entity.Assessment;
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.R;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {
    private final Context mContext;
    private List<Assessment> mAssessment;
    private final LayoutInflater mInflator;

    class AssessmentViewHolder extends RecyclerView.ViewHolder {
        private final TextView assessmentTitle;
        private final CardView assessmentCard;

        public AssessmentViewHolder(View view){
            super(view);
            assessmentTitle = view.findViewById(R.id.txt_assessment_name);
            assessmentCard = view.findViewById(R.id.card_assessment_item);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Assessment currentAssessment = mAssessment.get(position);
                    Intent intent = new Intent(mContext, AssessmentDetails.class);

                    intent.putExtra("id", Integer.toString(currentAssessment.getAssessmentId()));
                    intent.putExtra("title", currentAssessment.getTitle());
                    intent.putExtra("courseId", currentAssessment.getCourseId());
                //TODO: Create SQL query to pull Course Title from ID and use to push extra to Assessment Details screen
                    // intent.putExtra("courseTitle", currentCourse.getTitle(currentAssessment.getCourseId());
                    intent.putExtra("type", currentAssessment.getType());
                    intent.putExtra("start", DateConverter.toTimestamp(currentAssessment.getStartDate()));
                    intent.putExtra("end", DateConverter.toTimestamp(currentAssessment.getEndDate()));
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public AssessmentAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.assessment_list_item, parent, false);
        return new AssessmentAdapter.AssessmentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if(mAssessment != null){
            Assessment currentAssessment = mAssessment.get(position);
            String title = currentAssessment.getTitle();
            holder.assessmentTitle.setText(title);
        } else {
            holder.assessmentTitle.setText("No Title");
        }
    }

    @Override
    public int getItemCount() {
        if(mAssessment != null){
            return mAssessment.size();
        } else {
            return 0;
        }
    }

    public void setAssessmentList (List<Assessment> assessments){
        mAssessment = assessments;
        notifyDataSetChanged();
    }
}
