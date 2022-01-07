# How to fix 'System has not been booted with systemd' error?
The simple answer is to not use the systemctl command. Instead, use the equivalent sysvinit command.  
| systemd command | sysvinit command |
|----------------:|:----------------:|
| systemctl start service_name | service service_name start |
| systemctl stop service_name | service service_name stop |
| systemctl restart service_name | service service_name restart |
| systemctl status service_name | service service_name status |
| systemctl enable service_name | chkconfig service_name on |
| systemctl disable service_name | chkconfig service_name off |
