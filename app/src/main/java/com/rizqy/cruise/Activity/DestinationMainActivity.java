package com.rizqy.cruise.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rizqy.cruise.Adapter.RecyclerHorizontalAdapter;
import com.rizqy.cruise.Model.Destination;
import com.rizqy.cruise.Model.DestinationResponse;
import com.rizqy.cruise.Util.Api.ApiClient;
import com.rizqy.cruise.Util.Api.ApiService;
import com.rizqy.cruise.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DestinationMainActivity extends AppCompatActivity {

    ProgressDialog loading;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    public static DestinationMainActivity dma;

    Button btn_add;
    String ship ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.destination_main_activity );

        mRecyclerView = (RecyclerView) findViewById(R.id.rvDestination);
        mRecyclerView.setHasFixedSize( true );
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false );
        mRecyclerView.setLayoutManager(mLayoutManager);
        dma = this;

        btn_add = findViewById( R.id.btn_add );
        btn_add.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent( DestinationMainActivity.this, DestinationAddActivity.class ) );
            }
        } );

        final EditText searchView = (EditText) findViewById( R.id.search );
        searchView.addTextChangedListener( new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ship = searchView.getText().toString().trim();
                loadDestination(ship);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

        final TextView searchRoyal = (TextView) findViewById( R.id.royal );
        searchRoyal.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDestination( "royal" );
            }
        } );

        loadDestination( "" );

    }

    private void loadDestination(String ship) {
//        loading = new ProgressDialog( this);
//        loading.setCancelable( false );
//        loading.setTitle( null );
//        loading.setMessage( "Harap Tunggu ..." );
//        loading.setProgressStyle( ProgressDialog.STYLE_SPINNER );
//        loading.setIndeterminate( true );
//        loading.setIndeterminateDrawable( getResources().getDrawable( R.drawable.progress_icon ) );
//        loading.show();

        ApiService service = ApiClient.getRetrofit().create( ApiService.class );
        Call<DestinationResponse> destinations = service.getDestination(ship);
        destinations.enqueue( new Callback<DestinationResponse>() {
            @Override
            public void onResponse(Call<DestinationResponse> call, Response<DestinationResponse> response) {
                if (response.isSuccessful()){
//                    loading.dismiss();
                    List<Destination> DestinationList =response.body().getDestinations();
                    Log.d("Retrofit Get", "Jumlah data Destination: " +
                            String.valueOf(DestinationList.size()));
                    mAdapter = new RecyclerHorizontalAdapter( DestinationList );
                    mRecyclerView.setAdapter( mAdapter );
                }else {
//                    loading.dismiss();
                    Toast.makeText(getApplicationContext(), "Gagal mengambil data ", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DestinationResponse> call, Throwable t) {
//                loading.dismiss();
                Log.e("Retrofit Get", t.toString());
            }
        } );

    }
}
