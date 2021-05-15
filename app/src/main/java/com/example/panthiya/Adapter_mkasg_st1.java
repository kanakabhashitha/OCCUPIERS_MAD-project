package com.example.panthiya;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Adapter_mkasg_st1 extends RecyclerView.Adapter<Adapter_mkasg_st1.Holder> {

    private Context context;
    private ArrayList<Model_mkasg> arrayList;
    //database object
    DatabaseHelperMKASG databaseHelper;

    public Adapter_mkasg_st1(Context context, ArrayList<Model_mkasg> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        //inisialize dbheler
        databaseHelper = new DatabaseHelperMKASG(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_mkasg_st1, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Model_mkasg model = arrayList.get(position);
        //get for view
        final String id = model.getId();
        final String atfk = model.getAtfk();
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
        holder.deadLine.setText(deadLine);



      holder.viewButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Preview_assingnmnet_student.class );
                intent.putExtra("ID", id);
                intent.putExtra("ATFK_ID", atfk);
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

        holder.answerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, add_assingment_student.class );
                intent.putExtra("ID", id);
                intent.putExtra("SUBJECT", subject );
                intent.putExtra("NUMBER", number);
                intent.putExtra("DEADLINE", deadLine);
                intent.putExtra("DESCRIPTION", description);


                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class Holder extends RecyclerView.ViewHolder{

        ImageView assignmentImage;
        TextView subject, number, deadLine, description, addtime, teacher_email;
        ImageButton editButton, deleteButton, viewButton, answerButton;


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
            answerButton = itemView.findViewById(R.id.answer_btn);
            addtime = itemView.findViewById(R.id.add_date_time);
            teacher_email = itemView.findViewById(R.id.teacher_email);
        }
    }
}
