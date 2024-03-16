package org.nsgg.core.physics;

import org.nsgg.core.entity.scenes.SceneManager;
import org.nsgg.core.physics.collisions.Collidable;
import org.nsgg.core.utils.Utils;

import java.util.List;

import static org.nsgg.core.utils.Consts.G;

public class Gravity implements IPhysics {

    private List<Collidable> toProcess;

    public Gravity(List<Collidable> toProcess) {
        this.toProcess = toProcess;
    }

    @Override
    public void update() {
        for (Collidable collidable : toProcess) {
            //System.out.println(collidable.onGround);
            if(collidable.onGround) {
                collidable.vy = 0;
                continue;
            }



            collidable.vy += G * Utils.deltaTime;
            collidable.pos.y += collidable.vy;
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
