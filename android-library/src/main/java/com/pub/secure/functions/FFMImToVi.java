package com.pub.secure.functions;

import android.app.Activity;
import android.media.MediaMetadataRetriever;
import android.util.Log;

import com.arthenica.mobileffmpeg.FFmpeg;

import java.util.Arrays;

public class FFMImToVi {

    Activity activity;
    String framePath;
    String musicPath;
    String ratio;
    String outputDir;

    public FFMImToVi(Activity activity, String framePath, String musicPath, String ratio, String outputDir) {
        this.activity = activity;
        this.framePath = framePath;
        this.musicPath = musicPath;
        this.ratio = ratio;
        this.outputDir = outputDir;
    }

    public void exe(FFMImToViResponse ffmPubResponse){
        String[] split = ratio.split(":");
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(musicPath);

        int duration = Integer.parseInt(retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION));
        int width = Integer.parseInt(split[0]);
        int height = Integer.parseInt(split[1]);

        String[] ffmpegCommand = {
                "-loop", "1", // Loop the image
                "-i", framePath, // Input image file path
                "-c:v", "libx264", // Video codec
                "-t", String.valueOf(duration / 1000.0), // Duration of the output video (in seconds)
                "-s", width + "x" + height, // Output video dimensions
                "-vf", "fps=30", // Frame rate (adjust as needed)
                "-pix_fmt", "yuv420p", // Pixel format
                outputDir // Output video file path
        };

        FFmpeg.executeAsync(ffmpegCommand, (executionId, returnCode) -> {
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

    public interface FFMImToViResponse{
        void onComplete(String output);
        void onFailed();
        void onProgress(String progress);
    }
}
