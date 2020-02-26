# Instalacija
Instalirati composer
https://getcomposer.org/download/

cd $rootprojekta
izvesti naredbu
composer install


## Instalacija na windows
Instalirati https://www.apachefriends.org/xampp-files/7.4.2/xampp-windows-x64-7.4.2-0-VC15-installer.exe
Uz pretpostavku da je xampp na c:\ disku
pokrenuti program c:\xampp\xampp-control.exe te startati Apache i Mysql

RESTAPI direktorij kopirati u c:\xampp\htdocs

otvoriti naredbeni redak (win tipka + r i napisati cmd i klik na run)
izvesti sljedeće naredbe\
    cd c:\xampp\htdocs\RESTAPI\
    c:\xampp\php\php.exe  -r "copy('https://getcomposer.org/installer', 'composer-setup.php');"\
    c:\xampp\php\php.exe  -r "if (hash_file('sha384', 'composer-setup.php') === 'e0012edf3e80b6978849f5eff0d4b4e4c79ff1609dd1e613307e16318854d24ae64f26d17af3ef0bf7cfb710ca74755a') { echo 'Installer verified'; } else { echo 'Installer corrupt'; unlink('composer-setup.php'); } echo PHP_EOL;"\
    c:\xampp\php\php.exe  composer-setup.php\
    c:\xampp\php\php.exe  -r "unlink('composer-setup.php');"\
    c:\xampp\php\php.exe composer.phar install

Postavljanje baze podataka\
    c:\xampp\mysql\bin\mysql.exe -uroot < c:\xampp\htdocs\RESTAPI\sktipta.sql

Iz mobilnih aplikacija REST API je dostupan na adresi http://IP_RAČUNALA/RESTAPI
