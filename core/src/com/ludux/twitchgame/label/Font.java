package com.ludux.twitchgame.label;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class Font {
    private static FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("core/assets/Fonts/10968.ttf"));
    private static final String CHARACTERS = "абвгдежзийклмнопрстуфхцчшщъыьэюя" +
                                             "abcdefghijklmnopqrstuvwxyz" +
                                             "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
                                             "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                                             "0123456789][_!$%#@|\\/?-+=()*&.;:,{}\"´`'<>";

    public static BitmapFont getFont(int size, float borderwidth) {
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = CHARACTERS;
        parameter.size = size;
        parameter.color = Color.WHITE;
        parameter.borderWidth = borderwidth;
        parameter.borderColor = Color.WHITE;
        parameter.shadowColor = Color.BLACK;
        parameter.shadowOffsetX = 3;
        parameter.shadowOffsetY = 3;
        BitmapFont font = generator.generateFont(parameter);
        font.getData().setScale(1.5f,1.2f);
        return font;
    }
}
