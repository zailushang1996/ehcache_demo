##简介
###1. 基本介绍
EhCache 是一个纯Java的进程内缓存框架，具有快速、精干等特点，是Hibernate中默认CacheProvider。Ehcache是一种广泛使用的开源Java分布式缓存。主要面向通用缓存,Java EE和轻量级容器。它具有内存和磁盘存储，缓存加载器,缓存扩展,缓存异常处理程序,一个gzip缓存servlet过滤器,支持REST和SOAP api等特点。

Spring 提供了对缓存功能的抽象：即允许绑定不同的缓存解决方案（如Ehcache），但本身不直接提供缓存功能的实现。它支持注解方式使用缓存，非常方便。

###2. 主要的特性有：
1. 快速
2. 简单
3. 多种缓存策略
4. 缓存数据有两级：内存和磁盘，因此无需担心容量问题
5. 缓存数据会在虚拟机重启的过程中写入磁盘
6. 可以通过RMI、可插入API等方式进行分布式缓存
7. 具有缓存和缓存管理器的侦听接口
8. 支持多缓存管理器实例，以及一个实例的多个缓存区域
9. 提供Hibernate的缓存实现

###3. ehcache 和 redis 比较
+ ehcache直接在jvm虚拟机中缓存，速度快，效率高；但是缓存共享麻烦，集群分布式应用不方便。
+ redis是通过socket访问到缓存服务，效率比ecache低，比数据库要快很多， 
处理集群和分布式缓存方便，有成熟的方案。如果是单个应用或者对缓存访问要求很高的应用，用ehcache。如果是大型系统，存在缓存共享、分布式部署、缓存内容很大的，建议用redis。

##HelloWorld
1. 在pom.xml中引入依赖

```xml
<dependency>
    <groupId>net.sf.ehcache</groupId>
    <artifactId>ehcache</artifactId>
    <version>2.10.2</version>
</dependency>
```
2. 在src/main/resources/创建一个配置文件 ehcache.xml

默认情况下Ehcache会自动加载classpath根目录下名为ehcache.xml文件，也可以将该文件放到其他地方在使用时指定文件的位置
##缓存配置
###一：xml配置方式：
+ diskStore ： ehcache支持内存和磁盘两种存储

+ path ：指定磁盘存储的位置
+ defaultCache ： 默认的缓存

    + maxEntriesLocalHeap=”10000”
    + eternal=”false”
    + timeToIdleSeconds=”120”
    + timeToLiveSeconds=”120”
    + maxEntriesLocalDisk=”10000000”
    + diskExpiryThreadIntervalSeconds=”120”
    + memoryStoreEvictionPolicy=”LRU”
+ cache ：自定的缓存，当自定的配置不满足实际情况时可以通过自定义（可以包含多个cache节点）

    + name : 缓存的名称，可以通过指定名称获取指定的某个Cache对象

    + maxElementsInMemory ：内存中允许存储的最大的元素个数，0代表无限个

    + clearOnFlush：内存数量最大时是否清除。

    + eternal ：设置缓存中对象是否为永久的，如果是，超时设置将被忽略，对象从不过期。根据存储数据的不同，例如一些静态不变的数据如省市区等可以设置为永不过时

    + timeToIdleSeconds ： 设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。

    + timeToLiveSeconds ：缓存数据的生存时间（TTL），也就是一个元素从构建到消亡的最大时间间隔值，这只能在元素不是永久驻留时有效，如果该值是0就意味着元素可以停顿无穷长的时间。

    + overflowToDisk ：内存不足时，是否启用磁盘缓存。

    + maxEntriesLocalDisk：当内存中对象数量达到maxElementsInMemory时，Ehcache将会对象写到磁盘中。

    + maxElementsOnDisk：硬盘最大缓存个数。

    + diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。

    + diskPersistent：是否在VM重启时存储硬盘的缓存数据。默认值是false。

    + diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
###二：编程方式配置
```xml
Cache cache = manager.getCache("mycache"); 
CacheConfiguration config = cache.getCacheConfiguration(); 
config.setTimeToIdleSeconds(60); 
config.setTimeToLiveSeconds(120); 
config.setmaxEntriesLocalHeap(10000); 
config.setmaxEntriesLocalDisk(1000000);
```
##Ehcache API


https://blog.csdn.net/vbirdbest/article/details/72763048