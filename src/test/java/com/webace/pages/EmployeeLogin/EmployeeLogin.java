package com.webace.pages.EmployeeLogin;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.webace.utilities.Driver;

public class EmployeeLogin {
	
    @FindBy(id = "UserName")
    public WebElement user_name_field;

    @FindBy(name = "Password")
    public WebElement password_field;

    @FindBy(id="btnOkay")
    public WebElement submit_btn;
    
    @FindBy(id="msg")
    public WebElement invalid_login_message;

    public void login(String usr, String pass){
        user_name_field.sendKeys(usr);
        password_field.sendKeys(pass);
        submit_btn.click();
    }
   
    public EmployeeLogin(){
        PageFactory.initElements(Driver.getDriver(), this);


    }

}

