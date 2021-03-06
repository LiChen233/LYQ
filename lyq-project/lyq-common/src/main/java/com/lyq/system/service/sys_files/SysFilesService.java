package com.lyq.system.service.sys_files;

import com.lyq.common.mybatisplus.MyIService;
import com.lyq.common.utils.sys_files.FilePathEnum;
import com.lyq.system.entity.sys_files.SysFiles;

import java.io.File;
import java.util.List;

public interface SysFilesService extends MyIService<SysFiles> {

    void insert(SysFiles sysFiles);

    /**
     * 保存文件
     * @param fileId
     * @param path
     * @param filename
     * @param suffix
     */
    void saveFile(String fileId, String path, String filename, String suffix);

    void saveFile(String fileId, FilePathEnum filePathEnum, String filename, String suffix);

    /**
     * 保存临时文件
     * @param fileId
     * @param filename
     * @param suffix
     */
    void saveInterimFile(String fileId, String filename, String suffix);

    /**
     * 删除文件
     * @param fileId
     */
    void delFile(String fileId);

    /**
     * 获取文件file对象
     * @param fileId
     * @return
     */
    File getFile(String fileId);

    File getFile(SysFiles sysFiles);

    /**
     * 获取文件信息列表
     * @param idList
     * @return
     */
    List<SysFiles> getListByIdList(List<String> idList);

    List<SysFiles> getListByIds(String ids);

    /**
     * 获取文件的绝对地址
     * @param filesId
     * @return
     */
    String getFileRealAddress(String filesId);

    String getFileRealAddress(SysFiles files);

}
