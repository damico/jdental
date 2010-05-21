#/bin/bash
mkdir /etc/jdental/
cp jdental.conf /etc/jdental/jdental.conf
mkdir /var/log/jdental/
mkdir /opt/jdental/
chmod 777 -R /opt/jdental
cp ../../design/**.png /opt/jdental
touch /var/log/jdental/jdental.log