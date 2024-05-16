package com.finalproject.game.server;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryonet.Connection;
import com.finalproject.game.server.builder.entity.LiveEntityBuilder;
import com.finalproject.game.server.builder.entity.ProjectileBuilder;
import com.finalproject.game.server.entity.live.enemy.Enemy;
import com.finalproject.game.server.entity.live.enemy.melee.Zombie;
import com.finalproject.game.server.entity.live.player.Player;
import com.finalproject.game.server.entity.projectile.Projectile;
import com.finalproject.game.server.packet.server.GameInstanceSnapshot;

import java.util.*;

public class GameInstanceServer {


    // TODO: add current state of game, maybe paused or some thing


    private final int GAME_ID;
    public HashMap<Connection, RemoteClient> remoteClientsInServer = new HashMap<>();

    public List<Player> players = new ArrayList<>();
    public List<Projectile> projectiles = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();

    public World world;

    public List<Body> bodiesToRemove = new ArrayList<>();

    public GameInstanceServer(int GAME_ID, HashMap<Connection, RemoteClient> remoteClients) {
        this.GAME_ID = GAME_ID;
        this.remoteClientsInServer = remoteClients;

        this.world = new World(new Vector2(0, 0), true);

        this.world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();

                Object a = fixtureA.getBody().getUserData();
                Object b = fixtureB.getBody().getUserData();

                if (a instanceof Enemy && b instanceof Projectile) {
                    Object c = a;
                    a = b;
                    b = c;
                }

                if (a instanceof Projectile && b instanceof Enemy) {
                    Projectile p = (Projectile)a;
                    Enemy e = (Enemy)b;

                    e.takeDamage(p.getDamage());
                    p.removeBody();
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


    public void removeObject(Body body) {
        bodiesToRemove.add(body);
    }


    int tmp = 0;
    public void update(float delta) {

        world.step(1 / 60f, 6, 2);

        for (Player p : players) p.update(delta);
        for (Enemy p : enemies) p.update(delta);
        for (Projectile p : projectiles) p.update(delta);

        GameInstanceSnapshot gameInstanceSnapshot = new GameInstanceSnapshot(this);
        remoteClientsInServer.keySet().forEach(connection -> connection.sendTCP(gameInstanceSnapshot));

        if (tmp <= 0) {
            enemies.add(new Zombie((LiveEntityBuilder) new LiveEntityBuilder().setGameInstanceServer(this)));
            tmp = 5;
        }
        tmp -= delta;

        clearBodiesToRemove();
    }


    public void addRemoteClient(Connection connection, RemoteClient remoteClient) {
        Player p = new Player((LiveEntityBuilder) new LiveEntityBuilder().setGameInstanceServer(this).setRemoteClient(remoteClient));

        remoteClientsInServer.put(connection, remoteClient);
        players.add(p);

        remoteClient.setPlayer(p);
        remoteClient.setCurrentGameID(this.GAME_ID);
    }

    public void clearBodiesToRemove() {
        if (world.isLocked()) return;

        for (Body body : bodiesToRemove) {
            if (body == null || !body.isActive()) continue;

            // Destroy all joints connected to the body
            final Array<JointEdge> joints  = body.getJointList();
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


}
