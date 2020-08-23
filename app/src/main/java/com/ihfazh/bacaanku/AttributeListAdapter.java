package com.ihfazh.bacaanku;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ihfazh.bacaanku.data.Attribute;

import java.util.List;

public class AttributeListAdapter extends RecyclerView.Adapter<AttributeListAdapter.AttributeViewHolder> {
    private List<Attribute> attributes;

    public AttributeListAdapter(List<Attribute> attributes) {
        this.attributes = attributes;
    }


    @NonNull
    @Override
    public AttributeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.attribute_list_item, parent, false);
        return new AttributeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AttributeViewHolder holder, int position) {
        Attribute attribute = attributes.get(position);
        holder.tvKey.setText(attribute.getKey());
        holder.tvValue.setText(attribute.getValue());
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return attributes.size();
    }

    public class AttributeViewHolder extends RecyclerView.ViewHolder {
        private TextView tvKey, tvValue;

        public AttributeViewHolder(@NonNull View itemView) {
            super(itemView);
            tvKey = itemView.findViewById(R.id.tv_attribute_key);
            tvValue = itemView.findViewById(R.id.tv_attribute_value);
        }

    }
}
