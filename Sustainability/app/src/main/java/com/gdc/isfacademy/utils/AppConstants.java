package com.gdc.isfacademy.utils;



@SuppressWarnings("ALL")
public class AppConstants {

    public static final int SPLASH_HOLD_TIME = 3000;
    public static final int TOP_RANKED_VIEW=0;
    public static final int OTHERS_RANK_VIEW=1;
    public static final String API_KEY="25a64d9a-b9c8-44d9-9a7d-e76aa39913429086752942922195079";
    public static final String CONTENT_TYPE="application/json";
    public static final String REQUEST="Request :";
    public static final String RESPONSE="Response :";

    public static final String STUDENT_ID="studentId";
    public static final String STUDENT_KEY="studentKey";
    public static final String STUDENT_NAME="studentName";
    public static final String STUDENT_HOUSE="studentHouse";

    public static final String RANK_TYPE_FRIEND="friend";
    public static final String RANK_TYPE_HOUSE="house";
    public static final String RESPONSE_CODE_SUCCUSS="SUCCESS";
    public static final String ANSWER_COUNT="answerCount";
    public static final String CURRENT_ENERGY_CONSUMPTION="cec";
    public static final String CURRENT_ENERGY_UNIT="currentEnergyUnit";

    public static final String RESPONSE_CODE_INVALID_STAFF_CODE="C9904";
    public static final String RESPONSE_CODE_INVALID_STAFF_CODE_SECOND="C0711";
    public static final String RESPONSE_CODE_INVALID_VOUCHER="C0707";


    public static final int  ERROR_LOG=1;
    public static final int  DEBUG_LOG=0;
    public static final int REQUEST_LOCATION=101;
    public static final String ERROR_CODE_STUDENT_KEY_NOT_MATCHED="C0703";

    public static final String ID_ENERGY_SAVING="1";
    public static final String ID_QUIZHOLIC="2";
    public static final String ID_PERSISTANT="3";
    public static final String ID_PERFECTION="4";
    public static final String ID_BROADCASTER="6";
    public static final String ID_FRIENDLY="7";
    public static final String ID_LEGENDARY="8";
    public static final String ID_SUPERIOR_SPECLIST="9";
    public static final String ID_CHAMPION="10";


    public static final float VALUE_ENERGY_SAVING_BRNOZE=5;
    public static final float VALUE_ENERGY_SAVING_SILVER=10;
    public static final float VALUE_ENERGY_SAVING_GOLD=30;
    public static final float VALUE_ENERGY_SAVING_HIDDEN=50;

    public static final float VALUE_QUIZHOLIC_BRONZE=10;
    public static final float VALUE_QUIZHOLIC_SILVER=20;
    public static final float VALUE_QUIZHOLIC_GOLDE=50;
    public static final float VALUE_QUIZHOLIC_HIDDEN=100;

    public static final float VALUE_PERSISTANT_BRONZE=5;
    public static final float VALUE_PERSISTANT_SILVER=10;
    public static final float VALUE_PERSISTANT_GOLD=20;
    public static final float VALUE_PERSISTANT_HIDDEN=50;

    public static final float VALUE_PERFECTION_BRONZE=5;
    public static final float VALUE_PERFECTION_SILVER=10;
    public static final float VALUE_PERFECTION_GOLD=20;
    public static final float VALUE_PERFECTION_HIDDEN=50;

    public static final float VALUE_BROADCASTER_BRONZE=30;
    public static final float VALUE_BROADCASTER_SILVER=80;
    public static final float VALUE_BROADCASTER_GOLD=150;
    public static final float VALUE_BROADCASTER_HIDDEN=300;

    public static final float VALUE_FRIENDLY_BRONZE=5;
    public static final float VALUE_FRIENDLY_SILVER=15;
    public static final float VALUE_FRIENDLY_GOLD=50;
    public static final float VALUE_FRIENDLY_HIDDEN=100;

    public static final float VALUE_LEGENDARY_BRONZE=1;
    public static final float VALUE_LEGENDARY_SILVER=2;
    public static final float VALUE_LEGENDARY_GOLD=5;
    public static final float VALUE_LEGENDARY_HIDDEN=10;

    public static final float VALUE_SUPERIOR_SPECLIST_BRONZE=1;
    public static final float VALUE_SUPERIOR_SPECLIST_SILVER=2;
    public static final float VALUE_SUPERIOR_SPECLIST_GOLD=5;
    public static final float VALUE_SUPERIOR_SPECLIST_HIDDEN=10;


