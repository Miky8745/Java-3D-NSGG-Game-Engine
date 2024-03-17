package org.nsgg.core.physics;

import org.nsgg.core.entity.scenes.SceneManager;
import org.nsgg.core.physics.collisions.Collidable;

public interface IPhysics {

    public void update();
    public void add(Collidable collidable);
    public void remove(Collidable collidable);
    public void syncWithSceneManager(SceneManager sceneManagerInstance);
}
