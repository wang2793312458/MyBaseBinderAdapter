package com.example.mybasebinderadapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * @author qdafengzi
 */
public class RvvAdapter extends RecyclerView.Adapter<RvvAdapter.ViewHolder> {
    private Context mContext;
    private List<SimpleData.ChildData> mList;

    private int mPosition;

    public RvvAdapter(Context context, List<SimpleData.ChildData> list, int position) {
        mContext = context;
        mList = list;
        mPosition = position;
    }

    /**
     * 新增方法
     *
     * @param position 位置
     */
    public void setPosition(int position) {
        this.mPosition = position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_option, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.lay_option.setTag(position);

        holder.tvOption.setText(mList.get(position).childName);
        if (mList.get(position).select) {
            holder.lay_option.setBackgroundResource(R.drawable.background_grid_unselect);
        } else {
            holder.lay_option.setBackgroundResource(R.drawable.background_grid_select);
        }

    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout lay_option;
        TextView tvOption;

        ViewHolder(View view) {
            super(view);

            lay_option=view.findViewById(R.id.lay_option);
            tvOption=view.findViewById(R.id.tv_option);


            //这里设置一个tag,作为被嵌套的recycleview item点击事件的 position
            lay_option.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.get((int) v.getTag()).select=true;
                    ((MainActivity) mContext).OnClickListener(mPosition, (int) v.getTag());
                }
            });


        }
    }
}