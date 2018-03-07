package com.ludux.twitchgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ludux.twitchgame.label.Font;

public class Const {
    //FIGHT ARENA PARAMETERS
    public static final int REGISTRATION_WIDTH = 684;
    public static final int REGISTRATION_HEIGHT = 962;
    public static final int LANDSCAPE_VIEWPORT_WIDTH = 1920;
    public static final int LANDSCAPE_VIEWPORT_HEIGHT = 1080;
    public static float getWidthKRes() {return Gdx.graphics.getWidth() / (float) LANDSCAPE_VIEWPORT_WIDTH;}
    public static float getHeightKRes() {return Gdx.graphics.getHeight() / (float) LANDSCAPE_VIEWPORT_HEIGHT;}



    //Регистрация
    public static final TextureRegionDrawable rgbutton_default = new TextureRegionDrawable(
            new TextureRegion(new Texture("core/assets/Sprites/registration/rgbutton_default.png")));
    public static final TextureRegionDrawable rgbutton_selected = new TextureRegionDrawable(
            new TextureRegion(new Texture("core/assets/Sprites/registration/rgbutton_selected.png")));
    public static final TextureRegionDrawable rgbutton_pressed = new TextureRegionDrawable(
            new TextureRegion(new Texture("core/assets/Sprites/registration/rgbutton_pressed.png")));
    public static final Label.LabelStyle registerStyle = new Label.LabelStyle(Font.getFont(32, 0.8f), Color.YELLOW);
    private static final Label.LabelStyle labelStyle = new Label.LabelStyle(Font.getFont(20, 0.5f), Color.RED);
    public static final TextureRegionDrawable wrong_bg = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("core/assets/Sprites/registration/wrong_bg.png"))));
    public static final Label registrationLabel = new Label("РЕГИСТРАЦИЯ", new Label.LabelStyle(Font.getFont(32, 1.5f), Color.RED));
    public static final Label nameLabel = new Label("Имя", labelStyle);
    public static final Label loginLabel = new Label("Логин", labelStyle);
    public static final Label passwordLabel = new Label("Пароль", labelStyle);
    public static final String FULLHP = "FULLHP";


    public static final TextField.TextFieldStyle getTextFieldStyle() {
        TextField.TextFieldStyle textFieldStyle = new TextField.TextFieldStyle();
        textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/assets/Sprites/registration/field.png"))));
        textFieldStyle.fontColor = Color.WHITE;
        textFieldStyle.font = Font.getFont(20, 0.5f);
        textFieldStyle.cursor = new TextureRegionDrawable(new TextureRegion(
                new Texture(Gdx.files.internal("core/assets/Sprites/cursors/textCursor.png"))));
        return textFieldStyle;
    }

    //Меню игры
    public static final Texture name_texture = new Texture(Gdx.files.internal("core/assets/Sprites/Menu/name.png"));
    public static final Texture shop_texture = new Texture(Gdx.files.internal("core/assets/Sprites/Menu/shop.png"));
    public static final TextureRegionDrawable menu_background = new TextureRegionDrawable(new TextureRegion(
            new Texture(Gdx.files.internal("core/assets/Sprites/Menu/background.jpg"))));
    public static final TextureRegionDrawable menuButton_noactive = new TextureRegionDrawable(new TextureRegion(
            new Texture(Gdx.files.internal("core/assets/Sprites/Menu/button_bg_noactive.png"))));
    public static final TextureRegionDrawable menuButton_active = new TextureRegionDrawable(new TextureRegion(
            new Texture(Gdx.files.internal("core/assets/Sprites/Menu/button_bg_active.png"))));
    public static final Animation<TextureRegion> play_menu = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
            Gdx.files.internal("core/assets/Sprites/Menu/playmenu.gif").read());
    public static final Animation<TextureRegion> shop_menu = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP_PINGPONG,
            Gdx.files.internal("core/assets/Sprites/Menu/shopkeeper1.gif").read());
    public static final Animation<TextureRegion> inventory_menu = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
            Gdx.files.internal("core/assets/Sprites/Menu/inventory.gif").read());
    public static final Animation<TextureRegion> profile_menu = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
            Gdx.files.internal("core/assets/Sprites/Menu/profile.gif").read());
    public static final Animation<TextureRegion> settings_menu = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP,
            Gdx.files.internal("core/assets/Sprites/Menu/settings.gif").read());
    public static final Animation<TextureRegion> exit_menu = GifDecoder.loadGIFAnimation(Animation.PlayMode.LOOP_RANDOM,
            Gdx.files.internal("core/assets/Sprites/Menu/exit.gif").read());

    //Магазин
    public static final Texture cell = new Texture(Gdx.files.internal("core/assets/Sprites/shop/cell.png"));

    //Битва
    public static final byte QUANTITY_CLOUDS = 40;
    public static final int BAR_WIDTH = 780;
    public static final int BAR_HEIGHT = 100;
    public static final float HPBAR_HEIGHT = 0.7f * BAR_HEIGHT;
    public static final float MANABAR_HEIGHT = 0.3f * BAR_HEIGHT;
    public static final Color MANA_COLOR = new Color().set(Color.rgb565(135, 206,250));

    //Сообщение
    public static final TextureRegionDrawable message_bg = new TextureRegionDrawable(new TextureRegion(
            new Texture(Gdx.files.internal("core/assets/Sprites/message/message.png"))));
    public static final TextureRegionDrawable messagebutton_bg = new TextureRegionDrawable(new TextureRegion(
            new Texture(Gdx.files.internal("core/assets/Sprites/message/message_button.png"))));
    public static final TextButton.TextButtonStyle messageStyle = getMessageStyle();
    private static TextButton.TextButtonStyle getMessageStyle() {
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = Font.getFont(22, 0.5f);
        textButtonStyle.fontColor = Color.YELLOW;
        textButtonStyle.up = messagebutton_bg;
        return textButtonStyle;
    }


    //Общение клиента и сервера
    public static final String CHANGE_SCREEN = "changeScreen";
        public static final String TO_MENU_SCREEN = "toMenuScreen";
        public static final String TO_BATTLE_SCREEN = "toBattleScreen";
        public static final String TO_SHOP_SCREEN = "toShopScreen";

    public static final String IN_BATTLE = "inBattle";
        public static final boolean P1 = true;
        public static final boolean P2 = false;
        public static final String NEXT_STEP = "nextStep";
        public static final String FIRST = "First";
        public static final String SECOND = "Second";
        public static final String HEALTH = "health";
        public static final String MANA = "mana";
        public static final String STAND = "stand";
        public static final String DIE = "die";
        public static final String ATTACK = "attack";
        public static final String HURT = "hurt";
        public static final String HURT_BASE = "hurtBase";
        public static final String HURT_ULTIMATE = "hurtUltimate";
        public static final String HEAL_RING = "HealingRing";
        public static final String ULTIMATE = "Ultimate";
        public static final String WIN = "win";
        public static final String LOSE = "lose";
    
            

    public static final int ElfManaRegen = 3;
    public static final int ElfHealthRegen = 4;
    public static final String BROKEN_BLADE = "BrokenBlade";
    public static final String OLD_HAUBERK = "OldHauberk";


}
