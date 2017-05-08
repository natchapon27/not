package com.natchapon.final57160093.OPPMSService;

import com.natchapon.final57160093.OPPMSService.OPPMSDAO.OPPMSDAO;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Created by Natchapon on 4/5/2560.
 */

public interface OPPMSService {

    @POST("~56160140/final_service/graph_cycle.php")
    Call<OPPMSDAO> getOPPMSData();




}
