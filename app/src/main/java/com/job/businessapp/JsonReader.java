package com.job.businessapp;

import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;

/**
 * Created by JOB on 8/24/2017.
 */

public class JsonReader {
    public static final String TAG = JsonReader.class.getSimpleName();

    public static  <T> T readJsonStream(InputStream inputStream, Type typeOfT) throws IOException {
       Writer writer = new StringWriter();
        char[] buffer = new char[1024];

        try {

            Reader reader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer))!= -1){
                writer.write(buffer, 0,pointer);
            }
        }finally {
            try {
            inputStream.close();
            }catch (IOException ex){
                Log.d(TAG, "readJsonStream: Couldn't close the connection");
            }
        }

        String jsonString = writer.toString();
        Gson gson = new Gson();
        Log.d(TAG, "readJsonStream: json string returning"+jsonString);
        Log.d(TAG, "readJsonStream: json to java"+gson.fromJson(jsonString, typeOfT).toString());
        return gson.fromJson(jsonString, typeOfT);
    }
}
