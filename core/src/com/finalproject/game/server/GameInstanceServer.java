package com.finalproject.game.server;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Rectangle;
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

public class GameInstanceServer extends Game {

    // TODO: add current state of game, maybe paused or some thing

    private static final float PPM = 10f;
    private final int GAME_ID;
    public GameInstanceStatus gameInstanceStatus = GameInstanceStatus.LOADING;
    public HashMap<Connection, RemoteClient> remoteClientsInServer = new HashMap<>();
    public World world;
    public GameWorld gameWorld = GameWorld.OVERWORLD;
    public List<Player> players = new ArrayList<>();
    public List<Projectile> projectiles = new ArrayList<>();
    public List<Enemy> enemies = new ArrayList<>();
    public List<Body> bodiesToRemove = new ArrayList<>();
    protected TiledMap map;

    public GameInstanceServer(int GAME_ID, GameWorld gameWorld) {
        this(GAME_ID, gameWorld, new HashMap<>());
    }


    public GameInstanceServer(int GAME_ID, GameWorld gameWorld, HashMap<Connection, RemoteClient> remoteClients) {
        this.GAME_ID = GAME_ID;
        this.remoteClientsInServer = remoteClients;
        this.gameWorld = gameWorld;
    }

    public void removeObject(Body body) {
        bodiesToRemove.add(body);
    }

    public void update(float delta) {
        world.step(delta, 6, 2);

        for (Player p : players) p.update(delta);
        for (Enemy p : enemies) p.update(delta);
        for (Projectile p : projectiles) p.update(delta);

        remoteClientsInServer.values().forEach((remoteClient) -> remoteClient.update(delta));
        clearBodiesToRemove();
    }

    public void addRemoteClient(Connection connection, RemoteClient remoteClient) {
        // TODO: logic where player spawns on location with less playersr

        remoteClientsInServer.put(connection, remoteClient);
        remoteClient.setCurrentGameID(this.GAME_ID);
        remoteClient.setGameInstanceServer(this);

        spawnPlayer(remoteClient);

    }

    public void spawnPlayer(RemoteClient remoteClient) {
        Player p = new Player((LiveEntityBuilder) new LiveEntityBuilder().setGameInstanceServer(this).setRemoteClient(remoteClient).setCurrentWorld(world));
        remoteClient.setPlayer(p);
        players.add(p);
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

    @Override
    public void create() {
        world = new World(new Vector2(0, 0), true);

        TmxMapLoader mapLoader = new TmxMapLoader();
        map = mapLoader.load("OOP/ADRIAN NAA DIRI TANAN/ooptilesets/oopmap_backend.tmx");

        // Load collision objects
        for (MapObject object : map.getLayers().get("collision layer").getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set((rect.x + rect.width / 2) / PPM, (rect.y + rect.height / 2) / PPM);

                Body body = world.createBody(bodyDef);

                PolygonShape shape = new PolygonShape();
                shape.setAsBox(rect.width / 2 / PPM, rect.height / 2 / PPM);

                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.density = 1.0f;
                fixtureDef.friction = 0.0f;
                fixtureDef.restitution = 0.0f;

                body.createFixture(fixtureDef);
                body.setUserData("Wall");
                shape.dispose();

            }
        }


        world.setContactListener(new ContactListener() {
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


                if (a.equals("Wall") && b instanceof Projectile) {
                    ((Projectile) b).removeBody();
                }
                if (a instanceof Projectile && b.equals("Wall")) {
                    ((Projectile) a).removeBody();
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

    public enum GameInstanceStatus {
        LOADING, LOBBY, INGAME
    }


    public enum GameWorld {
        OVERWORLD,
    }


}
