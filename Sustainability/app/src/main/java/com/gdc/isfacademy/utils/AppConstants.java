package com.gdc.isfacademy.utils;


import android.content.Context;

@SuppressWarnings("ALL")
public class AppConstants {

    public static final int SPLASH_HOLD_TIME = 3000;
    public static final int TOP_RANKED_VIEW = 0;
    public static final int OTHERS_RANK_VIEW = 1;
    public static final int HOUSE_RANK_VIEW = 2;
    public static final String API_KEY = "25a64d9a-b9c8-44d9-9a7d-e76aa39913429086752942922195079";
    public static final String CONTENT_TYPE = "application/json";
    public static final String REQUEST = "Request :";
    public static final String RESPONSE = "Response :";

    public static final String STUDENT_ID = "studentId";
    public static final String STUDENT_KEY = "studentKey";
    public static final String STUDENT_NAME = "studentName";
    public static final String STUDENT_HOUSE = "studentHouse";

    public static final String RANK_TYPE_FRIEND = "friend";
    public static final String RANK_TYPE_HOUSE = "house";
    public static final String RESPONSE_CODE_SUCCUSS = "SUCCESS";
    public static final String ANSWER_COUNT = "answerCount";
    public static final String CURRENT_ENERGY_CONSUMPTION = "cec";
    public static final String CURRENT_ENERGY_UNIT = "currentEnergyUnit";

    public static final String RESPONSE_CODE_INVALID_STAFF_CODE = "C9904";
    public static final String RESPONSE_CODE_INVALID_STAFF_CODE_SECOND = "C0711";
    public static final String RESPONSE_CODE_INVALID_VOUCHER = "C0707";


    public static final int ERROR_LOG = 1;
    public static final int DEBUG_LOG = 0;
    public static final int REQUEST_LOCATION = 101;
    public static final String ERROR_CODE_STUDENT_KEY_NOT_MATCHED = "C0703";
    public static final String ERROR_CODE_FRIEND_NOT_EXIST = "C0713";

    public static final String ID_ENERGY_SAVING = "1";
    public static final String ID_QUIZHOLIC = "2";
    public static final String ID_PERSISTANT = "3";
    public static final String ID_PERFECTION = "4";
    public static final String ID_BROADCASTER = "6";
    public static final String ID_FRIENDLY = "7";
    public static final String ID_LEGENDARY = "8";
    public static final String ID_SUPERIOR_SPECLIST = "9";
    public static final String ID_CHAMPION = "10";


    public static final float VALUE_ENERGY_SAVING_BRNOZE = 5;
    public static final float VALUE_ENERGY_SAVING_SILVER = 10;
    public static final float VALUE_ENERGY_SAVING_GOLD = 30;
    public static final float VALUE_ENERGY_SAVING_HIDDEN = 50;

    public static final float VALUE_QUIZHOLIC_BRONZE = 10;
    public static final float VALUE_QUIZHOLIC_SILVER = 20;
    public static final float VALUE_QUIZHOLIC_GOLDE = 50;
    public static final float VALUE_QUIZHOLIC_HIDDEN = 100;

    public static final float VALUE_PERSISTANT_BRONZE = 5;
    public static final float VALUE_PERSISTANT_SILVER = 10;
    public static final float VALUE_PERSISTANT_GOLD = 20;
    public static final float VALUE_PERSISTANT_HIDDEN = 50;

    public static final float VALUE_PERFECTION_BRONZE = 5;
    public static final float VALUE_PERFECTION_SILVER = 10;
    public static final float VALUE_PERFECTION_GOLD = 20;
    public static final float VALUE_PERFECTION_HIDDEN = 50;

    public static final float VALUE_BROADCASTER_BRONZE = 30;
    public static final float VALUE_BROADCASTER_SILVER = 80;
    public static final float VALUE_BROADCASTER_GOLD = 150;
    public static final float VALUE_BROADCASTER_HIDDEN = 300;

    public static final float VALUE_FRIENDLY_BRONZE = 5;
    public static final float VALUE_FRIENDLY_SILVER = 15;
    public static final float VALUE_FRIENDLY_GOLD = 50;
    public static final float VALUE_FRIENDLY_HIDDEN = 100;

