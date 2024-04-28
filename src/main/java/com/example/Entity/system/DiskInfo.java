package com.example.Entity.system;

public class DiskInfo {

    private double totalDiskSpaceGB;
    private double usedDiskSpacePercentage;

    //PDC
    public DiskInfo() {}

    //PPC
    public DiskInfo(double totalDiskSpaceGB, double usedDiskSpacePercentage) {
        super();
        this.totalDiskSpaceGB = totalDiskSpaceGB;
        this.usedDiskSpacePercentage = usedDiskSpacePercentage;
    }

    //PSM & PGM
    public double getTotalDiskSpaceGB() {
        return totalDiskSpaceGB;
    }

    public void setTotalDiskSpaceGB(double totalDiskSpaceGB) {
        this.totalDiskSpaceGB = totalDiskSpaceGB;
    }

    public double getUsedDiskSpacePercentage() {
        return usedDiskSpacePercentage;
    }

    public void setUsedDiskSpacePercentage(double usedDiskSpacePercentage) {
        this.usedDiskSpacePercentage = usedDiskSpacePercentage;
    }

}
