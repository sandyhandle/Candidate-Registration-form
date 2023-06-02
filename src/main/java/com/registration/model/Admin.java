package com.registration.model;

public class Admin {
    private static boolean admin = false;

    public static boolean isAdmin() {
        return admin;
    }

    public static void setAdmin(boolean admin) {
        Admin.admin = admin;
    }
}