    public static final float VALUE_CHAMPION_BRONZE=1;
    public static final float VALUE_CHAMPION_SILVER=2;
    public static final float VALUE_CHAMPION_GOLD=5;
    public static final float VALUE_CHAMPION_HIDDEN=10;


    public static final String typeStudenLogUser="user";
    public static final String typeStudenLogBuilding="building";
    public static final String typeStudenLogShare="share";
    public static final String typeStudenLogChallange="challenge";

    public static final String LIST_BADGE="list";
    public static final int UPDATE_BADGE_TIME=700;
    public static final String PICK_ENERGY_SAVING_DATE="date";


    public static final String HIDDEN="A";
    public static final String GOLD="B";
    public static final String SILVER="C";
    public static final String BRONZE="D";
    public static final String LOCKED="E";

    public static final String QUESTION="question";
    public static final String QUESTION_NUMBER="questionNo";
    public static final String TOTAL_QUESTION="totalQuestion";
    public static final String OPTIONS="options_list";

    public static final int SHARE_QUIZ_RESULT_STATUS=101;
    public static final String TRUE="true";
    public static final String FALSE="false";

    public static final String ABOUT_US_URL="https://www.isf.edu.hk/";
    public static final String TERMS_AND_CONDITION_URL="https://www.energybox.com/terms-of-sales/";
    public static final String PRIVACY_POLICY_URL="https://www.energybox.com/privacy-policy/";
    public static final String REAL_TIME_SITE="http://webxr.s3-accelerate.amazonaws.com/ISF/index.html?l=true";

    public static final String FORGOT_PASSWORD="https://powerschool.isf.edu.hk/public/account_recovery_begin.html";
    public static final String USED_REWARD="USED";
    public static final String ACTIVE_REWARD="ACTIVE";

    public static final String IS_STATUS_MAP="isStatusMap";
    public static final String OTHER="other";

    public static final String FAQ_TEXT = "<!doctype html>\n" +
            "<html lang=\"en\">\n" +
            "   <head>\n" +
            "      <meta charset=\"utf-8\">\n" +
            "      <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">\n" +
            "      <title>HTML Template</title>\n" +
            "      <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "      <style type=\"text/css\">\n" +
            "        body{\n" +
            "          font-family: arial;\n" +
            "          font-size: 15px;\n" +
            "        }\n" +
            "        p{\n" +
            "          margin-top: 7px;\n" +
            "        }\n" +
            "      </style>\n" +
            "   </head>\n" +
            "   <body>\n" +
            "<h4 style=\"margin-bottom: 0px\">Cycle</h4>\n" +
            "<p>If you’re close to a school environment, you must know !!!<br/>\n" +
            "An every 10-day loop schedule</p>\n" +
            "\n" +
            "<p>Same Period Last Cycle<br/>\n" +
            "Same time period this week compared to same time period last week </p>\n" +
            "\n" +
            "<h4 style=\"margin-bottom: 0px\">Ranking</h4>\n" +
            "<p>A ranking sorted by the the scores you earnt;  based on the 3 areas ( energy consumption, daily quiz and sharings) </p>\n" +
            "\n" +
            "\n" +
            "<h4 style=\"margin-bottom: 0px\">Rewards</h4>\n" +
            "<p>Users can earn a prize each cycle if they fulfill all 3 requirements below:<br/>\n" +
            "1) one of the first 5 places<br/>\n" +
            "2) at least did 1 quiz <br/>\n" +
            "3) at least shared the result once<br/>\n" +
            "</p>\n" +
            "\n" +
            "<h4 style=\"margin-bottom: 0px\">Prize</h4>\n" +
            "<p>A HK$40 cash coupon of educafe will be provided. No change, and it is not allowed to change into cash. Each coupon can only redeemed once..</p>\n" +
            "\n" +
            "<p>User can spend as much as the value on the voucher,  and will need to pay the rest if the total amount is over the value (HK$40).</p>\n" +
            "\n" +
            "\n" +
            "<h4 style=\"margin-bottom: 0px\">Friends</h4>\n" +
            "<p>Users can add friends to their own friend list by sending an email invitation. Users can also manage their friend list (accept/ decline requests or remove a friend) on the page.</p>\n" +
            "\n" +
            "   </body>\n" +
            "</html>";








}
