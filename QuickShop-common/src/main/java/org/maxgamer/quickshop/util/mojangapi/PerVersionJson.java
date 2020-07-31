/*
 *     Copyright (c) 2020, Bukkit Commons Studio.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>
 */

package org.maxgamer.quickshop.util.mojangapi;

import java.util.List;

public class PerVersionJson {

    /**
     * arguments :
     * {"game":["--username","${auth_player_name}","--version","${version_name}","--gameDir","${game_directory}","--assetsDir","${assets_root}","--assetIndex","${assets_index_name}","--uuid","${auth_uuid}","--accessToken","${auth_access_token}","--userType","${user_type}","--versionType","${version_type}",{"rules":[{"action":"allow","features":{"is_demo_user":true}}],"value":"--demo"},{"rules":[{"action":"allow","features":{"has_custom_resolution":true}}],"value":["--width","${resolution_width}","--height","${resolution_height}"]}],"jvm":[{"rules":[{"action":"allow","os":{"name":"osx"}}],"value":["-XstartOnFirstThread"]},{"rules":[{"action":"allow","os":{"name":"windows"}}],"value":"-XX:HeapDumpPath=MojangTricksIntelDriversForPerformance_javaw.exe_minecraft.exe.heapdump"},{"rules":[{"action":"allow","os":{"name":"windows","version":"^10\\."}}],"value":["-Dos.name=Windows
     * 10","-Dos.version=10.0"]},{"rules":[{"action":"allow","os":{"arch":"x86"}}],"value":"-Xss1M"},"-Djava.library.path=${natives_directory}","-Dminecraft.launcher.brand=${launcher_name}","-Dminecraft.launcher.version=${launcher_version}","-cp","${classpath}"]}
     * assetIndex :
     * {"id":"1.15","sha1":"62745391056f1d0e1ffc808a0d4f0145b668ca24","size":234874,"totalSize":216306372,"url":"https://launchermeta.mojang.com/v1/packages/62745391056f1d0e1ffc808a0d4f0145b668ca24/1.15.json"}
     * assets : 1.15 downloads :
     * {"client":{"sha1":"8b11614bea9293592a947ea8f4fd72981ea66677","size":15518397,"url":"https://launcher.mojang.com/v1/objects/8b11614bea9293592a947ea8f4fd72981ea66677/client.jar"},"client_mappings":{"sha1":"cc77cb804c2cc0fa151b0745df4c5de8e64d1bb5","size":4962913,"url":"https://launcher.mojang.com/v1/objects/cc77cb804c2cc0fa151b0745df4c5de8e64d1bb5/client.txt"},"server":{"sha1":"4d1826eebac84847c71a77f9349cc22afd0cf0a1","size":36164938,"url":"https://launcher.mojang.com/v1/objects/4d1826eebac84847c71a77f9349cc22afd0cf0a1/server.jar"},"server_mappings":{"sha1":"47f8a03f5492223753f5f2b531d4938813903684","size":3730004,"url":"https://launcher.mojang.com/v1/objects/47f8a03f5492223753f5f2b531d4938813903684/server.txt"}}
     * id : 1.15.1 libraries :
     * [{"downloads":{"artifact":{"path":"com/mojang/patchy/1.1/patchy-1.1.jar","sha1":"aef610b34a1be37fa851825f12372b78424d8903","size":15817,"url":"https://libraries.minecraft.net/com/mojang/patchy/1.1/patchy-1.1.jar"}},"name":"com.mojang:patchy:1.1"},{"downloads":{"artifact":{"path":"oshi-project/oshi-core/1.1/oshi-core-1.1.jar","sha1":"9ddf7b048a8d701be231c0f4f95fd986198fd2d8","size":30973,"url":"https://libraries.minecraft.net/oshi-project/oshi-core/1.1/oshi-core-1.1.jar"}},"name":"oshi-project:oshi-core:1.1"},{"downloads":{"artifact":{"path":"net/java/dev/jna/jna/4.4.0/jna-4.4.0.jar","sha1":"cb208278274bf12ebdb56c61bd7407e6f774d65a","size":1091208,"url":"https://libraries.minecraft.net/net/java/dev/jna/jna/4.4.0/jna-4.4.0.jar"}},"name":"net.java.dev.jna:jna:4.4.0"},{"downloads":{"artifact":{"path":"net/java/dev/jna/platform/3.4.0/platform-3.4.0.jar","sha1":"e3f70017be8100d3d6923f50b3d2ee17714e9c13","size":913436,"url":"https://libraries.minecraft.net/net/java/dev/jna/platform/3.4.0/platform-3.4.0.jar"}},"name":"net.java.dev.jna:platform:3.4.0"},{"downloads":{"artifact":{"path":"com/ibm/icu/icu4j-core-mojang/51.2/icu4j-core-mojang-51.2.jar","sha1":"63d216a9311cca6be337c1e458e587f99d382b84","size":1634692,"url":"https://libraries.minecraft.net/com/ibm/icu/icu4j-core-mojang/51.2/icu4j-core-mojang-51.2.jar"}},"name":"com.ibm.icu:icu4j-core-mojang:51.2"},{"downloads":{"artifact":{"path":"com/mojang/javabridge/1.0.22/javabridge-1.0.22.jar","sha1":"6aa6453aa99a52a5cd91749da1af6ab70e082ab3","size":5111,"url":"https://libraries.minecraft.net/com/mojang/javabridge/1.0.22/javabridge-1.0.22.jar"}},"name":"com.mojang:javabridge:1.0.22"},{"downloads":{"artifact":{"path":"net/sf/jopt-simple/jopt-simple/5.0.3/jopt-simple-5.0.3.jar","sha1":"cdd846cfc4e0f7eefafc02c0f5dce32b9303aa2a","size":78175,"url":"https://libraries.minecraft.net/net/sf/jopt-simple/jopt-simple/5.0.3/jopt-simple-5.0.3.jar"}},"name":"net.sf.jopt-simple:jopt-simple:5.0.3"},{"downloads":{"artifact":{"path":"io/netty/netty-all/4.1.25.Final/netty-all-4.1.25.Final.jar","sha1":"d0626cd3108294d1d58c05859add27b4ef21f83b","size":3823147,"url":"https://libraries.minecraft.net/io/netty/netty-all/4.1.25.Final/netty-all-4.1.25.Final.jar"}},"name":"io.netty:netty-all:4.1.25.Final"},{"downloads":{"artifact":{"path":"com/google/guava/guava/21.0/guava-21.0.jar","sha1":"3a3d111be1be1b745edfa7d91678a12d7ed38709","size":2521113,"url":"https://libraries.minecraft.net/com/google/guava/guava/21.0/guava-21.0.jar"}},"name":"com.google.guava:guava:21.0"},{"downloads":{"artifact":{"path":"org/apache/commons/commons-lang3/3.5/commons-lang3-3.5.jar","sha1":"6c6c702c89bfff3cd9e80b04d668c5e190d588c6","size":479881,"url":"https://libraries.minecraft.net/org/apache/commons/commons-lang3/3.5/commons-lang3-3.5.jar"}},"name":"org.apache.commons:commons-lang3:3.5"},{"downloads":{"artifact":{"path":"commons-io/commons-io/2.5/commons-io-2.5.jar","sha1":"2852e6e05fbb95076fc091f6d1780f1f8fe35e0f","size":208700,"url":"https://libraries.minecraft.net/commons-io/commons-io/2.5/commons-io-2.5.jar"}},"name":"commons-io:commons-io:2.5"},{"downloads":{"artifact":{"path":"commons-codec/commons-codec/1.10/commons-codec-1.10.jar","sha1":"4b95f4897fa13f2cd904aee711aeafc0c5295cd8","size":284184,"url":"https://libraries.minecraft.net/commons-codec/commons-codec/1.10/commons-codec-1.10.jar"}},"name":"commons-codec:commons-codec:1.10"},{"downloads":{"artifact":{"path":"net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar","sha1":"39c7796b469a600f72380316f6b1f11db6c2c7c4","size":208338,"url":"https://libraries.minecraft.net/net/java/jinput/jinput/2.0.5/jinput-2.0.5.jar"}},"name":"net.java.jinput:jinput:2.0.5"},{"downloads":{"artifact":{"path":"net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar","sha1":"e12fe1fda814bd348c1579329c86943d2cd3c6a6","size":7508,"url":"https://libraries.minecraft.net/net/java/jutils/jutils/1.0.0/jutils-1.0.0.jar"}},"name":"net.java.jutils:jutils:1.0.0"},{"downloads":{"artifact":{"path":"com/mojang/brigadier/1.0.17/brigadier-1.0.17.jar","sha1":"c6b7dc51dd44379cc751b7504816006e9be4b1e6","size":77392,"url":"https://libraries.minecraft.net/com/mojang/brigadier/1.0.17/brigadier-1.0.17.jar"}},"name":"com.mojang:brigadier:1.0.17"},{"downloads":{"artifact":{"path":"com/mojang/datafixerupper/2.0.24/datafixerupper-2.0.24.jar","sha1":"0944c24a8519981847ffb36c6dcd059d96fcb4b0","size":436066,"url":"https://libraries.minecraft.net/com/mojang/datafixerupper/2.0.24/datafixerupper-2.0.24.jar"}},"name":"com.mojang:datafixerupper:2.0.24"},{"downloads":{"artifact":{"path":"com/google/code/gson/gson/2.8.0/gson-2.8.0.jar","sha1":"c4ba5371a29ac9b2ad6129b1d39ea38750043eff","size":231952,"url":"https://libraries.minecraft.net/com/google/code/gson/gson/2.8.0/gson-2.8.0.jar"}},"name":"com.google.code.gson:gson:2.8.0"},{"downloads":{"artifact":{"path":"com/mojang/authlib/1.5.25/authlib-1.5.25.jar","sha1":"9834cdf236c22e84b946bba989e2f94ef5897c3c","size":65621,"url":"https://libraries.minecraft.net/com/mojang/authlib/1.5.25/authlib-1.5.25.jar"}},"name":"com.mojang:authlib:1.5.25"},{"downloads":{"artifact":{"path":"org/apache/commons/commons-compress/1.8.1/commons-compress-1.8.1.jar","sha1":"a698750c16740fd5b3871425f4cb3bbaa87f529d","size":365552,"url":"https://libraries.minecraft.net/org/apache/commons/commons-compress/1.8.1/commons-compress-1.8.1.jar"}},"name":"org.apache.commons:commons-compress:1.8.1"},{"downloads":{"artifact":{"path":"org/apache/httpcomponents/httpclient/4.3.3/httpclient-4.3.3.jar","sha1":"18f4247ff4572a074444572cee34647c43e7c9c7","size":589512,"url":"https://libraries.minecraft.net/org/apache/httpcomponents/httpclient/4.3.3/httpclient-4.3.3.jar"}},"name":"org.apache.httpcomponents:httpclient:4.3.3"},{"downloads":{"artifact":{"path":"commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar","sha1":"f6f66e966c70a83ffbdb6f17a0919eaf7c8aca7f","size":62050,"url":"https://libraries.minecraft.net/commons-logging/commons-logging/1.1.3/commons-logging-1.1.3.jar"}},"name":"commons-logging:commons-logging:1.1.3"},{"downloads":{"artifact":{"path":"org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar","sha1":"31fbbff1ddbf98f3aa7377c94d33b0447c646b6e","size":282269,"url":"https://libraries.minecraft.net/org/apache/httpcomponents/httpcore/4.3.2/httpcore-4.3.2.jar"}},"name":"org.apache.httpcomponents:httpcore:4.3.2"},{"downloads":{"artifact":{"path":"it/unimi/dsi/fastutil/8.2.1/fastutil-8.2.1.jar","sha1":"5ad88f325e424f8dbc2be5459e21ea5cab3864e9","size":18800417,"url":"https://libraries.minecraft.net/it/unimi/dsi/fastutil/8.2.1/fastutil-8.2.1.jar"}},"name":"it.unimi.dsi:fastutil:8.2.1"},{"downloads":{"artifact":{"path":"org/apache/logging/log4j/log4j-api/2.8.1/log4j-api-2.8.1.jar","sha1":"e801d13612e22cad62a3f4f3fe7fdbe6334a8e72","size":228859,"url":"https://libraries.minecraft.net/org/apache/logging/log4j/log4j-api/2.8.1/log4j-api-2.8.1.jar"}},"name":"org.apache.logging.log4j:log4j-api:2.8.1"},{"downloads":{"artifact":{"path":"org/apache/logging/log4j/log4j-core/2.8.1/log4j-core-2.8.1.jar","sha1":"4ac28ff2f1ddf05dae3043a190451e8c46b73c31","size":1402925,"url":"https://libraries.minecraft.net/org/apache/logging/log4j/log4j-core/2.8.1/log4j-core-2.8.1.jar"}},"name":"org.apache.logging.log4j:log4j-core:2.8.1"},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1.jar","sha1":"2bb514e444994c6fece99a21f76e0c90438e377f","size":317748,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1.jar"}},"name":"org.lwjgl:lwjgl:3.2.1","rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2.jar","sha1":"8ad6294407e15780b43e84929c40e4c5e997972e","size":321900,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2.jar"}},"name":"org.lwjgl:lwjgl:3.2.2","rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1.jar","sha1":"7a0c583fcbec32b15784f846df536e1837d83666","size":38616,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1.jar"}},"name":"org.lwjgl:lwjgl-jemalloc:3.2.1","rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2.jar","sha1":"ee8e57a79300f78294576d87c4a587f8c99402e2","size":34848,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2.jar"}},"name":"org.lwjgl:lwjgl-jemalloc:3.2.2","rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1.jar","sha1":"dc7ff2dabb40a141ee9bf2e326d9b1b19f3278fb","size":80103,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1.jar"}},"name":"org.lwjgl:lwjgl-openal:3.2.1","rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2.jar","sha1":"2b772a102b0a11ee5f2109a5b136f4dc7c630827","size":80012,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2.jar"}},"name":"org.lwjgl:lwjgl-openal:3.2.2","rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1.jar","sha1":"57008c2374c5bc434b18adfef3f3653ee450ee18","size":931322,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1.jar"}},"name":"org.lwjgl:lwjgl-opengl:3.2.1","rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2.jar","sha1":"6ac5bb88b44c43ea195a570aab059f63da004cd8","size":929780,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2.jar"}},"name":"org.lwjgl:lwjgl-opengl:3.2.2","rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1.jar","sha1":"027abb7f64894b61cad163791acd8113f0b21296","size":116708,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1.jar"}},"name":"org.lwjgl:lwjgl-glfw:3.2.1","rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2.jar","sha1":"d3ad4df38e400b8afba1de63f84338809399df5b","size":108907,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2.jar"}},"name":"org.lwjgl:lwjgl-glfw:3.2.2","rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1.jar","sha1":"31f5eb5fce3791d58ec898bc5c1867d76d781ba1","size":105765,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1.jar"}},"name":"org.lwjgl:lwjgl-stb:3.2.1","rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2.jar","sha1":"3b8e6ebc5851dd3d17e37e5cadce2eff2a429f0f","size":104469,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2.jar"}},"name":"org.lwjgl:lwjgl-stb:3.2.2","rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1.jar","sha1":"259f1dbddb921e27e01b32458d6f584eb8bba13a","size":7088,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1.jar"}},"name":"org.lwjgl:lwjgl-tinyfd:3.2.1","rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2.jar","sha1":"fcbe606c8f8da6f8f9a05e2c540eb1ee8632b0e9","size":7092,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2.jar"}},"name":"org.lwjgl:lwjgl-tinyfd:3.2.2","rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1.jar","sha1":"2bb514e444994c6fece99a21f76e0c90438e377f","size":317748,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1.jar"},"classifiers":{"javadoc":{"path":"org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-javadoc.jar","sha1":"1f6b7050737559b775d797c0ea56612b8e373fd6","size":1287174,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-javadoc.jar"},"natives-linux":{"path":"org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-natives-linux.jar","sha1":"9bdd47cd63ce102cec837a396c8ded597cb75a66","size":87484,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-natives-macos.jar","sha1":"5a4c271d150906858d475603dcb9479453c60555","size":39835,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-natives-windows.jar","sha1":"e799d06b8969db0610e68776e0eff4b6191098bd","size":255871,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-natives-windows.jar"},"sources":{"path":"org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-sources.jar","sha1":"106f90ac41449004a969309488aa6e3a2f7d6731","size":255671,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.1/lwjgl-3.2.1-sources.jar"}}},"name":"org.lwjgl:lwjgl:3.2.1","natives":{"osx":"natives-macos"},"rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2.jar","sha1":"8ad6294407e15780b43e84929c40e4c5e997972e","size":321900,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2.jar"},"classifiers":{"natives-linux":{"path":"org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2-natives-linux.jar","sha1":"ae7976827ca2a3741f6b9a843a89bacd637af350","size":124776,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2-natives-macos.jar","sha1":"bbfb75693bdb714c0c69c2c9f9be73d259b43b62","size":48462,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2-natives-windows.jar","sha1":"05359f3aa50d36352815fc662ea73e1c00d22170","size":279593,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl/3.2.2/lwjgl-3.2.2-natives-windows.jar"}}},"name":"org.lwjgl:lwjgl:3.2.2","natives":{"linux":"natives-linux","windows":"natives-windows"},"rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1.jar","sha1":"7a0c583fcbec32b15784f846df536e1837d83666","size":38616,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1.jar"},"classifiers":{"javadoc":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-javadoc.jar","sha1":"04f6897be1e2d68bff5ec5e91a2b96e32f084c09","size":461041,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-javadoc.jar"},"natives-linux":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-natives-linux.jar","sha1":"5536616b558cea2fea6330ca682fd7c733db9c43","size":156057,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-natives-macos.jar","sha1":"439ab9d0264167a949cc7bcce673704322baaf50","size":117001,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-natives-windows.jar","sha1":"3c869b3d7638c800b7039cd859d064658643ad6e","size":218136,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-natives-windows.jar"},"sources":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-sources.jar","sha1":"4450dca46228c02c51bb9bbda70e7cfc3154296d","size":31279,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.1/lwjgl-jemalloc-3.2.1-sources.jar"}}},"name":"org.lwjgl:lwjgl-jemalloc:3.2.1","natives":{"osx":"natives-macos"},"rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2.jar","sha1":"ee8e57a79300f78294576d87c4a587f8c99402e2","size":34848,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2.jar"},"classifiers":{"natives-linux":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2-natives-linux.jar","sha1":"268c08a150347e04e44ba56e359d62c9b78669df","size":156173,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2-natives-macos.jar","sha1":"805f5a10465375ba034b27b72331912fd2846690","size":117127,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2-natives-windows.jar","sha1":"338b25b99da3ba5f441f6492f2ce2a9c608860ed","size":220623,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-jemalloc/3.2.2/lwjgl-jemalloc-3.2.2-natives-windows.jar"}}},"name":"org.lwjgl:lwjgl-jemalloc:3.2.2","natives":{"linux":"natives-linux","windows":"natives-windows"},"rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1.jar","sha1":"dc7ff2dabb40a141ee9bf2e326d9b1b19f3278fb","size":80103,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1.jar"},"classifiers":{"javadoc":{"path":"org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-javadoc.jar","sha1":"95752f443686da1b3443e397dc83e730e1907a1e","size":617869,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-javadoc.jar"},"natives-linux":{"path":"org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-natives-linux.jar","sha1":"bcd4be67863dd908f696f628c3ca9f6eb9ae5152","size":590716,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-natives-macos.jar","sha1":"9357ebfc82a0d6f64e17093dd963219367cd6fa2","size":528004,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-natives-windows.jar","sha1":"92fb931e65c637cea209ad5c3ffebd1b325ed41d","size":1310083,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-natives-windows.jar"},"sources":{"path":"org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-sources.jar","sha1":"8fe3d6e6353685164b1eb3a22980aaa1115d4a32","size":78379,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.1/lwjgl-openal-3.2.1-sources.jar"}}},"name":"org.lwjgl:lwjgl-openal:3.2.1","natives":{"osx":"natives-macos"},"rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2.jar","sha1":"2b772a102b0a11ee5f2109a5b136f4dc7c630827","size":80012,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2.jar"},"classifiers":{"natives-linux":{"path":"org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2-natives-linux.jar","sha1":"0364f9f5c3947393083ab5f37a571f5603aadd0b","size":590997,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2-natives-macos.jar","sha1":"a97b6345d5a9ddf889e262bd7ad8eed43b1bb063","size":528006,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2-natives-windows.jar","sha1":"ec20a7d42a2438528fca87e60b1705f1e2339ddb","size":1310102,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-openal/3.2.2/lwjgl-openal-3.2.2-natives-windows.jar"}}},"name":"org.lwjgl:lwjgl-openal:3.2.2","natives":{"linux":"natives-linux","windows":"natives-windows"},"rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1.jar","sha1":"57008c2374c5bc434b18adfef3f3653ee450ee18","size":931322,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1.jar"},"classifiers":{"javadoc":{"path":"org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-javadoc.jar","sha1":"e25fc8cbcbee68182a6b7f13ad71b1f0961005ad","size":4307561,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-javadoc.jar"},"natives-linux":{"path":"org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-natives-linux.jar","sha1":"c43bb08ed7dcf1a6d344803e464148b3b14dd274","size":77401,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-natives-macos.jar","sha1":"dca9ad9e59a87172144d531e08ef7f6988073db0","size":38998,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-natives-windows.jar","sha1":"80954961b31084d7b4f2f041d6b5a799a774c880","size":170804,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-natives-windows.jar"},"sources":{"path":"org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-sources.jar","sha1":"47930ffbef53c0f45c7e35c01b1c6ad5b2205809","size":1251582,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.1/lwjgl-opengl-3.2.1-sources.jar"}}},"name":"org.lwjgl:lwjgl-opengl:3.2.1","natives":{"osx":"natives-macos"},"rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2.jar","sha1":"6ac5bb88b44c43ea195a570aab059f63da004cd8","size":929780,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2.jar"},"classifiers":{"natives-linux":{"path":"org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2-natives-linux.jar","sha1":"338d33387919cb3f4cdba143c2b738a71ccfda60","size":77392,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2-natives-macos.jar","sha1":"cf4f43e69ee70d8ebfbb6ba93dec9016339e4fdc","size":38989,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2-natives-windows.jar","sha1":"d8dcdc91066cae2d2d8279cb4a9f9f05d9525826","size":170798,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-opengl/3.2.2/lwjgl-opengl-3.2.2-natives-windows.jar"}}},"name":"org.lwjgl:lwjgl-opengl:3.2.2","natives":{"linux":"natives-linux","windows":"natives-windows"},"rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1.jar","sha1":"027abb7f64894b61cad163791acd8113f0b21296","size":116708,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1.jar"},"classifiers":{"javadoc":{"path":"org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-javadoc.jar","sha1":"81482a14b617e4fb0c7de69b3e06ef2e28ef894f","size":690774,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-javadoc.jar"},"natives-linux":{"path":"org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-natives-linux.jar","sha1":"5a2fb27f9e12a34ecabf6f6a7606c61849e347ee","size":157431,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-natives-macos.jar","sha1":"72fe6dab6110a5a1cd4833f11840eef7b2eadce5","size":64724,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-natives-windows.jar","sha1":"00def7c58ad2e1cb258d6d73be181ffab8ef8bd5","size":265304,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-natives-windows.jar"},"sources":{"path":"org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-sources.jar","sha1":"4c56ae817da75996b19601c87d7e759b846c3902","size":101885,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.1/lwjgl-glfw-3.2.1-sources.jar"}}},"name":"org.lwjgl:lwjgl-glfw:3.2.1","natives":{"osx":"natives-macos"},"rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2.jar","sha1":"d3ad4df38e400b8afba1de63f84338809399df5b","size":108907,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2.jar"},"classifiers":{"natives-linux":{"path":"org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2-natives-linux.jar","sha1":"0957733f26a6661d4883da0335f7ef46d3bbbd7d","size":159198,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2-natives-macos.jar","sha1":"98f745038d17ac3192fcd01dc44126b03ec1570d","size":67311,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2-natives-windows.jar","sha1":"dc6826d636bf796b33a49038c354210e661bfc17","size":266648,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-glfw/3.2.2/lwjgl-glfw-3.2.2-natives-windows.jar"}}},"name":"org.lwjgl:lwjgl-glfw:3.2.2","natives":{"linux":"natives-linux","windows":"natives-windows"},"rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1.jar","sha1":"31f5eb5fce3791d58ec898bc5c1867d76d781ba1","size":105765,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1.jar"},"classifiers":{"javadoc":{"path":"org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-javadoc.jar","sha1":"524d79537f840d6cfe50e030d24413933f0d464b","size":684972,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-javadoc.jar"},"natives-linux":{"path":"org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-natives-linux.jar","sha1":"66e01b8036258619332cb452b970ca0a52db1a87","size":197208,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-natives-macos.jar","sha1":"1f5615c952451c30afafba4a6e3ba4e1cd9e7f5c","size":192364,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-natives-windows.jar","sha1":"d100bfd2b0d03223a043cfcb64a2dfd2bb7f4c61","size":454473,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-natives-windows.jar"},"sources":{"path":"org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-sources.jar","sha1":"50ac43d4c6ea5846f354f9576134c0f9264345c2","size":96479,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.1/lwjgl-stb-3.2.1-sources.jar"}}},"name":"org.lwjgl:lwjgl-stb:3.2.1","natives":{"osx":"natives-macos"},"rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2.jar","sha1":"fcbe606c8f8da6f8f9a05e2c540eb1ee8632b0e9","size":7092,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2.jar"},"classifiers":{"javadoc":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-javadoc.jar","sha1":"ba657a222ee267b75fa81ae5ab29ae29b50f725f","size":368913,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-javadoc.jar"},"natives-linux":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-natives-linux.jar","sha1":"39e35b161c130635d9c8918ce04e887a30c5b687","size":38804,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-natives-macos.jar","sha1":"46d0798228b8a28e857a2a0f02310fd6ba2a4eab","size":42136,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-natives-windows.jar","sha1":"e9115958773644e863332a6a06488d26f9e1fc9f","size":208314,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-natives-windows.jar"},"sources":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-sources.jar","sha1":"2fe76dcf2ca02ae0e64ac7c69eb251c09df0e922","size":5034,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.2/lwjgl-tinyfd-3.2.2-sources.jar"}}},"name":"org.lwjgl:lwjgl-tinyfd:3.2.2","natives":{"linux":"natives-linux","windows":"natives-windows"},"rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1.jar","sha1":"259f1dbddb921e27e01b32458d6f584eb8bba13a","size":7088,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1.jar"},"classifiers":{"javadoc":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-javadoc.jar","sha1":"0a85d995178cdab6b94d9a172dd9e7d2a0d70cfb","size":368913,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-javadoc.jar"},"natives-linux":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-natives-linux.jar","sha1":"4ad49108397322596d7b85c2c687e5de6ee52157","size":38192,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-natives-macos.jar","sha1":"759c2fd9cc5c6ce0b5b7af77ac8200483b7fb660","size":41962,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-natives-windows.jar","sha1":"85750d2ca022852e15f58c0b94b3d1d4e7f0ba52","size":207577,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-natives-windows.jar"},"sources":{"path":"org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-sources.jar","sha1":"c375699fd794c4c87d935e0f9a84e7d80d0de77e","size":5034,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-tinyfd/3.2.1/lwjgl-tinyfd-3.2.1-sources.jar"}}},"name":"org.lwjgl:lwjgl-tinyfd:3.2.1","natives":{"osx":"natives-macos"},"rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2.jar","sha1":"3b8e6ebc5851dd3d17e37e5cadce2eff2a429f0f","size":104469,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2.jar"},"classifiers":{"natives-linux":{"path":"org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2-natives-linux.jar","sha1":"172c52e586fecf43f759bc4f70a778c01f6fdcc1","size":203476,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2-natives-linux.jar"},"natives-macos":{"path":"org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2-natives-macos.jar","sha1":"ee059b129b09fdecbd8595273926ae930bf5a5d7","size":196796,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2-natives-macos.jar"},"natives-windows":{"path":"org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2-natives-windows.jar","sha1":"811f705cbb29e8ae8d60bdf8fdd38c0c123ad3ef","size":465810,"url":"https://libraries.minecraft.net/org/lwjgl/lwjgl-stb/3.2.2/lwjgl-stb-3.2.2-natives-windows.jar"}}},"name":"org.lwjgl:lwjgl-stb:3.2.2","natives":{"linux":"natives-linux","windows":"natives-windows"},"rules":[{"action":"allow"},{"action":"disallow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"com/mojang/text2speech/1.11.3/text2speech-1.11.3.jar","sha1":"f378f889797edd7df8d32272c06ca80a1b6b0f58","size":13164,"url":"https://libraries.minecraft.net/com/mojang/text2speech/1.11.3/text2speech-1.11.3.jar"}},"name":"com.mojang:text2speech:1.11.3"},{"downloads":{"artifact":{"path":"com/mojang/text2speech/1.11.3/text2speech-1.11.3.jar","sha1":"f378f889797edd7df8d32272c06ca80a1b6b0f58","size":13164,"url":"https://libraries.minecraft.net/com/mojang/text2speech/1.11.3/text2speech-1.11.3.jar"},"classifiers":{"natives-linux":{"path":"com/mojang/text2speech/1.11.3/text2speech-1.11.3-natives-linux.jar","sha1":"ac641755a2a841d1fca9e660194f42523ee5cfe0","size":7833,"url":"https://libraries.minecraft.net/com/mojang/text2speech/1.11.3/text2speech-1.11.3-natives-linux.jar"},"natives-windows":{"path":"com/mojang/text2speech/1.11.3/text2speech-1.11.3-natives-windows.jar","sha1":"c0b242c0091be5acbf303263c7eeeaedd70544c7","size":81379,"url":"https://libraries.minecraft.net/com/mojang/text2speech/1.11.3/text2speech-1.11.3-natives-windows.jar"},"sources":{"path":"com/mojang/text2speech/1.11.3/text2speech-1.11.3-sources.jar","sha1":"772a37dd77417571e6f119a8d306f0c14c2ee410","size":5332,"url":"https://libraries.minecraft.net/com/mojang/text2speech/1.11.3/text2speech-1.11.3-sources.jar"}}},"extract":{"exclude":["META-INF/"]},"name":"com.mojang:text2speech:1.11.3","natives":{"linux":"natives-linux","windows":"natives-windows"}},{"downloads":{"artifact":{"path":"ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0.jar","sha1":"6ef160c3133a78de015830860197602ca1c855d3","size":40502,"url":"https://libraries.minecraft.net/ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0.jar"},"classifiers":{"javadoc":{"path":"ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0-javadoc.jar","sha1":"fb0092f22cb4fe8e631452f577b7a238778abf2a","size":174060,"url":"https://libraries.minecraft.net/ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0-javadoc.jar"},"natives-osx":{"path":"ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0-natives-osx.jar","sha1":"08befab4894d55875f33c3d300f4f71e6e828f64","size":5629,"url":"https://libraries.minecraft.net/ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0-natives-osx.jar"},"sources":{"path":"ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0-sources.jar","sha1":"865837a198189aee737019561ece842827f24278","size":43283,"url":"https://libraries.minecraft.net/ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0-sources.jar"}}},"extract":{"exclude":["META-INF/"]},"name":"ca.weblite:java-objc-bridge:1.0.0","natives":{"osx":"natives-osx"},"rules":[{"action":"allow","os":{"name":"osx"}}]},{"downloads":{"artifact":{"path":"ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0.jar","sha1":"6ef160c3133a78de015830860197602ca1c855d3","size":40502,"url":"https://libraries.minecraft.net/ca/weblite/java-objc-bridge/1.0.0/java-objc-bridge-1.0.0.jar"}},"name":"ca.weblite:java-objc-bridge:1.0.0","rules":[{"action":"allow","os":{"name":"osx"}}]}]
     * logging :
     * {"client":{"argument":"-Dlog4j.configurationFile=${path}","file":{"id":"client-1.12.xml","sha1":"ef4f57b922df243d0cef096efe808c72db042149","size":877,"url":"https://launcher.mojang.com/v1/objects/ef4f57b922df243d0cef096efe808c72db042149/client-1.12.xml"},"type":"log4j2-xml"}}
     * mainClass : net.minecraft.client.main.Main minimumLauncherVersion : 21 releaseTime :
     * 2019-12-16T10:29:47+00:00 time : 2019-12-16T10:29:47+00:00 type : release
     */
    private ArgumentsBean arguments;

