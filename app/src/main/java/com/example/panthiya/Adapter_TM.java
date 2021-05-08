package com.example.panthiya;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_TM extends RecyclerView.Adapter<Adapter_TM.Holder> {

    private Context context;
    private ArrayList<Model_TM> arrayList;

    //database object
    DatabaseHelperMKASG databaseHelper;

    public Adapter_TM(Context context, ArrayList<Model_TM> arrayList) {
        this.context = context;
        this.arrayList = arrayList;

        //inisialize dbheler
        databaseHelper = new DatabaseHelperMKASG(context);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.custom_marks_details, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int position) {

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

  holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UpdateDetails(
                  ""+position,
                  ""+id,
                  ""+assignment,
                  ""+studentid,
                  ""+name,
                  ""+subject,
                  ""+mark,
                  ""+comment
                );
            }
        });




    //when long press on item, show and alert dialog for delete
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                DeleteItem(
                        "" + id
                );

                return false;
            }
        });




}

  private void DeleteItem(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.delete);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                databaseHelper.deleteInfo_table_5(id);
                ((addMark)context).onResume();
                Toast.makeText(context, "Delete Successful", Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        builder.create().show();
    }




    private void UpdateDetails(String position, String id, String assignment, String studentid, String name, String subject, String marks, String comment) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you want update ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.edit);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, EditMarks.class );
                intent.putExtra("ID", id);
                intent.putExtra("assignment", assignment);
                intent.putExtra("studentID", studentid);
                intent.putExtra("name", name);
                intent.putExtra("subject", subject);
                intent.putExtra("mark", marks);
                intent.putExtra("comment", comment);
                intent.putExtra("EditMode", true);

                context.startActivity(intent);

            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends  RecyclerView.ViewHolder{

        TextView AssignmentID, Studentid, Subject,Mark;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            AssignmentID = itemView.findViewById(R.id.assignment);
            Studentid = itemView.findViewById(R.id.student);
            Subject = itemView.findViewById(R.id.sub);
            Mark = itemView.findViewById(R.id.markc);
            imageView=(itemView).findViewById(R.id.imageView);
        }
    }

}
