package com.pub.secure.functions;

import android.app.Activity;
import android.util.Log;

import com.arthenica.mobileffmpeg.FFmpeg;

import java.util.Arrays;

public class FFMMuOnVi {


    Activity activity;
    String path;String musicPath;String outputDir;

    public FFMMuOnVi(Activity activity, String path, String musicPath, String outputDir) {
        this.activity = activity;
        this.path = path;
        this.musicPath = musicPath;
        this.outputDir = outputDir;
    }

    public void exe(FFMMuOnViResponse ffmPubResponse){
        FFmpeg.executeAsync(new String[]{"-i", path, "-i", musicPath, "-c:v", "copy", "-c:a", "copy", "-map", "0:v:0", "-map", "1:a:0", "-shortest", outputDir}, (executionId, returnCode) -> {
            if (returnCode == 1) {
                FFmpeg.cancel(executionId);
                ffmPubResponse.onFailed();
            }
            if (returnCode == 0) {
                ffmPubResponse.onComplete(outputDir);
            } else if (returnCode == 255) {
                Log.e("finalProcess__", "Command execution cancelled by user.");
            } else {
                String str = String.format("Command execution failed with rc=%d and the output below.", Arrays.copyOf(new Object[]{Integer.valueOf(returnCode)}, 1));
                Log.i("finalProcess__", str);
            }
        });
    }


    public interface FFMMuOnViResponse{
        void onComplete(String output);
        void onFailed();
        void onProgress(String progress);
    }
}
