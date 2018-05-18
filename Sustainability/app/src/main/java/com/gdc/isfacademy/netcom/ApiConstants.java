package com.gdc.isfacademy.netcom;

import com.gdc.isfacademy.BuildConfig;

/**
 * Created by ashishthakur on 17/4/18.
 */

public interface ApiConstants {

    public static final String API_SERVER_URL = BuildConfig.BASE_URL;


    public static class ApiUrls {
        /*
        * API Names
        * */
        public static final String LOGIN = "login";
        public static final String SUBMIT_ANSWER="submitQuestion";
        public static final String GET_STUDENTS_RANKINGS="getStudentRankingList";
        public static final String GET_STUDENTS_ENERGY_SAVING="getStudentEnergySaving";
        public static final String GET_BUILDING_ENERGY_SAVING="getBuildingEnergySaving";
        public static final String SUBMIT_SHARE_POINTS="submitShare";
        public static final String GET_STUDENT_FOOT_PRINT="getStudentFootPrint";

    }

    public class ApiParams {
        /*
     *
     * Common Post Params
     * */
        public static final String USER_ID = "user_id";
        public static final String HEADER_API_KEY="api-key";
        public static final String HEADER_CONTENT_TYPE="Content-Type";

        /*LOGIN API PARAMS*/
        public static final String STUDENT_ID="studentId";
        public static final String PASSWORD="password";
        public static final String STUDENT_KEY="studentKey";
        public static final String CORRECT="correct";
        public static final String TYPE="type";








    }



}
