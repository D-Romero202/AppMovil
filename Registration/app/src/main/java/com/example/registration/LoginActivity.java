package com.example.registration;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.registration.interfaces.CRUD;
import com.example.registration.model.Empleado;
import com.example.registration.utils.Constants;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginActivity extends AppCompatActivity {
    List<Empleado> empleados;
    CRUD crud;
    private String EmailAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView btn = findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        Button btnLog = findViewById(R.id.btnlogin);
        btnLog.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    EditText email = findViewById(R.id.inputEmail);
                    EditText password = findViewById(R.id.inputPassword);
                    if(email.getText().toString().length() == 0 || password.getText().toString().length() == 0){
                        Log.i("Error:","No pueden haber contraseñas ó usuarios vacios");
                    }else {
                        String response = getAll(email, password);
                    }
                }
            });

    }

    private String getAll(EditText email, EditText password){

        EmailAddress = "Nuevo Ingreso";
        Retrofit retrofit=new Retrofit.Builder().baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        crud = retrofit.create(CRUD.class);
        Call<List<Empleado>> call= crud.getAll();
        call.enqueue(new Callback<List<Empleado>>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call<List<Empleado>> call, Response<List<Empleado>> response) {
                if(!response.isSuccessful()){
                    Toast toast= Toast.makeText(getApplicationContext(),response.message(),Toast.LENGTH_LONG);
                    toast.show();

                    Log.e("Response err: ",response.message());
                    return;
                }

                String emailComparative =  email.getText().toString().toLowerCase(Locale.ROOT);
                String passComparative = password.getText().toString();
                empleados = response.body();
                empleados.forEach(p -> {
                    //int i = Log.i("Prods: ", p.getEmail().toString());
                    Log.i("XD",emailComparative);
                 if (emailComparative.equals(p.getEmail().toString().toLowerCase(Locale.ROOT))  && passComparative.equals(p.getPassword().toString())){
                     EmailAddress = "Es correcto";
                 }
                });

                if(EmailAddress.equals("Es correcto")){
                    startActivity(new Intent(LoginActivity.this, SliderMenuActivity.class));
                }
            }

            @Override
            public void onFailure(Call<List<Empleado>> call, Throwable t) {
                Toast toast=Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_LONG);
                toast.show();
                Log.e("Throw err: ",t.getMessage());
            }
        });
        return EmailAddress;
    }
}
