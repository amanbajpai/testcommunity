package com.gdc.isfacademy.model;

import java.io.Serializable;

/**
 * Created by ashishthakur on 22/8/18.
 */

public class StudentStatusResponse extends CommonResponse implements Serializable {

    private IsfStudentStatus isfStudentStatus;

    public IsfStudentStatus getIsfStudentStatus() {
        return isfStudentStatus;
    }

    public void setIsfStudentStatus(IsfStudentStatus isfStudentStatus) {
        this.isfStudentStatus = isfStudentStatus;
    }

    public class IsfStudentStatus implements Serializable {

        private String challengeCount;
        private String rankingCount;
        private String shareCount;


        public String getChallengeCount() {
            return challengeCount;
        }

        public void setChallengeCount(String challengeCount) {
            this.challengeCount = challengeCount;
        }

        public String getRankingCount() {
            return rankingCount;
        }

        public void setRankingCount(String rankingCount) {
            this.rankingCount = rankingCount;
        }

        public String getShareCount() {
            return shareCount;
        }

        public void setShareCount(String shareCount) {
            this.shareCount = shareCount;
        }
    }
}
