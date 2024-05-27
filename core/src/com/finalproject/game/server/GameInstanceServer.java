package com.finalproject.game.server;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.entity.live.enemy.Enemy;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameInstanceServer {

    // TODO: add current state of game, maybe paused or some thing

    private final int GAME_ID;
    public HashMap<Connection, RemoteClient> remoteClientsInServer = new HashMap<>();

    public List<Player> players = new ArrayList<>();
    public List<Projectile> projectiles = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
    public HashMap<GameWorld, World> worlds = new HashMap<>();
    public List<Body> bodiesToRemove = new ArrayList<>();

    public GameInstanceServer(int GAME_ID, HashMap<Connection, RemoteClient> remoteClients) {
        this.GAME_ID = GAME_ID;
        this.remoteClientsInServer = remoteClients;

        for (GameWorld world : GameWorld.values()) {
            World w = new World(new Vector2(0, 0), true);
            this.worlds.put(world, w);

            w.setContactListener(new ContactListener() {
                @Override
                public void beginContact(Contact contact) {
                    Fixture fixtureA = contact.getFixtureA();
                    Fixture fixtureB = contact.getFixtureB();

                    Object a = fixtureA.getBody().getUserData();
                    Object b = fixtureB.getBody().getUserData();

                    if (a == null || b == null) return;

                    if (a instanceof Player && b instanceof Projectile) {
                        Object c = a;
                        a = b;
                        b = c;
                    }

                    if (a instanceof Projectile && b instanceof Player) {
                        Projectile p = (Projectile) a;
                        Player pl = (Player) b;

                        if (pl != null && p != null && !p.getRemoteClient().equals(pl.getRemoteClient())) {
                            pl.takeDamage(p.getDamage());
                            p.removeBody();
                        }
                    }

                }

                @Override
                public void endContact(Contact contact) {

                }

                @Override
                public void preSolve(Contact contact, Manifold manifold) {

                }

                @Override
                public void postSolve(Contact contact, ContactImpulse contactImpulse) {

                }
            });


        }

    }

    public void removeObject(Body body) {
        bodiesToRemove.add(body);
    }

    public void update(float delta) {
        worlds.values().forEach(world -> world.step(delta, 6, 2));

        for (Player p : players) p.update(delta);
        for (Enemy p : enemies) p.update(delta);
        for (Projectile p : projectiles) p.update(delta);

        remoteClientsInServer.values().forEach(RemoteClient::update);
        clearBodiesToRemove();
    }

    public void addRemoteClient(Connection connection, RemoteClient remoteClient) {
        // TODO: logic where player spawns on location with less playersr

        Player p = new Player((LiveEntityBuilder) new LiveEntityBuilder().setGameInstanceServer(this).setRemoteClient(remoteClient).setCurrentWorld(worlds.get(GameWorld.OVERWORLD)));
        remoteClientsInServer.put(connection, remoteClient);

        players.add(p);

        remoteClient.setPlayer(p);
        remoteClient.setCurrentGameID(this.GAME_ID);
        remoteClient.setGameInstanceServer(this);
    }

    public void clearBodiesToRemove() {
        for (Body body : bodiesToRemove) {
            if (body == null || !body.isActive()) continue;

            World world = ((GameObject) body.getUserData()).getCurrentWorld();
            if (world.isLocked()) continue;

            // Destroy all joints connected to the body
            final Array<JointEdge> joints = body.getJointList();
            while (!joints.isEmpty()) {
                world.destroyJoint(joints.get(0).joint);
            }

            // Destroy all fixtures attached to the body
            final Array<Fixture> fixtures = body.getFixtureList();
            while (!fixtures.isEmpty()) {
                body.destroyFixture(fixtures.get(0));
            }

            // Retrieve the user data
            Object userData = body.getUserData();
            body.setUserData(null);
            world.destroyBody(body);

            // Safely remove the object from the corresponding list
            if (userData != null) {
                if (userData instanceof Enemy) enemies.remove(userData);
                if (userData instanceof Projectile) projectiles.remove(userData);
                if (userData instanceof Player) players.remove(userData);
            }
        }

        bodiesToRemove.clear();
    }

    public enum GameWorld {
        OVERWORLD,
    }


}
