package com.rizqy.cruise.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.rizqy.cruise.R;
import com.rizqy.cruise.Util.Api.ApiClient;
import com.rizqy.cruise.Util.Api.ApiService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationAddActivity extends AppCompatActivity {
    EditText edt_photo,edt_price,edt_ship,edt_title,edt_date,edt_visiting;
    Button btn_save;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.destination_add_activity );

        edt_photo = (EditText)findViewById( R.id.edt_photo );
        edt_price = (EditText)findViewById( R.id.edt_price );
        edt_ship = (EditText)findViewById( R.id.edt_ship );
        edt_title = (EditText)findViewById( R.id.edt_title );
        edt_date = (EditText)findViewById( R.id.edt_date );
        edt_visiting = (EditText)findViewById( R.id.edt_visiting );
        btn_save = (Button)findViewById( R.id.btn_save );

        btn_save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addDestination();
            }
        } );
    }

    private void addDestination() {
        loading = new ProgressDialog( this);
        loading.setCancelable( false );
        loading.setTitle( null );
        loading.setMessage( "Harap Tunggu ..." );
        loading.setProgressStyle( ProgressDialog.STYLE_SPINNER );
        loading.setIndeterminate( true );
        loading.setIndeterminateDrawable( getResources().getDrawable( R.drawable.progress_icon ) );
        loading.show();

        ApiService service = ApiClient.getRetrofit().create( ApiService.class );
        Call<ResponseBody> destinations = service.postDestination(
                edt_photo.getText().toString(),
                edt_price.getText().toString(),
                edt_ship.getText().toString(),
                edt_title.getText().toString(),
                edt_date.getText().toString(),
                edt_visiting.getText().toString()
        );
        destinations.enqueue( new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Berhasil menambahkan data", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DestinationMainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "All Field must be required ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                loading.dismiss();
                Log.e("Retrofit Get", t.toString());
            }
        } );
    }
}
