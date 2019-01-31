package com.gdc.isfacademy.netcom;

import com.gdc.isfacademy.model.BuildingEnergySaving;
import com.gdc.isfacademy.model.CommonResponse;
import com.gdc.isfacademy.model.EnergySavingResponse;
import com.gdc.isfacademy.model.HouseParentResponse;
import com.gdc.isfacademy.model.LoginParentResponse;
import com.gdc.isfacademy.model.ParentFriendListResponse;
import com.gdc.isfacademy.model.QuizParentResponse;
import com.gdc.isfacademy.model.RankingParentResponse;
import com.gdc.isfacademy.model.RetrieveDailyConsResponse;
import com.gdc.isfacademy.model.StudentBadgeResponse;
import com.gdc.isfacademy.model.StudentFootPrintResponse;
import com.gdc.isfacademy.model.StudentLogResponse;
import com.gdc.isfacademy.model.StudentRewardResponse;
import com.gdc.isfacademy.model.StudentSavedCostResponse;
import com.gdc.isfacademy.model.StudentStatusResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


@SuppressWarnings("ALL")
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

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST(ApiConstants.ApiUrls.AUTH + ApiConstants.ApiUrls.LOGIN)
    Call<LoginParentResponse> loginnew(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                       @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                       @Body() RequestBody jsonObject);


    /*
    *
    * Api call for submitting quiz result answer to server
    *
    * */
    @GET(ApiConstants.ApiUrls.SUBMIT_ANSWER)
    Call<CommonResponse> submitQuestion(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                        @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                        @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                        @Query(ApiConstants.ApiParams.CORRECT) String jsonObject);

    /*
    *
    *
    * Api call for gettinga all student list according to there ranking.
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_STUDENTS_RANKINGS)
    Call<RankingParentResponse> getStudentRankings(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                   @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                   @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                                   @Query(ApiConstants.ApiParams.TYPE) String type);


    /*
 *
 *
 * Api call for house according to there ranking.
 *
 * */
    @GET(ApiConstants.ApiUrls.GET_HOUSE_RANKINGS)
    Call<HouseParentResponse> getHouseRankings(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                               @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                               @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);


    /*
    *
    *
    * Api call to get student energy saving.
    *
    * */

    @GET(ApiConstants.ApiUrls.GET_STUDENTS_ENERGY_SAVING)
    Call<EnergySavingResponse> getStudentEnergySaving(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                      @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                      @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                                      @Query(ApiConstants.ApiParams.TYPE) String type);

    /*
    *
    *
    * Api call to get building enrgy saving data
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_BUILDING_ENERGY_SAVING)
    Call<BuildingEnergySaving> getBuildingStudentEnergySaving(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                              @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                              @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    /*
    *
    *
    * Api call to check quiz result shared on diffrent platform
    *
    * */
    @GET(ApiConstants.ApiUrls.SUBMIT_SHARE_POINTS)
    Call<CommonResponse> submitSharePoints(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                           @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                           @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    /*
    *
    *
    * Api call to get student foot print record
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_STUDENT_FOOT_PRINT)
    Call<StudentFootPrintResponse> getStudentFootPrint(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                       @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                       @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                                       @Query(ApiConstants.ApiParams.TYPE) String type);


    /*
    *
    *
    * Api call to get student saved cost data
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_STUDENT_COST_SAVED)
    Call<StudentSavedCostResponse> getStudentCostSaved(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                       @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                       @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                                       @Query(ApiConstants.ApiParams.TYPE) String type);

    /*
    *
    *
    * Api call to check quiz submitted for the day or not
    *
    * */
    @GET(ApiConstants.ApiUrls.CHECK_STUDENT_QUESTION)
    Call<CommonResponse> checkStudenQuestions(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                              @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                              @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    /*
    *
    *
    * Api call for getting reward list of student logged in.
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_STUDENT_REWARDS)
    Call<StudentRewardResponse> getStudentRewards(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                  @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                  @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    /*
    *
    *
    * Api call for getting consumption comparison of student and school
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_DAILY_CONSUMPTION_RETRIVE_LIST)
    Call<RetrieveDailyConsResponse> getDailyConsumptionGraphList(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                                 @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                                 @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                                                 @Query(ApiConstants.ApiParams.TYPE) String type);

    /*
    *
    * Api Call for getting student activity logs.
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_STUDENT_LOGS)
    Call<StudentLogResponse> getStudentLogs(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                            @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                            @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    /*
    *
    * Api call for getting student badges allotment to student
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_STUDENT_BADGES)
    Call<StudentBadgeResponse> getStudentBadges(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    /*
    *
    * Api call for getting student status for ranking,challenge count,share count
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_STUDENT_STATUS)
    Call<StudentStatusResponse> getStudentStatus(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                 @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                 @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    /*
    *
    * Api call for retriev student reward token.
    *
    * */
    @GET(ApiConstants.ApiUrls.GET_REWARD_TOKEN)
    Call<CommonResponse> getRewardToken(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                        @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                        @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                        @Query(ApiConstants.ApiParams.REWARD_ID) String isfRewardsId);


    /*
    *
    *
    * Api call to reddem student reward.
    *
    * */
    @GET(ApiConstants.ApiUrls.REDEEM_REWARD)
    Call<CommonResponse> redeemReward(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                      @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                      @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                      @Query(ApiConstants.ApiParams.REWARD_TOKEN) String token,
                                      @Query(ApiConstants.ApiParams.PASSWORD_REWARD) String staffCode);


    /*
    *
    * Api call for getting student friend list status
    *
    * */
    @GET(ApiConstants.ApiUrls.RETRIVE_FRIEND_LIST)
    Call<ParentFriendListResponse> getFriendList(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                 @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                 @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);

    /*
   *
   * Api call for accepting friend request
   *
   * */
    @GET(ApiConstants.ApiUrls.ACCEPT_FRIEND_REQUEST)
    Call<CommonResponse> acceptFriendRequest(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                             @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                             @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                             @Query(ApiConstants.ApiParams.FRIEND_ID) String friendId);


    /*
    *
    *
    * Api call for Remove friend from friend list
    *
    *
    * */
    @GET(ApiConstants.ApiUrls.REMOVE_FRIEND)
    Call<CommonResponse> removeFriendFromList(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                              @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                              @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                              @Query(ApiConstants.ApiParams.FRIEND_ID) String friendId);


    /*
    *
    * Api call for sending friend request.
    *
    * */
    @GET(ApiConstants.ApiUrls.SEND_FRIEND_REQUEST_VIA_EMAIL)
    Call<CommonResponse> sendFriendRequest(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                           @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                           @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey,
                                           @Query(ApiConstants.ApiParams.EMAIL) String email,
                                           @Query(ApiConstants.ApiParams.BODY) String body);


    /*
  *
  *
  * Api call to check quiz result shared on diffrent platform
  *
  * */
    @GET(ApiConstants.ApiUrls.START_QUIZ)
    Call<QuizParentResponse> getQuizQuestionAnswer(@Header(ApiConstants.ApiParams.HEADER_API_KEY) String apiKey,
                                                   @Header(ApiConstants.ApiParams.HEADER_CONTENT_TYPE) String contentType,
                                                   @Query(ApiConstants.ApiParams.STUDENT_KEY) String studentKey);
}
