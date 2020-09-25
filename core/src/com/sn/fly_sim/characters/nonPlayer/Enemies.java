package com.sn.fly_sim.characters.nonPlayer;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.sn.fly_sim.Bullet;
import com.sn.fly_sim.characters.Damagable;

public abstract class Enemies  extends Sprite implements Damagable {
    protected int health;
    protected Texture tx_bullet;

    Enemies (Texture texture){
        super(texture);
        spawn();
    }

    // Описывает критерии спавна. Так как для каждого из врагов будет своя генерация спавна,
    // то ее нужно переопределеить у каждого врага отдельно
    public abstract void spawn();
    // Описивает событие на получение урона
    @Override
    public abstract void takeDamage(Bullet bullet);
    // Описывает событие передвижения. Каждый враг двигается согласно своим алгоритмам
    public abstract void move();
    // Возвращает снаряд, которым стреляет враг. У каждого врага свой снаряд
    public abstract Bullet fire();

    // Возвращает true если жив
    public boolean isAlive(){
        return health > 0;
    }

    public int getHealth() {
        return health;
    }
}
