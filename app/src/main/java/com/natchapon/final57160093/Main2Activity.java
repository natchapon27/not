package com.natchapon.final57160093;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.natchapon.final57160093.OPPMSService.OPPMSDAO.OPPMSDAO;
import com.natchapon.final57160093.OPPMSService.OPPMSService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main2Activity extends AppCompatActivity {

    private OPPMSService service;
    private PieChart mypiechart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mypiechart = (PieChart)findViewById(R.id.mypiechart);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://http://dekdee.buu.ac.th/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(OPPMSService.class);

        service.getOPPMSData().enqueue(new Callback<OPPMSDAO>() {
            @Override
            public void onResponse(Call<OPPMSDAO> call, Response<OPPMSDAO> response) {
                Log.d("RESPONSE", "SUCCESS");
                if(response.isSuccessful()) {
                    //Log.d("RESPONSE", response.body().details.creator);
                    ArrayList<PieEntry> entries = new ArrayList<PieEntry>();
                    for (int i = 0 ; i < response.body().graphData.size() ; i++){
                        entries.add(new PieEntry(
                                response.body().graphData.get(i).cat,
                                response.body().graphData.get(i).dog
                                )
                        );

                        PieDataSet pieDataSet = new PieDataSet(entries, "####");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        PieData pieData = new PieData(pieDataSet);
                        mypiechart.setData(pieData);

                    }
                } else {
                    Log.d("RESPONSE", "SERVICE ERROR");
                }
            }

            @Override
            public void onFailure(Call<OPPMSDAO> call, Throwable t) {
                Log.d("RESPONSE", t.getMessage());
            }
        });

    }
}
