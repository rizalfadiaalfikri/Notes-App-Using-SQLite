package com.example.notes;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements Filterable {
    Context context;
    Activity activity;
    List<Model> notelist;
    List<Model> newList;

    public MyAdapter(Context context, Activity activity, List<Model> notelist) {
        this.context = context;
        this.activity = activity;
        this.notelist = notelist;

        newList = new ArrayList(notelist);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(notelist.get(position).getTitle());
        holder.description.setText(notelist.get(position).getDescription());

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UpdateNotesActivity.class);

                intent.putExtra("title",notelist.get(position).getTitle());
                intent.putExtra("description",notelist.get(position).getDescription());
                intent.putExtra("id",notelist.get(position).getId());

                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return notelist.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Model> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(newList);
            } else {
                String filteredPattern = constraint.toString().toLowerCase().trim();
                for(Model item:newList) {
                    if (item.getTitle().toLowerCase().contains(filteredPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            notelist.clear();
            notelist.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        RelativeLayout layout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.txt_title);
            description = itemView.findViewById(R.id.txt_description);
            layout = itemView.findViewById(R.id.note_layout);
        }
    }

   public List<Model> getList () {
        return notelist;
   }

   public void removeItem (int position) {
        notelist.remove(position);
        notifyItemRemoved(position);
   }

   public void restoreItem (Model model, int position) {
        notelist.add(position,model);
        notifyItemInserted(position);
   }


}
