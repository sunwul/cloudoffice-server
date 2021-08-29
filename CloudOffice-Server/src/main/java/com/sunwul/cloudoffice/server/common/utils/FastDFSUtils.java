package com.sunwul.cloudoffice.server.common.utils;

import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/*****
 * @author sunwul
 * @date 2021/3/29 18:34
 * @description FastDFS工具类
 */
public class FastDFSUtils {

    private static final Logger log = LoggerFactory.getLogger(FastDFSUtils.class);

    /**
     * 初始化客户端
     */
    static {
        try {
            // 获取resource目录下的FastDFS配置文件
            String filePath = new ClassPathResource("fdfs_client.conf").getFile().getAbsolutePath();
            // 读取配置文件并初始化对应属性
            ClientGlobal.init(filePath);
        } catch (Exception e) {
            log.error("初始化FastDFS失败,请检查配置文件: {}", e.getMessage());
        }
    }

    /**
     * 上传文件
     *
     * @param file 文件
     * @return 上传结果
     */
    public static String[] upload(MultipartFile file) {
        String name = file.getOriginalFilename();
        log.info("文件名: " + name);
        StorageClient storageClient = null;
        String[] uploadResults = null; // 上传结果
        try {
            // 获取Storage客户端
            storageClient = getStorageClient();
            if (name == null || name.trim().equals("") || name.trim().equals("null")) {
                // 需自定义名称
                log.error("文件名未知...生成文件名>");
            }
            // 上传
            uploadResults = storageClient.upload_file(file.getBytes(), name.substring(name.lastIndexOf(".") + 1), null);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传文件失败: {}", e.getMessage());
        }
        if (uploadResults == null) {
            log.error("上传文件失败,Code:{}", storageClient.getErrorCode());
        }
        return uploadResults;
    }

    /**
     * 下载文件
     *
     * @param groupName      groupName
     * @param remoteFileName 远程文件名
     * @return InputStream 输入流
     */
    public static InputStream downFile(String groupName, String remoteFileName) {
        StorageClient storageClient = null;
        try {
            // 获取Storage客户端
            storageClient = getStorageClient();
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            if (fileByte == null) {
                log.error("下载文件失败!");
                return null;
            }
            // 将byte数组转为流
            return new ByteArrayInputStream(fileByte);
        } catch (Exception e) {
            log.error("下载文件失败: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param groupName      groupName
     * @param remoteFileName 远程文件名
     */
    public static void deleteFile(String groupName, String remoteFileName) {
        try {
            // 获取Storage客户端
            StorageClient storageClient = getStorageClient();
            // 删除文件
            int count = storageClient.delete_file(groupName, remoteFileName);
            if (count > 0) {
                log.info("文件删除成功!");
                return;
            }
            log.error("文件删除失败!");
        } catch (Exception e) {
            log.error("文件删除失败: {}", e.getMessage());
        }
    }

    /**
     * 获取文件信息
     *
     * @param groupName      groupName
     * @param remoteFileName 远程文件名
     * @return FileInfo
     */
    public static FileInfo getFileInfo(String groupName, String remoteFileName) {
        try {
            // 获取Storage客户端
            StorageClient storageClient = getStorageClient();
            // 获取文件信息
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (Exception e) {
            log.error("文件信息获取失败: {}", e.getMessage());
        }
        return null;
    }

    /**
     * 获取文件路径
     *
     * @return 文件路径
     */
    public static String getTrackerUrl() {
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;
        try {
            trackerServer = trackerClient.getTrackerServer();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            log.error("文件路径获取失败: {}", e.getMessage());
        }
        log.info("地址: " + storageServer.getInetSocketAddress());
        return "http://" + storageServer.getInetSocketAddress().getHostString() + ":8888/";
    }

    /**
     * 通过TrackerServer生成Storage客户端
     *
     * @return StorageClient
     * @throws IOException IO异常
     */
    private static StorageClient getStorageClient() throws IOException {
        TrackerServer trackerServer = getTrackerServer();
        return new StorageClient(trackerServer, null);
    }

    /**
     * 生成Tracker服务器
     *
     * @return TrackerServer
     * @throws IOException IO异常
     */
    private static TrackerServer getTrackerServer() throws IOException {
        TrackerClient trackerClient = new TrackerClient();
        return trackerClient.getTrackerServer();
    }
}
