## Jenkins 설치

---

```bash
# Java 설치
sudo apt-get install openjdk-11-jdk

# 1. Jenkins 저장소 key 다운로드
# 2. sources.list 추가
# 3. key 등록
# 4. jenkins 설치
wget -q -O - https://pkg.jenkins.io/debian/jenkins-ci.org.key | sudo apt-key add -
echo deb http://pkg.jenkins.io/debian-stable binary/ | sudo tee /etc/apt/sources.list.d/jenkins.list
sudo apt-key adv --keyserver keyserver.ubuntu.com --recv-keys FCEF32E745F2C3D5
sudo apt-get update
sudo apt-get install jenkins

# Jenkins 서버 포트 번호 변경
sudo vi /etc/default/jenkins
# Jenkins 서비스 재기동
sudo service jenkins restart
# Jenkins 서비스 상태 확인
sudo systemctl status jenkins
# Jenkins 초기 비밀번호 확인
sudo cat /var/lib/jenkins/secrets/initialAdminPassword

# jenkins포트 설정 후 변경하는 법
sudo chmod 777 /usr/lib/systemd/system/jenkins.service
sudo vi /usr/lib/systemd/system/jenkins.service
sudo chmod 444 /usr/lib/systemd/system/jenkins.service
sudo systemctl daemon-reload
sudo service jenkins restart
```

<br>

### jenkins id & pw

ssafyland

OBh00UOs4dq8

<br>

## Jenkins & gitlab 연동

---

deploy token : gitlab+deploy-token-3725

token pw : XaRCBzDRnLBH2D2qcXo1

jenkins secret token : 4fa094f6225d8c9899db272b2ad310db

<br>

### gitlab deploy token 생성

**project - Settings - Repository - Deploy tokens**

<br>

### jenkins pipeline 생성 및 script

- secret token - generate
- pipeline script

```json
pipeline {
    agent any

    tools {
        nodejs 'node:16.13.2'
    }

    environment {
		GIT_URL = "https://gitlab+deploy-token-3725:XaRCBzDRnLBH2D2qcXo1@lab.ssafy.com/s06-final/S06P31D102.git"
    }

    stages {
        stage('pull') {
            steps {
                git branch: 'develop', url: "${GIT_URL}"
                sh 'git lfs pull'
            }
        }

        stage('build') {
            steps {
                dir('frontend') {
                    sh 'npm install && npm run build'
                }

                dir('backend') {
                    sh './gradlew clean build'
                }
            }
        }

        stage('deploy') {
            steps {
                sh 'docker-compose up -d --build --force-recreate'
            }
        }
    }
}
```

<br>

### jenkins & gitlab webhook

- **project - Settings - Webhooks**
- URL, Secret Token - Add Webhook

<br>

## Nginx & SSL

---

### nginx 설치

```bash
sudo apt update
sudo apt upgrade
sudo apt autoremove

sudo apt install nginx

sudo service nginx start
sudo service nginx status
```

<br>

### nginx 설정

> /etc/nginx/nginx.conf

```
user www-data;
worker_processes auto;
pid /run/nginx.pid;
include /etc/nginx/modules-enabled/*.conf;

events {
	worker_connections 768;
	# multi_accept on;
}

http {

	##
	# Basic Settings
	##

	sendfile on;
	tcp_nopush on;
	tcp_nodelay on;
	keepalive_timeout 65;
	types_hash_max_size 2048;
	# server_tokens off;

	# server_names_hash_bucket_size 64;
	# server_name_in_redirect off;

	include /etc/nginx/mime.types;
	default_type application/octet-stream;

	##
	# SSL Settings
	##

	ssl_protocols TLSv1 TLSv1.1 TLSv1.2 TLSv1.3; # Dropping SSLv3, ref: POODLE
	ssl_prefer_server_ciphers on;

	##
	# Logging Settings
	##

	access_log /var/log/nginx/access.log;
	error_log /var/log/nginx/error.log;

	##
	# Gzip Settings
	##

	gzip on;

	# gzip_vary on;
	# gzip_proxied any;
	# gzip_comp_level 6;
	# gzip_buffers 16 8k;
	# gzip_http_version 1.1;
	# gzip_types text/plain text/css application/json application/javascript text/xml application/xml application/xml+rss text/javascript;

	##
	# Virtual Host Configs
	##

	include /etc/nginx/conf.d/*.conf;
	include /etc/nginx/sites-enabled/*;
}

#mail {
#	# See sample authentication script at:
#	# http://wiki.nginx.org/ImapAuthenticateWithApachePhpScript
#
#	# auth_http localhost/auth.php;
#	# pop3_capabilities "TOP" "USER";
#	# imap_capabilities "IMAP4rev1" "UIDPLUS";
#
#	server {
#		listen     localhost:110;
#		protocol   pop3;
#		proxy      on;
#	}
#
#	server {
#		listen     localhost:143;
#		protocol   imap;
#		proxy      on;
#	}
#}
```

<br>

> /etc/nginx/con.d/default.conf

