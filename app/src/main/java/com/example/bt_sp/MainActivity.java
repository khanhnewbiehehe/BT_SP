package com.example.bt_sp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etTask;
    private Button btnSave;
    private TextView tvDisplayTask;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etTask = findViewById(R.id.et_task);
        btnSave = findViewById(R.id.btn_save);
        tvDisplayTask = findViewById(R.id.tv_display_task);
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedTasks = sharedPreferences.getString("tasks", "No tasks saved");
        tvDisplayTask.setText(savedTasks);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTask = etTask.getText().toString();
                String currentTasks = sharedPreferences.getString("tasks", "");

                String updatedTasks = currentTasks + (currentTasks.isEmpty() ? "" : "\n") + newTask;

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("tasks", updatedTasks);
                editor.apply();

                tvDisplayTask.setText(updatedTasks);

                etTask.setText("");
            }
        });
    }
}
