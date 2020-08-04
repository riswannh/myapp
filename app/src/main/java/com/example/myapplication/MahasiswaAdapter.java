package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.FooterBinding;
import com.example.myapplication.databinding.ListViewBinding;

import java.util.ArrayList;

public class MahasiswaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Mahasiswa> dataList;
    private Context context;
    private AdapterListener listener;
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;
    private boolean isFooterVisible;

    public MahasiswaAdapter(Context ctx, ArrayList<Mahasiswa> dataList, AdapterListener listener) {
        this.listener = listener;
        this.dataList = dataList;
        context = ctx;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_ITEM) {
            ListViewBinding binding = ListViewBinding.inflate(layoutInflater, parent, false);
            return new MahasiswaViewHolder(binding);
        } else if (viewType == TYPE_FOOTER) {
            FooterBinding binding = FooterBinding.inflate(layoutInflater, parent, false);
            return new FooterViewHolder(binding);
        } else return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof MahasiswaViewHolder) {
            final Mahasiswa mahasiswa = dataList.get(position);
            ListViewVM viewVM = new ListViewVM(context, mahasiswa);
            ((MahasiswaViewHolder) holder).getBinding().setVariable(BR.vm, viewVM);
            ((MahasiswaViewHolder) holder).getBinding().getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(position, mahasiswa);
                }
            });
            ((MahasiswaViewHolder)holder).getBinding().btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClickDelete(position);
                }
            });
        }else if (holder instanceof FooterViewHolder){
            MahasiswaFooterVM viewVM = new MahasiswaFooterVM("loading",isFooterVisible);
            ((FooterViewHolder)holder).getBinding().setVariable(BR.vm, viewVM);
        }
    }

    public void setFooterVisible(boolean footerVisible){
        if (isFooterVisible){
            isFooterVisible = footerVisible;
            notifyItemChanged(dataList.size() +1);
        }else {
            isFooterVisible = footerVisible;
            notifyItemChanged(dataList.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == dataList.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
//        return (dataList != null) ? dataList.size() : 0;
        return dataList.size()+1;
    }

    public class MahasiswaViewHolder extends RecyclerView.ViewHolder {
        private ListViewBinding binding;

        public MahasiswaViewHolder(ListViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public ListViewBinding getBinding() {
            return binding;
        }
    }


    public class FooterViewHolder extends RecyclerView.ViewHolder {
        private FooterBinding binding;

        public FooterViewHolder(FooterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public FooterBinding getBinding() {
            return binding;
        }
    }

    public interface AdapterListener {
        void onClick(int position, Mahasiswa item);
        void onClickDelete(int position);
    }
}
