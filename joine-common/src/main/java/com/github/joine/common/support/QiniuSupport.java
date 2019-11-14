package com.github.joine.common.support;

import com.qiniu.cdn.CdnManager;
import com.qiniu.cdn.CdnResult;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * @Author: JenphyJohn
 * @Date: 2019/7/22 10:40 AM
 */
@Component
public class QiniuSupport {

    @Value("${qiniu.accessKey:0}")
    private String accessKey;
    @Value("${qiniu.secretKey:0}")
    private String secretKey;

    public QiniuSupport() {
    }

    private Auth auth() {
        return Auth.create(this.accessKey, this.secretKey);
    }

    public String createUploadToken(String bucket) {
        return this.auth().uploadToken(bucket);
    }

    public String createUploadToken(String bucket, String key) {
        return this.auth().uploadToken(bucket, key, 3600000L, new StringMap());
    }

    public Response put(byte[] data, String key, String token) throws QiniuException {
        UploadManager uploadManager = new UploadManager(new Configuration());
        return uploadManager.put(data, key, token);
    }

    public Response put(File file, String key, String token) throws QiniuException {
        UploadManager uploadManager = new UploadManager(new Configuration());
        return uploadManager.put(file, key, token);
    }

    public Response put(String filePath, String key, String token) throws QiniuException {
        UploadManager uploadManager = new UploadManager(new Configuration());
        return uploadManager.put(filePath, key, token);
    }

    public Response upload(byte[] data, String key, String bucket) throws QiniuException {
        String token = this.createUploadToken(bucket, key);
        return this.put(data, key, token);
    }

    public Response upload(File file, String key, String bucket) throws QiniuException {
        String token = this.createUploadToken(bucket, key);
        return this.put(file, key, token);
    }

    public Response upload(String filePath, String key, String bucket) throws QiniuException {
        String token = this.createUploadToken(bucket, key);
        return this.put(filePath, key, token);
    }

    public Response upload(InputStream inputStream, String key, String bucket) throws QiniuException {
        String token = this.createUploadToken(bucket, key);
        UploadManager uploadManager = new UploadManager(new Configuration());
        return uploadManager.put(inputStream, key, token, null, null);
    }

    public void refresh(String[] urls) {
        Auth auth = this.auth();
        CdnManager c = new CdnManager(auth);
        try {
            // 单次方法调用刷新的链接不可以超过100个
            CdnResult.RefreshResult result = c.refreshUrls(urls);
        } catch (QiniuException e) {
            System.err.println(e.response.toString());
        }

    }
}
