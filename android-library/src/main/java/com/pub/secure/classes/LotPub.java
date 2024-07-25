package com.pub.secure.classes;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.airbnb.lottie.FontAssetDelegate;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieCompositionFactory;
import com.airbnb.lottie.LottieListener;
import com.airbnb.lottie.RenderMode;
import com.airbnb.lottie.TextDelegate;
import com.pub.secure.model.VideoTamplateModel;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.zip.ZipInputStream;

public class LotPub {

    OnResult onResult;

    public LotPub() {
    }

    public LotPub(OnResult onResult) {
        this.onResult = onResult;
    }

    public void loadAnimation(Activity context, LottieAnimationView lottieAnimationView, VideoTamplateModel templateModel, LottieAnimationView loader, File file) {
        lottieAnimationView.setRenderMode(RenderMode.HARDWARE);
        lottieAnimationView.setRepeatCount(0);
        if (templateModel != null && templateModel.getTamplate_type() != null && templateModel.getTamplate_type().contains("frame")) {
            lottieAnimationView.setFontAssetDelegate(new FontAssetDelegate() {
                @Override
                public Typeface fetchFont(String fontFamily) {
                    try {
                        String ttfPath = context.getIntent().getStringExtra("filepath") + "/" + templateModel.getTitle() + "/res/fonts/" + fontFamily + ".ttf";
                        String otfPath = context.getIntent().getStringExtra("filepath") + "/" + templateModel.getTitle() + "/res/fonts/" + fontFamily + ".otf";

                        if (new File(ttfPath).exists()) {
                            return Typeface.createFromFile(ttfPath);
                        }

                        if (new File(otfPath).exists()) {
                            return Typeface.createFromFile(otfPath);
                        }

                        return Typeface.SANS_SERIF;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return Typeface.SANS_SERIF;
                    }
                }
            });

            lottieAnimationView.setTextDelegate(new TextDelegate(lottieAnimationView));
        }

        loadanimation2(context, lottieAnimationView, templateModel, file, loader);
    }

    @SuppressLint("RestrictedApi")
    private void loadanimation2(Activity context, LottieAnimationView lottieAnimationView, VideoTamplateModel templateModel, File file, LottieAnimationView loader) {
        Executors.newSingleThreadExecutor().execute(() -> {
            try {
                if (file.exists()) {
                    InputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                    LottieCompositionFactory.fromZipStream(context, new ZipInputStream(bufferedInputStream), templateModel.getId()).addListener(new LottieListener<LottieComposition>() {
                        @Override
                        public void onResult(LottieComposition result) {
                            new Handler(Looper.getMainLooper()).post(() -> {
                                if (loader != null){
                                    loader.setVisibility(View.GONE);
                                }
                                if (result != null) {
                                    lottieAnimationView.setComposition(result);

                                    if (onResult != null){
                                        onResult.onResult(result, lottieAnimationView);
                                    }else{
                                        lottieAnimationView.playAnimation();
                                        lottieAnimationView.loop(true);
                                    }

                                }else {
                                    Log.d("LottieComposition__","LottieComposition NULL");
                                }
                            });
                        }
                    }).addFailureListener(throwable -> {
                        new Handler(Looper.getMainLooper()).post(() -> {
                            if (loader != null) {
                                loader.setVisibility(View.GONE);
                            }
                            file.delete();
//                            showLottieAnimation(context,lottieAnimationView,templateModel,loader);
//                            Log.d("LottieComposition__","LottieComposition "+ throwable.getMessage());
                            // Handle the error appropriately, e.g., show an error message to the user
                        });
                    });;

                }else{
                    Log.d("LottieComposition__","LottieComposition File Not Exist");
                }
            } catch (Exception e) {
                Log.d("LottieComposition__","LottieComposition "+e.getMessage());
            }
        });
    }

    public interface OnResult{
        void onResult(LottieComposition result, LottieAnimationView lottieAnimationView);
    }

}
