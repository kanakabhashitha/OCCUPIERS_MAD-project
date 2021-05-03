package com.example.panthiya;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_mkasg extends RecyclerView.Adapter<Adapter_mkasg.Holder>{

    private Context context;
    private ArrayList<Model_mkasg> arrayList;

    //database object
    DatabaseHelperMKASG databaseHelper;

    public Adapter_mkasg(Context context, ArrayList<Model_mkasg> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        //inisialize dbheler
        databaseHelper = new DatabaseHelperMKASG(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_mkasg, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Model_mkasg model = arrayList.get(position);
        //get for view
        final String id = model.getId();
        final String image = model.getImage();
        final String subject = model.getSubject();
        final String number = model.getNumber();
        final String deadLine = model.getDeadLine();
        final String description = model.getDescription();
        final String addTimeStamp = model.getAddTimeStamp();
        final String updateTimeStamp = model.getUpdateTimestamp();

        //set views
        holder.assignmentImage.setImageURI(Uri.parse(image));
        holder.subject.setText(subject);
        holder.number.setText(number);
        holder.addtime.setText(addTimeStamp);
        //holder.deadLine.setText(deadLine);


        holder.viewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, preview_assingment.class );
                intent.putExtra("ID", id);
                intent.putExtra("SUBJECT", subject );
                intent.putExtra("NUMBER", number);
                intent.putExtra("DEADLINE", deadLine);
                intent.putExtra("DESCRIPTION", description);
                intent.putExtra("IMAGE", image);
                intent.putExtra("ADD_TIMESTAMP", addTimeStamp);
                intent.putExtra("UPDATE_TIMESTAMP", updateTimeStamp);
                intent.putExtra("EditMode", true);

                context.startActivity(intent);
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+subject,
                        ""+number,
                        ""+deadLine,
                        ""+description,
                        ""+image,
                        ""+addTimeStamp,
                        ""+updateTimeStamp
                );
            }
        });


        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDialog(
                        ""+id
                );
            }
        });


    }



    private void editDialog(String position, String id, String subject, String number, String deadLine, String description, String image, String addTimeStamp, String updateTimeStamp) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you want update ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.eddit_btn);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, edit_assingment.class );
                intent.putExtra("ID", id);
                intent.putExtra("SUBJECT", subject );
                intent.putExtra("NUMBER", number);
                intent.putExtra("DEADLINE", deadLine);
                intent.putExtra("DESCRIPTION", description);
                intent.putExtra("IMAGE", image);
                intent.putExtra("ADD_TIMESTAMP", addTimeStamp);
                intent.putExtra("UPDATE_TIMESTAMP", updateTimeStamp);
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



    private void deleteDialog(final String id) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Delete");
        builder.setMessage("Are you want to delete ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.delete_icon);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                databaseHelper.deleteInfo(id);
                ((makeAssingment)context).onResume();
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



    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        ImageView assignmentImage;
        TextView subject, number, deadLine, description, addtime, update_time;
        ImageButton editButton, deleteButton, viewButton;


        public Holder(@NonNull View itemView) {
            super(itemView);

            assignmentImage = itemView.findViewById(R.id.assignmentImage);
            subject = itemView.findViewById(R.id.assignmentSubject);
            number = itemView.findViewById(R.id.assignemtNo);
            deadLine = itemView.findViewById(R.id.ass_dead);
            description = itemView.findViewById(R.id.assignemtDescription);
            editButton = itemView.findViewById(R.id.editbtn);
            deleteButton = itemView.findViewById(R.id.deletebtn);
            viewButton = itemView.findViewById(R.id.viewbtn);
            addtime = itemView.findViewById(R.id.add_date_time);
            update_time = itemView.findViewById(R.id.update_date);
        }
    }

}
