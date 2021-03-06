package com.ycwl.servebixin.cn.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ycwl.servebixin.cn.db.db.UserDB;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_DB".
*/
public class UserDBDao extends AbstractDao<UserDB, Long> {

    public static final String TABLENAME = "USER_DB";

    /**
     * Properties of entity UserDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Token = new Property(1, String.class, "token", false, "TOKEN");
        public final static Property Identity = new Property(2, int.class, "identity", false, "IDENTITY");
        public final static Property RongToken = new Property(3, String.class, "rongToken", false, "RONG_TOKEN");
        public final static Property Phone = new Property(4, String.class, "phone", false, "PHONE");
        public final static Property Nickname = new Property(5, String.class, "nickname", false, "NICKNAME");
        public final static Property Sex = new Property(6, int.class, "sex", false, "SEX");
        public final static Property Birthday = new Property(7, String.class, "birthday", false, "BIRTHDAY");
        public final static Property Constellation = new Property(8, String.class, "constellation", false, "CONSTELLATION");
        public final static Property Avatar = new Property(9, String.class, "avatar", false, "AVATAR");
        public final static Property OccupationName = new Property(10, String.class, "occupationName", false, "OCCUPATION_NAME");
        public final static Property IsPerfectData = new Property(11, int.class, "isPerfectData", false, "IS_PERFECT_DATA");
        public final static Property DataAuditState = new Property(12, int.class, "dataAuditState", false, "DATA_AUDIT_STATE");
        public final static Property LeaderIDCardAuditState = new Property(13, int.class, "leaderIDCardAuditState", false, "LEADER_IDCARD_AUDIT_STATE");
        public final static Property IsEnableSkill = new Property(14, int.class, "isEnableSkill", false, "IS_ENABLE_SKILL");
        public final static Property ServiceIDCardAuditState = new Property(15, int.class, "serviceIDCardAuditState", false, "SERVICE_IDCARD_AUDIT_STATE");
        public final static Property OrderNum = new Property(16, int.class, "orderNum", false, "ORDER_NUM");
        public final static Property FansNum = new Property(17, int.class, "fansNum", false, "FANS_NUM");
        public final static Property FollowNum = new Property(18, int.class, "followNum", false, "FOLLOW_NUM");
        public final static Property BixinId = new Property(19, int.class, "bixinId", false, "BIXIN_ID");
        public final static Property Age = new Property(20, int.class, "age", false, "AGE");
        public final static Property UserID = new Property(21, String.class, "userID", false, "USER_ID");
        public final static Property Jmpassword = new Property(22, String.class, "jmpassword", false, "JMPASSWORD");
    }


    public UserDBDao(DaoConfig config) {
        super(config);
    }
    
    public UserDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TOKEN\" TEXT," + // 1: token
                "\"IDENTITY\" INTEGER NOT NULL ," + // 2: identity
                "\"RONG_TOKEN\" TEXT," + // 3: rongToken
                "\"PHONE\" TEXT," + // 4: phone
                "\"NICKNAME\" TEXT," + // 5: nickname
                "\"SEX\" INTEGER NOT NULL ," + // 6: sex
                "\"BIRTHDAY\" TEXT," + // 7: birthday
                "\"CONSTELLATION\" TEXT," + // 8: constellation
                "\"AVATAR\" TEXT," + // 9: avatar
                "\"OCCUPATION_NAME\" TEXT," + // 10: occupationName
                "\"IS_PERFECT_DATA\" INTEGER NOT NULL ," + // 11: isPerfectData
                "\"DATA_AUDIT_STATE\" INTEGER NOT NULL ," + // 12: dataAuditState
                "\"LEADER_IDCARD_AUDIT_STATE\" INTEGER NOT NULL ," + // 13: leaderIDCardAuditState
                "\"IS_ENABLE_SKILL\" INTEGER NOT NULL ," + // 14: isEnableSkill
                "\"SERVICE_IDCARD_AUDIT_STATE\" INTEGER NOT NULL ," + // 15: serviceIDCardAuditState
                "\"ORDER_NUM\" INTEGER NOT NULL ," + // 16: orderNum
                "\"FANS_NUM\" INTEGER NOT NULL ," + // 17: fansNum
                "\"FOLLOW_NUM\" INTEGER NOT NULL ," + // 18: followNum
                "\"BIXIN_ID\" INTEGER NOT NULL ," + // 19: bixinId
                "\"AGE\" INTEGER NOT NULL ," + // 20: age
                "\"USER_ID\" TEXT," + // 21: userID
                "\"JMPASSWORD\" TEXT);"); // 22: jmpassword
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(2, token);
        }
        stmt.bindLong(3, entity.getIdentity());
 
        String rongToken = entity.getRongToken();
        if (rongToken != null) {
            stmt.bindString(4, rongToken);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(6, nickname);
        }
        stmt.bindLong(7, entity.getSex());
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(8, birthday);
        }
 
        String constellation = entity.getConstellation();
        if (constellation != null) {
            stmt.bindString(9, constellation);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(10, avatar);
        }
 
        String occupationName = entity.getOccupationName();
        if (occupationName != null) {
            stmt.bindString(11, occupationName);
        }
        stmt.bindLong(12, entity.getIsPerfectData());
        stmt.bindLong(13, entity.getDataAuditState());
        stmt.bindLong(14, entity.getLeaderIDCardAuditState());
        stmt.bindLong(15, entity.getIsEnableSkill());
        stmt.bindLong(16, entity.getServiceIDCardAuditState());
        stmt.bindLong(17, entity.getOrderNum());
        stmt.bindLong(18, entity.getFansNum());
        stmt.bindLong(19, entity.getFollowNum());
        stmt.bindLong(20, entity.getBixinId());
        stmt.bindLong(21, entity.getAge());
 
        String userID = entity.getUserID();
        if (userID != null) {
            stmt.bindString(22, userID);
        }
 
        String jmpassword = entity.getJmpassword();
        if (jmpassword != null) {
            stmt.bindString(23, jmpassword);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String token = entity.getToken();
        if (token != null) {
            stmt.bindString(2, token);
        }
        stmt.bindLong(3, entity.getIdentity());
 
        String rongToken = entity.getRongToken();
        if (rongToken != null) {
            stmt.bindString(4, rongToken);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(5, phone);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(6, nickname);
        }
        stmt.bindLong(7, entity.getSex());
 
        String birthday = entity.getBirthday();
        if (birthday != null) {
            stmt.bindString(8, birthday);
        }
 
        String constellation = entity.getConstellation();
        if (constellation != null) {
            stmt.bindString(9, constellation);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(10, avatar);
        }
 
        String occupationName = entity.getOccupationName();
        if (occupationName != null) {
            stmt.bindString(11, occupationName);
        }
        stmt.bindLong(12, entity.getIsPerfectData());
        stmt.bindLong(13, entity.getDataAuditState());
        stmt.bindLong(14, entity.getLeaderIDCardAuditState());
        stmt.bindLong(15, entity.getIsEnableSkill());
        stmt.bindLong(16, entity.getServiceIDCardAuditState());
        stmt.bindLong(17, entity.getOrderNum());
        stmt.bindLong(18, entity.getFansNum());
        stmt.bindLong(19, entity.getFollowNum());
        stmt.bindLong(20, entity.getBixinId());
        stmt.bindLong(21, entity.getAge());
 
        String userID = entity.getUserID();
        if (userID != null) {
            stmt.bindString(22, userID);
        }
 
        String jmpassword = entity.getJmpassword();
        if (jmpassword != null) {
            stmt.bindString(23, jmpassword);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserDB readEntity(Cursor cursor, int offset) {
        UserDB entity = new UserDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // token
            cursor.getInt(offset + 2), // identity
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // rongToken
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phone
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // nickname
            cursor.getInt(offset + 6), // sex
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // birthday
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // constellation
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // avatar
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // occupationName
            cursor.getInt(offset + 11), // isPerfectData
            cursor.getInt(offset + 12), // dataAuditState
            cursor.getInt(offset + 13), // leaderIDCardAuditState
            cursor.getInt(offset + 14), // isEnableSkill
            cursor.getInt(offset + 15), // serviceIDCardAuditState
            cursor.getInt(offset + 16), // orderNum
            cursor.getInt(offset + 17), // fansNum
            cursor.getInt(offset + 18), // followNum
            cursor.getInt(offset + 19), // bixinId
            cursor.getInt(offset + 20), // age
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // userID
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22) // jmpassword
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setToken(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setIdentity(cursor.getInt(offset + 2));
        entity.setRongToken(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhone(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setNickname(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setSex(cursor.getInt(offset + 6));
        entity.setBirthday(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setConstellation(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setAvatar(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setOccupationName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIsPerfectData(cursor.getInt(offset + 11));
        entity.setDataAuditState(cursor.getInt(offset + 12));
        entity.setLeaderIDCardAuditState(cursor.getInt(offset + 13));
        entity.setIsEnableSkill(cursor.getInt(offset + 14));
        entity.setServiceIDCardAuditState(cursor.getInt(offset + 15));
        entity.setOrderNum(cursor.getInt(offset + 16));
        entity.setFansNum(cursor.getInt(offset + 17));
        entity.setFollowNum(cursor.getInt(offset + 18));
        entity.setBixinId(cursor.getInt(offset + 19));
        entity.setAge(cursor.getInt(offset + 20));
        entity.setUserID(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setJmpassword(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserDB entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
