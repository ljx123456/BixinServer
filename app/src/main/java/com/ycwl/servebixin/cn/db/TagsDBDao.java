package com.ycwl.servebixin.cn.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ycwl.servebixin.cn.db.db.TagsDB;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TAGS_DB".
*/
public class TagsDBDao extends AbstractDao<TagsDB, Long> {

    public static final String TABLENAME = "TAGS_DB";

    /**
     * Properties of entity TagsDB.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property TagId = new Property(1, int.class, "tagId", false, "TAG_ID");
        public final static Property TagName = new Property(2, String.class, "tagName", false, "TAG_NAME");
    }


    public TagsDBDao(DaoConfig config) {
        super(config);
    }
    
    public TagsDBDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TAGS_DB\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"TAG_ID\" INTEGER NOT NULL ," + // 1: tagId
                "\"TAG_NAME\" TEXT);"); // 2: tagName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TAGS_DB\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TagsDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getTagId());
 
        String tagName = entity.getTagName();
        if (tagName != null) {
            stmt.bindString(3, tagName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TagsDB entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getTagId());
 
        String tagName = entity.getTagName();
        if (tagName != null) {
            stmt.bindString(3, tagName);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TagsDB readEntity(Cursor cursor, int offset) {
        TagsDB entity = new TagsDB( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // tagId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2) // tagName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TagsDB entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTagId(cursor.getInt(offset + 1));
        entity.setTagName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TagsDB entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TagsDB entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TagsDB entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
