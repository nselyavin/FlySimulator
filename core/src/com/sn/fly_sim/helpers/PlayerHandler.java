package com.sn.fly_sim.helpers;

import com.sn.fly_sim.Player;


public class PlayerHandler {
    // ToDo пока функции работают как пустышки, реализовать тело, когда подключу бд

    public static Player getPlayer(String loginText, String passText) {
        // Где-то здесь считал данные об игроке с таким логином и паролем
        int rec = 1000; // Имитация

        if(loginText.equals("ASD"))
            return null;

        // ToDo программс должна создавать новый объект игрока с данными
        // Считанными из места хранения данных игроков
        return new Player(loginText, rec);
    }

    /*
    Если фукнция возвращает false, значит игрок с таким ником уже существует. И создание не удалось
    Если true, значит создание успешно
     */
    public static boolean createPlayer(String login, String password){
        // Здесь код добавляющий игрока в базу данных

        if (login.equals("ASD"))
            return false;

        return true;
    }

    public static void rewriterecord(String name, int record){

    }
}
