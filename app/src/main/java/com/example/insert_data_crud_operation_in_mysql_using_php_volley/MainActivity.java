package com.example.insert_data_crud_operation_in_mysql_using_php_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btn_add_activity(View view) {
        startActivity(new Intent(getApplicationContext(), AddDataActivity.class));
    }
}
