package com.gdc.isfacademy.netcom;

import com.gdc.isfacademy.model.BuildingEnergySaving;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.model.EnergySavingResponse;
import com.gdc.isfacademy.model.LoginParentResponse;
import com.gdc.isfacademy.model.RankingParentResponse;

import org.json.JSONObject;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ashishthakur on 17/4/18.
 */

public interface Api {

    /*
    *
    *
    * USER LOGIN API CALL
    *
    * */

    /*
    *
    * {"studentId":10010006,"password":"A24cFuxs"}
    * */

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST(ApiConstants.ApiUrls.LOGIN)
    Call<LoginParentResponse> login(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                    @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                    @Body() RequestBody jsonObject);

    @GET(ApiConstants.ApiUrls.SUBMIT_ANSWER)
    Call<CommonResponse> submitQuestion(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                        @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                        @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                        @Query(ApiConstants.ApiParams.CORRECT) String jsonObject);

    @GET(ApiConstants.ApiUrls.GET_STUDENTS_RANKINGS)
    Call<RankingParentResponse> getStudentRankings(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                   @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                   @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                                   @Query(ApiConstants.ApiParams.TYPE) String type);

    @GET(ApiConstants.ApiUrls.GET_STUDENTS_ENERGY_SAVING)
    Call<EnergySavingResponse> getStudentEnergySaving(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                      @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                      @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    @GET(ApiConstants.ApiUrls.GET_BUILDING_ENERGY_SAVING)
    Call<BuildingEnergySaving> getBuildingStudentEnergySaving(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                              @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                              @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

}
