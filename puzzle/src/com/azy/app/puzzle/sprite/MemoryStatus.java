package com.azy.app.puzzle.sprite;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

public class MemoryStatus {
	
	 static final int ERROR = -1;   
     /** 获取外部扩展存储卡的状态 */
  static public boolean externalMemoryAvailable() {   
      return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);  
  }
    /** 获取可用的SD卡的空间大小 */
  static public long getAvailableExternalMemorySize() {  
          /** 判断SD卡是否可用，或者是否存在 */ 
      if(externalMemoryAvailable()) {   
          /** 获取SD卡的路径 */
          File path = Environment.getExternalStorageDirectory();   
          /** StatFs 看文件系统空间使用情况 */
          StatFs stat = new StatFs(path.getPath()); 
          /** StatFs 获得Block块的大小 */
          long blockSize = stat.getBlockSize();   
          /** 获得可用的Block块的数量 */
          long availableBlocks = stat.getAvailableBlocks(); 
          /** 获得可用的SD卡的存储空间 */  
          return availableBlocks * blockSize;   
      } else {   
          return ERROR;   
      }   
  }   
     /** 获得总的SD卡的容量 */
  static public long getTotalExternalMemorySize() {   
      if(externalMemoryAvailable()) {   
          File path = Environment.getExternalStorageDirectory();   
          StatFs stat = new StatFs(path.getPath());   
          long blockSize = stat.getBlockSize();   
           /** 获得总的Block块的数量 */
          long totalBlocks = stat.getBlockCount();   
          return totalBlocks * blockSize;   
      } else {   
          return ERROR;   
      }   
  }   
        
      /** 获取内存大小 */
  static public long getAvailableInternalMemorySize() {   
     /** 获取内存的路径 */
      File path = Environment.getDataDirectory();   
     /** StatFs 看文件系统空间使用情况 */
      StatFs stat = new StatFs(path.getPath());   
     /** Block 的 size*/ 
      long blockSize = stat.getBlockSize();   
     /**可用 Block 的 数量*/ 
      long availableBlocks = stat.getAvailableBlocks();   
     /**可用 内存空间大小*/ 
      return availableBlocks * blockSize;   
  }   
     /** 获得总的内存容量 */
  static public long getTotalInternalMemorySize() {   
      File path = Environment.getDataDirectory();   
      StatFs stat = new StatFs(path.getPath());   
      long blockSize = stat.getBlockSize();  
     /**总的内存Block 的数量*/  
      long totalBlocks = stat.getBlockCount();  
      /**总的内存空间大小*/ 
      return totalBlocks * blockSize;   
  }   
     
  /**拼接显示的字符串 */
  static public String formatSize(long size) {   
      String suffix = null;   
     
      if (size >= 1024) {   
          suffix = "KiB";   
          size /= 1024;   
          if (size >= 1024) {   
              suffix = "MiB";   
              size /= 1024;   
          }   
      }   
     
      StringBuilder resultBuffer = new StringBuilder(Long.toString(size));   
     
      int commaOffset = resultBuffer.length() - 3;   
      while (commaOffset > 0) {   
          resultBuffer.insert(commaOffset, ',');   
          commaOffset -= 3;   
      }   
     
      if (suffix != null)   
          resultBuffer.append(suffix);   
      return resultBuffer.toString();   
  }   

}
