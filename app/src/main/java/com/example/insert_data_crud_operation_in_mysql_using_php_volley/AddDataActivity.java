package com.example.insert_data_crud_operation_in_mysql_using_php_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddDataActivity extends AppCompatActivity {

    EditText etName, etEmail, etContact, etAddress;
    Button btnInsert;
    String url = "https://retrofit2practice.000webhostapp.com/insert.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        etName = findViewById(R.id.editName);
        etEmail = findViewById(R.id.editEmail);
        etContact = findViewById(R.id.editContact);
        etAddress = findViewById(R.id.editAddress);

        btnInsert = findViewById(R.id.btnInsert);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }

    private void insertData() {

//        Progress Dialog

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");

//        Get Data from User

        final String name = etName.getText().toString().trim();
        final String email = etEmail.getText().toString().trim();
        final String contact = etContact.getText().toString().trim();
        final String address = etAddress.getText().toString().trim();

        if (name.isEmpty()) {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        } else if (email.isEmpty()) {
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            return;
        } else if (contact.isEmpty()) {
            Toast.makeText(this, "Enter Contact", Toast.LENGTH_SHORT).show();
            return;
        } else if (address.isEmpty()) {
            Toast.makeText(this, "Enter Address", Toast.LENGTH_SHORT).show();
            return;
        } else {

//            Progress Dialog show

            progressDialog.show();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {


                    if (response.equalsIgnoreCase("Data Insert")) {
                        Toast.makeText(AddDataActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();

//                        Progress Dialog end

                        progressDialog.dismiss();

                    } else {
                        Toast.makeText(AddDataActivity.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                    Toast.makeText(AddDataActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();


//                    Progress Dialog end
                    progressDialog.dismiss();
                }
            }
            ) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

//                    Put data in server

                    Map<String, String> params = new HashMap<String, String>();
                    params.put("name", name);
                    params.put("email", email);
                    params.put("contact", contact);
                    params.put("address", address);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(AddDataActivity.this);
            requestQueue.add(request);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}
