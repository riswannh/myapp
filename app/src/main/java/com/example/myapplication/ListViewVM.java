package com.example.myapplication;

import android.content.Context;

import androidx.databinding.ObservableField;

public class ListViewVM {
    public ObservableField<String> namaSiswa = new ObservableField<>();
    public ObservableField<String> npmSiswa = new ObservableField<>();
    public ObservableField<String> noHpSiswa = new ObservableField<>();

    public ListViewVM(Context context, Mahasiswa mahasiswa) {
       this.namaSiswa.set(mahasiswa.getNama());
       this.npmSiswa.set(mahasiswa.getNpm());
       this.noHpSiswa.set(mahasiswa.getNohp());
    }
}
