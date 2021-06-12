package com.app.projectandroid.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.projectandroid.R;
import com.app.projectandroid.data.CompanyData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CompanyRecyclerAdapter extends RecyclerView.Adapter<CompanyRecyclerAdapter.VhMyData> implements Filterable {

    private List<CompanyData> data;
    private List<CompanyData> copyList;


    class VhMyData extends RecyclerView.ViewHolder {
        TextView tv_name, tv_address, tv_number, tv_beg, tv_end;

        public VhMyData(@NonNull View itemView) {
            super(itemView);

            tv_name = itemView.findViewById(R.id.custom_company_name);
            tv_address = itemView.findViewById(R.id.custom_company_address);
            tv_number = itemView.findViewById(R.id.custom_company_number);
            tv_beg = itemView.findViewById(R.id.custom_company_beg);
            tv_end = itemView.findViewById(R.id.custom_company_end);
        }
    }

    public CompanyRecyclerAdapter(ArrayList<CompanyData> data) {
        this.data = data;
        copyList = new ArrayList<>(data);


    }

    @NonNull
    @Override
    public VhMyData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_company, parent, false);

        VhMyData vh = new VhMyData(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VhMyData holder, int position) {
        CompanyData d = data.get(position);

        holder.tv_name.setText(d.getName());
        holder.tv_address.setText(d.getAddress());
        holder.tv_number.setText(d.getNumber());
        holder.tv_beg.setText(d.getBegWork());
        holder.tv_end.setText(d.getEndWork());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CompanyData> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(copyList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (CompanyData item : copyList) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
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
            data.clear();
            data.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