    public static final float VALUE_LEGENDARY_BRONZE = 1;
    public static final float VALUE_LEGENDARY_SILVER = 2;
    public static final float VALUE_LEGENDARY_GOLD = 5;
    public static final float VALUE_LEGENDARY_HIDDEN = 10;

    public static final float VALUE_SUPERIOR_SPECLIST_BRONZE = 1;
    public static final float VALUE_SUPERIOR_SPECLIST_SILVER = 2;
    public static final float VALUE_SUPERIOR_SPECLIST_GOLD = 5;
    public static final float VALUE_SUPERIOR_SPECLIST_HIDDEN = 10;


    public static final float VALUE_CHAMPION_BRONZE = 1;
    public static final float VALUE_CHAMPION_SILVER = 2;
    public static final float VALUE_CHAMPION_GOLD = 5;
    public static final float VALUE_CHAMPION_HIDDEN = 10;


    public static final String typeStudenLogUser = "user";
    public static final String typeStudenLogBuilding = "building";
    public static final String typeStudenLogShare = "share";
    public static final String typeStudenLogChallange = "challenge";

    public static final String LIST_BADGE = "list";
    public static final int UPDATE_BADGE_TIME = 700;
    public static final String PICK_ENERGY_SAVING_DATE = "date";


    public static final String HIDDEN = "A";
    public static final String GOLD = "B";
    public static final String SILVER = "C";
    public static final String BRONZE = "D";
    public static final String LOCKED = "E";

    public static final String QUESTION = "question";
    public static final String QUESTION_NUMBER = "questionNo";
    public static final String TOTAL_QUESTION = "totalQuestion";
    public static final String OPTIONS = "options_list";

    public static final int SHARE_QUIZ_RESULT_STATUS = 101;
    public static final String TRUE = "true";
    public static final String FALSE = "false";

    public static final String ABOUT_US_URL = "https://www.isf.edu.hk/";
    public static final String TERMS_AND_CONDITION_URL = "https://www.energybox.com/terms-of-sales/";
    public static final String PRIVACY_POLICY_URL = "https://www.energybox.com/privacy-policy/";
    public static final String REAL_TIME_SITE = "http://webxr.s3-accelerate.amazonaws.com/ISF/index.html?l=true";

    public static final String FORGOT_PASSWORD = "https://mypw.isf.edu.hk/accounts/Reset";
    public static final String USED_REWARD = "USED";
    public static final String ACTIVE_REWARD = "ACTIVE";

    public static final String IS_STATUS_MAP = "isStatusMap";
    public static final String OTHER = "other";

    public static final String FOR_ANDROID = "android link:-";
    public static final String FOR_IOS = "iphone link:-";
    public static final String ANDROID_APP_LINK = "https://play.google.com/store/apps/details?id=com.energybox.isf&hl=en";
    public static final String IOS_APP_LINK = "https://itunes.apple.com/app/id1423558942?mt=8&ign-mpt=uo%3D4";

    public static final String SOLAR ="\"file:///android_asset/img/solar_icon.png\"";
    public static final String CLASSROOM ="\"file:///android_asset/img/classroom_icon.png\"";
    public static final String FOSSIL_FUEL ="\"file:///android_asset/img/fossilfuel_icon.png\"";
    public static final  String AIRCON ="\"file:///android_asset/img/aircon_icon.png\"";
    public static final String PLUG ="\"file:///android_asset/img/plug_icon.png\"";
    public static final String LIGHTS ="\"file:///android_asset/img/lights_icon.png\"";

    public static final String FAQ_BADGE_TABLE ="\"file:///android_asset/img/faq_table.png\"";


