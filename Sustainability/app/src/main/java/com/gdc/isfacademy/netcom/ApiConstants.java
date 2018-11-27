package com.gdc.isfacademy.netcom;

import com.gdc.isfacademy.BuildConfig;


@SuppressWarnings("ALL")
public interface ApiConstants {
    String API_SERVER_URL = BuildConfig.BASE_URL;
    String API_LOGIN_URL = "https://iam.staging.energybox.com/api/v1/";


    class ApiUrls {
        /*
        * API Names
        * */
        public static final String LOGIN = "login";
        public static final String SUBMIT_ANSWER = "submitQuestion";
        public static final String GET_STUDENTS_RANKINGS = "getStudentRankingList";
        public static final String GET_STUDENTS_ENERGY_SAVING = "getStudentEnergySaving";
        public static final String GET_BUILDING_ENERGY_SAVING = "getBuildingEnergySaving";
        public static final String SUBMIT_SHARE_POINTS = "submitShare";
        public static final String GET_STUDENT_FOOT_PRINT = "getStudentFootPrint";
        public static final String GET_STUDENT_COST_SAVED = "getStudentCostSaved";
        public static final String CHECK_STUDENT_QUESTION = "checkStudentQuestion";
        public static final String GET_DAILY_CONSUMPTION_RETRIVE_LIST = "retrieveDailyConsList";
        public static final String GET_STUDENT_BADGES = "retrieveStudentBadges";
        public static final String GET_STUDENT_LOGS = "retrieveStudentLog";
        public static final String GET_STUDENT_REWARDS = "retrieveStudentRewards";
        public static final String GET_STUDENT_STATUS = "retrieveStudentStatus";
        public static final String GET_REWARD_TOKEN = "retrieveStudentRewardsToken";
        public static final String REDEEM_REWARD = "redeemReward";
        public static final String RETRIVE_FRIEND_LIST="retrieveFriendList";
        public static final String ACCEPT_FRIEND_REQUEST="confirmFriend";
        public static final String SEND_FRIEND_REQUEST_VIA_EMAIL="addFriend";
        public static final String REMOVE_FRIEND="deleteFriend";
        public static final String START_QUIZ="retrieveQNA";




        /*
        *
        * API Sub
        * */

        public static final String AUTH = "auth/";

    }

    class ApiParams {
        /*
     *
     * Common Post Params
     * */
        public static final String USER_ID = "user_id";
        public static final String HEADER_API_KEY = "api-key";
        public static final String HEADER_CONTENT_TYPE = "Content-Type";

        /*LOGIN API PARAMS*/
        public static final String STUDENT_ID = "studentId";
        public static final String EMAIL = "email";
        public static final String ORGANIZATION_ID = "organizationId";

        public static final String PASSWORD = "password";
        public static final String STUDENT_KEY = "studentKey";
        public static final String CORRECT = "correct";
        public static final String TYPE = "type";
        public static final String REWARD_ID="isfRewardsId";
        public static final String REWARD_TOKEN="token";
        public static final String PASSWORD_REWARD="password";

        public static final String FRIEND_ID="friendId";
        public static final String BODY="body";


    }


}
