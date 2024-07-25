package com.pub.secure.renderer;


import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.os.Handler;
import android.util.Log;
import android.view.Surface;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.lottie.LottieAnimationView;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class VideoRecorder {

    private static final String MIME_TYPE = "video/avc";
    private static final int FRAMES_PER_SECOND = 40;
    private static final int IFRAME_INTERVAL = 5;
    private static final String TAG = "newRecorderTry";
    public static int BIT_RATE = 12 * 1000000;
    public static int WIDTH = 640;
    public static int HEIGHT = 480;
    public static RenderEngienCallBack renderEngienCallBack;
    public LottieAnimationView animationView;
    public View constraintLayout;
    public Activity context;
    public String str = TAG;
    public int videoFrames = 900;
    int templateFps = 0;
    // "live" state during recording
    private MediaCodec.BufferInfo mBufferInfo;
    private MediaCodec mEncoder;
    private MediaMuxer mMuxer;
    private Surface mInputSurface;
    private int mTrackIndex;
    private boolean mMuxerStarted;
    private long mFakePts;
    private final boolean VERBOSE = true;

    String videoName;


    public VideoRecorder(Activity context2, View constraintLayout2, LottieAnimationView lottieAnimationView, String name) {
        this.context = context2;
        this.constraintLayout = constraintLayout2;
        this.animationView = lottieAnimationView;
        this.videoFrames =(int) lottieAnimationView.getMaxFrame();
        this.videoName = name;
        height = constraintLayout.getHeight();
        width = constraintLayout.getWidth();
    }

    public VideoRecorder(Activity context2,int width ,int height, View constraintLayout2, LottieAnimationView lottieAnimationView, String name) {
        this.context = context2;
        this.constraintLayout = constraintLayout2;
        this.animationView = lottieAnimationView;
        this.videoFrames =(int) lottieAnimationView.getMaxFrame();
        this.videoName = name;
        this.width = width;
        this.height = height;

        if (height == 0){
            this.height = constraintLayout.getHeight();
            this.width = constraintLayout.getWidth();
        }
    }

    public void setInterfaceRenderCallback(RenderEngienCallBack renderCallback) {
        renderEngienCallBack = renderCallback;
        runExport();
    }

    public void setResolution(int height, int width) {
        HEIGHT = height;
        WIDTH = width;

    }

    int height;
    int width;
    public void runExport() {
        File file = new File(videoName);
        setResolution(height, width);
        try {
            generateMovie(file);
            Log.i(this.str, "Movie generation complete");
        } catch (Exception e) {
            renderEngienCallBack.onExportingFailed();
            showAlertDialog(this.str, "Movie generation FAILED "+e.getMessage());
        }
    }

    public void setFrameResolution(int heightnew, int widthnew) {
        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.constraintLayout.getLayoutParams();
        layoutParams.width = widthnew;
        layoutParams.height = heightnew;
        this.constraintLayout.setLayoutParams(layoutParams);
    }

    public void showAlertDialog(String str2, String str22) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this.context);
        builder.setTitle(str2);
        builder.setMessage(str22);
        builder.setPositiveButton("OK", (dialogInterface, i) -> dialogInterface.dismiss());
        builder.create().show();
    }

    private void prepareEncoder(File outputFile) throws IOException {
        mBufferInfo = new MediaCodec.BufferInfo();
        int i = WIDTH;

        if (i % 2 != 0) {
            WIDTH = i - 1;
        }

        int i2 = HEIGHT;

        if (i2 % 2 != 0) {
            HEIGHT = i2 - 1;
        }
        MediaFormat format = MediaFormat.createVideoFormat(MIME_TYPE, WIDTH, HEIGHT);
        // Set some properties.  Failing to specify some of these can cause the MediaCodec
        // configure() call to throw an unhelpful exception.
        format.setInteger(MediaFormat.KEY_COLOR_FORMAT,
                MediaCodecInfo.CodecCapabilities.COLOR_FormatSurface); // color格式
        format.setInteger(MediaFormat.KEY_BIT_RATE, BIT_RATE); // bitrate
        format.setInteger(MediaFormat.KEY_FRAME_RATE, 60); // 帧率(frames/sec)
        format.setInteger(MediaFormat.KEY_I_FRAME_INTERVAL, IFRAME_INTERVAL); // 关键帧
        Log.d(TAG, "format: " + format);
        // Create a MediaCodec encoder, and configure it with our format.  Get a Surface
        // we can use for input and wrap it with a class that handles the EGL work.
        mEncoder = MediaCodec.createEncoderByType(MIME_TYPE);

        mEncoder.configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE);

        mInputSurface = mEncoder.createInputSurface();


        mEncoder.start(); // 启动编码器
        // Create a MediaMuxer. We can't add the video track and start() the muxer here,
        // because our MediaFormat doesn't have the Magic Goodies.  These can only be
        // obtained from the encoder after it has started processing data.
        //
        // We're not actually interested in multiplexing audio.  We just want to convert
        // the raw H.264 elementary stream we get from MediaCodec into a .mp4 file.
        Log.d(TAG, "output will go to " + outputFile);
        // 创建MediaMuxer
        mMuxer = new MediaMuxer(outputFile.toString(), MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);
        mTrackIndex = -1;
        mMuxerStarted = false;
    }

    private void drainEncoder(boolean endOfStream) {
        final int TIMEOUT_USEC = 10000;

        if (VERBOSE) Log.d(TAG, "drainEncoder(" + endOfStream + ")");

        if (mEncoder != null) {

            if (endOfStream) {
                if (VERBOSE) Log.d(TAG, "sending EOS to encoder");
                mEncoder.signalEndOfInputStream();
            }
            // Retrieve the set of OutputBuffers


            ByteBuffer[] encoderOutputBuffers = mEncoder.getOutputBuffers();
            // 获取编码数据数组
            while (true) {
                // Returns the index of an output buffer that has been successfully decoded.
                // or one of the INFO_* constants.
                int encoderStatus = mEncoder.dequeueOutputBuffer(mBufferInfo, TIMEOUT_USEC);
                if (encoderStatus == MediaCodec.INFO_TRY_AGAIN_LATER) {
                    Log.d(TAG, "try again later");
                    // no output available yet
                    if (!endOfStream) {
                        break; // out of while
                    } else {
                        if (VERBOSE) Log.d(TAG, "no output available, spinning to await EOS");
                    }
                } else if (encoderStatus == MediaCodec.INFO_OUTPUT_BUFFERS_CHANGED) {
                    // not expected for an encoder
                    Log.d(TAG, "output buffers changed");
                    encoderOutputBuffers = mEncoder.getOutputBuffers();
                } else if (encoderStatus == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED) {
                    // should happen before receiving buffers, and should only happen once
                    if (mMuxerStarted) {
                        throw new RuntimeException("format changed twice");
                    }
                    MediaFormat newFormat = mEncoder.getOutputFormat();
                    Log.d(TAG, "encoder output format changed: " + newFormat);
                    // now that we have the Magic Goodies, start the muxer
                    mTrackIndex = mMuxer.addTrack(newFormat);
                    mMuxer.start();
                    mMuxerStarted = true;
                } else if (encoderStatus < 0) {
                    Log.w(TAG, "unexpected result from encoder.dequeueOutputBuffer: " +
                            encoderStatus);
                    // let's ignore it
                } else {
                    ByteBuffer encodedData = encoderOutputBuffers[encoderStatus];
                    if (encodedData == null) {
                        throw new RuntimeException("encoderOutputBuffer " + encoderStatus +
                                " was null");
                    }
                    if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_CODEC_CONFIG) != 0) {
                        // The codec config data was pulled out and fed to the muxer when we got
                        // the INFO_OUTPUT_FORMAT_CHANGED status, Ignore it.
                        Log.d(TAG, "ignoring BUFFER_FLAG_CODEC_CONFIG: " + mBufferInfo.flags);
                        mBufferInfo.size = 0;
                    }
                    if (mBufferInfo.size != 0) {
                        if (!mMuxerStarted) {
                            throw new RuntimeException("muxer hasn't started");
                        }
                        // adjust the ByteBuffer values to match BufferInfo
                        encodedData.position(mBufferInfo.offset); // 设置偏移量
                        encodedData.limit(mBufferInfo.offset + mBufferInfo.size); // 设置limit
                        mBufferInfo.presentationTimeUs = mFakePts;
                        mFakePts += 1000000L / FRAMES_PER_SECOND;
                        // 使用Muxer写入视频数据
                        mMuxer.writeSampleData(mTrackIndex, encodedData, mBufferInfo);
                        Log.d(TAG, "sent " + mBufferInfo.offset + "/" + mBufferInfo.size + " bytes to muxer");
                    }
                    // release释放索引位置对应的buffer
                    mEncoder.releaseOutputBuffer(encoderStatus, false);
                    // endOfStream -> break;
                    if ((mBufferInfo.flags & MediaCodec.BUFFER_FLAG_END_OF_STREAM) != 0) {
                        if (!endOfStream) {
                            Log.w(TAG, "reached end of stream unexpectedly");
                        } else {
                            if (VERBOSE) Log.d(TAG, "end of stream reached");
                        }
                        break; // out of while
                    }
                }
            }
        }

    }

    public void generateMovie(File file) {
        try {
            prepareEncoder(file);
            Handler handler = new Handler();
            handler.post(new RunnableC4727b(handler, file));
        } catch (IOException e) {
            Log.e(TAG, "generateMovie: ", e);
        }
    }

    public Bitmap getBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        bitmap = Bitmap.createScaledBitmap(bitmap, WIDTH, HEIGHT, false);

        return bitmap;
    }

    public float calcFramePercentage(int i, int i2) {
        if (i2 == 0) {
            return 0.0f;
        }
        return (float) ((i * 100) / i2);
    }

    private void releaseEncoder() {
        if (VERBOSE) Log.d(TAG, "releasing encoder objects");
        if (mEncoder != null) {
            mEncoder.stop();
            mEncoder.release();
            mEncoder = null;
        }
        if (mInputSurface != null) {
            mInputSurface.release();
            mInputSurface = null;
        }
        if (mMuxer != null) {
            mMuxer.stop();
            mMuxer.release();
            mMuxer = null;
        }
    }

    private void generateFrame(Bitmap bitmap) {

//        Log.i("exportProcess__", "getBitmapFromView -> "+bitmap.getWidth()+" "+bitmap.getHeight());
        if (mInputSurface != null) {
            Canvas lockCanvas = this.mInputSurface.lockCanvas(null);
            try {
                lockCanvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
            } finally {
                this.mInputSurface.unlockCanvasAndPost(lockCanvas);
            }

        }

    }


    public class RunnableC4727b implements Runnable {


        public final Handler handler;
        public final File file;
        public int frames = 0;

        public RunnableC4727b(Handler handler, File file) {
            this.handler = handler;
            this.file = file;
        }

        public void run() {


            drainEncoder(false);

            LottieAnimationView lottieAnimationView = animationView;

            lottieAnimationView.setFrame((int) (((float) this.frames) % lottieAnimationView.getMaxFrame()));

            generateFrame(getBitmapFromView(constraintLayout));


            int i = this.frames;
            this.frames = i + 1;


            if (((float) i) < ((float) videoFrames)) {
                this.handler.postDelayed(this, 0);

                float calcFramePercentage = calcFramePercentage(this.frames, videoFrames);
                renderEngienCallBack.onProgress(calcFramePercentage);
                return;
            }

            drainEncoder(true);
            releaseEncoder();

            renderEngienCallBack.onExported(this.file);
        }
    }

    public interface RenderEngienCallBack{
        void onExported(File file);
        void onProgress(float onProgress);
        void onExportingFailed();
    }
}
