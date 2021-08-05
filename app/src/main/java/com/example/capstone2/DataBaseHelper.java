package com.example.capstone2;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper{
    public DataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private static String TAG = "DataBaseHelper";
    private static String databasePath = "";
    private static String databaseName ="sqlite_file.db";
    private SQLiteDatabase mDatabase;
    private Context mContext;


    public boolean OpenDatabaseFile() throws SQLException {

        if(!CheckDatabaseFileExist()){
        CreateDatabase();
        }

        String mPath = databasePath + databaseName;
        try{
        mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        }
        catch(SQLException sqlException){
        Log.e(TAG, "[ERROR]" + "Can't Open Database");
        }
        return mDatabase != null;
        }

// 데이터베이스 파일 존재 여부 확인
public boolean CheckDatabaseFileExist(){
        File file = new File(databasePath + databaseName);
        return file.exists();
        }

// Database 만들기
public void CreateDatabase() throws SQLException{

        this.getReadableDatabase();
        this.close();

        try{
        CopyDatabaseFile();
        Log.e(TAG,  "[SUCCESS] " + databaseName + " are Created");
        }
        catch(IOException ioException){
        // Error Message
        Log.e(TAG, "[ERROR] " + "Unable to create " + databaseName);
        throw new Error(TAG);
        }
        }

// 데이터베이스 복사
public void CopyDatabaseFile() throws IOException{

        InputStream inputStream  = mContext.getAssets().open(databaseName);
        String outputFileName = databasePath + databaseName;
        OutputStream outputStream = new FileOutputStream(outputFileName);

        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) > 0){
        outputStream.write(buffer, 0, length);
        }

        outputStream.flush();
        outputStream.close();
        inputStream.close();
        }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


public List getTableData() {

    try{
        // 테이블 정보를 저장할 List
        List mList = new ArrayList();

        // 쿼리
        String sql = "SELECT * FROM " + "week.db";

        // 테이블 데이터를 읽기 위한 Cursor
        Cursor mCursor = mDatabase.rawQuery(sql, null);

        // 테이블 끝까지 읽기
        if (mCursor != null){

            // 다음 Row로 이동
            while(mCursor.moveToNext()){

                // 해당 Row 저장
                Week week = new Week();

                week.setDate(mCursor.getString(0));
                week.setStart_line(mCursor.getInt(1));
                week.setStart_station(mCursor.getString(2));
                week.setStop_line(mCursor.getInt(3));
                week.setStop_station(mCursor.getString(4));
                week.setCount(mCursor.getInt(5));
                week.setPb(mCursor.getInt(6));

                // List에 해당 Row 추가
                mList.add(week);
                }

        }
        return mList;

    }
    catch (SQLException mSQLException){
        // Error Message
        Log.e(TAG, mSQLException.toString());
        throw mSQLException;
    }

}

}