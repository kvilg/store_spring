package com.example.demo.model;


public class UserAuth {
    private String email;
    private String name;
    private String login;
    private String password;
    private String text;
    private String code;


    public UserAuth(){

    }

    public UserAuth(UserAuth userAuth) throws Exception {
        if(userAuth.getEmail() != null) {
            this.email = userAuth.getEmail();
        }
        if(userAuth.getName() != null) {
            this.name = userAuth.getName();
        }
        if(userAuth.getLogin() != null) {
            this.login = userAuth.getLogin();
        }
        if(userAuth.getPassword() != null) {
            this.password = userAuth.getPassword();
        }
        this.code = userAuth.getCode();

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setText(String text) {
        this.text = text;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
