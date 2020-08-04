package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.myapplication.databinding.ActivityDataBindingBinding;

public class DataBinding extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);

        //init data binding
        ActivityDataBindingBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_data_binding);
        DataBindingVM viewModel = new DataBindingVM(this);
        binding.setVm(viewModel);
    }
}
