package com.example.myapplication3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BlankFragment2_Detail extends AppCompatActivity {

    TextView dName,textView1,textView2,textView3;
    ImageView dImage;
    Button button_r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blank_fragment2_detail);
        Intent intent=getIntent();
        dName=findViewById(R.id.textDetail);
        dImage=findViewById((R.id.imageDetail));
        dImage.setImageResource(intent.getIntExtra("image",R.drawable.me));
        dName.setText(intent.getStringExtra("details"));
        textView1=findViewById(R.id.songer2);
        textView2=findViewById(R.id.song2);
        textView3=findViewById(R.id.wxtag2);
        textView1.setText(intent.getStringExtra("songer"));
        textView2.setText(intent.getStringExtra("tv"));
        textView3.setText(intent.getStringExtra("tag"));
        button_r=findViewById(R.id.returnButton);
        button_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(123,intent);
                finish();
            }
        });
    }
}