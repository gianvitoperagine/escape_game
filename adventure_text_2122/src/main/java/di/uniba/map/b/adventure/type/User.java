/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package di.uniba.map.b.adventure.type;

/**
 * @author federico
 * @author gianvito
 * @author sandro
 */

public class User {
    
    private String nickname;
    
    private int age;
    
    private boolean underAge = false;
    
    private boolean ageLonger = false;
    
    private boolean lengthShorter = false;
    

    /**
     *
     * @param nickname
     * @param age
     */
    
    public User(String nickname, int age) {
        this.nickname = nickname;
        this.age = age;
    }

    /**
     *
     * @return nickname
     */
    
    public String getNickname() {
        return nickname;
    }

    /**
     *
     * @param nickname
     */
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     *
     * @return age
     */
    
    public int getEta() {
        return age;
    }

    /**
     *
     * @param age
     */
    
    public void setEta(int age) {
        this.age = age;
    }
    
    /**
     *
     * @param nick
     * @return lengthShorter
     */
    
    public boolean checkNickShorter(String nick)
    {
        if (nick.length() < 5) {
            lengthShorter = true;
        }
        return lengthShorter;
    }
    
    /**
     *
     * @param age
     * @return ageLonger
     */
    
    public boolean checkAgeLonger(int age)
    {
        if (age > 100) {
            ageLonger = true;
        }
        return ageLonger;
    }
    
    /**
     *
     * @param age
     * @return underage
     */
    
    public boolean checkUnderage(int age)
    {
        if (age < 10) {
            underAge = true;
        }
        return underAge;
    }
}