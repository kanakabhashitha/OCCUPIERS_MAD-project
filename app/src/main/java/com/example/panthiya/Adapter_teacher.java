package com.example.panthiya;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_teacher extends RecyclerView.Adapter<Adapter_teacher.Holder> {

    private Context context;
    private ArrayList<Model_SR> arrayList;
    //database object
    DatabaseHelperMKASG databaseHelper;

    public Adapter_teacher(Context context, ArrayList<Model_SR> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        //inisialize dbheler
        databaseHelper = new DatabaseHelperMKASG(context);

    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_teacher, parent, false);

        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {


        Model_SR model = arrayList.get(position);
        //get for view
        final String sid = model.getSid();
        final String fkId = model.getFkid();
        final String image = model.getImage();
        final String fname = model.getFname();
        final String lname = model.getLname();
        final String grade = model.getGrade();
        final String age = model.getAge();
        final String gender = model.getGender();
        final String phone = model.getPhone();
        final String email = model.getEmail();
        final String password = model.getPassword();
        final String addTime = model.getAddTimestamp();
        final String updateTime = model.getUpdateTimestamp();

        System.out.println("sid__"+sid);

        //set views
        //holder.personImage.setImageURI(Uri.parse(image));
        holder.name.setText(fname);
        holder.grade.setText(grade);
        holder.sid.setText(sid);


        holder.seditBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editDialog(
                        "" + position,
                        "" + sid,
                        "" + fkId,
                        "" + image,
                        "" + fname,
                        "" + lname,
                        "" + grade,
                        "" + age,
                        "" + gender,
                        "" + phone,
                        "" + email,
                        "" + password,
                        "" + addTime,
                        "" + updateTime
                );
            }
        });



        //when long press on item, show and alert dialog for delete
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                deleteDialog(
                        ""+sid
                );

                return false;
            }
        });

    }



    private void editDialog(String position, String sid, String fkid, String image, String fname, String lname, String grade, String age, String gender, String phone, String email, String password, String addTimeStamp, String updateTimeStamp) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you want update ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.eddit_btn);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, edit_add_student_teacher.class);
                intent.putExtra("SR_ID", sid);
                intent.putExtra("TFK_ID", fkid);
                intent.putExtra("SR_IMAGE", image);
                intent.putExtra("SR_F_NAME", fname);
                intent.putExtra("SR_L_NAME", lname);
                intent.putExtra("SR_GRADE", grade);
                intent.putExtra("SR_AGE", age);
                intent.putExtra("SR_GENDER", gender);
                intent.putExtra("SR_PONE", phone);
                intent.putExtra("SR_EMAIL", email);
                intent.putExtra("SR_PASSWORD", password);
                intent.putExtra("SR_ADD_TIMESTAMP", addTimeStamp);
                intent.putExtra("SR_UPDATE_TIMESTAMP", updateTimeStamp);
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

                databaseHelper.deleteInfo_table_4_teacher(id);
                ((studentRegisterPage)context).onResume();
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



    class Holder extends RecyclerView.ViewHolder{

        TextView name, sid, grade;
        ImageView personImage,seditBtn;

        public Holder(@NonNull View itemView) {
            super(itemView);

            personImage = itemView.findViewById(R.id.personImage);
            name = itemView.findViewById(R.id.studentName);
            sid = itemView.findViewById(R.id.studentId);
            grade = itemView.findViewById(R.id.studentGrade);
            seditBtn = itemView.findViewById(R.id.s_edit_btn);
        }
    }
}
