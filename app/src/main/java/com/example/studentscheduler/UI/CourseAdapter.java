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
import com.example.studentscheduler.Entity.Course;
import com.example.studentscheduler.Entity.Term;
import com.example.studentscheduler.R;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private final Context mContext;
    private List<Course> mCourses;
    private final LayoutInflater mInflator;

    class CourseViewHolder extends RecyclerView.ViewHolder {
        private final TextView courseTitle;
        private final CardView courseCard;

        public CourseViewHolder(View view){
            super(view);
            courseTitle = view.findViewById(R.id.txt_course_name);
            courseCard = view.findViewById(R.id.card_course_item);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final Course currentCourse = mCourses.get(position);
                    Intent intent = new Intent(mContext, CourseDetails.class);

                    intent.putExtra("id", Integer.toString(currentCourse.getCourseId()));
                    intent.putExtra("title", currentCourse.getCourseTitle());
                    intent.putExtra("start", DateConverter.toTimestamp(currentCourse.getStartDate()));
                    intent.putExtra("end", DateConverter.toTimestamp(currentCourse.getEndDate()));
                    intent.putExtra("name", currentCourse.getInstructorName());
                    intent.putExtra("email", currentCourse.getInstructorEmail());
                    intent.putExtra("phone", currentCourse.getInstructorPhone());
                //TODO: Decide how to display term name in course detailed view and pass term title or query title from Id
                    intent.putExtra("term", currentCourse.getTermId());

                    mContext.startActivity(intent);
                }
            });
        }
    }

    public CourseAdapter(Context context){
        mInflator = LayoutInflater.from(context);
        this.mContext = context;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflator.inflate(R.layout.course_list_item, parent, false);
        return new CourseAdapter.CourseViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        if(mCourses != null){
            Course currentCourse = mCourses.get(position);
            String title = currentCourse.getCourseTitle();
            holder.courseTitle.setText(title);
        } else {
            holder.courseTitle.setText("No Title");
        }
    }

    @Override
    public int getItemCount() {
        if(mCourses != null){
            return mCourses.size();
        } else {
            return 0;
        }
    }

    public void setCourseList (List<Course> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

}
