package com.sn.fly_sim;

public class Player {
    private String name;
    private int record;
    /*
    Долже хранить информацию о текущем залогиненном игроке
    И обновлять информацию о рекордах этого игрока после игры
    С помощью касса Player reader
    Создание игрока и использование хэш-функции
     */

    public Player(String name, int record) {
        this.name = name;
        this.record = record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getName() {
        return name;
    }

    public int getRecord() {
        return record;
    }
}
