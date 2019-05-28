package com.example.primagama;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class request_jadwal extends AppCompatActivity {

    @BindView(R.id.input_mapel)
    EditText input_mapel;
    @BindView(R.id.input_tanggal)
    EditText input_tanggal;


    @BindView(R.id.simpan_button)
    Button simpan_button;

    @BindView(R.id.hasil_mapel)
    TextView hasil_mapel;
    @BindView(R.id.hasil_tanggal)
    TextView hasil_tanggal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.simpan_button)
    public void btnClicked() {
        String nama = input_mapel.getText().toString();
        hasil_mapel.setText(nama);
        String tanggal=input_tanggal.getText().toString();
        hasil_tanggal.setText(tanggal);

    }

    public void submit(View view) {
    }
}