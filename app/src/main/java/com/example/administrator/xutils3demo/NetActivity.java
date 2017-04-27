package com.example.administrator.xutils3demo;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.permission.AndPermission;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;

@ContentView(R.layout.activity_net)
public class NetActivity extends Activity {
    @ViewInject(R.id.progressbar)
    ProgressBar progressBar;
    @ViewInject(R.id.tv_net)
    TextView tv_net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        AndPermission.with(this)
                .requestCode(100)
                .permission(Manifest.permission.INTERNET, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .send();
    }

    @Event(value = {R.id.btn_net, R.id.btn_net_post, R.id.btn_net_get})
    private void getEvents(View view) {
        progressBar.setProgress(0);
        switch (view.getId()) {
            case R.id.btn_net:
                getAndPostNet();
                break;
            case R.id.btn_net_post:
                uploadFile();
                break;
            case R.id.btn_net_get:
                downloadFile();
                break;
        }
    }

    private void uploadFile() {
        RequestParams params = new RequestParams("http://192.168.31.33:8080/FileUpload/index.jsp");
        params.setMultipart(true);
        params.addBodyParameter("File", new File(Environment.getDownloadCacheDirectory() + "/161212190638286683.mp4"), null, "oppo.MP4");
        x.http().post(params, new Callback.ProgressCallback<File>() {

            @Override
            public void onSuccess(File result) {
                //下载成功时,回调下载路径
                Log.e("TAG", "onSuccess: " + result.toString());
                Toast.makeText(NetActivity.this, "上传成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //下载失败
                Log.e("TAG", "onError: " + ex.toString());
                Toast.makeText(NetActivity.this, "上传失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                //下载取消
                Log.e("TAG", "onCancelled: " + cex.toString());
            }

            @Override
            public void onFinished() {
                //下载完成
                Log.e("TAG", "onFinished");
                Toast.makeText(NetActivity.this, "上传完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWaiting() {
                //下载等待
                Log.e("TAG", "onWaiting");
                Toast.makeText(NetActivity.this, "上传等待", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStarted() {
                //下载开始
                Log.e("TAG", "onStarted");
                Toast.makeText(NetActivity.this, "开始上传", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                if (isDownloading) {
                    progressBar.setMax((int) total);
                    progressBar.setProgress((int) current);
                    //下载进度
                    Log.e("TAG", "onLoading" + current + "/" + total + "." + isDownloading);
                }
            }
        });
    }

    private void downloadFile() {
        RequestParams params = new RequestParams("http://vf1.mtime.cn/Video/2016/12/12/mp4/161212190638286683.mp4");
        //设置保存路径
        params.setSaveFilePath(Environment.getDownloadCacheDirectory() + "/161212190638286683.mp4");
        //是否可以取消
        params.setCancelFast(true);
        //自动命名
        params.setAutoRename(false);
        //断点续传
        params.setAutoResume(true);
        x.http().get(params, new Callback.ProgressCallback<File>() {
            @Override
            public void onSuccess(File result) {
                //下载成功时,回调下载路径
                Log.e("TAG", "onSuccess: " + result.toString());
                Toast.makeText(NetActivity.this, "下载成功", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                //下载失败
                Log.e("TAG", "onError: " + ex.toString());
                Toast.makeText(NetActivity.this, "下载失败", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(CancelledException cex) {
                //下载取消
                Log.e("TAG", "onCancelled: " + cex.toString());
            }

            @Override
            public void onFinished() {
                //下载完成
                Log.e("TAG", "onFinished");
                Toast.makeText(NetActivity.this, "下载完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onWaiting() {
                //下载等待
                Log.e("TAG", "onWaiting");
                Toast.makeText(NetActivity.this, "下载等待", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStarted() {
                //下载开始
                Log.e("TAG", "onStarted");
                Toast.makeText(NetActivity.this, "开始下载", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {
                if (isDownloading) {
                    progressBar.setMax((int) total);
                    progressBar.setProgress((int) current);
                    //下载进度
                    Log.e("TAG", "onLoading" + current + "/" + total + "." + isDownloading);
                }
            }
        });
    }

    public void getAndPostNet() {
        RequestParams params = new RequestParams("https://www.baidu.com/?tn=98012088_5_dg&ch=12");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                tv_net.setText(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }

            @Override
            public void onCancelled(CancelledException cex) {
            }

            @Override
            public void onFinished() {
            }
        });
    }
}
