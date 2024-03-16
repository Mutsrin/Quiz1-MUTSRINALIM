package com.example.quiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quiz1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class DetailBarang extends AppCompatActivity {

    private TextView NamaPelanggan, KodeBarang, NamaBarang,
            Harga, TotalHarga, DiskonHarga, DiskonMember, JumlahBayar;
    private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_barang);

        NamaPelanggan = findViewById(R.id.pelanggan);
        KodeBarang = findViewById(R.id.kode);
        NamaBarang = findViewById(R.id.nama);
        Harga = findViewById(R.id.harga);
        TotalHarga = findViewById(R.id.totalharga);
        DiskonHarga = findViewById(R.id.diskonharga);
        DiskonMember = findViewById(R.id.diskonmember);
        JumlahBayar = findViewById(R.id.jumlahbayar);
        shareButton = findViewById(R.id.share);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData();
            }
        });

        Intent intent = getIntent();
        String namaPelanggan = intent.getStringExtra("Nama Pelanggan");
        String kodeBarang = intent.getStringExtra("Kode Barang");
        String namaBarang = intent.getStringExtra("Nama Barang");
        long harga = intent.getLongExtra("Harga", 0);
        int jumlahBarang = intent.getIntExtra("Jumlah Barang", 0);
        long totalHarga = intent.getLongExtra("Total Harga", 0);
        long discountHarga = intent.getLongExtra("Diskon Harga", 0);
        long discountMember = intent.getLongExtra("Diskon Member", 0);
        long jumlahBayar = intent.getLongExtra("Jumlah Bayar", 0);

        NumberFormat formatter = new DecimalFormat("#,###,###,###");
        String formattedHarga = "Rp " + formatter.format(harga);
        String formattedTotalHarga = "Rp " + formatter.format(totalHarga);
        String formattedDiscountHarga = "Rp " + formatter.format(discountHarga);
        String formattedDiscountMember = "Rp " + formatter.format(discountMember);
        String formattedJumlahBayar = "Rp " + formatter.format(jumlahBayar);

        NamaPelanggan.setText("Nama Pelanggan: " + namaPelanggan);
        KodeBarang.setText("Kode Barang: " + kodeBarang);
        NamaBarang.setText("Nama Barang: " + namaBarang);
        Harga.setText("Harga: " + formattedHarga);
        TotalHarga.setText("Total Harga: " + formattedTotalHarga);
        DiskonHarga.setText("Diskon Harga: " + formattedDiscountHarga);
        DiskonMember.setText("Diskon Member: " + formattedDiscountMember);
        JumlahBayar.setText("Jumlah Bayar: " + formattedJumlahBayar);
    }

    private void shareData() {
        String dataToShare = "Detail Transaksi\n" +
                NamaPelanggan.getText().toString() + "\n" +
                KodeBarang.getText().toString() + "\n" +
                NamaBarang.getText().toString() + "\n" +
                Harga.getText().toString() + "\n" +
                TotalHarga.getText().toString() + "\n" +
                DiskonHarga.getText().toString() + "\n" +
                DiskonMember.getText().toString() + "\n" +
                JumlahBayar.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, dataToShare);
        startActivity(Intent.createChooser(shareIntent, "Bagikan via"));
    }
}