    public static String getContent() {

        return "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<title>ISF</title>\n" +
                "<style type=\"text/css\">\n" +
                "body {\n" +
                "\tfont-family: Arial, Helvetica, sans-serif; font-size:14px; line-height:19px;\n" +
                "}\n" +
                ".wrapper{ padding:10px;}\n" +
                "h1 {\n" +
                "\tfont-size: 24px;\n" +
                "\tpadding:10px 0px;\n" +
                "\tmargin: 0px; \n" +
                "}\n" +
                "hr{margin:15px 0px; color:#f3f3f3;}\n" +
                "h2 {\n" +
                "\tfont-size:18px;\n" +
                "\tpadding:10px 0px;\n" +
                "\tmargin: 0px;\n" +
                "}\n" +
                "p{ margin:0px; padding:6px 0px;}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"font-family:Arial, Helvetica, sans-serif;\">\n" +
                "<div class=\"wrapper\">\n" +
                "  <h1>FAQs</h1>\n" +
                "  <hr>\n" +
                "  <section class=\"section-start\">\n" +
                "    <h2>Hi, I’m the ISF Sustainability app.</h2>\n" +
                "    <p>I’ve been designed by students, teachers and energy heroes to make sustainability the name of the game, every day.</p>\n" +
                "    <p>Are you up for the challenge? Get ready to prove it.</p>\n" +
                "   </section>\n" +
                "  <section class=\"section-one\">\n" +
                "    <h2>How does the app work?</h2>\n" +
                "    <p>The app is all about getting you to think smarter and take action to be more sustainable—and ultimately, protect the planet.</p>\n" +
                "    <p>In the menu is a map of the ISF Academy campus. Here, you can see how much energy is being used in real time in any building or CLASSROOM, or by type of equipment such as aircons, LIGHTS, power sockets, etc.</p>\n" +
                "    <p>When you join the app, you’re automatically grouped into a team. There are 5 house teams, plus 1 teacher team. </p>\n" +
                "    <p>To win, you need to get as many points in a cycle as possible.</p>\n" +
                "    <p>The top 5 people on each team at the end of every cycle will win a $40 coupon to use in the ISF Cafe.</p>\n" +
                "  </section>\n" +
                "  <section class=\"section-two\">\n" +
                "    <h2>How do I earn points?</h2>\n" +
                "    <p>There are three ways to earn points:</p>\n" +
                "    <ol style=\"list-style:decimal; margin:0px;\">\n" +
                "      <li>During the day, take action around the school to reduce energy consumption.</li>\n" +
                "      <li>Answer the daily sustainability quiz questions.</li>\n" +
                "      <li> Share your results with friends. </li>\n" +
                "    </ol>\n" +
                "    <p>A maximum of 600 points can be earned per day, per person.</p>\n" +
                "  </section>\n" +
                "  <section class=\"section-three\">\n" +
                "    <h2>What is a ‘cycle’?</h2>\n" +
                "    <p>Think of a cycle as a new round of the game. A cycle lasts 14 days (just like in the ISF senior school calendar), then automatically restarts. </p>\n" +
                "  </section>\n" +
                "    <section class=\"section-four\">\n" +
                "    <h2>How do I reduce my energy consumption?</h2>\n" +
                "    <p>You’ll find daily tips about how to conserve energy in the app. </p>\n" +
                "    <p>The app knows your schedule and tracks which classrooms and areas you’re in during the day. If the energy usage goes down in the places you’ve been, you’ll earn points. </p>\n" +
                "  </section>\n" +
                "  \n" +
                "   <section class=\"section-five\">\n" +
                "    <h2>Where is the quiz?</h2>\n" +
                "    <p>You’ll find the daily quiz in the Challenges tab on the app. You can only answer this after 4pm every day, but don’t worry—you’ll receive a reminder notification when it’s time to take the challenge! </p>\n" +
                "    <p>Once you’ve completed the quiz, you can share your results with up to 5 friends to earn points.</p>\n" +
                "  </section>\n" +
                "  \n" +
                "   <section class=\"section-six\">\n" +
                "    <h2>How do I share my results?</h2>\n" +
                "    <p>When you complete the quiz or earn a new badge, the share icon pops up so you can share with friends. Select your friends to share with. You can share a maximum of 5 times a day and will earn points every time you share. </p>\n" +
                "  </section>\n" +
                "  \n" +
                "  <section class=\"section-seven\">\n" +
                "    <h2>What are ‘badges’?</h2>\n" +
                "    <p>Badges are milestones that recognise your achievements on your sustainability journey. You can check out the ‘Badges’ section under your Profile to see what you’ve earned. Each badge has different levels.</p>\n" +
                "  </section>\n" +
                "  \n" +
                "  <section class=\"section-img\">\n" +
                "  <p><img src="+FAQ_BADGE_TABLE+" style=\"width:110%;\"></p>\n" +
                "  </section>\n" +
                "  \n" +
                "  <section class=\"section-eight\">\n" +
                "    <h2>How do I add friends?</h2>\n" +
                "    <p>Great question!  You can add friends by sending a friend request to their school email address.  You can only add friends who have an ISF email address.</p>\n" +
                "  \n" +
                "    <section class=\"section-nine\">\n" +
                "    <h2>How do rankings work?</h2>\n" +
                "    <p>You are ranked by the points and scores you’ve earned from energy conservation, your daily quiz results, and when you’ve shared with friends. </p>\n" +
                "\n" +
                "<p>The app prioritises your actions as follows:</p>\n" +
                "\n" +
                " <ol style=\"list-style:decimal; margin:0px;\">\n" +
                "      <li>Reduced energy consumption = 35% <span style=\"font-size:10px; line-height:12px;\">(60% of this is based on your individual performance and 40% is based on the overall performance of the school) </span></li>\n" +
                "      <li>Quiz results = 30%</li>\n" +
                "      <li>Sharing with friends = 35%</li>\n" +
                "      \n" +
                "      \n" +
                "      </ol>\n" +
                "<p>A maximum of 600 points can be earned per day, per person.</p>\n" +
                "\n" +
                "  </section>\n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "    <section class=\"section-ten\">\n" +
                "    <h2>Where can I see the rankings?</h2>\n" +
                "    <p>In the Challenges tab, you’ll find daily rankings for where you rank in your friends list and within your house, and where your house ranks against other houses. </p>\n" +
                "    </section>  \n" +
                "    <section class=\"section-eleven\">\n" +
                "    <h2>What can I win?</h2>\n" +
                "    <p>Aside from the feeling that you’re making positive steps towards a sustainable future, you can also win a $40 coupon when you rank in the top 5 for your house every cycle.</p>\n" +
                "    \n" +
                "    <p>The $40 coupon can be used in the ISF Cafe. Please note that your coupon can’t be exchanged for money and can only be redeemed once. </p>\n" +
                "    </section>\n" +
                "  \n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";

    }


