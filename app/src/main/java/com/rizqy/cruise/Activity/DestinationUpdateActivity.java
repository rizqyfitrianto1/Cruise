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
import com.rizqy.cruise.Util.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationUpdateActivity extends AppCompatActivity {
    EditText edt_photo,edt_price,edt_ship,edt_title,edt_date,edt_visiting;
    Button btn_save;

    ProgressDialog loading;
    String mId, mPhoto, mPrice, mTitle, mShip, mDate, mVisiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.destination_update_activity );
        
        edt_photo = (EditText)findViewById( R.id.edt_photo );
        edt_price = (EditText)findViewById( R.id.edt_price );
        edt_ship = (EditText)findViewById( R.id.edt_ship );
        edt_title = (EditText)findViewById( R.id.edt_title );
        edt_date = (EditText)findViewById( R.id.edt_date );
        edt_visiting = (EditText)findViewById( R.id.edt_visiting );
        btn_save = (Button)findViewById( R.id.btn_save );

        mId = getIntent().getStringExtra( Constant.KEY_ID_DESTINATION );
        mPrice = getIntent().getStringExtra( Constant.KEY_PRICE);
        mTitle = getIntent().getStringExtra( Constant.KEY_TITLE );
        mShip = getIntent().getStringExtra( Constant.KEY_SHIP );
        mDate = getIntent().getStringExtra( Constant.KEY_DATE );
        mVisiting = getIntent().getStringExtra( Constant.KEY_VISITING);
        mPhoto = getIntent().getStringExtra( Constant.KEY_PHOTO );

//        Glide.with( this )
//                .asBitmap()
//                .load( mPhoto )
//                .into( edt_photo );
        edt_photo.setText( mPhoto );
        edt_price.setText( mPrice );
        edt_ship.setText( mTitle );
        edt_title.setText( mShip );
        edt_date.setText( mDate );
        edt_visiting.setText( mVisiting );
        
        btn_save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUpdate();
            }
        } );
    }

    private void saveUpdate() {
        loading = new ProgressDialog( this);
        loading.setCancelable( false );
        loading.setTitle( null );
        loading.setMessage( "Harap Tunggu ..." );
        loading.setProgressStyle( ProgressDialog.STYLE_SPINNER );
        loading.setIndeterminate( true );
        loading.setIndeterminateDrawable( getResources().getDrawable( R.drawable.progress_icon ) );
        loading.show();

        ApiService service = ApiClient.getRetrofit().create( ApiService.class );
        Call<ResponseBody> destinations = service.putDestination(mId,
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
                    Toast.makeText(getApplicationContext(), "Data berhasil diupdate", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DestinationMainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal mengupdate data ", Toast.LENGTH_SHORT).show();
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