    private AssetIndexBean assetIndex;

    private String assets;

    private DownloadsBean downloads;

    private String id;

    private LoggingBean logging;

    private String mainClass;

    private int minimumLauncherVersion;

    private String releaseTime;

    private String time;

    private String type;

    private List<LibrariesBean> libraries;

    public ArgumentsBean getArguments() {
        return arguments;
    }

    public void setArguments(ArgumentsBean arguments) {
        this.arguments = arguments;
    }

    public AssetIndexBean getAssetIndex() {
        return assetIndex;
    }

    public void setAssetIndex(AssetIndexBean assetIndex) {
        this.assetIndex = assetIndex;
    }

    public String getAssets() {
        return assets;
    }

    public void setAssets(String assets) {
        this.assets = assets;
    }

    public DownloadsBean getDownloads() {
        return downloads;
    }

    public void setDownloads(DownloadsBean downloads) {
        this.downloads = downloads;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LoggingBean getLogging() {
        return logging;
    }

    public void setLogging(LoggingBean logging) {
        this.logging = logging;
    }

    public String getMainClass() {
        return mainClass;
    }

    public void setMainClass(String mainClass) {
        this.mainClass = mainClass;
    }

    public int getMinimumLauncherVersion() {
        return minimumLauncherVersion;
    }

    public void setMinimumLauncherVersion(int minimumLauncherVersion) {
        this.minimumLauncherVersion = minimumLauncherVersion;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<LibrariesBean> getLibraries() {
        return libraries;
    }

    public void setLibraries(List<LibrariesBean> libraries) {
        this.libraries = libraries;
    }

    public static class ArgumentsBean {
        private List<String> game;

        private List<JvmBean> jvm;

        public List<String> getGame() {
            return game;
        }

        public void setGame(List<String> game) {
            this.game = game;
        }

        public List<JvmBean> getJvm() {
            return jvm;
        }

        public void setJvm(List<JvmBean> jvm) {
            this.jvm = jvm;
        }

        public static class JvmBean {
            private List<RulesBean> rules;

            private List<String> value;

            public List<RulesBean> getRules() {
                return rules;
            }

            public void setRules(List<RulesBean> rules) {
                this.rules = rules;
            }

            public List<String> getValue() {
                return value;
            }

            public void setValue(List<String> value) {
                this.value = value;
            }

            public static class RulesBean {
                /**
                 * action : allow os : {"name":"osx"}
                 */
                private String action;

                private OsBean os;

                public String getAction() {
                    return action;
                }

                public void setAction(String action) {
                    this.action = action;
                }

                public OsBean getOs() {
                    return os;
                }

                public void setOs(OsBean os) {
                    this.os = os;
                }

                public static class OsBean {
                    /**
                     * name : osx
                     */
                    private String name;

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }

                }

            }

        }

    }

    public static class AssetIndexBean {
        /**
         * id : 1.15 sha1 : 62745391056f1d0e1ffc808a0d4f0145b668ca24 size : 234874 totalSize : 216306372
         * url :
         * https://launchermeta.mojang.com/v1/packages/62745391056f1d0e1ffc808a0d4f0145b668ca24/1.15.json
         */
        private String id;

        private String sha1;

        private int size;

        private int totalSize;

        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getSha1() {
            return sha1;
        }

        public void setSha1(String sha1) {
            this.sha1 = sha1;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getTotalSize() {
            return totalSize;
        }

        public void setTotalSize(int totalSize) {
            this.totalSize = totalSize;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

    }

    public static class DownloadsBean {
        /**
         * client :
         * {"sha1":"8b11614bea9293592a947ea8f4fd72981ea66677","size":15518397,"url":"https://launcher.mojang.com/v1/objects/8b11614bea9293592a947ea8f4fd72981ea66677/client.jar"}
         * client_mappings :
         * {"sha1":"cc77cb804c2cc0fa151b0745df4c5de8e64d1bb5","size":4962913,"url":"https://launcher.mojang.com/v1/objects/cc77cb804c2cc0fa151b0745df4c5de8e64d1bb5/client.txt"}
         * server :
         * {"sha1":"4d1826eebac84847c71a77f9349cc22afd0cf0a1","size":36164938,"url":"https://launcher.mojang.com/v1/objects/4d1826eebac84847c71a77f9349cc22afd0cf0a1/server.jar"}
         * server_mappings :
         * {"sha1":"47f8a03f5492223753f5f2b531d4938813903684","size":3730004,"url":"https://launcher.mojang.com/v1/objects/47f8a03f5492223753f5f2b531d4938813903684/server.txt"}
         */
        private ClientBean client;

        private ClientMappingsBean client_mappings;

        private ServerBean server;

        private ServerMappingsBean server_mappings;

        public ClientBean getClient() {
            return client;
        }

        public void setClient(ClientBean client) {
            this.client = client;
        }

        public ClientMappingsBean getClient_mappings() {
            return client_mappings;
        }

        public void setClient_mappings(ClientMappingsBean client_mappings) {
            this.client_mappings = client_mappings;
        }

        public ServerBean getServer() {
            return server;
        }

        public void setServer(ServerBean server) {
            this.server = server;
        }

        public ServerMappingsBean getServer_mappings() {
            return server_mappings;
        }

        public void setServer_mappings(ServerMappingsBean server_mappings) {
            this.server_mappings = server_mappings;
        }

        public static class ClientBean {
            /**
             * sha1 : 8b11614bea9293592a947ea8f4fd72981ea66677 size : 15518397 url :
             * https://launcher.mojang.com/v1/objects/8b11614bea9293592a947ea8f4fd72981ea66677/client.jar
             */
            private String sha1;

            private int size;

            private String url;

            public String getSha1() {
                return sha1;
            }

            public void setSha1(String sha1) {
                this.sha1 = sha1;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

        public static class ClientMappingsBean {
            /**
             * sha1 : cc77cb804c2cc0fa151b0745df4c5de8e64d1bb5 size : 4962913 url :
             * https://launcher.mojang.com/v1/objects/cc77cb804c2cc0fa151b0745df4c5de8e64d1bb5/client.txt
             */
            private String sha1;

            private int size;

            private String url;

            public String getSha1() {
                return sha1;
            }

            public void setSha1(String sha1) {
                this.sha1 = sha1;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

        public static class ServerBean {
            /**
             * sha1 : 4d1826eebac84847c71a77f9349cc22afd0cf0a1 size : 36164938 url :
             * https://launcher.mojang.com/v1/objects/4d1826eebac84847c71a77f9349cc22afd0cf0a1/server.jar
             */
            private String sha1;

            private int size;

            private String url;

            public String getSha1() {
                return sha1;
            }

            public void setSha1(String sha1) {
                this.sha1 = sha1;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

        public static class ServerMappingsBean {
            /**
             * sha1 : 47f8a03f5492223753f5f2b531d4938813903684 size : 3730004 url :
             * https://launcher.mojang.com/v1/objects/47f8a03f5492223753f5f2b531d4938813903684/server.txt
             */
            private String sha1;

            private int size;

            private String url;

            public String getSha1() {
                return sha1;
            }

            public void setSha1(String sha1) {
                this.sha1 = sha1;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

    }

    public static class LoggingBean {
        /**
         * client :
         * {"argument":"-Dlog4j.configurationFile=${path}","file":{"id":"client-1.12.xml","sha1":"ef4f57b922df243d0cef096efe808c72db042149","size":877,"url":"https://launcher.mojang.com/v1/objects/ef4f57b922df243d0cef096efe808c72db042149/client-1.12.xml"},"type":"log4j2-xml"}
         */
        private ClientBeanX client;

        public ClientBeanX getClient() {
            return client;
        }

        public void setClient(ClientBeanX client) {
            this.client = client;
        }

        public static class ClientBeanX {
            /**
             * argument : -Dlog4j.configurationFile=${path} file :
             * {"id":"client-1.12.xml","sha1":"ef4f57b922df243d0cef096efe808c72db042149","size":877,"url":"https://launcher.mojang.com/v1/objects/ef4f57b922df243d0cef096efe808c72db042149/client-1.12.xml"}
             * type : log4j2-xml
             */
            private String argument;

            private FileBean file;

            private String type;

            public String getArgument() {
                return argument;
            }

            public void setArgument(String argument) {
                this.argument = argument;
            }

            public FileBean getFile() {
                return file;
            }

            public void setFile(FileBean file) {
                this.file = file;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public static class FileBean {
                /**
                 * id : client-1.12.xml sha1 : ef4f57b922df243d0cef096efe808c72db042149 size : 877 url :
                 * https://launcher.mojang.com/v1/objects/ef4f57b922df243d0cef096efe808c72db042149/client-1.12.xml
                 */
                private String id;

                private String sha1;

                private int size;

                private String url;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getSha1() {
                    return sha1;
                }

                public void setSha1(String sha1) {
                    this.sha1 = sha1;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

            }

        }

    }

    public static class LibrariesBean {
        /**
         * downloads :
         * {"artifact":{"path":"com/mojang/patchy/1.1/patchy-1.1.jar","sha1":"aef610b34a1be37fa851825f12372b78424d8903","size":15817,"url":"https://libraries.minecraft.net/com/mojang/patchy/1.1/patchy-1.1.jar"}}
         * name : com.mojang:patchy:1.1 rules : [{"action":"allow","os":{"name":"osx"}}] natives :
         * {"osx":"natives-macos"} extract : {"exclude":["META-INF/"]}
         */
        private DownloadsBeanX downloads;

        private String name;

        private NativesBean natives;

        private ExtractBean extract;

        private List<RulesBeanX> rules;

        public DownloadsBeanX getDownloads() {
            return downloads;
        }

        public void setDownloads(DownloadsBeanX downloads) {
            this.downloads = downloads;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public NativesBean getNatives() {
            return natives;
        }

        public void setNatives(NativesBean natives) {
            this.natives = natives;
        }

        public ExtractBean getExtract() {
            return extract;
        }

        public void setExtract(ExtractBean extract) {
            this.extract = extract;
        }

        public List<RulesBeanX> getRules() {
            return rules;
        }

        public void setRules(List<RulesBeanX> rules) {
            this.rules = rules;
        }

        public static class DownloadsBeanX {
            /**
             * artifact :
             * {"path":"com/mojang/patchy/1.1/patchy-1.1.jar","sha1":"aef610b34a1be37fa851825f12372b78424d8903","size":15817,"url":"https://libraries.minecraft.net/com/mojang/patchy/1.1/patchy-1.1.jar"}
             */
            private ArtifactBean artifact;

            public ArtifactBean getArtifact() {
                return artifact;
            }

            public void setArtifact(ArtifactBean artifact) {
                this.artifact = artifact;
            }

            public static class ArtifactBean {
                /**
                 * path : com/mojang/patchy/1.1/patchy-1.1.jar sha1 :
                 * aef610b34a1be37fa851825f12372b78424d8903 size : 15817 url :
                 * https://libraries.minecraft.net/com/mojang/patchy/1.1/patchy-1.1.jar
                 */
                private String path;

                private String sha1;

                private int size;

                private String url;

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getSha1() {
                    return sha1;
                }

                public void setSha1(String sha1) {
                    this.sha1 = sha1;
                }

                public int getSize() {
                    return size;
                }

                public void setSize(int size) {
                    this.size = size;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

            }

        }

        public static class NativesBean {
            /**
             * osx : natives-macos
             */
            private String osx;

            public String getOsx() {
                return osx;
            }

            public void setOsx(String osx) {
                this.osx = osx;
            }

        }

        public static class ExtractBean {
            private List<String> exclude;

            public List<String> getExclude() {
                return exclude;
            }

            public void setExclude(List<String> exclude) {
                this.exclude = exclude;
            }

        }

        public static class RulesBeanX {
            /**
             * action : allow os : {"name":"osx"}
             */
            private String action;

            private OsBeanX os;

            public String getAction() {
                return action;
            }

            public void setAction(String action) {
                this.action = action;
            }

            public OsBeanX getOs() {
                return os;
            }

            public void setOs(OsBeanX os) {
                this.os = os;
            }

            public static class OsBeanX {
                /**
                 * name : osx
                 */
                private String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

            }

        }

    }

}