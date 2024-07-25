package com.pub.secure.functions;

import static com.arthenica.mobileffmpeg.Config.RETURN_CODE_SUCCESS;

import android.app.Activity;
import android.util.Log;

import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.FFmpeg;

public class FFMMuPub {

    Activity activity;
    String path; String start; String end; String outputPath;

    public FFMMuPub(Activity activity, String path, String start, String end, String outputPath) {
        this.activity = activity;
        this.path = path;
        this.start = start;
        this.end = end;
        this.outputPath = outputPath;
    }

    public void exe(FFMMuResponse ffmPubResponse){
        String[] cmd = new String[]{"-ss", start + "", "-i", path, "-t", end + "", "-c", "copy", outputPath};
        Log.d("FFMPEG", "start: " + start);
        Log.d("FFMPEG", "path: " + path);
        Log.d("FFMPEG", "end: " + end);
        Log.d("FFMPEG", "outputPath: " + outputPath);
        FFmpeg.executeAsync(cmd, (executionId, returnCode) -> {
            if (returnCode == RETURN_CODE_SUCCESS) {
                ffmPubResponse.onComplete(outputPath);
            }else{
                ffmPubResponse.onFailed();
            }
        });
        Config.enableStatisticsCallback(statistics -> Log.d("FFMPEG", "apply: " + statistics));
    }

    public interface FFMMuResponse{
        void onComplete(String output);
        void onFailed();
        void onProgress(String progress);
    }
}
