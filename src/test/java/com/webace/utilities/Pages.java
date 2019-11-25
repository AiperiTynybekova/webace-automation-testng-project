package com.webace.utilities;

import com.webace.pages.EmployeeLogin.EmployeeLogin;
import com.webace.pages.LineItems.FMLineItem;

public class Pages {
    private EmployeeLogin employeeLogin;
    private FMLineItem fmLineItem;

    public EmployeeLogin empLogin() {
        if (employeeLogin == null) {
            employeeLogin = new EmployeeLogin();
        }
        return employeeLogin;
    }
    public FMLineItem lineItem() {
        if (fmLineItem == null) {
            fmLineItem = new FMLineItem();
        }
        return fmLineItem;
    }

}
