package com.example.myapplication;

import androidx.databinding.ObservableField;

public class MahasiswaFooterVM {
    public ObservableField<String> pesan = new ObservableField<>();
    public ObservableField<Boolean> isVisible = new ObservableField<>(false);

    public MahasiswaFooterVM(String pesan,boolean isVisible) {
        this.pesan.set(pesan);
        this.isVisible.set(isVisible);
    }
}