```
##
# You should look at the following URL's in order to grasp a solid understanding
# of Nginx configuration files in order to fully unleash the power of Nginx.
# https://www.nginx.com/resources/wiki/start/
# https://www.nginx.com/resources/wiki/start/topics/tutorials/config_pitfalls/
# https://wiki.debian.org/Nginx/DirectoryStructure
#
# In most cases, administrators will remove this file from sites-enabled/ and
# leave it as reference inside of sites-available where it will continue to be
# updated by the nginx packaging team.
#
# This file will automatically load configuration files provided by other
# applications, such as Drupal or Wordpress. These applications will be made
# available underneath a path with that package name, such as /drupal8.
#
# Please see /usr/share/doc/nginx-doc/examples/ for more detailed examples.
##

# Default server configuration
#

# Virtual Host configuration for example.com
#
# You can move that to a different file under sites-available/ and symlink that
# to sites-enabled/ to enable it.
#
#server {
#	listen 80;
#	listen [::]:80;
#
#	server_name example.com;
#
#	root /var/www/example.com;
#	index index.html;
#
#	location / {
#		try_files $uri $uri/ =404;
#	}
#}

upstream backend {
    server 127.0.0.1:8080;
    keepalive 32;
}

server {

	# SSL configuration
	#
	# listen 443 ssl default_server;
	# listen [::]:443 ssl default_server;
	#
	# Note: You should disable gzip for SSL traffic.
	# See: https://bugs.debian.org/773332
	#
	# Read up on ssl_ciphers to ensure a secure configuration.
	# See: https://bugs.debian.org/765782
	#
	# Self signed certs generated by the ssl-cert package
	# Don't use them in a production server!
	#
	# include snippets/snakeoil.conf;

	# root /var/www/html;

	# Add index.php to the list if you are using PHP
	# index index.html index.htm index.nginx-debian.html;
    server_name k6d102.p.ssafy.io; # managed by Certbot
	server_tokens off;

	location / {
		# First attempt to serve request as file, then
		# as directory, then fall back to displaying a 404.
		root /var/lib/jenkins/workspace/ssafyland/frontend/build;
		index index.html index.htm;
		try_files $uri $uri/ =404;
	}

	location /api {
		proxy_pass http://backend;
		# try_files $uri $uri/ =404;
	}

	# locations /image {
		# root /var/lib/jenkins/workspace/ssafyland/frontend/build/image;
		# try_files $uri $uri/ =404;
	# }

	# pass PHP scripts to FastCGI server
	#
	#location ~ \.php$ {
	#	include snippets/fastcgi-php.conf;
	#
	#	# With php-fpm (or other unix sockets):
	#	fastcgi_pass unix:/var/run/php/php7.4-fpm.sock;
	#	# With php-cgi (or other tcp sockets):
	#	fastcgi_pass 127.0.0.1:9000;
	#}

	# deny access to .htaccess files, if Apache's document root
	# concurs with nginx's one
	#
	#location ~ /\.ht {
	#	deny all;
	#}

    listen [::]:443 ssl ipv6only=on; # managed by Certbot
    listen 443 ssl; # managed by Certbot
    ssl_certificate /etc/letsencrypt/live/k6d102.p.ssafy.io/fullchain.pem; # managed by Certbot
    ssl_certificate_key /etc/letsencrypt/live/k6d102.p.ssafy.io/privkey.pem; # managed by Certbot
    include /etc/letsencrypt/options-ssl-nginx.conf; # managed by Certbot
    ssl_dhparam /etc/letsencrypt/ssl-dhparams.pem; # managed by Certbot
}

server {
    if ($host = k6d102.p.ssafy.io) {
        return 301 https://$host$request_uri;
    } # managed by Certbot

    listen 80 ;
    listen [::]:80 ;
    server_name k6d102.p.ssafy.io;
    return 404; # managed by Certbot
}
```

<br>

### SSL 설치 및 실행

> **Let’s Encrypt**

```bash
sudo apt-get update
sudo apt-get upgrade -y
sudo apt-get install software-properties-common
sudo add-apt-repository universe
sudo add-apt-repository ppa:certbot/certbot
sudo apt-get update
sudo apt install certbot python3-certbot-nginx

sudo certbot --nginx -d k6d102.p.ssafy.io
```

<br>

## Docker

---

### docker networks create

```bash
docker networks create ssafyland
```

<br>

### docker-compose.yml

```yaml
version: "3.8"

services:
    backend:
        container_name: ssafyland_backend
        build: ./backend/
        restart: always
        environment:
          TZ: Asia/Seoul
				volumes:
					- ./data/files:/data/files
        depends_on:
          - db
        ports:
          - "8080:8080"
        networks:
          - ssafyland
    db:
        image: mysql:8
        container_name: ssafyland_db
        platform: linux/x86_64
        env_file: ./db/.env
        environment:
          TZ: Asia/Seoul
        restart: always
        volumes:
          # - ./docker/db/conf.d:/etc/mysql/conf.d
          - ./db/data:/var/lib/mysql
          - ./db/initdb.d:/docker-entrypoint-initdb.d
        ports:
          - "3306:3306"
        networks:
          - ssafyland
networks:
    ssafyland:
        driver: bridge
```

<br>

### Dockerfile

```docker
# backend
FROM openjdk:11-jdk
RUN mkdir data
RUN mkdir /data/files
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

<br>

### db/.env

```
MYSQL_HOST=localhost
MYSQL_PORT=3306
MYSQL_ROOT_PASSWORD=root
MYSQL_DATABASE=ssafyland
MYSQL_USER=ssafyland
MYSQL_PASSWORD=ssafyland!102
```
