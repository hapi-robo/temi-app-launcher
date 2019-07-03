package com.hapirobo.applauncher;

public class Package {
    private String label, package_name;

    public Package(String label, String package_name) {
        this.label = label;
        this.package_name = package_name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPackageName() {
        return package_name;
    }

    public void setPackageName(String package_name) {
        this.package_name = package_name;
    }
}
