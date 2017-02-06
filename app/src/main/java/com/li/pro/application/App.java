package com.li.pro.application;

import android.app.Application;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

/**
 * Created by Administrator on 2016/11/7 0007.
 */

public class App extends Application {
    private static App app;

    public static synchronized App getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;

        
//        startService(new Intent(this, FileReceiverService.class));


        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryPath(new File(Environment.getExternalStorageDirectory() + "/111LMW"))
                .build();
        ImagePipelineConfig imagePipelineConfig = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this, imagePipelineConfig);


//        //内存泄露检测
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            // This process is dedicated to LeakCanary for heap analysis.
//            // You should not init your app in this process.
//            return;
//        }
//        LeakCanary.install(this);
//        // Normal app init code...
    }
}
