package com.ludux.twitchgame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.ludux.twitchgame.Server.Connection.Client;
import com.ludux.twitchgame.Const;
import com.ludux.twitchgame.Main;
import com.ludux.twitchgame.Sended;
import com.ludux.twitchgame.registration.RegistrationScreen;

import java.util.Random;


public class DesktopLauncher {

    public static void main(String[] arg) {
        System.setProperty("user.name", "CorrectUserName");
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.width = Const.REGISTRATION_WIDTH;
        cfg.height = Const.REGISTRATION_HEIGHT;
        cfg.resizable = false;
        cfg.vSyncEnabled = false;
        cfg.foregroundFPS = 120;
		cfg.backgroundFPS = 120;

		new LwjglApplication(new Main(), cfg) {
			@Override
            public void exit() {
                try {
					System.out.println("МЕНЯЮ ЭКРАН И ЗАКРЫВАЮ");
					if (((Main) listener).getClient() != null || !(((Main) listener).getScreen() instanceof RegistrationScreen)) {
                        Client client = ((Main) listener).getClient();
                        new Client(new Sended(client.name, "change", ((Main) listener).getUser()),"EX" + new Random().nextInt());
                        if (!client.getConnection().isClosed()) {
                            client.sendData(new Sended(client.name, "break", client.name));
                        }
                    }
                } catch (Exception e) {}
                finally {
                    super.exit();
                }
			}
		};
    }
}
