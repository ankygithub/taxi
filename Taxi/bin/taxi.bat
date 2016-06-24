set classpath=%classpath%;lib\commons-lang-2.3.jar;lib\jxl.jar;lib\jxls-core-0.9.6.jar;lib\jxls-reader-0.9.6.jar;
@echo off
SET   /P   VAL=1：华威,2:公交,3:海峡,4:福汽,5:台客，6：鼓楼  ：
ECHO   %VAL%  
java export.ExportTaxiAd %VAL% 
