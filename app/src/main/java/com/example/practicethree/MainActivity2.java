package com.example.practicethree;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

public class MainActivity2 extends AppCompatActivity {
        ImageView imageTarget;
        MaterialButton nameTarget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageTarget = findViewById(R.id.imageTarget);
        nameTarget = findViewById(R.id.nameTarget);

        String image = getIntent().getStringExtra("A");
        String name = getIntent().getStringExtra("B");

        Glide.with(MainActivity2.this).load(image).into(imageTarget);
        nameTarget.setText(name);
    }
}