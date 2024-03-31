package org.nsgg.core.physics;

import org.nsgg.core.entity.scenes.SceneManager;
import org.nsgg.core.physics.collisions.Collidable;
import org.nsgg.core.utils.Utils;
import org.nsgg.main.Launcher;

import java.util.List;

public class Gravity implements IPhysics {

    private List<Collidable> toProcess;

    public Gravity(List<Collidable> toProcess) {
        this.toProcess = toProcess;
    }

    @Override
    public void update() {
        for (Collidable collidable : toProcess) {
            collidable.pos.y += collidable.vy;
            if(collidable.onGround) {
                if (collidable.vy < 0) {
                    collidable.vy = 0;
                }
                continue;
            }
            collidable.vy += Launcher.config.GRAVITY * Utils.deltaTime;
        }
    }

    @Override
    public void add(Collidable collidable) {
        toProcess.add(collidable);
    }

    @Override
    public void remove(Collidable collidable) {
        toProcess.remove(collidable);
    }

    public void syncWithSceneManager(SceneManager sceneManagerInstance) {
        toProcess = sceneManagerInstance.getCollidables();
    }
}
