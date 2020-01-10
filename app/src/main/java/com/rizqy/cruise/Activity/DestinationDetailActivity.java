package com.rizqy.cruise.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rizqy.cruise.R;
import com.rizqy.cruise.Util.Api.ApiClient;
import com.rizqy.cruise.Util.Api.ApiService;
import com.rizqy.cruise.Util.Constant;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationDetailActivity extends AppCompatActivity {
    TextView price, title, ship, date, visiting;
    ImageView photo;
    Button hapus, edit;

    ProgressDialog loading;
    String mId, mPhoto, mPrice, mTitle, mShip, mDate, mVisiting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.destination_detail_activity );

        photo = (ImageView) findViewById( R.id.img_destination);
        price = (TextView) findViewById(R.id.price);
        title = (TextView) findViewById(R.id.title);
        ship = (TextView) findViewById(R.id.ship);
        date = (TextView) findViewById(R.id.date);
        visiting = (TextView) findViewById(R.id.visiting);
        hapus = (Button) findViewById( R.id.btn_hapus );
        edit = (Button) findViewById( R.id.btn_edit );

        mId = getIntent().getStringExtra( Constant.KEY_ID_DESTINATION );
        mPrice = getIntent().getStringExtra( Constant.KEY_PRICE);
        mTitle = getIntent().getStringExtra( Constant.KEY_TITLE );
        mShip = getIntent().getStringExtra( Constant.KEY_SHIP );
        mDate = getIntent().getStringExtra( Constant.KEY_DATE );
        mVisiting = getIntent().getStringExtra( Constant.KEY_VISITING);
        mPhoto = getIntent().getStringExtra( Constant.KEY_PHOTO );

        Glide.with( this )
                .asBitmap()
                .load( mPhoto )
                .into( photo );

        price.setText( mPrice );
        title.setText( mTitle );
        ship.setText( mShip );
        date.setText( mDate );
        visiting.setText( mVisiting );

        hapus.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusData();
            }
        } );

        edit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DestinationUpdateActivity.class);
                intent.putExtra( Constant.KEY_ID_DESTINATION, mId );
                intent.putExtra( Constant.KEY_PHOTO, mPhoto );
                intent.putExtra( Constant.KEY_PRICE, mPrice );
                intent.putExtra( Constant.KEY_TITLE, mTitle );
                intent.putExtra( Constant.KEY_SHIP, mShip );
                intent.putExtra( Constant.KEY_DATE, mDate );
                intent.putExtra( Constant.KEY_VISITING, mVisiting );
                view.getContext().startActivity(intent);
            }
        } );
    }

    private void hapusData() {
        loading = new ProgressDialog( this);
        loading.setCancelable( false );
        loading.setTitle( null );
        loading.setMessage( "Harap Tunggu ..." );
        loading.setProgressStyle( ProgressDialog.STYLE_SPINNER );
        loading.setIndeterminate( true );
        loading.setIndeterminateDrawable( getResources().getDrawable( R.drawable.progress_icon ) );
        loading.show();

        ApiService service = ApiClient.getRetrofit().create( ApiService.class );
        Call<ResponseBody> destinations = service.deleteDestination(mId);
        destinations.enqueue( new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()){
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Berhasil mengapus destinasi", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), DestinationMainActivity.class)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                }else {
                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal menghapus destinasi ", Toast.LENGTH_SHORT).show();
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
