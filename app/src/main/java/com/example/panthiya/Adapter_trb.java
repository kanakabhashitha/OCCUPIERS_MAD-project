package com.example.panthiya;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter_trb extends RecyclerView.Adapter<Adapter_trb.Holder> {

    private Context context;
    private ArrayList<Model_trb> arrayList;

    //database object
    DatabaseHelperTRBnSRB databaseHelper;


    public Adapter_trb(Context context, ArrayList<Model_trb> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        //inisialize dbheler
        databaseHelper = new DatabaseHelperTRBnSRB(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_trb, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        Model_trb model = arrayList.get(position);

        final String id = model.getTr_id();
        final String tite = model.getTr_titel();
        final String date = model.getTr_date();
        final String outcoms = model.getTr_outComes();
        final String subject = model.getTr_subject();
        final String doePoint = model.getTr_donePoints();
        final String execption = model.getTr_exceptionPoints();
        final String addTime = model.getTr_addTimes();
        final String updateTime = model.getTr_updateTimes();

        //set views
        holder.tr_titel.setText(tite);
        holder.tr_date.setText(date);
        holder.tr_outComes.setText(outcoms);


        holder.tr_edit_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editDialog(
                        ""+position,
                        ""+id,
                        ""+date,
                        ""+subject,
                        ""+tite,
                        ""+outcoms,
                        ""+doePoint,
                        ""+execption,
                        ""+addTime,
                        ""+updateTime
                );
            }
        });


        //when long press on item, show and alert dialog for delete
       holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                deleteDialog(
                        ""+id
                );

                return false;
            }
        });

       holder.tr_view_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent = new Intent(context, TRB_view.class );
               intent.putExtra("ID", id);
               intent.putExtra("DATE", date );
               intent.putExtra("SUBJECT", subject);
               intent.putExtra("TITEL", tite);
               intent.putExtra("OUTCOMES", outcoms);
               intent.putExtra("DONE_POINT", doePoint);
               intent.putExtra("EXCEPTION_POINT", execption);
               intent.putExtra("ADD_TIMESTAMP", addTime);
               intent.putExtra("UPDATE_TIMESTAMP", updateTime);

               context.startActivity(intent);
           }
       });

    }


    private void editDialog(String position, String id, String date, String subject, String titel, String outComes, String donePoints, String exceptionPoints, String addTimes, String updateTimes) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Update");
        builder.setMessage("Are you want update ?");
        builder.setCancelable(false);
        builder.setIcon(R.drawable.eddit_btn);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(context, Edit_teacher_record_book.class );
                intent.putExtra("ID", id);
                intent.putExtra("DATE", date );
                intent.putExtra("SUBJECT", subject);
                intent.putExtra("TITEL", titel);
                intent.putExtra("OUTCOMES", outComes);
                intent.putExtra("DONE_POINT", donePoints);
                intent.putExtra("EXCEPTION_POINT", exceptionPoints);
                intent.putExtra("ADD_TIMESTAMP", addTimes);
                intent.putExtra("UPDATE_TIMESTAMP", updateTimes);
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
                ((TeacherRecordBook)context).onResume();
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

        TextView tr_date, tr_subject, tr_titel, tr_outComes, tr_donePoints, tr_exceptionPoints, tr_addTimes, tr_updateTimes;
        ImageButton tr_edit_btn, tr_view_btn;

        public Holder(@NonNull View itemView) {
            super(itemView);

            tr_titel = itemView.findViewById(R.id.tr_titel);
            tr_date = itemView.findViewById(R.id.tr_date);
            tr_outComes = itemView.findViewById(R.id.tr_outcome);
            tr_edit_btn = itemView.findViewById(R.id.tr_edit);
            tr_view_btn = itemView.findViewById(R.id.tr_viewbtn);
            tr_subject = itemView.findViewById(R.id.tr_subject);
            tr_donePoints = itemView.findViewById(R.id.tr_done_points);
            tr_exceptionPoints = itemView.findViewById(R.id.tr_expected_pont);
        }
    }
}
