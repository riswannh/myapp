package com.example.myapplication;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.ObservableField;

import java.util.Observable;

public class DataBindingVM {
    public ObservableField<String> etNama = new ObservableField<>();
    public DataBinding mActivitiy;

    public DataBindingVM(DataBinding mActivitiy) {
        this.mActivitiy = mActivitiy;
    }

    public void onClickSubmit(View v){
        Toast.makeText(mActivitiy,etNama.get(),Toast.LENGTH_LONG).show();
    }
}
