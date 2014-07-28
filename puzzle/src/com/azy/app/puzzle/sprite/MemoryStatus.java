package com.azy.app.puzzle.sprite;

import java.io.File;

import android.os.Environment;
import android.os.StatFs;

public class MemoryStatus {
	
	 static final int ERROR = -1;   
     /** ��ȡ�ⲿ��չ�洢����״̬ */
  static public boolean externalMemoryAvailable() {   
      return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);  
  }
    /** ��ȡ���õ�SD���Ŀռ��С */
  static public long getAvailableExternalMemorySize() {  
          /** �ж�SD���Ƿ���ã������Ƿ���� */ 
      if(externalMemoryAvailable()) {   
          /** ��ȡSD����·�� */
          File path = Environment.getExternalStorageDirectory();   
          /** StatFs ���ļ�ϵͳ�ռ�ʹ����� */
          StatFs stat = new StatFs(path.getPath()); 
          /** StatFs ���Block��Ĵ�С */
          long blockSize = stat.getBlockSize();   
          /** ��ÿ��õ�Block������� */
          long availableBlocks = stat.getAvailableBlocks(); 
          /** ��ÿ��õ�SD���Ĵ洢�ռ� */  
          return availableBlocks * blockSize;   
      } else {   
          return ERROR;   
      }   
  }   
     /** ����ܵ�SD�������� */
  static public long getTotalExternalMemorySize() {   
      if(externalMemoryAvailable()) {   
          File path = Environment.getExternalStorageDirectory();   
          StatFs stat = new StatFs(path.getPath());   
          long blockSize = stat.getBlockSize();   
           /** ����ܵ�Block������� */
          long totalBlocks = stat.getBlockCount();   
          return totalBlocks * blockSize;   
      } else {   
          return ERROR;   
      }   
  }   
        
      /** ��ȡ�ڴ��С */
  static public long getAvailableInternalMemorySize() {   
     /** ��ȡ�ڴ��·�� */
      File path = Environment.getDataDirectory();   
     /** StatFs ���ļ�ϵͳ�ռ�ʹ����� */
      StatFs stat = new StatFs(path.getPath());   
     /** Block �� size*/ 
      long blockSize = stat.getBlockSize();   
     /**���� Block �� ����*/ 
      long availableBlocks = stat.getAvailableBlocks();   
     /**���� �ڴ�ռ��С*/ 
      return availableBlocks * blockSize;   
  }   
     /** ����ܵ��ڴ����� */
  static public long getTotalInternalMemorySize() {   
      File path = Environment.getDataDirectory();   
      StatFs stat = new StatFs(path.getPath());   
      long blockSize = stat.getBlockSize();  
     /**�ܵ��ڴ�Block ������*/  
      long totalBlocks = stat.getBlockCount();  
      /**�ܵ��ڴ�ռ��С*/ 
      return totalBlocks * blockSize;   
  }   
     
  /**ƴ����ʾ���ַ��� */
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
