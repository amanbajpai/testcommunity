package com.gdc.isfacademy.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishalchhodwani on 17/4/18.
 */

@Entity(
        // Whether an all properties constructor should be generated.
        // A no-args constructor is always required.
        generateConstructors = true,

        // Whether getters and setters for properties should be generated if missing.
        generateGettersSetters = true
)
public class Question implements Parcelable {

    @Id(autoincrement = true)
    Long id;

    @Transient
    boolean answered;

    @Transient
    boolean correct;

    @SerializedName("questionId")
    @Expose
    private Integer questionId;

    @SerializedName("question")
    @Expose
    private String question;

    @SerializedName("options")
    @Expose
    @ToMany(referencedJoinProperty = "questionId")
    private List<Options> options = null;

    @SerializedName("answer")
    @Expose
    private String answer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 1599256878)
    public List<Options> getOptions() {
        if (options == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            OptionsDao targetDao = daoSession.getOptionsDao();
            List<Options> optionsNew = targetDao._queryQuestion_Options(id);
            synchronized (this) {
                if (options == null) {
                    options = optionsNew;
                }
            }
        }
        return options;
    }

    public void setOptions(List<Options> options) {
        this.options = options;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isAnswered() {
        return answered;
    }

    public void setAnswered(boolean answered) {
        this.answered = answered;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.questionId);
        dest.writeString(this.question);
        dest.writeList(this.options);
        dest.writeString(this.answer);
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 37457025)
    public synchronized void resetOptions() {
        options = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 754833738)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getQuestionDao() : null;
    }

    public Question() {
    }

    protected Question(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.questionId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.question = in.readString();
        this.options = new ArrayList<Options>();
        in.readList(this.options, Options.class.getClassLoader());
        this.answer = in.readString();
    }

    @Generated(hash = 1354499475)
    public Question(Long id, Integer questionId, String question, String answer) {
        this.id = id;
        this.questionId = questionId;
        this.question = question;
        this.answer = answer;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            return new Question(source);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated(hash = 891254763)
    private transient QuestionDao myDao;
}
