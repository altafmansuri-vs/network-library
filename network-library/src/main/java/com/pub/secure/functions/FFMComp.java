package com.pub.secure.functions;

import android.app.Activity;
import android.util.Log;

import com.arthenica.mobileffmpeg.FFmpeg;

import java.util.Arrays;

public class FFMComp {

    Activity activity;
    String path;
    String outputDir;

    public FFMComp(Activity activity, String path, String outputDir) {
        this.activity = activity;
        this.path = path;
        this.outputDir = outputDir;
    }


    public void exe(FFMCompResponse ffmCompResponse){
        String[] ffmpegCommand = {
                "-i",
                path,
                "-c:v",
                "libx264",
                "-c:a",
                "copy",
                "-crf",
                "15",
                outputDir
        };
        FFmpeg.executeAsync(ffmpegCommand, (executionId, returnCode) -> {
            if (returnCode == 1) {
                FFmpeg.cancel(executionId);
                ffmCompResponse.onFailed();
            } else if (returnCode == 0) {
                FFmpeg.cancel(executionId);
                ffmCompResponse.onComplete(outputDir);
            } else if (returnCode == 255) {
                Log.e("finalProcess__", "Command execution cancelled by user.");
            } else {
                String str = String.format("Command execution failed with rc=%d and the output below.",
                        Arrays.copyOf(new Object[]{Integer.valueOf(returnCode)}, 1));
                Log.i("finalProcess__", str);
            }
        });
    }

    public interface FFMCompResponse{
        void onComplete(String output);
        void onFailed();
        void onProgress(String progress);
    }
}
