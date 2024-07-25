package com.pub.secure.functions;

import static com.arthenica.mobileffmpeg.Config.RETURN_CODE_SUCCESS;
import static com.pub.secure.classes.OneSingnol.factorialL;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;

import com.arthenica.mobileffmpeg.Config;
import com.arthenica.mobileffmpeg.FFmpeg;
import com.pub.secure.classes.OneSingnol;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class FFMAniVid {

    Activity activity;
    String tempVideoPath;
    String fileWatermark;
    boolean isWatermarkEnabled;
    String musicPath;
    String outputPath;
    int animDuration = 0;
    String base;

    public FFMAniVid(Activity activity,String tempVideoPath, String fileWatermark, boolean isWatermarkEnabled, String musicPath, String outputPath,int animDuration, String base) {
        this.activity = activity;
        this.tempVideoPath = tempVideoPath;
        this.fileWatermark = fileWatermark;
        this.isWatermarkEnabled = isWatermarkEnabled;
        this.musicPath = musicPath;
        this.outputPath = outputPath;
        this.animDuration = animDuration;
        this.base = base;
    }

    public void exe(FFMAniVidResponse ffmAniVidResponse){
        cmd(ffmAniVidResponse);
    }

    public void cmd(FFMAniVidResponse ffmPubResponse){
        factorialL().create(Api.class).tiouk(
                "ffmpeg-anim-video-cmd",
                tempVideoPath,
                fileWatermark,
                isWatermarkEnabled,
                musicPath,
                outputPath,
                base,
                getClass().getName()
        ).enqueue(new Callback<OneSingnol.Tjfdkj>() {
            @Override
            public void onResponse(Call<OneSingnol.Tjfdkj> call, Response<OneSingnol.Tjfdkj> response) {
                if (response.body() != null){
                    if (response.body().code == 200){
                        finalProcess(response.body().message,ffmPubResponse);
                    }else {
                        showError(response.body().message);
                    }
                }
            }
            @Override
            public void onFailure(Call<OneSingnol.Tjfdkj> call, Throwable t) {
                ffmPubResponse.onFailed();
            }
        });
    }

    private void finalProcess(String cmd, FFMAniVidResponse ffmPubResponse) {
        FFmpeg.executeAsync(cmd, (executionId, returnCode) -> {
            if (returnCode == RETURN_CODE_SUCCESS) {
                ffmPubResponse.onComplete(outputPath);
            } else {
                ffmPubResponse.onFailed();
            }
        });

        Config.enableStatisticsCallback(statistics -> {

            int ffmpegprogress = (getPercentages(statistics.getTime(), animDuration)) / 2;

            new Handler(Looper.getMainLooper()).postDelayed(() -> ffmPubResponse.onProgress(""+(50 + ffmpegprogress)), 100);

        });
    }

    private int getPercentages(int curnt_time, int duration) {

        float progress_f;
        int progress;

        int vid_dur = duration;

        progress_f = ((float) curnt_time / (float) vid_dur) * 100;

        progress = (int) progress_f;

        if (progress >= 100) {
            return 100;
        } else {
            return progress;
        }
    }

    public interface Api {
        @FormUrlEncoded
        @POST("envanto-license-check/brands-ly.php")
        Call<OneSingnol.Tjfdkj> tiouk(@Field("type") String type,
                                      @Field("tempVideoPath") String tempVideoPath,
                                      @Field("fileWatermark") String fileWatermark,
                                      @Field("isWatermarkEnabled") boolean isWatermarkEnabled,
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

    public interface FFMAniVidResponse{
        void onComplete(String output);
        void onFailed();
        void onProgress(String progress);
    }
}