    public static String getGlossary() {

        return "<!doctype html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n" +
                "<title>ISF-2</title>\n" +
                "<style type=\"text/css\">\n" +
                "body {\n" +
                "\tfont-family: Arial, Helvetica, sans-serif; font-size:14px; line-height:19px;\n" +
                "}\n" +
                ".wrapper{ padding:10px;}\n" +
                "h1 {\n" +
                "\tfont-size: 24px;\n" +
                "\tpadding:10px 0px;\n" +
                "\tmargin: 0px; \n" +
                "}\n" +
                "hr{margin:15px 0px; color:#f3f3f3;}\n" +
                "h2 {\n" +
                "\tfont-size:18px;\n" +
                "\tpadding:10px 0px;\n" +
                "\tmargin: 0px;\n" +
                "}\n" +
                "p{ margin:0px; padding:6px 0px;}\n" +
                "</style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"font-family:Arial, Helvetica, sans-serif;\">\n" +
                "<div class=\"wrapper\">\n" +
                "  <h1>App glossary</h1>\n" +
                "  <hr>\n" +
                "  <section class=\"section-one\">\n" +
                "    <h2>Cycle</h2>\n" +
                "    <p>Think of a cycle as a new round of the game. A cycle lasts 10 days (just like in the ISF senior school calendar), then automatically restarts. </p>\n" +
                "  \n" +
               /* "  </section>\n" +
                "  <section class=\"section-two\">\n" +
                "    <h2>kW</h2>\n" +
                "    <p>kW means kilowatt which is 1,000 Watts. It is a measure of power.Power (speed at which the energy is generated/used); this is how we measure the energy used in each room on the 3D map (as well as for the solar microgrid) and this updates every minute </p>\n" +
                "    \n" +
                "  </section>\n" +*/
                "  <section class=\"section-three\">\n" +
                "    <h2>kWh</h2>\n" +
                "    <p>kWh stands for kilowatt-hour. A kWh is a measure of energy, not power.How much electrical energy is generated/used per period of time; this is how we measure your personal energy consumption over 24 hours </p>\n" +
                "  </section>\n" +
                "    <section class=\"section-four\">\n" +
                "    <h2>Carbon footprint</h2>\n" +
                "    <p>How much carbon dioxide* is produced by burning fossil fuels for your electricity needs at school (*or the equivalent, in terms of Greenhouse Gases)</p>\n" +
                "  </section>\n" +
                "  \n" +
                "  </section>\n" +
                "    <section class=\"section-grid\">\n" +
                "    <h2>Grid</h2>\n" +
                "    <p>An electrical grid, or electric grid, is an interconnected network for delivering electricity from producers to consumers.  For ISF, this means getting energy from the city’s main electrical grid which obtains energy from burning fossil fuels.</p>\n" +
                "  </section>\n" +
                "  \n" +
                "   <section class=\"section-five\">\n" +
                "    <h2>Microgrid</h2>\n" +
                "    <p>A microgrid is a small-scale energy supply network. At ISF Academy, the SOLAR panels on our roof are considered a microgrid.</p>\n" +
                "  </section>\n" +
                "  \n" +
                "   <section class=\"section-six\">\n" +
                "    <h2>Prizes</h2>\n" +
                "    <p>The top 5 people on each team at the end of every cycle will win a prize – a $40 coupon to use in the ISF Cafe.</p>\n" +
                "  </section>\n" +
                "  \n" +
                "  <section class=\"section-seven\">\n" +
                "    <h2>Badges</h2>\n" +
                "    <p>Badges are milestones that recognise your achievements on your sustainability journey. You can check out the ‘Badges’ section under your Profile to see what you’ve earned. Each badge has different levels.</p>\n" +
                "  </section>\n" +
                "  \n" +
                "  <section class=\"section-eight\">\n" +
                "    <h2>Ranking</h2>\n" +
                "    <p>You are ranked by the points and scores you’ve earned from energy conservation, your daily quiz results, and when you’ve shared with friends.</p>\n" +
                "\n" +
                "  </section>\n" +
                "  \n" +
                "    <section class=\"section-nine\">\n" +
                "    <h2>Electricity usage of 6 classrooms on the 6th Floor of R-block</h2>\n" +
                "   \n" +
                "  </section>\n" +
                "  </section>\n" +
                "  \n" +
                "  <section class=\"section-img\">\n" +
                "  <p><img src=" + CLASSROOM + " style=\"width:20%;\"></p>\n" +
                "  </section>\n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "  \n" +
                "    <section class=\"section-ten\">\n" +
                "    <h2>Electricity generated by the Solar Microgrid </h2>\n" +
                "   \n" +
                "    </section>\n" +
                "  \n" +
                "  \n" +
                "  <section class=\"section-img\">\n" +
                "  <p><img src=" + SOLAR + " style=\"width:20%;\"></p>\n" +
                "  </section>\n" +
                "    \n" +
                "    <section class=\"section-eleven\">\n" +
                "    <h2>Electricity from the grid that is generated by burning fossil fuels</h2>\n" +
                "   \n" +
                "    </section>\n" +
                "  <section class=\"section-img\">\n" +
                "  <p><img src=" + FOSSIL_FUEL + " style=\"width:20%;\"></p>\n" +
                "  </section>\n" +
                "    \n" +
                "  \n" +
                "      <section class=\"section-twelve\">\n" +
                "    <h2>Electricity usage of air conditioning</h2>\n" +
                "   \n" +
                "  <section class=\"section-img\">\n" +
                "  <p><img src=" + AIRCON + " style=\"width:20%;\"></p>\n" +
                "  </section>\n" +
                "    </section>\n" +
                "          <section class=\"section-twelve\">\n" +
                "    <h2>Electricity from socket</h2>\n" +
                "   \n" +
                "  <section class=\"section-img\">\n" +
                "  <p><img src=" + PLUG + " style=\"width:20%;\"></p>\n" +
                "  </section>\n" +
                "    </section>\n" +
                "     <section class=\"section-thirteen\">\n" +
                "    <h2>Electricity usage of lighting</h2>\n" +
                "   \n" +
                "    </section>\n" +
                "  <section class=\"section-img\">\n" +
                "  <p><img src=" + LIGHTS + " style=\"width:20%;\"></p>\n" +
                "  </section>\n" +
                "    \n" +
                "  \n" +
                "</div>\n" +
                "</body>\n" +
                "</html>";
    }

}
