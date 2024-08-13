# 项目布署

## 安装docker

### windows 安装 docker

若要在 Windows 10 或 11 上运行容器，需要以下各项：
1、 一个运行 Windows 10 或 11 专业版或企业版（含周年更新（版本 1607）或更高版本）的物理计算机系统。
2、 Hyper-V 应已启用（在控制面板的程序和功能里安装，或者运行powershell 输入 Enable-WindowsOptionalFeature -Online
-FeatureName Microsoft-Hyper-V -All）。

docker官网下载地址：
https://desktop.docker.com/win/main/amd64/Docker%20Desktop%20Installer.exe

### linux (centOS) 安装 docker (sudo -i 切换身份)

yum install -y docker

### linux (centOS) 安装 docker-compose (sudo -i 切换身份)

yum install -y docker-compose
或者
sudo curl -L "https://github.com/docker/compose/releases/download/v2.5.0/docker-compose-$(uname -s)-$(uname -m)" -o
/usr/local/bin/docker-compose
sudo chmod +x /usr/local/bin/docker-compose

## 安装JDK 17

### windows 安装JDK 17

1、从Oracle的官方网站下载JDK 17的安装包(压缩包也可以)
2、新建系统变量JAVA_HOME，‌其值应指向你安装JDK的目录（‌例如C:\Program
Files\Java\jdk-17.0.2）‌。‌此外，‌还需要将%JAVA_HOME%\bin添加到Path环境变量中

Oracle的官方网站下载地址：
https://www.oracle.com/cn/java/technologies/downloads/#jdk17-windows

### linux 安装JDK 17 (sudo -i 切换身份)

sudo yum search openjdk
sudo yum install -y java-17-openjdk.x86_64

## 下载和配置 maven

### windows 安装maven

maven 官网下载地址:
https://maven.apache.org/download.cgi&gt
windows 配置 M2_HOME环境变量，并把 maven的bin目录(%M2_HOME%\bin)加入path

### linux(centos) 安装maven

yum install -y maven

## 打包项目 （网络问题，可能要多执行几次，可能使用阿里仓库，参考项目的/maven/conf/settings.xml）

进入项目目录
mvn clean package -Dmaven.test.skip=true docker:build

## 普通打包项目 （要执行测试用例，就要先启动数据库）

mvn clean package -Dmaven.test.skip=true

## 运行项目

### 确保 3306和8080端口可用 （docker-compose运行，把src/main/resource下的yml的localhost改为mysql）

docker-compose up -d

### 普通运行 （检查src/main/resource下的yml的数据库连接串为localhost）

docker pull mysql
md d:\var\lib\mysql
md d:\var\data\mysql
docker run -itd --name mysql -p 3306:3306 -v "/d/var/lib/mysql":/var/lib/mysql -v "
/d/var/data/mysql":/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=root mysql
java -jar ./target/feettool-0.0.1-SNAPSHOT.jar

## 停止项目

docker-compose down

## 访问项目

http://localhost:8080
http://ip:8080