package edu.feicui.student.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mystudentgl.R;
import com.example.mystudentgl.db.Student;

import java.util.List;

import edu.feicui.student.db.StudentInfo;

/**
 * Created by 工作 on 2016/8/17.
 */
public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.MyViewHolder>{

    Context context;
    List<StudentInfo> list;

    public StudentAdapter(Context context) {
        this.context = context;
    }

    public List<StudentInfo> getList() {
        return list;
    }

    public void setList(List<StudentInfo> list) {
        this.list = list;
    }

    public StudentAdapter(Context context, List<StudentInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_student,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        StudentInfo studentInfo=list.get(position);
        holder.name.setText(studentInfo.getName());
        holder.sex.setText(studentInfo.getSex());
        holder.age.setText(studentInfo.getAge());

        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,sex,age;
        public MyViewHolder(View itemView) {
            super(itemView);
            name= (TextView) itemView.findViewById(R.id.tv_name);
            sex= (TextView) itemView.findViewById(R.id.tv_sex);
            age= (TextView) itemView.findViewById(R.id.tv_age);
        }
    }


    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int position);
    }
}
