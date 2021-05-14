package com.example.panthiya;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class teacherReportAdapter extends RecyclerView.Adapter<teacherReportAdapter.Holder> {

    private Context context;
    private ArrayList<Model_TM> arrayList;

    //database object
    DatabaseHelperMKASG databaseHelper;

    public teacherReportAdapter(Context context, ArrayList<Model_TM> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        //inisialize dbheler
        databaseHelper = new DatabaseHelperMKASG(context);

    }

    // method for filtering our recyclerview items.
    public void filterList(ArrayList<Model_TM> filterllist) {
        // below line is to add our filtered
        // list in our course array list.
        arrayList = filterllist;
        // below line is to notify our adapter
        // as change in recycler view data.
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_marks_studentview, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Model_TM model = arrayList.get(position);
        //get for view
        final String id = model.getId();
        final String assignment = model.getAssignment();
        final String studentid = model.getStudentid();
        final String name = model.getName();
        final String subject = model.getSubject();
        final String mark = model.getMarks();
        final String comment = model.getComment();

        //set views
        holder.AssignmentID.setText(assignment);
        holder.Studentid.setText(studentid);
        holder.Subject.setText(subject);
        holder.Mark.setText(mark);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent summary = new Intent(context,MarksSummaryActivity.class);
                summary.putExtra("studentId",studentid);
                context.startActivity(summary);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class Holder extends  RecyclerView.ViewHolder{

        TextView AssignmentID, Studentid, Subject,Mark;

        public Holder(@NonNull View itemView) {
            super(itemView);

            AssignmentID = itemView.findViewById(R.id.assignments);
            Studentid = itemView.findViewById(R.id.students);
            Subject = itemView.findViewById(R.id.subs);
            Mark = itemView.findViewById(R.id.markcs);
        }
    }

}
