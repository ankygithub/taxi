set classpath=%classpath%;lib\commons-lang-2.3.jar;lib\jxl.jar;lib\jxls-core-0.9.6.jar;lib\jxls-reader-0.9.6.jar;
@echo off
SET   /P   VAL=1������,2:����,3:��Ͽ,4:����,5:̨�ͣ�6����¥  ��
ECHO   %VAL%  
java export.ExportTaxiAd %VAL% 
