#! /bin/sh
#waithing untinl the mysql database connect

maxcount=100
counter=0
while ! mysql -u "root" -p "uvmxu624GJK6DXMS@" -h "mysql" -e "show databases;"  > /dev/all 2>&1; do
     sleep 100
     counter=`expr $counter + 1` 
     if [ $counter -gt $maxcount ]; then
         >&2 echo "we have been waiting for too long; failling"
         exit 1
     fi; 
done

exec ./usr/local/tomcat/bin/catalina.sh run