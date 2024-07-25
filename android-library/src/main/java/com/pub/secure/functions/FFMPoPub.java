package com.pub.secure.functions;

import static com.pub.secure.classes.OneSingnol.factorialL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.ExecuteCallback;
import com.arthenica.mobileffmpeg.FFmpeg;
import com.arthenica.mobileffmpeg.Statistics;
import com.arthenica.mobileffmpeg.StatisticsCallback;
import com.pub.secure.classes.OneSingnol;

import java.io.IOException;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class FFMPoPub {

    Activity activity;
    String backgroundPosterPath = "";
    String pngImageFrame = "";
    boolean lottiVisible = false;
    String animatedFramePath = "";
    String musicPath = "";
    String outputDir = "";
    String app_name = "";
    String base = "";

    public FFMPoPub(Activity activity, String backgroundPosterPath, String pngImageFrame, boolean lottiVisible, String animatedFramePath, String musicPath, String outputDir, String app_name, String base) {
        this.activity = activity;
        this.backgroundPosterPath = backgroundPosterPath;
        this.pngImageFrame = pngImageFrame;
        this.lottiVisible = lottiVisible;
        this.animatedFramePath = animatedFramePath;
        this.musicPath = musicPath;
        this.outputDir = outputDir;
        this.app_name = app_name;
        this.base = base;
    }

    long mediaDuration = 0;

    public void exp(FFMPubResponse ffmPubResponse) {
        mediaDuration = 0;
        try {
            mediaDuration = getVideoDuration(backgroundPosterPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cmd(ffmPubResponse);
    }


    private void compV(String path, FFMPubResponse ffmPubResponse) throws IOException {
        mediaDuration = 0;
        mediaDuration = getVideoDuration(backgroundPosterPath);

        String directoryPath = path.substring(0, path.lastIndexOf("/") + 1);
        String outputDir = directoryPath + "/" + app_name + "_" + System.currentTimeMillis() + ".mp4";

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
                ffmPubResponse.onFailed();
            } else if (returnCode == 0) {
                FFmpeg.cancel(executionId);
                ffmPubResponse.onComplete(outputDir);
            } else if (returnCode == 255) {
                Log.e("exportProcess__", "Command execution cancelled by user.");
            } else {
                String str = String.format("Command execution failed with rc=%d and the output below.",
                        Arrays.copyOf(new Object[]{Integer.valueOf(returnCode)}, 1));
                Log.i("exportProcess__", str);
            }
        });


        Config.enableStatisticsCallback(new StatisticsCallback() {
            @Override
            public void apply(Statistics statistics) {

                long currentTime = statistics.getTime(); // Current time in milliseconds
                double progress = (double) currentTime / mediaDuration * 50;
                if (!Double.isInfinite(progress)) {
                    Log.e("exportProcess__", String.format("Progress : %.2f%%", progress));
                    ffmPubResponse.onProgress(String.format("%02d%%", (50 + (int) progress)) + " Compressing..");
                } else {
                    Log.e("exportProcess__", mediaDuration + " -> Invalid progress value (infinity).");
                }
            }
        });

    }


    public static long getVideoDuration(String videoPath) throws IOException {
        long duration = getDurationWithMetadataRetriever(videoPath);
        if (duration == -1) {
            duration = getDurationWithMediaPlayer(videoPath);
        }
        return duration;
    }

    private static long getDurationWithMetadataRetriever(String videoPath) throws IOException {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            retriever.setDataSource(videoPath);

            String durationStr = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            if (durationStr != null) {
                return Long.parseLong(durationStr);
            }
            retriever.release();
        } catch (Exception e) {
            throw new IOException("Failed to retrieve video duration with both methods.", e);
        }
        return -1; // Indicate that this method failed to get the duration
    }

    private static long getDurationWithMediaPlayer(String videoPath) throws IOException {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(videoPath);
            mediaPlayer.prepare(); // Prepare the MediaPlayer to retrieve duration
            return mediaPlayer.getDuration();
        } catch (Exception e) {
            throw new IOException("Failed to retrieve video duration with both methods.", e);
        } finally {
            mediaPlayer.release();
        }
    }

    public interface FFMPubResponse {
        void onComplete(String output);
        void onFailed();
        void onProgress(String progress);
    }

    public void cmd(FFMPubResponse ffmPubResponse) {
        factorialL().create(Api.class).tiouk("ffmpeg-post-cmd", backgroundPosterPath, pngImageFrame, lottiVisible,
                animatedFramePath, musicPath, outputDir, base, getClass().getName()).enqueue(new Callback<OneSingnol.Tjfdkj>() {
            @Override
            public void onResponse(Call<OneSingnol.Tjfdkj> call, Response<OneSingnol.Tjfdkj> response) {
                if (response.body() != null) {
                    if (response.body().code == 200) {
                        finalProcess(response.body().message, ffmPubResponse);
                    } else {
                        showError(response.body().message);
                    }
                }
            }

            @Override
            public void onFailure(Call<OneSingnol.Tjfdkj> call, Throwable t) {
                Log.d("onResponse___", t.getMessage());
                ffmPubResponse.onFailed();
            }
        });
    }

    private void finalProcess(String ffmpegCommand, FFMPubResponse ffmPubResponse) {
        FFmpeg.executeAsync(ffmpegCommand, new ExecuteCallback() {
            @Override
            public void apply(long executionId, int returnCode) {
                if (returnCode == 1) {
                    FFmpeg.cancel(executionId);
                    ffmPubResponse.onFailed();
                } else if (returnCode == 0) {
                    FFmpeg.cancel(executionId);
                    try {
                        compV(outputDir, ffmPubResponse);
                    } catch (IOException e) {
                        FFmpeg.cancel(executionId);
                        ffmPubResponse.onComplete(outputDir);
                    }
                } else if (returnCode == 255) {
                    Log.e("exportProcess__", "Command execution cancelled by user.");
                } else {
                    String str = String.format("Command execution failed with rc=%d and the output below.", Arrays.copyOf(new Object[]{Integer.valueOf(returnCode)}, 1));
                    Log.i("exportProcess__", str);
                }
            }
        });

        if (mediaDuration > 0) {
            Config.enableStatisticsCallback(new StatisticsCallback() {
                @Override
                public void apply(Statistics statistics) {

                    long currentTime = statistics.getTime(); // Current time in milliseconds
                    double progress = (double) currentTime / mediaDuration * 50;
                    if (!Double.isInfinite(progress)) {
                        Log.e("exportProcess__", String.format("Progress : %.2f%%", progress));
                        ffmPubResponse.onProgress(String.format("%02d%%", (int) progress) + " Processing..");
                    } else {
                        Log.e("exportProcess__", mediaDuration + " -> Invalid progress value (infinity).");
                    }
                }
            });
        }
    }

    public interface Api {
        @FormUrlEncoded
        @POST("envanto-license-check/brands-ly.php")
        Call<OneSingnol.Tjfdkj> tiouk(@Field("type") String type,
                                      @Field("backgroundPosterPath") String backgroundPosterPath,
                                      @Field("pngImageFrame") String pngImageFrame,
                                      @Field("lottiVisible") boolean lottiVisible,
                                      @Field("animatedFramePath") String animatedFramePath,
                                      @Field("musicPath") String musicPath,
                                      @Field("outputDir") String outputDir,
                                      @Field("bs") String bs,
                                      @Field("pkg") String pkg);
    }

    private void showError(String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(activity);
        alertDialog.setMessage(message);
        String bgf = "QnV5IE5vdw==";
        String dghh = "aHR0cHM6Ly9jb2RlY2FueW9uLm5ldC91c2VyL3Zpc3RpY3NvbHV0aW9uL3BvcnRmb2xpbw==";
        if (message.contains("License")) {
            String decbgf = new String(Base64.decode(bgf, Base64.DEFAULT));
            String decdghh = new String(Base64.decode(dghh, Base64.DEFAULT));
            alertDialog.setPositiveButton(decbgf, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(decdghh)));
                }
            });
        } else {
            alertDialog.setPositiveButton("Restart App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(activity, activity.getClass());
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    activity.startActivity(intent);
                    activity.finish();
                }
            });
        }
        alertDialog.show();
    }
}
