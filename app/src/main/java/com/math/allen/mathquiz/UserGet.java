package com.math.allen.mathquiz;

/**
 * Created by Allen on 6/19/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserGet extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mathgame";
    private static final String TABLE_USERS = "users";
    private static final String USER_NAME = "name";
    private static int leadScore = -1;
    private static String leader = "";

    public UserGet (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table " + TABLE_USERS +
                " (name text,high text)";
        db.execSQL(sql);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    public boolean insertUser(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("high", "0");
        db.insert(TABLE_USERS, null, contentValues);
        return true;
    }

    public String getScore(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USERS,null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            if(res.getString(0).equals(name)) {
                return res.getString(1);
            }
            res.moveToNext();
        }
        String[] s = res.getColumnNames();
        String ret = "";
        for(String str : s) {
            ret += str + " ";
        }
        return ret;
    }

    public boolean isUser(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_USERS,null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            if(res.getString(0).equals(name)) {
                return true;
            }
            res.moveToNext();
        }
        return false;
    }

    public boolean updateUser(String name, String score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("high",score);
        db.update(TABLE_USERS, contentValues, USER_NAME + " = ?", new String[]{name});
        return true;
    }
    public String[] getLeader(){
        if(leader.equals("")){
            return new String[]{"",""};
        }
        else return new String[]{leader,Integer.toString(leadScore)};
    }
    public void updateLeader(String name,int score) {
        if(score > leadScore) {
            leadScore = score;
            leader = name;
        }
    }
}
