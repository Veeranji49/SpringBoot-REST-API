package com.example.Service;

import com.example.Entity.DiskInfo;
import com.example.Entity.JvmInfo;
import com.example.Entity.MemoryInfo;
import com.example.Entity.SystemInfo;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Service
public class SystemInfoService {

    public SystemInfo getSystemInfo() {
        return populateSystemInfo();
    }

    private SystemInfo populateSystemInfo() {
        SystemInfo systemInfo = new SystemInfo();

        try {

            // CPU usage percentage
            com.sun.management.OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
            double cpuUsagePercentage = osBean.getProcessCpuLoad() * 100;
            systemInfo.setCpuUsagePercentage(cpuUsagePercentage);

            // Get IP Address and Hostname
            InetAddress localHost = InetAddress.getLocalHost();
            systemInfo.setIpAddress(localHost.getHostAddress());
            systemInfo.setHostname(localHost.getHostName());

            // Get Memory Information
            Runtime runtime = Runtime.getRuntime();
            long totalMemory = runtime.totalMemory();
            long freeMemory = runtime.freeMemory();
            double totalMemoryGB = totalMemory / (1024.0 * 1024.0 * 1024.0);
            double usedMemoryPercentage = ((double)(totalMemory - freeMemory) / totalMemory) * 100;
            String operatingSystem = null;
            MemoryInfo memoryInfo = new MemoryInfo(totalMemoryGB, usedMemoryPercentage,operatingSystem);
            systemInfo.setMemoryInfo(memoryInfo);

            // Get Disk Information
            File root = new File("/");
            long totalDiskSpace = root.getTotalSpace();
            long freeDiskSpace = root.getFreeSpace();
            double totalDiskSpaceGB = totalDiskSpace / (1024.0 * 1024.0 * 1024.0);
            double usedDiskSpacePercentage = ((double)(totalDiskSpace - freeDiskSpace) / totalDiskSpace) * 100;
            DiskInfo diskInfo = new DiskInfo(totalDiskSpaceGB, usedDiskSpacePercentage);
            systemInfo.setDiskInfo(diskInfo);

            // Get JVM Information
            RuntimeMXBean runtimeMxBean = ManagementFactory.getRuntimeMXBean();
            JvmInfo jvmInfo = new JvmInfo();
            jvmInfo.setJvmName(runtimeMxBean.getVmName());
            jvmInfo.setJvmVendor(runtimeMxBean.getVmVendor());
            jvmInfo.setJvmVersion(runtimeMxBean.getVmVersion());
            systemInfo.setJvmInfo(jvmInfo);

            // Get Additional Information
            systemInfo.setProcessors(Runtime.getRuntime().availableProcessors());
            systemInfo.setArchitecture(System.getProperty("os.arch"));
            systemInfo.setCurrentTime(LocalDateTime.now(ZoneId.systemDefault()));

            // Operating System details
            String osInfo = System.getProperty("os.name") + " " + System.getProperty("os.version");
            systemInfo.getMemoryInfo().setOperatingSystem(osInfo);

            // classLoader details
            ClassLoader classLoader = SystemInfo.class.getClassLoader();
            String classLoaderDetails = classLoader.toString();
            systemInfo.setClassLoaderDetails(classLoaderDetails);

            // System Threads information
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadMXBean.getAllThreadIds());

            Map<Thread.State, Integer> threadDetails = new HashMap<>();
            for (ThreadInfo threadInfo : threadInfos) {
                Thread.State state = threadInfo.getThreadState();
                threadDetails.put(state, threadDetails.getOrDefault(state, 0) + 1);
            }

            systemInfo.setThreadDetails(threadDetails);

            Map<String, String> envVariables = new HashMap<>(System.getenv());
            systemInfo.setEnvironmentVariables(envVariables);

            // Populate System Properties
            Map<String, String> systemProps = new HashMap<String,String>();
            systemInfo.setSystemProperties(systemProps);

            // Populate System Environment Details
            systemInfo.setOperatingSystem(System.getProperty("os.name"));
            systemInfo.setUserHomeDirectory(System.getProperty("user.home"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        return systemInfo;
    }
}
